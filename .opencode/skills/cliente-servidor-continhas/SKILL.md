---
name: cliente-servidor-continhas
description: Estrutura completa de um projeto cliente/servidor com Threads e Sockets no padrão do "Servidor de Continhas" do professor Maligno (PUC-Campinas, POO). Use para montar, revisar ou explicar esse tipo de projeto, ou para conferir se um trabalho tem todas as peças.
---

# Projeto cliente/servidor: Servidor de Continhas

**Fonte:** `Materias de aula/conteudos caderno até 25-out.pdf`, páginas 28 a 44 (aulas de 23, 24 e 25/9/26). O caderno é manuscrito e digitalizado, e não há `.java` do professor sobre o tema. Itens marcados **[GENERALIZAÇÃO]** são boas práticas de Java que o caderno não diz. Itens marcados **[RECONSTRUÇÃO]** não estão escritos no caderno.

## 1. O que o sistema faz

- O **servidor** roda primeiro e espera conexões. Vários **clientes** podem se conectar, e cada um tem sua própria "conta": um `double` guardado no servidor, que começa em 0.
- O **cliente** escolhe `+ - * /` e digita um valor, que é aplicado à conta no servidor. Com `=`, pede o valor atual. Com `T`, sai.
- No **servidor**, digitar `desativar` manda um `ComunicadoDeDesligamento` a todos os clientes e encerra o programa. Cada cliente avisa que o servidor ficou inoperante e também encerra.
- O cliente pode ser executado várias vezes ao mesmo tempo.

## 2. Arquivos que o projeto deve ter

| Classe | Papel | Tipo |
|---|---|---|
| `Comunicado` | Topo da hierarquia de mensagens. `implements Serializable` | Oca |
| `PedidoDeOperacao extends Comunicado` | Operação (`char`) e valor (`double`), com construtor e getters | Com dados |
| `PedidoDeResultado extends Comunicado` | Pede o valor atual ao servidor | Oca |
| `Resultado extends Comunicado` | Resposta do servidor, com o valor (`double`) e getter | Com dados |
| `PedidoParaSair extends Comunicado` | Cliente avisa que vai sair | Oca |
| `ComunicadoDeDesligamento extends Comunicado` | Servidor avisa que vai desativar | Oca |
| `Teclado` | Leitura do teclado, usada no cliente e no servidor | Não replicável (`static`) |
| `Parceiro` | Representa o outro lado da conexão. No cliente, representa o servidor; no servidor, representa um cliente | Com dados e comportamento |
| `AceitadoraDeConexao extends Thread` | Aceita conexões. Há uma só, no servidor | Thread |
| `SupervisoraDeConexao extends Thread` | Atende um cliente. Há uma para cada conexão aceita (relação 1:N) | Thread |
| `TratadoraDeComunicadoDeDesligamento extends Thread` | Espera o aviso de desligamento. Só no cliente | Thread |
| `Cliente` (main) | Conexão e menu | Programa |
| `Servidor` (main) | Aceita conexões e lê o comando `desativar` | Programa |

## 3. Comunicado e as classes de mensagem

- `Comunicado` implementa `Serializable` (`java.io.*`). É obrigatório para trafegar pelo socket. Também seria necessário para gravar em arquivo ou banco.
- As classes de pedido e de aviso são **ocas**, ou seja, não têm atributos. Só `PedidoDeOperacao` e `Resultado` carregam dados.
- O nome `PedidoDeOperacao` vem com um construtor que recebe a operação e o valor, e getters para lidar com isso no servidor.
- Como são objetos enviados pela rede, o servidor e o cliente precisam ter as mesmas classes.

## 4. Parceiro

**Atributos (caderno, p. 31):**
- `Socket conexao`
- `ObjectInputStream receptor` (lê o que chega)
- `ObjectOutputStream transmissor` (envia)
- `Semaphore mutEx`: "mútua exclusão". Protege um único recurso. Quem pede espera, e só continua quando o recurso estiver livre.

**Construtor:** recebe `Socket`, `ObjectInputStream` e `ObjectOutputStream`, e valida cada um (não pode ser nulo).

**Métodos.** Todos são vistos do ponto de vista do **parceiro**. Por isso o nome parece invertido:

| Método | O que faz | Retorno |
|---|---|---|
| `receba(Comunicado x)` | O parceiro **recebe** x, ou seja, este lado **envia**. Usa `writeObject` e `flush` | `void` |
| `espie()` | Olha o próximo comunicado **sem consumir**. Sempre devolve a mesma coisa | `Comunicado` |
| `envie()` | O parceiro **envia**, ou seja, este lado **recebe e consome**. Devolve um comunicado diferente a cada chamada | `Comunicado` |
| `adeus()` | Fecha transmissor, receptor e conexão. "Desfaz todas as conexões e garante que as threads não executem mais" | `void` |

- `espie` e `envie` usam `mutEx` para que duas threads não misturem a leitura. O caderno cita o "atropelo" como o problema que o semáforo evita.
- **[RECONSTRUÇÃO]** Para `espie` não consumir, é preciso guardar o comunicado lido num atributo, por exemplo `Comunicado proximoComunicado`. O caderno não escreve esse nome. Da mesma forma, `new Semaphore(1, true)` com fairness é uma sugestão, não algo confirmado.

## 5. Cliente

### Início da `main` (executado uma vez)

1. **Checar `args`.** Pode receber 0, 1 ou 2 itens: `host` no índice 0 e `porta` no índice 1. Mais de 2 é erro. Sem argumento, usa uma máquina e uma porta padrão (caderno, p. 29). Erros são informados com mensagem.
2. **Conexão.** Em ordem:
   - `Socket conexao = new Socket(host, porta);`
   - `transmissor = new ObjectOutputStream(conexao.getOutputStream());`
   - `receptor = new ObjectInputStream(conexao.getInputStream());`
   - `Parceiro servidor = new Parceiro(conexao, receptor, transmissor);`

   **[GENERALIZAÇÃO]** O `ObjectOutputStream` deve ser criado antes do `ObjectInputStream`, em ambos os lados. Caso contrário, os dois ficam esperando o cabeçalho um do outro e travam.
3. **Iniciar a thread de desligamento.** `new TratadoraDeComunicadoDeDesligamento(servidor).start();`. Ela fica em `espie` até chegar um `ComunicadoDeDesligamento`, avisa o usuário e chama `System.exit(0)`.

### Laço do menu (`do/while` na `main`)

- Mostra as opções e lê o caractere com `Teclado`.
- Valida com `"+-*/=T".indexOf(opcao)`. Se for inválido, lança exceção ou mostra erro e faz `continue`.
- **Operação aritmética** (`+ - * /`): lê o `double` com `Teclado`. Em `/` com valor 0, mostra erro e faz `continue`. Depois, `servidor.receba(new PedidoDeOperacao(opcao, valor));`.
- **`=`:**
  - `servidor.receba(new PedidoDeResultado());`
  - Laço `do { comunicado = (Comunicado) servidor.espie(); } while (!(comunicado instanceof Resultado));`
  - `Resultado r = (Resultado) servidor.envie();`
  - Imprime `r.getValor()`.
- **`T`:** sai do laço, faz `servidor.receba(new PedidoParaSair());` e depois `System.exit(0);`. O `exit` encerra também a thread de desligamento.

## 6. Servidor

### Início da `main` (executado uma vez)

1. **Checar `args`.** Pode receber 0 ou 1 item, que é a porta. Mais de 1 é erro e encerra. Sem argumento, usa a porta padrão.
2. **Criar a lista compartilhada:** `ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>();`
3. **Iniciar a aceitadora:** `new AceitadoraDeConexao(porta, usuarios).start();`. Porta inválida lança exceção e encerra o programa.

### Laço do administrador (`for(;;)` na `main`)

- Mostra que o servidor está ativo e pode ser desativado. Lê o teclado com `Teclado`.
- Se a leitura for `"desativar"`, faz:

```java
synchronized (usuarios)
{
    ComunicadoDeDesligamento comunicado = new ComunicadoDeDesligamento ();
    for (Parceiro usuario : usuarios)
    {
        usuario.receba (comunicado);
        usuario.adeus ();
    }
}
System.exit(0);
```

### AceitadoraDeConexao extends Thread

- `import java.net.*;` e `import java.util.*;`.
- Atributos: `ServerSocket pedido` (criado no construtor com a porta, podendo lançar exceção) e `ArrayList<Parceiro> usuarios` (a mesma lista da `main`).
- `run()`: `for(;;)` com `Socket conexao = this.pedido.accept();` e `new SupervisoraDeConexao(conexao, this.usuarios).start();`.

### SupervisoraDeConexao extends Thread

- `import java.io.*;`, `import java.net.*;` e `import java.util.*;`.
- Atributos: `double valor = 0`, `Parceiro usuario`, `Socket conexao` (recebido no construtor) e `ArrayList<Parceiro> usuarios`.
- `run()`:
  1. Cria `transmissor` e `receptor` a partir de `conexao`. Mesma ordem da seção 5.
  2. `this.usuario = new Parceiro(conexao, receptor, transmissor);`
  3. `synchronized (this.usuarios) { this.usuarios.add(this.usuario); }`
  4. `for(;;)`: `Comunicado comunicado = this.usuario.envie();`. Se for `null`, faz `return`. Depois trata o tipo:
     - `instanceof PedidoDeOperacao`: faz `switch` sobre a operação e aplica ao `valor`.
     - `instanceof PedidoDeResultado`: `this.usuario.receba(new Resultado(this.valor));`
     - `instanceof PedidoParaSair`: `synchronized (this.usuarios) { this.usuarios.remove(this.usuario); }`, depois `this.usuario.adeus();` e `return`.

## 7. Threads e sincronização

| Thread | Onde nasce | O que faz |
|---|---|---|
| Main do cliente | `Cliente.main` | Laço do menu |
| `TratadoraDeComunicadoDeDesligamento` | `Cliente.main` | Espera o `ComunicadoDeDesligamento` |
| Main do servidor | `Servidor.main` | Laço do administrador (`desativar`) |
| `AceitadoraDeConexao` | `Servidor.main` | `accept()` em laço, uma vez por cliente |
| `SupervisoraDeConexao` | `AceitadoraDeConexao.run` | Atende um cliente, uma por conexão |

Regras:
- **`synchronized (usuarios)`** em toda alteração e toda iteração da lista compartilhada: `add`, `remove` e o laço de desligamento. Mais de uma thread acessa essa lista.
- **Semáforo `mutEx`** em `espie` e `envie` do `Parceiro`, para que as leituras não se misturem.
- **`System.exit(0)`** no cliente (ao sair e ao receber desligamento) e no servidor (ao desativar). Isso encerra as threads que ficariam esperando.

## 8. Protocolo: o que trafega

| Ação do cliente | Comunicado enviado | Resposta do servidor |
|---|---|---|
| `+`, `-`, `*`, `/` com valor | `PedidoDeOperacao(op, valor)` | Nenhuma. O valor no servidor muda |
| `=` | `PedidoDeResultado` | `Resultado(valor)` |
| `T` | `PedidoParaSair` | Nenhuma. A conexão é fechada |
| `desativar` no servidor | `ComunicadoDeDesligamento` (para todos) | Nenhuma. Os clientes encerram |

## 9. Checklist de entrega

- [ ] Todas as classes da seção 2, com esses nomes exatos.
- [ ] `Comunicado implements Serializable`, e todas as classes de pedido e de aviso estendem `Comunicado`.
- [ ] `Parceiro` com os três atributos de conexão, o semáforo e os quatro métodos (`receba`, `espie`, `envie`, `adeus`).
- [ ] Cliente: `args` validados (0 a 2 itens), conexão nessa ordem, thread de desligamento, menu com validação e divisão por zero tratada.
- [ ] Servidor: `args` validados (0 ou 1 item), lista compartilhada, `AceitadoraDeConexao` como thread, laço de `desativar`.
- [ ] `synchronized` em todo acesso à lista compartilhada.
- [ ] Constantes em `MAIUSCULAS_COM_UNDERSCORE`, como a porta e o host padrão.
- [ ] Mensagens de exceção com acento (decisão do projeto).
- [ ] `System.exit(0)` no cliente e no servidor, nos pontos da seção 7.
- [ ] Teste manual: dois ou mais clientes ao mesmo tempo; um cliente sai e o servidor continua; `desativar` encerra todos.

## 10. Erros comuns

- Esquecer o `implements Serializable`, ou fazer uma classe de pedido não estender `Comunicado`.
- Criar o `ObjectInputStream` antes do `ObjectOutputStream` **[GENERALIZAÇÃO]**.
- Esquecer o `flush()` depois do `writeObject` **[GENERALIZAÇÃO]**.
- Alterar a lista compartilhada sem `synchronized`.
- Não usar o `espie` antes do `envie` na hora do `=`. Sem o `espie`, o cliente pode pegar um comunicado que não é o `Resultado`.
- Não ter a thread de desligamento no cliente. Sem ela, o cliente fica preso no teclado quando o servidor cai.
- Não fechar a conexão no `T` e no `desativar`.

## 11. Confiança das informações

- **Verificado:** páginas 28 a 44 do caderno, lidas uma a uma.
- **[RECONSTRUÇÃO]:** atributo `proximoComunicado`, `Semaphore(1, true)`, e a forma de guardar o comunicado no `espie`.
- **[GENERALIZAÇÃO]:** ordem das streams (para evitar travamento), `flush`, e o fato de o cliente ser o único lugar com a thread de desligamento. Esse último é uma decisão do caderno, mas as razões são generalização.

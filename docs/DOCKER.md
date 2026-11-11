# Usar o Benigno com Docker

Alternativa para quem não quer instalar OpenCode, Git e JDK: a imagem já traz tudo, e você só precisa do Docker. O uso local com o [OpenCode](OPENCODE.md) continua valendo e não muda.

## 1. Ter o Docker
Instale o [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Windows e macOS) ou o Docker Engine (Linux). No Windows, use o terminal do WSL.

## 2. Gerar a imagem
Dentro da pasta do repositório (é preciso clonar ou baixar o ZIP, como no [passo 2 do guia do OpenCode](OPENCODE.md#2-baixar-o-repositório)):
```bash
docker build -t benigno .
```
A primeira vez leva alguns minutos. A imagem traz a versão do OpenCode fixada no `Dockerfile` (2.2.5) e o plugin `ponytail` já instalado, então o primeiro uso não precisa de download extra.

Quando a imagem estiver publicada (a cada tag `v*`, pelo workflow `.github/workflows/docker.yml`), o `docker build` deixa de ser necessário e você pode usar `docker pull ghcr.io/egili/benigno:latest`, trocando `benigno` pelo nome `ghcr.io/egili/benigno:latest` nos comandos abaixo.

## 3. Abrir o Benigno com o seu projeto
Rode o comando **dentro da pasta do seu projeto Java**, não dentro da pasta do Benigno:
```bash
docker run -it --rm \
  --user "$(id -u):$(id -g)" \
  -v "$PWD":/benigno/projeto \
  -v benigno-login:/home/opencode/.local/share/opencode \
  benigno
```
- `-v "$PWD":/benigno/projeto` monta o seu projeto dentro do container. Os arquivos criados pelo Benigno aparecem na sua pasta.
- `--user "$(id -u):$(id -g)"` faz os arquivos pertencerem a você, e não ao root.
- `-v benigno-login:...` guarda o login do modelo num volume Docker, então você não precisa conectar de novo a cada uso.

No Windows (WSL), rode o mesmo comando dentro do terminal do WSL.

## 4. Verificar a compilação (opcional)
Para checar se os `.java` do seu projeto compilam com Java 8, o padrão da disciplina, rode dentro da pasta do projeto:
```bash
docker run --rm \
  --user "$(id -u):$(id -g)" \
  -v "$PWD":/benigno/projeto \
  --entrypoint verificar-java \
  benigno
```
Sai com `OK` se tudo compila; caso contrário, mostra os erros do `javac`. O Benigno não roda essa verificação sozinho: peça no chat, por exemplo *"rode verificar-java"*.

## 5. Conectar um modelo
O modelo fica sob sua conta, não na imagem. Dentro do OpenCode que abriu no container, siga o [passo 1.2 do guia do OpenCode](OPENCODE.md#12-conectar-um-modelo-de-ia): `/models` para os gratuitos, ou `/connect` para a sua conta. O login fica salvo no volume `benigno-login`.

## Problemas comuns

| Sintoma | Solução |
|---|---|
| `docker: command not found` ou `Cannot connect to the Docker daemon` | Abra o Docker Desktop, ou inicie o serviço (`sudo systemctl start docker` no Linux). |
| Arquivos criados pertencem ao root | Rode com `--user "$(id -u):$(id -g)"`, como no passo 3. |
| Precisa conectar o modelo de novo a cada uso | Confirme que o `-v benigno-login:...` está no comando. Para apagar o login, use `docker volume rm benigno-login`. |
| `PermissionDenied` ou `EACCES` em `.local/share/opencode` | O volume `benigno-login` foi criado por uma imagem antiga. Apague com `docker volume rm benigno-login` (você vai precisar conectar o modelo de novo). |
| Ollama (modelo local) não aparece | O Ollama roda no seu PC, e o container não enxerga `127.0.0.1` do host. Use os modelos do OpenCode ou um provedor em nuvem nesse modo. |

## Outras formas de uso
- [OpenCode](OPENCODE.md): instalação local completa.
- [Cursor](CURSOR.md): usando o Cursor como editor.

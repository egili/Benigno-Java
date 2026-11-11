# Exemplos de enunciado

## Ruim (Benigno demais)

```markdown
**PROGRAMAR** a interface com:
`void guarde (X x) throws Exception;`

Faça `toString`, `equals`, `hashCode` e `clone`.
Não faça `compareTo`.

| Vazia? | `qtd == 0` |
```

## Bom (Maligno)

```markdown
**PROGRAMAR** a interface `PadraoDeDeposito <X>` com três métodos públicos e não estáticos: um chamado `guarde`, que recebe um `X` e lança `Exception`; ...

**PROGRAMAR** a classe `Deposito <X>`, que implementa essa interface e também `Cloneable`, com atributos privados `elem` e `qtd`...

Implemente os métodos que se fizerem necessários.
```

## Gabarito interno (só no log)

```
Gabarito 6 métodos: trio SIM; compareTo NÃO; clone+cópia SIM (guarde/remova alteram).
```

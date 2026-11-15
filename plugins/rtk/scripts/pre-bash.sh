#!/bin/sh
# Repassa o PreToolUse(Bash) ao RTK, que reescreve o comando (git status -> rtk git status).
# Sem o binário rtk no PATH, não faz nada: o comando roda como foi pedido.
command -v rtk >/dev/null 2>&1 || exit 0
exec rtk hook claude

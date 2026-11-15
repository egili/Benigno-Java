#!/bin/sh
# Injeta no início da sessão a instrução de uso do RTK (o que o `rtk init` poria no CLAUDE.md).
if ! command -v rtk >/dev/null 2>&1; then
  echo "Plugin rtk ativo, mas o binário rtk não está no PATH: a saída dos comandos não será condensada. Instalação: https://github.com/rtk-ai/rtk"
  exit 0
fi
cat <<'TXT'
# Command output

Command output here is condensed to save tokens, keeping every signal and
dropping costly noise. Treat it as the complete result: run commands
normally, and batch related commands into one call to avoid extra turns.
Truncated results state their recovery path in their own output. Re-run a
command as `rtk bypass <cmd>` only when its result is unusable: empty when
output was clearly expected, contradicting its exit code, or garbled.
TXT

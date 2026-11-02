#!/bin/sh
# Compila com Java 8 (padrão da disciplina); falha se algum .java não compilar
set -e
javac --release 8 -encoding UTF-8 -d "$(mktemp -d)" $(find . -name '*.java')
echo "OK: todos os .java compilam com --release 8"

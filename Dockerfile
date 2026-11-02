FROM node:22-bookworm-slim

RUN apt-get update \
 && apt-get install -y --no-install-recommends bash ca-certificates curl git openjdk-17-jdk-headless \
 && rm -rf /var/lib/apt/lists/* \
 && git config --system --add safe.directory '*'

RUN curl -fsSL https://opencode.ai/v2/install | bash -s -- --no-modify-path \
 && install -m 755 /root/.opencode/bin/opencode /usr/local/bin/opencode

COPY . /benigno

RUN mkdir -p /benigno/projeto /home/opencode/.local/share/opencode \
 && chmod -R 777 /home/opencode

ENV HOME=/home/opencode
WORKDIR /benigno/projeto
ENTRYPOINT ["opencode"]

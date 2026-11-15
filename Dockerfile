FROM node:24-bookworm-slim

ARG TARGETARCH
ARG RTK_VERSION=1.2.0

RUN apt-get update \
 && apt-get install -y --no-install-recommends bash ca-certificates curl git openjdk-17-jdk-headless \
 && rm -rf /var/lib/apt/lists/* \
 && git config --system --add safe.directory '*'

RUN case "${TARGETARCH:-amd64}" in arm64) RTK_ARCH=aarch64 ;; *) RTK_ARCH=x86_64 ;; esac \
 && curl -fsSL "https://github.com/rtk-ai/rtk/releases/download/v${RTK_VERSION}/rtk-${RTK_ARCH}-unknown-linux-musl.tar.gz" | tar -xz -C /tmp \
 && install -m 755 "$(find /tmp -maxdepth 2 -type f -name rtk | head -n 1)" /usr/local/bin/rtk \
 && rtk --version

RUN curl -fsSL https://opencode.ai/v2/install | bash -s -- --no-modify-path --version 2.2.5 \
 && install -m 755 /root/.opencode/bin/opencode /usr/local/bin/opencode

COPY . /benigno
COPY docker/verificar-java.sh /usr/local/bin/verificar-java

ENV HOME=/home/opencode
RUN mkdir -p /benigno/projeto /home/opencode/.local/share/opencode \
 && cd /benigno && (timeout 180 opencode api get "/api/agent?location%5Bdirectory%5D=/benigno" >/dev/null 2>&1 || true) \
 && rm -rf /home/opencode/.local/share/opencode/* \
 && chmod -R 777 /home/opencode \
 && chmod +x /usr/local/bin/verificar-java

WORKDIR /benigno/projeto
ENTRYPOINT ["opencode"]

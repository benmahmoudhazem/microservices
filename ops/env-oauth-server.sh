#!/usr/bin/env bash

export APP_NAME=oauth-server
. ./ops/env.sh

export JAVA_OPTS=-Xmx256M
export SERVER_PORT=9090


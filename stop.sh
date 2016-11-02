#!/usr/bin/env bash

set -e

# ANSI Colors
echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }


if [ $# -eq 0 ]; then
    echoRed "No arguments provided"
    exit 1
fi

export SERVICE=$1
export SERVICE_TARGET_DIR=$SERVICE*/target/

. ./ops/env-$SERVICE*.sh

cd $SERVICE_TARGET_DIR
echoYellow "$(pwd)"
./$SERVICE*-exec.jar stop

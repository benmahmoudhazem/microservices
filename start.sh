#!/usr/bin/env bash

set -e

export SIMPLE_SERVICE_TARGET_DIR=simple-service/target/

clear


./build.sh

clear

. ./env.sh

cd $SIMPLE_SERVICE_TARGET_DIR
./simple*-exec.jar start


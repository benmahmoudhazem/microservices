#!/usr/bin/env bash

export MODE=service
WORKING_DIR="$(pwd)"
export JARFILE=$WORKING_DIR/data-service/target/data-service-1.0-SNAPSHOT-exec.jar
export APP_NAME=data-service
export PID_FOLDER=$WORKING_DIR/data-service/target/
export LOG_FOLDER=$WORKING_DIR/data-service/target/


export JAVA_OPTS=-Xmx1024M


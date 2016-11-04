#!/usr/bin/env bash

set -e

#./start.sh simple-service
./start.sh data-service
./start.sh edge-server
./start.sh discovery-server
./start.sh uaa-server

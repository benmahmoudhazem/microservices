#!/usr/bin/env bash

set -e

#./stop.sh simple-service
./stop.sh data-service
./stop.sh edge-server
./stop.sh discovery-server
./stop.sh uaa-server
#./stop.sh oauth-server

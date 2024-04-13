#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo "DEX: $DEX_SRC"

bash $DEX2JAR -f $DEX_SRC -o $JAR_PATH
echo $JAR_PATH
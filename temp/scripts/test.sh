#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

cd $DECODE_DIR
cd "com/example/dexclass"
CLASSES=$(ls)

echo "classes: $CLASSES"
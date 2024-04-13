#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo "APK: $APK_PATH"

bash $DEX2JAR -f $APK_PATH -o $JAR_PATH
echo $JAR_PATH
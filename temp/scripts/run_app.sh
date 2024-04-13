#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo ""
echo "PACKAGE: $PACKAGE_NAME"

adb shell am force-stop "$PACKAGE_NAME"

adb shell am start -n "$PACKAGE_NAME/$PACKAGE_NAME.MainActivity"

echo "App $PACKAGE_NAME restarted"
echo ""

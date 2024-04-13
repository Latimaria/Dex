#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo ""
echo "PUSHING: $TARGET_DEX"
echo "TO PACKAGE: "$PACKAGE_NAME

# copy agent to program's private directory
adb push $TARGET_DEX /sdcard/
adb shell <<EOF
run-as $PACKAGE_NAME
cp /sdcard/$DEX_NAME ./
cp /sdcard/$DEX_NAME ./files/
pwd
exit
EOF

echo "pushed"
echo "-- adb shell ls output -- "
adb shell ls "/data/data/$PACKAGE_NAME/files"

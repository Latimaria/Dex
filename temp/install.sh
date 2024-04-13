#!/bin/bash

# reinstall im.vector.app.debug to connected device

cd "$(dirname "$0")" # cd to where the script is located
pwd
# source ./vars_dex.sh

APK_PATH="/Users/qing/Courses/Android/ASM/temp/app-d8.apk"
PACKAGE_NAME="com.example.dextest"
echo "apk:"
echo $APK_PATH

adb uninstall $PACKAGE_NAME
adb install $APK_PATH

echo "done"

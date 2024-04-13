#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo ""
echo "PACKAGE: $PACKAGE_NAME"

num_iter=50
i=0
while [ $i -le $num_iter ]
do
    echo $i
    adb shell am force-stop "$PACKAGE_NAME"
    adb shell am start -n "$PACKAGE_NAME/$PACKAGE_NAME.MainActivity"
    sleep 4
    ((i++))
done

echo ""

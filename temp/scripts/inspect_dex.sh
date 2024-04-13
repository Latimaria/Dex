#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

echo "classes#.dex: $APK_CLASSES"

for dex in $APK_CLASSES/*.dex; do
    # echo "dex: $dex"
    $DEX_DUMP "$dex" | grep -q $CLASS_NAME_SHORT
    if [ $? -eq 0 ]; then
        echo "$CLASS_NAME_SHORT is in $(basename "$dex")"
    fi
done
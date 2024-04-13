#!/bin/bash
cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

rm -r $EXTRACT_DIR
echo "APK: $APK_PATH"
unzip -o "$APK_PATH" "classes*.dex" -d "$EXTRACT_DIR"

echo "EXTRACTED: $EXTRACT_DIR/$DEX_WANTED"
cp "$EXTRACT_DIR/$DEX_WANTED" "$DEX_SRC"
echo "created: $DEX_SRC"


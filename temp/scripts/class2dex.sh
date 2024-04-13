#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

cd $CLASS_PATH
pwd
CLASSES=$(ls)
echo "classes:"
echo CLASSES

cd $DECODE_DIR
echo "DECODE: $DECODE_DIR"

TEMP_JAR="$TARGET_DIR/temp.jar"
# jar cfm $TEMP_JAR META-INF/MANIFEST.MF com/example
jar cf $TEMP_JAR $DECODED_CLASS

echo "generated temp.jar"
ls -l $TEMP_JAR

cd $BASE_PATH
pwd
TARGET_PATH="$APK_BASE/$TARGET_NAME"
TARGET_JAR="$TARGET_PATH.jar"

$D8_DIR --output $TARGET_JAR $TEMP_JAR

unzip $TARGET_JAR  -d "$APK_BASE/dex"
echo "created dex"

cp "$APK_BASE/dex/classes.dex" $TARGET_DEX
echo "copied to: $TARGET_DEX"
ls -l $TARGET_DEX
echo "done"

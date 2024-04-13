#!/bin/bash

BASE_PATH=$(pwd)
cd ..
PARENT=$(pwd)
cd $BASE_PATH
echo "BASE_PATH: $BASE_PATH"

BUILD_TOOLS="/Users/qing/Library/Android/sdk/build-tools/34.0.0"
D8_DIR="$BUILD_TOOLS/d8"
DEX_DUMP="$BUILD_TOOLS/dexdump"

DEXTOOLS_DIR="/Users/qing/Courses/Android/ASM/dex-tools-v2.4"
DEX2JAR="$DEXTOOLS_DIR/d2j-dex2jar.sh"

#######
# app specific 
source $BASE_PATH/vars/vars_timing.sh

######
# interim files
JAR_PATH="$APK_BASE/classes_d2j.jar"
DECODE_DIR="$APK_BASE/decoded/"

TARGET_DIR="$BASE_PATH/target"

DEX_NAME="classes_CG.dex"
TARGET_DEX="$TARGET_DIR/$DEX_NAME"

EXTRACT_DIR="$BASE_PATH/dex_extracted"

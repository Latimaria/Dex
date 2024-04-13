#!/bin/bash


# app specific 

# APK_BASE="$PARENT/timing/OG"
# APK_BASE="$PARENT/timing/CG_Z1"
APK_BASE="$PARENT/timing/CG_log"
APK_NAME="app-debug"

APK_PATH="$APK_BASE/$APK_NAME.apk"
APK_CLASSES="$APK_BASE/$APK_NAME/"

DEX_WANTED="classes3.dex"
DEX_SRC="$APK_BASE/$DEX_WANTED"

# CLASS_NAME="org.matrix.android.sdk.internal.session.sync.handler.room.RoomSyncHandler"
# CLASS_NAME_SHORT="RoomSyncHandler"
CLASS_NAME="com.example.timing.logX"
CLASS_NAME_SHORT="logX"

DECODED_CLASS="com/example/timing/logX.class"
PACKAGE_NAME="com.example.timing"

TARGET_NAME="classes3_CG"
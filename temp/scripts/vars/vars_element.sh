#!/bin/bash


# app specific 

# APK_BASE="$PARENT/element/Original"
APK_BASE="$PARENT/element/print_AG"
APK_PATH="$APK_BASE/vector-gplay-arm64-v8a-debug.apk"
APK_CLASSES="$APK_BASE/AG_arm64-v8a-debug/"
DEX_SRC="$BASE_PATH/classes21_AG.dex"

# CLASS_NAME="org.matrix.android.sdk.internal.session.sync.handler.room.RoomSyncHandler"
# CLASS_NAME_SHORT="RoomSyncHandler"
CLASS_NAME="org.matrix.android.sdk.internal.session.room.send.DefaultSendService"
CLASS_NAME_SHORT="DefaultSendService"

DECODED_CLASS="org/matrix/android/sdk/internal/session/room/send/DefaultSendService.class"
PACKAGE_NAME="im.vector.app.debug"

TARGET_NAME="classes21_CG"
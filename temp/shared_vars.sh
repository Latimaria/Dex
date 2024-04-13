#!/bin/bash

# push.sh
INSTRUMENT_DIR="/Users/qing/Courses/Android/ASM"
INSTRUMENT_PLAN="plan.btm"

# generate_agent.py
CC_DIR='/Users/account/Library/Android/sdk/ndk/26.1.10909125/toolchains/llvm/prebuilt/darwin-x86_64/bin'
CFLAGS='-fPIC -shared -static-libstdc++ -llog'
INCLUDES='''-I/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/include \\
           -I/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/include/darwin \\
		   -I/Users/account/Library/Android/sdk/ndk/26.1.10909125/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include/android'''

# general
AGENT_NAME="libagent.so"

############################

# generated
AGENT_DIR=$INSTRUMENT_DIR"/agent/"
AGENT_PATH=$AGENT_DIR/$AGENT_NAME
LOG_DIR=$INSTRUMENT_DIR"/logs/"

# src
SRC_DIR=$INSTRUMENT_DIR"/src/"
PY_PATH=$SRC_DIR"/generate_agent.py"
PUSH_PATH=$SRC_DIR"/push.sh"

# inputs
PLAN_PATH=$INSTRUMENT_DIR/$INSTRUMENT_PLAN


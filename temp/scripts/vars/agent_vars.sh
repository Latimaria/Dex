#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd

INSTRUMENT_DIR="/Users/qing/Courses/Android/ASM"

# general
AGENT_NAME="libagent.so"

############################

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

ARTTI_AGENT_DIR="/Users/qing/Courses/Android/ARTTI_instrument/agent"
ARTTI_PATH=$ARTTI_AGENT_DIR/$AGENT_NAME

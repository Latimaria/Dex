#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh
source ./vars/agent_vars.sh

echo ""
echo "PACKAGE: "$PACKAGE_NAME
echo ""

cd $ARTTI_AGENT_DIR
pwd

make clean
make
echo ""

# copy agent to program's private directory
adb push $ARTTI_PATH /sdcard/
adb shell <<EOF
run-as $PACKAGE_NAME
cp /sdcard/$AGENT_NAME ./
cp /sdcard/$AGENT_NAME ./files
pwd
exit
EOF

echo "pushed"
echo ""

# find pid of the program
adb shell ps | grep $PACKAGE_NAME
pid=$(adb shell ps | grep "$PACKAGE_NAME" | tr -s ' ' | cut -d ' ' -f 2)
echo "PID: $pid"
echo ""

if [ ! -z "$pid" ]; then
    echo "pid = "$pid
    echo ""
    echo "to attach:"
    echo "adb shell cmd activity attach-agent $pid /data/data/$PACKAGE_NAME/$AGENT_NAME"
    echo ""
    read -r -p "attach agent? [y/n] " response
    if [[ "$response" =~ ^([yY][eE][sS]|[yY])$ ]]
    then
        adb shell cmd activity attach-agent $pid /data/data/$PACKAGE_NAME/$AGENT_NAME
        echo "attached"
    else
        echo ""
    fi
else
    echo "No pid found for package: $PACKAGE_NAME"
fi

echo 'done'

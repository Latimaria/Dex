#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh


unzip $JAR_PATH -d $DECODE_DIR
cd $DECODE_DIR

mkdir META-INF 
touch META-INF/MANIFEST.MF




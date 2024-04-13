#!/bin/bash

cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

./dex2jar.sh 
./jar2class.sh 
./class2dex.sh 
./push_dex.sh  
./push_agent.sh

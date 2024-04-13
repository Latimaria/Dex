cd "$(dirname "$0")" # cd to where the script is located
pwd
source ./vars.sh

rm -r $TARGET_DIR
rm -r $DECODE_DIR
rm -r $EXTRACT_DIR

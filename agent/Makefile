CC = /Users/qing/Library/Android/sdk/ndk/26.2.11394342/toolchains/llvm/prebuilt/darwin-x86_64/bin/aarch64-linux-android28-clang++
CFLAGS = -fPIC -shared -static-libstdc++ -llog
INCLUDES = -I/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/include \
           -I/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/include/darwin \
		   -I/Users/qing/Library/Android/sdk/ndk/26.2.11394342/toolchains/llvm/prebuilt/darwin-x86_64/sysroot/usr/include/android
SRC = agent.cpp
TARGET = libagent.so

$(TARGET): $(SRC)
	$(CC) $(CFLAGS) $(INCLUDES) $(SRC) -o $(TARGET)

clean:
	rm -f $(TARGET) 

.PHONY: clean

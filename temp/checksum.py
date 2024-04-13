#!/usr/bin/env python3

import struct
import zlib
import hashlib

# def calculateChecksum(self):
#     skip_nonsum = 8 + 4 #skip 8b magic + 4b checksum
#     dexOptHdr=self.dexFile.getDexOptHeader()
#     self._fp.seek(dexOptHdr.dexOffset + skip_nonsum)
#     data=self._fp.read(self.fileSize - skip_nonsum)
#     true_checksum=zlib.adler32(data)
#     return true_checksum
magic = bytes.fromhex('6465780a30333900')
print(magic)

def adler32_checksum(file_path, offset=0, length=None):
    with open(file_path, 'rb') as file:
        file.seek(offset)
        if length is None:
            content = file.read()
        else:
            content = file.read(length)
        checksum = zlib.adler32(content)
        # Adler-32 returns signed value in Python 2 and unsigned in Python 3
        # Ensure result is positive
        if checksum < 0:
            print("checksum:", checksum)
            checksum += 2**32
    return checksum, len(content)

def find_bytes(file_path, search_bytes):

    offset = -1

    with open(file_path, 'rb') as file:
        data = file.read()
        offset = data.find(search_bytes)
    
    if offset == -1:
        print("String not found.")
    
    return offset

def find_string(file_path, search_str):
    search_bytes = search_str.encode()
    print("search bytes:", search_bytes)

    return find_bytes(file_path, search_bytes)


def replace(file_path, offset, replacement):
    
    # read+write binary mode
    with open(file_path, 'r+b') as file:  
        file.seek(offset)  
        file.write(replacement) 

def replace_bytes(file_path, offset, replacement, byteorder='little', num_bytes=4):
    
    replacement_bytes = replacement.to_bytes(num_bytes, byteorder=byteorder)

    replace(file_path, offset, replacement_bytes)
    # read+write binary mode
    # with open(file_path, 'r+b') as file:  
    #     file.seek(offset)  
    #     file.write(replacement_bytes) 

def read_bytes(file_path, offset=0, length=None):
    with open(file_path, 'rb') as file:
        file.seek(offset)
        if length is None:
            content = file.read()
        else:
            content = file.read(length)
        # hex_content = content.hex()
    return content, len(content)

def print_format_hex(content):
    hex_content = content.hex()
    hex_list = [''.join([a, b]) for a, b in zip(hex_content[::2], hex_content[1::2])]
    hex_string = ' '.join(hex_list)
    print(hex_string)
    return hex_string 


if __name__ == '__main__':
    base_path = "/Users/qing/Courses/Android/ASM/temp/original/"

    apk_path = base_path + "app-debug-copy.apk"

    print("=== search ")
    print("base.apk --")

    target = "classes3.dex".encode() + b'\x00\x00' + magic
    print("target:", target)
    offset = find_bytes(apk_path, target)
    print("offset:", hex(offset))

    offset += 14
    print_format_hex(read_bytes(apk_path, offset, 8)[0])

    original_dex = base_path + "classes3.dex"
    content, length = read_bytes(original_dex)
    print("original dex length:", length)
    print_format_hex(read_bytes(apk_path, offset+length, 8)[0])

    print("-- replace --")
    replace(apk_path, offset, content)
    print_format_hex(read_bytes(apk_path, offset, 12)[0])

    # offset += 0x4
    # checksum, length = adler32_checksum(path, offset, length)
    # print("Len:", length)
    # print("Computed Checksum:", hex(checksum))


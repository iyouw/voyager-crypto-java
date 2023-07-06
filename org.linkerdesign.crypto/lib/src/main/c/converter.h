#ifndef LINKER_DESIGN_CONVERTER_H
#define LINKER_DESIGN_CONVERTER_H

#include <jni.h>

jbyteArray as_jbyte_array(JNIEnv *env, unsigned char *bytes, int length, int freeBytes);

unsigned char *as_unsigned_char_array(JNIEnv *env, jbyteArray jBytes);

int as_unsigned_char_array_ex(unsigned char *bytes, JNIEnv *env, jbyteArray jBytes);

#endif
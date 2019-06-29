//
// Created by ChrisChan on 2019/6/29.
//

#include <jni.h>
#include <string>
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_chrischan_imagelooker_pages_ndk_NativeLibrary_getString(JNIEnv *env, jobject) {
    std::string result = "String from Native";
    return env->NewStringUTF(result.c_str());
}
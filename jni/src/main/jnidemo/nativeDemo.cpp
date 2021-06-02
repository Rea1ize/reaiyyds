#include <jni.h>
#include <string>
extern "C" JNIEXPORT jstring JNICALL
Java_com_hsae_d531mc_jni_MainActivity_stringFromJNI(JNIEnv* env,jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_hsae_d531mc_jni_JniManager_sum(JNIEnv *env, jclass clazz, jint a, jint b) {
    // TODO: implement sum()
    jint result = a+b;
    return result;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_hsae_d531mc_jni_JniManager_clone(JNIEnv *env, jclass clazz, jstring free) {
    // TODO: implement clone()
    jstring result = free;
    return result;
}
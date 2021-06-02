LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_PREBUILT_JNI_LIBS = libAes128.so
LOCAL_SRC_FILES := nativeDemo.cpp test.cpp

LOCAL_MODULE := nativedemo

# external/stlport/stlport \

#设置头文件的include目录
LOCAL_C_INCLUDES := \
    $(LOCAL_PATH)/include \

LOCAL_CFLAGS += -Wno-unused-function
#Wno表示诊断时忽视这个警告
#遇到仅声明过但尚未定义的静态函数时发出警告。
LOCAL_CFLAGS += -Wno-unused-parameter
#从未用过的函数参数的警告。
LOCAL_CFLAGS += -Wno-unused-variable
#在本地声明但从未用过的变量的警告。
LOCAL_CPPFLAGS += -fexceptions
#启动编译器的异常捕获功能

#表示模块在运行时要依赖的共享库（动态库），在链接时就需要，以便在生成文件时嵌入其相应的信息。
#LOCAL_SHARED_LIBRARIES := \
#	libc++ \

LOCAL_LDLIBS := -llog

#生成so共享库
include $(BUILD_SHARED_LIBRARY)

#当前path jnidemo/ 最顶层目录，然后往下找子文件夹里面的mk文件
include $(call all-makefiles-under,$(LOCAL_PATH))
#递归编译

#引用第三方so库
#LOCAL_PREBUILT_LIBS := \
#	jniLibs/libc++.so  \
#	jniLibs/libcutils.so \
# 	jniLibs/libutils.so \
#
#LOCAL_LDLIBS: \
# 	jniLibs/libc++.so  \
#    jniLibs/libcutils.so \
#   jniLibs/libutils.so \


#LOCAL_SHARED_LIBRARIES := \
#    libutils \
#    libcutils \
#    libc++ \
LOCAL_PATH:= $(call my-dir)
#设置当前模块的编译路径为当前文件夹路径。
include $(CLEAR_VARS)
#清理（可能由其他模块设置过的）编译环境中用到的变量。

#LOCAL_PREBUILT_JNI_LIBS = libs/lib64/libhsae.so
LOCAL_SRC_FILES := nativeReai.cpp

LOCAL_MODULE := nativereai

# external/stlport/stlport \

LOCAL_C_INCLUDES := \
	bionic


LOCAL_CFLAGS += -Wno-unused-function
#Wno表示诊断时忽视这个警告
#遇到仅声明过但尚未定义的静态函数时发出警告。
LOCAL_CFLAGS += -Wno-unused-parameter
#从未用过的函数参数的警告。
LOCAL_CFLAGS += -Wno-unused-variable
#在本地声明但从未用过的变量的警告。
LOCAL_CPPFLAGS += -fexceptions
#启动编译器的异常捕获功能
#LOCAL_SHARED_LIBRARIES := \
#	libc++ \

LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(call all-makefiles-under,$(LOCAL_PATH))
#递归编译


#include $(CLEAR_VARS)
#
#
#include $(BUILD_SHARED_LIBRARY)
Android平台下基于Ffmpeg播放本地MP4的简单Demo

编译Android平台各个CPU架构的Ffmpeg动态库的步骤 \
环境 Ubuntu 16.04 \
NDK版本 android-ndk-r14b

下载FFmpeg \
git clone https://git.ffmpeg.org/ffmpeg.git

编译armv7-a版本动态库脚本build_android_ffmpeg_armv7a.sh内容

#!/bin/bash

NDK_PATH=/opt/android/android-ndk-r14b \
SYSROOT=$NDK_PATH/platforms/android-21/arch-arm \
TOOLCHAIN=$NDK_PATH/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64 \
CPU=armeabi-v7a \
mkdir -p $(pwd)/android_build_out/$CPU \
PREFIX=$(pwd)/android_build_out/$CPU

function build_android {

    ./configure \
    --prefix=$PREFIX \
    --target-os=android \
    --enable-shared \
    --enable-cross-compile \
    --enable-small \
    --disable-static \
    --disable-programs \
    --disable-ffmpeg \
    --disable-ffplay \
    --disable-ffprobe \
    --disable-doc \
    --disable-symver \
    --disable-asm \
    --arch=arm \
    --cpu=armv7-a \
    --cc="${TOOLCHAIN}/bin/arm-linux-androideabi-gcc" \
    --cross-prefix="${TOOLCHAIN}/bin/arm-linux-androideabi-" \
    --sysroot="${SYSROOT}/" \
    --extra-cflags="-march=armv7-a -mfloat-abi=softfp -mfpu=neon" \
    --extra-ldexeflags=-pie

};

build_android


执行./build_android_ffmpeg_armv7a.sh \
make -j8 \
make install


编译armv8-a版本动态库脚本build_android_ffmpeg_armv8a.sh内容

#!/bin/bash

NDK_PATH=/opt/android/android-ndk-r14b \
SYSROOT=$NDK_PATH/platforms/android-21/arch-arm64 \
TOOLCHAIN=$NDK_PATH/toolchains/aarch64-linux-android-4.9/prebuilt/linux-x86_64 \
CPU=arm64-v8a \
mkdir -p $(pwd)/android_build_out/$CPU \
PREFIX=$(pwd)/android_build_out/$CPU

build_android(){

    ./configure \
    --prefix=$PREFIX \
    --target-os=android \
    --enable-shared \
    --enable-cross-compile \
    --enable-small \
    --disable-static \
    --disable-programs \
    --disable-ffmpeg \
    --disable-ffplay \
    --disable-ffprobe \
    --disable-doc \
    --disable-symver \
    --disable-asm \
    --arch=aarch64 \
    --cpu=armv8-a \
    --cc="${TOOLCHAIN}/bin/aarch64-linux-android-gcc" \
    --cross-prefix="${TOOLCHAIN}/bin/aarch64-linux-android-" \
    --sysroot="${SYSROOT}/" \
    --extra-cflags="-march=armv8-a -DANDROID -Wfatal-errors -Wno-deprecated" \
    --extra-ldexeflags=-pie
}


build_android


执行./build_android_ffmpeg_armv8a.sh \
make -j8 \
make install


将编译出来的动态库和头文件拷贝到工程FfmpegPlayerAndroid下jni下ffmpeg目录中

AS编译运行即可


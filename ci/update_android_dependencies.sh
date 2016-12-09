#!/bin/bash

set -euxo pipefail

BUILD_GRADLE="app/build.gradle"

SDK_VERSION=`grep "compileSdkVersion" $BUILD_GRADLE | sed "s/.*compileSdkVersion \(.*\).*/\1/"`
if [ ! -d "/usr/local/android-sdk-linux/platforms/android-$SDK_VERSION" ]; then
    echo y | android update sdk --no-ui --all --filter "android-$SDK_VERSION"
fi

if [ ! -d "/usr/local/android-sdk-linux/build-tools/24.0.3" ]; then
    echo y | android update sdk --no-ui --all --filter "build-tools-24.0.3"
fi

SUPPORT_VERSION=`grep "com.android.support:appcompat-v7:" $BUILD_GRADLE | sed "s/.*appcompat-v7:\(.*\)'.*/\1/"`
if [ ! -d "/usr/local/android-sdk-linux/extras/android/m2repository/com/android/support/appcompat-v7/$SUPPORT_VERSION" ]; then
    echo y | android update sdk --no-ui --all --filter "extra-android-m2repository"
fi

echo y | android update sdk --no-ui --all --filter "platform-tools"

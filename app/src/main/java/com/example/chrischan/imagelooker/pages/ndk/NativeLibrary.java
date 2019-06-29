package com.example.chrischan.imagelooker.pages.ndk;

public class NativeLibrary {
    static {
        System.loadLibrary("NativeLibrary");
    }

    public native String getString();
}

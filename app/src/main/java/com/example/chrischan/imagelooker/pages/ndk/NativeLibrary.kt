package com.example.chrischan.imagelooker.pages.ndk;

class NativeLibrary {
    init {
        System.loadLibrary("NativeLibrary")
    }

    external fun getString(): String
}

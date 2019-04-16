package ru.rpuxa

import java.io.OutputStream

class CountBytesStream : OutputStream() {
    var count = 0
        private set

    override fun write(b: Int) {
        count++
    }
}
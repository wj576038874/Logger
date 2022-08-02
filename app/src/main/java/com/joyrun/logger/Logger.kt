package com.joyrun.logger

/**
 * author: wenjie
 * date: 2022-06-01 17:43
 * descption:
 */
class Logger {

    companion object {
        init {
            System.loadLibrary("logger")
        }
    }

    external fun initNative(
        bufferPath: String,
        capacity: Int,
        logPath: String,
        compress: Boolean
    ): Long

    external fun write(ptr: Long, log: String)

    external fun flushAsync(ptr: Long)

    external fun release(ptr: Long)

    external fun changeLogPath(ptr: Long, logPath: String)
}
package com.tusharkathuria.androidplayground.concurrency.threads

import android.util.Log
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor

class ThreadsRaceConditionDeterministic: IDefaultExecutor {
    private var sharedResource = 0;
    private val sharedResourceLock = Any()

    /**
     * Fix issues faced in [ThreadsRaceConditionNonDeterministic] using [synchronized]
     */
    override fun execute() {
        val thread1 = Thread {
            for(i in 1..1000) {
                Thread.sleep(10)
                synchronized(sharedResourceLock) {
                    sharedResource++
                    Log.i(APP_TAG, "$sharedResource after thread 1 incremented")
                }
            }
        }

        val thread2 = Thread {
            for(i in 1..1000) {
                Thread.sleep(10)
                synchronized(sharedResourceLock) {
                    sharedResource--
                    Log.i(APP_TAG, "$sharedResource after thread 2 decremented")
                }
            }
        }

        thread1.start()
        thread2.start()
    }
}
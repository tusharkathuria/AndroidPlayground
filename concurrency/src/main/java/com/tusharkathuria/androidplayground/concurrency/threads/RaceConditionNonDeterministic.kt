package com.tusharkathuria.androidplayground.concurrency.threads

import android.util.Log
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor

class RaceConditionNonDeterministic: IDefaultExecutor {
    private var sharedResource = 0;

    /**
     * Increment and decrement are not atomic operators. So this will lead to Nondeterministic end result
     * It can be any of 0,negative,positive depending on order of execution of internal instructions
     */
    override fun execute() {
        val thread1 = Thread {
            for(i in 1..1000) {
                sharedResource++
                Log.i(APP_TAG, "$sharedResource after thread 1 incremented")
            }
        }

        val thread2 = Thread {
            for(i in 1..1000) {
                sharedResource--
                Log.i(APP_TAG, "$sharedResource after thread 2 decremented")
            }
        }

        thread1.start()
        thread2.start()
    }
}
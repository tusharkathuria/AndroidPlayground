package com.tusharkathuria.androidplayground.concurrency.threads

import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import android.os.SystemClock
import android.util.Log
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor
import java.util.*

class ConsumeAndQuit : IDefaultExecutor {
    private val list = LinkedList<Int>()
    private val limit = 10;
    private val listLock = Object()

    private fun produce() {
        var value = 0;
        while (true) {
            synchronized(listLock) {
                while (list.size == limit) {
                    listLock.wait() // Give up execution
                }
                value++
                list.add(value)
                Log.i(APP_TAG, "Producing $value")
                Log.i(APP_TAG, "Updated list: $list")
                listLock.notify() // Notify waiting threads that they can resume
            }
            Thread.sleep((Math.random()*2000).toLong())
        }
    }

    private fun consume() {
        while (true) {
            synchronized(listLock) {
                while (list.size == 0) {
                    listLock.wait()
                }
                val value = list.removeFirst()
                Log.i(APP_TAG, "Consuming $value")
                Log.i(APP_TAG, "Updated list: $list")
                listLock.notify()
            }
            Thread.sleep((Math.random()*2000).toLong())
        }
    }

    class ConsumeAndQuitThread: Thread(), MessageQueue.IdleHandler {
        var requestHandler: Handler? = null
        var isFirstIdle = true

        override fun run() {
            Looper.prepare()
            requestHandler = Handler {
                Log.i(APP_TAG, "Consuming ${it.what}")
                return@Handler true
            }
            Looper.myQueue().addIdleHandler(this)
            Looper.loop()
        }

        override fun queueIdle(): Boolean {
            if(isFirstIdle) {
                isFirstIdle = false
                return true
            }

            requestHandler?.looper?.quit()
            return false
        }

        fun enqueue(i: Int) {
            requestHandler?.sendEmptyMessage(i)
        }
    }

    override fun execute() {
        val consumer = ConsumeAndQuitThread()
        consumer.start()

        for(i in 1..10) {
            Thread {
                for(j in 1..10) {
//                    SystemClock.sleep(Random().nextInt(10).toLong())
                    consumer.enqueue(j)
                }
            }.start()
        }
    }
}
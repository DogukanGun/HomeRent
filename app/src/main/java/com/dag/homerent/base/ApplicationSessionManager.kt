package com.dag.homerent.base

import android.os.CountDownTimer
import java.util.*

class ApplicationSessionManager {

    companion object {
        val homeRentSession = HomeRentSession()

        var isSessionEndingProcessStarted = false

        fun startSession(sessionTimeOutInSeconds: Long) {
            homeRentSession.startSession(sessionTimeOutInSeconds)
        }

        fun restartSessionTime() {
            homeRentSession.restartSessionTime()
        }

        fun endSession() {
            isSessionEndingProcessStarted = false
            homeRentSession.endSession()
        }

        fun addObserver(observer: Observer) {
            homeRentSession.addObserver(observer)
        }

        fun deleteObserver(observer: Observer) {
            homeRentSession.deleteObserver(observer)
        }

        fun deleteObservers() {
            homeRentSession.deleteObservers()
        }
    }

    class HomeRentSession : Observable() {

        var sessionStarted: Boolean = false
            private set
        private var timer: CountDownTimer? = null

        fun startSession(sessionTimeOutInSeconds: Long) {
            if (timer == null) {
                timer = object : CountDownTimer(sessionTimeOutInSeconds * 1000, 1000L) {
                    override fun onFinish() {
                        sessionStarted = false
                        setChanged()
                        notifyObservers() // session timeout
                    }

                    override fun onTick(millisUntilFinished: Long) {
                    }
                }
            }

            sessionStarted = true
            timer?.cancel()
            timer?.start()
        }

        fun restartSessionTime() {
            if (sessionStarted) {
                timer?.cancel()
                timer?.start()
            }
        }

        fun endSession() {
            timer?.cancel()
            sessionStarted = false
        }
    }
}
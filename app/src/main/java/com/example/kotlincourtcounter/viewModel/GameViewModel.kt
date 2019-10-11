package com.example.kotlincourtcounter.viewModel

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 3000L
    }

    private val _teamAScore = MutableLiveData<Int>()
    val teamAScore : LiveData<Int>
        get() = _teamAScore

    private val _teamBScore = MutableLiveData<Int>()
    val teamBScore : LiveData<Int>
        get() = _teamBScore

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val timer : CountDownTimer
    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }

    init {
        _eventGameFinish.value = false
        resetScores()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinish.value = true
            }

            override fun onTick(p0: Long) {
                _currentTime.value = (p0 / ONE_SECOND)
            }
        }
        timer.start()
    }

    fun addThree(isTeamA: Boolean) {
        if (isTeamA) {
            _teamAScore.value = _teamAScore.value?.plus(3)
        } else{
            _teamBScore.value = _teamBScore.value?.plus(3)
        }
    }

    fun addTwo(isTeamA: Boolean) {
        if (isTeamA) {
            _teamAScore.value = _teamAScore.value?.plus(2)
        } else{
            _teamBScore.value = _teamBScore.value?.plus(2)
        }
    }

    fun addOne(isTeamA: Boolean) {
        if (isTeamA) {
            _teamAScore.value = _teamAScore.value?.plus(1)
        } else{
            _teamBScore.value = _teamBScore.value?.plus(1)
        }
    }

    fun resetScores() {
        _teamAScore.value = 0
        _teamBScore.value = 0
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}
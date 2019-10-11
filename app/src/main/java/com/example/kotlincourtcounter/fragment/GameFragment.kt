package com.example.kotlincourtcounter.fragment


import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.kotlincourtcounter.R
import com.example.kotlincourtcounter.databinding.FragmentGameBinding
import com.example.kotlincourtcounter.viewModel.GameViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class GameFragment : Fragment() {

    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel : GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.buttonA3.setOnClickListener {
            viewModel.addThree(true)
        }

        binding.buttonB3.setOnClickListener {
            viewModel.addThree(false)
        }

        binding.buttonA2.setOnClickListener {
            viewModel.addTwo(true)
        }

        binding.buttonB2.setOnClickListener {
            viewModel.addTwo(false)
        }

        binding.buttonAFree.setOnClickListener {
            viewModel.addOne(true)
        }

        binding.buttonBFree.setOnClickListener {
            viewModel.addOne(false)
        }

        binding.buttonReset.setOnClickListener {
            viewModel.resetScores()
        }

        viewModel.currentTime.observe(this, Observer {
            binding.textViewTimer.text = DateUtils.formatElapsedTime(it).toString()
        })

        viewModel.teamAScore.observe(this, Observer {
            binding.teamAScore.text = it.toString()
        })

        viewModel.teamBScore.observe(this, Observer {
            binding.teamBScore.text = it.toString()
        })

        viewModel.eventGameFinish.observe(this, Observer {
            if (it) {
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })

        return binding.root
    }

    private fun gameFinished() {
        val teamAScore = viewModel.teamAScore.value ?: 0 // ?: means that if value is not null then ok else pass through 0
        val teamBScore = viewModel.teamBScore.value ?: 0

        val action = GameFragmentDirections
            .actionGameFragmentToScoreFragment(teamAScore, teamBScore)
        findNavController().navigate(action)
    }
}

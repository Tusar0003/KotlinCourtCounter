package com.example.kotlincourtcounter.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.kotlincourtcounter.R
import com.example.kotlincourtcounter.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentScoreBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val args = ScoreFragmentArgs.fromBundle(arguments!!)
        binding.textViewScoreA.text = args.teamAScore.toString()
        binding.textViewScoreB.text = args.teamBScore.toString()

        binding.buttonPlayAgain.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        }

        return binding.root
    }
}

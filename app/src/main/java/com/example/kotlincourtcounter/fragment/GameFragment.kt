package com.example.kotlincourtcounter.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

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

        /**
         * It allows me to use live data to automatically update by data binding layouts
         */
        binding.setLifecycleOwner(this)

        return binding.root
    }


}

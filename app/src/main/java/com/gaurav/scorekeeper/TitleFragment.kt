package com.gaurav.scorekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.gaurav.scorekeeper.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)


        binding.button.setOnClickListener {
            findNavController().navigate(
                TitleFragmentDirections.actionTitleFragmentToGameFragment(
                    binding.EditTextTeam1.text.toString(),
                    binding.EditTextTeam2.text.toString()
                )
            )
        }


        return binding.root
    }

}
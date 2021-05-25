package com.gaurav.scorekeeper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.gaurav.scorekeeper.databinding.FragmentGameOverBinding
import androidx.navigation.fragment.findNavController


class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)


        val args = GameOverFragmentArgs.fromBundle(requireArguments())
        binding.textViewTeam1.text = "${args.team1Name} : ${args.team1Score}"
        binding.textViewTeam2.text = "${args.team2Name} : ${args.team2Score}"


        when {
            args.team1Score < args.team2Score -> {
                binding.textViewTeamWiner.text = "${args.team2Name} Wins!"
                binding.textViewTeamWiner.setTextColor(
                    ContextCompat.getColor(
                        requireContext().applicationContext,
                        R.color.team1
                    )
                )

            }
            args.team1Score > args.team2Score -> {
                binding.textViewTeamWiner.text = "${args.team1Name} Wins!"
                binding.textViewTeamWiner.setTextColor(
                    ContextCompat.getColor(
                        requireContext().applicationContext,
                        R.color.team2
                    )
                )

            }
            else -> {
                binding.textViewTeamWiner.text = "Draws!"
                binding.textViewTeamWiner.setTextColor(
                    ContextCompat.getColor(
                        requireContext().applicationContext,
                        R.color.orange
                    )
                )

            }
        }

        binding.btnNew.setOnClickListener {
            findNavController().navigate(
                GameOverFragmentDirections.actionGameOverFragmentToTitleFragment()
            )
        }

        return binding.root


    }

}
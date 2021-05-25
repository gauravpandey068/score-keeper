package com.gaurav.scorekeeper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.gaurav.scorekeeper.databinding.FragmentGameBinding
import kotlin.math.round


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    //counter Shots
    //team1
    private var team1TotalShots = 0
    private var team1MadeShots = 0

    //team2
    private var team2TotalShots = 0
    private var team2MadeShots = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        val args = GameFragmentArgs.fromBundle(requireArguments())

        binding.textViewTeam1Name.text = args.team1.capitalize()
        binding.textViewTeam2Name.text = args.team2.capitalize()


        //set on click listener
        //team 1
        binding.buttonTeam1Ft.setOnClickListener { teamShoot("team1", 1) }
        binding.buttonTeam12Pt.setOnClickListener { teamShoot("team1", 2) }
        binding.buttonTeam13Pt.setOnClickListener { teamShoot("team1", 3) }
        binding.buttonTeam1Ms.setOnClickListener { teamShoot("team1", 0) }

        //team 2
        binding.buttonTeam2Ft.setOnClickListener { teamShoot("team2", 1) }
        binding.buttonTeam22Pt.setOnClickListener { teamShoot("team2", 2) }
        binding.buttonTeam23Pt.setOnClickListener { teamShoot("team2", 3) }
        binding.buttonTeam2Ms.setOnClickListener { teamShoot("team2", 0) }


        //end game button
        binding.buttonEndGame.setOnClickListener {
            findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToGameOverFragment(
                    binding.textViewTeam1score.text.toString(),
                    binding.textViewTeam2score.text.toString(),
                    binding.textViewTeam1Name.text.toString(),
                    binding.textViewTeam2Name.text.toString()
                )
            )
        }




        return binding.root
    }

    private fun updateQuarterScore(teamName: String, points: Int) {
        if (teamName == "team1") {
            when {
                binding.radioButtonQ1.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam1q1score.text.toString().toInt() + points
                    binding.textViewTeam1q1score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ2.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam1q2score.text.toString().toInt() + points
                    binding.textViewTeam1q2score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ3.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam1q3score.text.toString().toInt() + points
                    binding.textViewTeam1q3score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ4.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam1q4score.text.toString().toInt() + points
                    binding.textViewTeam1q4score.text = newQuarterScore.toString()
                }

            }
        } else {
            when {
                binding.radioButtonQ1.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam2q1score.text.toString().toInt() + points
                    binding.textViewTeam2q1score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ2.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam2q2score.text.toString().toInt() + points
                    binding.textViewTeam2q2score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ3.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam2q3score.text.toString().toInt() + points
                    binding.textViewTeam2q3score.text = newQuarterScore.toString()
                }
                binding.radioButtonQ4.isChecked -> {
                    val newQuarterScore =
                        binding.textViewTeam2q4score.text.toString().toInt() + points
                    binding.textViewTeam2q4score.text = newQuarterScore.toString()
                }

            }
        }
    }

    private fun updateShotingPercentage(){

    }

    @SuppressLint("SetTextI18n")
    private fun teamShoot(teamName: String, points: Int) {

        if (teamName == "team1") {
            //count team1 total shoot
            team1TotalShots++

            if (points != 0) {
                team1MadeShots++

                //update total score
                val newScore = binding.textViewTeam1score.text.toString().toInt() + points

                binding.textViewTeam1score.text = newScore.toString()

                //update quater score
                updateQuarterScore("team1", points)

            }

            //update shoot percentage %%

            val shootingPercent = round(team1MadeShots.toFloat()/team1TotalShots*10000) /100
            binding.textViewTeam1Percent.text = "$shootingPercent %"

        } else {
            //count team2 total shoot
            team2TotalShots++
            if (points != 0) {
                team2MadeShots++

                val newScore = binding.textViewTeam2score.text.toString().toInt() + points

                binding.textViewTeam2score.text = newScore.toString()

                updateQuarterScore("team2", points)

            }
            val shootingPercent = round(team2MadeShots.toFloat()/team2TotalShots*10000) /100
            binding.textViewTeam2Percent.text = "$shootingPercent %"

        }
    }
}
package com.game.space.sho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.game.space.sho.databinding.FragmentStttaBinding

class StttaFragment : Fragment() {
    private val names = listOf(
        "Noah",
        "Oliver",
        "Elijah",
        "James",
        "William",
        "Benjamin",
        "Lucas",
        "Henry",
        "Theodore",
        "Jack",
        "Levi",
        "Alexander",
        "Jackson",
        "Mateo",
        "Daniel",
        "Michael",
        "Mason",
        "Sebastian",
        "Ethan",
        "Logan",
        "Owen",
        "Samuel",
        "Jacob",
        "Asher",
        "Aiden",
        "John",
        "Joseph",
        "Wyatt",
        "David",
        "Leo",
        "Luke",
        "Julian",
        "Hudson",
        "Grayson",
        "Matthew",
        "Ezra",
        "Gabriel",
        "Carter",
        "Isaac",
        "Jayden",
        "Luca",
        "Anthony",
        "Dylan",
        "Lincoln",
        "Thomas",
        "Maverick",
        "Elias",
        "Josiah",
        "Charles",
    ).shuffled()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAAAABinding = FragmentStttaBinding.inflate(inflater, container, false)
        return staaartBinding.root
    }

    private var fragmentAAAABinding: FragmentStttaBinding? = null
    private val staaartBinding
        get() = fragmentAAAABinding ?: throw RuntimeException("FragmentStttaBinding = null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            staaartBinding.tvTeam1gtgtgt.text = names.random()
            staaartBinding.tvTeam2gtgtgt.text = names.random()

            staaartBinding.imgEl1frfrf.setOnClickListener {
                gtkgtkgtktg()
            }
            staaartBinding.imgEl2ggtgtgt.setOnClickListener {
                gtkgtkgtktg()
            }

        } catch (e: Exception) {
            efrfrgtgttg5()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun gtkgtkgtktg() {
        findNavController().navigate(R.id.action_stttaFragment_to_mstaaFragment)
    }

    override fun onDestroy() {
        fragmentAAAABinding = null
        super.onDestroy()
    }

    private fun efrfrgtgttg5() {
        Toast.makeText(
            requireContext(),
            "Error 888...",
            Toast.LENGTH_SHORT
        ).show()
        hkyhykhkykhy()
    }

    private fun hkyhykhkykhy() {
        requireActivity().onBackPressed()
    }
}
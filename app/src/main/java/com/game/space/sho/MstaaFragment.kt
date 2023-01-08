package com.game.space.sho

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.game.space.sho.databinding.FragmentMstaaBinding
import kotlinx.coroutines.delay
import kotlin.random.Random


class MstaaFragment : Fragment() {
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
        fragmentMstaaBinding = FragmentMstaaBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var fragmentMstaaBinding: FragmentMstaaBinding? = null
    private val binding
        get() = fragmentMstaaBinding ?: throw RuntimeException("FragmentMstaaBinding = null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            lifecycleScope.launchWhenCreated {
                val num = Random.nextInt(from = 10, until = 100)
                Toast.makeText(
                    requireContext(),
                    "You team CAPTAIN is ${names.random()}.Earned $num points",
                    Toast.LENGTH_SHORT
                ).show()
                delay(2100)
                hkyhykhkykhy()
            }

        } catch (e: Exception) {
            efrfrgtgttg5()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        fragmentMstaaBinding = null
        super.onDestroy()
    }

    private fun efrfrgtgttg5() {
        Toast.makeText(
            requireContext(),
            "Error 656...",
            Toast.LENGTH_SHORT
        ).show()
        hkyhykhkykhy()
    }

    private fun hkyhykhkykhy() {
        requireActivity().onBackPressed()
    }
}
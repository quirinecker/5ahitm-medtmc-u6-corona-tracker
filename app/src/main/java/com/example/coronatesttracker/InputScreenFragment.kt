package com.example.coronatesttracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs

class InputScreenFragment : Fragment() {

    private val args: InputScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        args.coronaTest?.let {
            Toast.makeText(context, it.location.name, Toast.LENGTH_SHORT)
                .show()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_screen, container, false)
    }
}
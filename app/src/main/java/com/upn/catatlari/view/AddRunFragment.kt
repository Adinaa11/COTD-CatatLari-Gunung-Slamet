package com.upn.catatlari.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.upn.catatlari.databinding.FragmentAddRunBinding
import com.upn.catatlari.database.RunEntity
import com.upn.catatlari.viewmodel.RunViewModel

class AddRunFragment : Fragment() {

    private lateinit var binding: FragmentAddRunBinding
    private val runViewModel: RunViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveRun.setOnClickListener {

            val runDate = binding.etDate.text.toString()
            val runDurationText = binding.etRunDuration.text.toString()
            val runDistanceText = binding.etRunDistance.text.toString()

            //validasi cek kosong
            if (runDate.isEmpty() || runDurationText.isEmpty() || runDistanceText.isEmpty()) {
                binding.etDate.error = "Tidak boleh kosong"
                return@setOnClickListener
            }

            //validasi angka
            val duration = runDurationText.toIntOrNull()
            val distance = runDistanceText.toIntOrNull()

            if (duration == null || distance == null) {
                binding.etRunDuration.error = "Harus angka"
                binding.etRunDistance.error = "Harus angka"
                return@setOnClickListener
            }

            //validasi > 0
            if (duration <= 0) {
                binding.etRunDuration.error = "Harus lebih dari 0"
                return@setOnClickListener
            }

            if (distance <= 0) {
                binding.etRunDistance.error = "Harus lebih dari 0"
                return@setOnClickListener
            }

            //simpan data
            val run = RunEntity(
                id = 0,
                runDate = runDate,
                runDistance = distance,
                runDuration = duration
            )

            runViewModel.insert(run)

            findNavController().popBackStack()
        }
    }
}
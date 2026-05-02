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

    private var currentRun: RunEntity? = null

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

        // 🔹 cek mode EDIT
        currentRun = arguments?.getParcelable("run")

        // 🔥 isi form kalau EDIT
        if (currentRun != null) {

            binding.etDate.setText(currentRun?.runDate)
            binding.etRunDuration.setText(currentRun?.runDuration.toString())
            binding.etRunDistance.setText(currentRun?.runDistance.toString())
            binding.etNote.setText(currentRun?.runNote)

            binding.btnSaveRun.visibility = View.GONE
            binding.btnUpdateRun.visibility = View.VISIBLE

        } else {

            binding.btnSaveRun.visibility = View.VISIBLE
            binding.btnUpdateRun.visibility = View.GONE
        }

        // 🟢 LOGIC BUTTON SIMPAN (ADD SAJA)
        binding.btnSaveRun.setOnClickListener {

            val runDate = binding.etDate.text.toString()
            val runDurationText = binding.etRunDuration.text.toString()
            val runDistanceText = binding.etRunDistance.text.toString()
            val note = binding.etNote.text.toString()

            if (runDate.isEmpty() || runDurationText.isEmpty() || runDistanceText.isEmpty()) {
                binding.etDate.error = "Tidak boleh kosong"
                return@setOnClickListener
            }

            val duration = runDurationText.toIntOrNull()
            val distance = runDistanceText.toIntOrNull()

            if (duration == null || distance == null) {
                binding.etRunDuration.error = "Harus angka"
                binding.etRunDistance.error = "Harus angka"
                return@setOnClickListener
            }

            if (duration <= 0) {
                binding.etRunDuration.error = "Harus lebih dari 0"
                return@setOnClickListener
            }

            if (distance <= 0) {
                binding.etRunDistance.error = "Harus lebih dari 0"
                return@setOnClickListener
            }

            val run = RunEntity(
                id = 0,
                runDate = runDate,
                runDistance = distance,
                runDuration = duration,
                runNote = note
            )

            runViewModel.insert(run)
            findNavController().popBackStack()
        }

        // 🔵 LOGIC BUTTON UPDATE (KHUSUS UPDATE)
        binding.btnUpdateRun.setOnClickListener {

            val runDate = binding.etDate.text.toString()
            val runDurationText = binding.etRunDuration.text.toString()
            val runDistanceText = binding.etRunDistance.text.toString()
            val note = binding.etNote.text.toString()

            val duration = runDurationText.toIntOrNull()
            val distance = runDistanceText.toIntOrNull()

            if (currentRun != null && duration != null && distance != null) {

                val updatedRun = currentRun!!.copy(
                    runDate = runDate,
                    runDistance = distance,
                    runDuration = duration,
                    runNote = note
                )

                runViewModel.update(updatedRun)
                findNavController().popBackStack()
            }
        }
    }
}
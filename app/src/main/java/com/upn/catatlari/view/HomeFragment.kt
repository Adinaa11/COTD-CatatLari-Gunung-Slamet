package com.upn.catatlari.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.upn.catatlari.R // 🔥 TAMBAHAN
import com.upn.catatlari.databinding.FragmentHomeBinding
import com.upn.catatlari.viewmodel.RunViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val runViewModel: RunViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val user = (activity as MainActivity).user
        binding.welcomingTxt.text = "Halo, ${user?.email}"

        // ➕ tombol tambah (FIX)
        binding.floatingBtnAddRun.setOnClickListener {
            findNavController().navigate(
                R.id.addRunFragment
            )
        }

        val runAdapter = RunAdapter(

            // DELETE
            onDeleteClick = { run ->
                runViewModel.delete(run)
            },

            // EDIT
            onItemClick = { run ->
                val bundle = Bundle()
                bundle.putParcelable("run", run)

                findNavController().navigate(
                    R.id.addRunFragment,
                    bundle
                )
            }
        )

        binding.rvRunList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRunList.adapter = runAdapter

        runViewModel.allRuns.observe(viewLifecycleOwner) { runList ->
            runAdapter.setData(runList)
        }

        return binding.root
    }
}
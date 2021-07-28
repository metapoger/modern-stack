package com.pogerapp.departments_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pogerapp.core.entity.State
import com.pogerapp.core.gone
import com.pogerapp.core.visible
import com.pogerapp.departments_list.databinding.FragmentDepartmentsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentsListFragment : Fragment() {

    private val viewModel by viewModels<DepartmentsListViewModel>()

    private val adapter = DepartmentsListAdapter {
//        findNavController().
    }

    lateinit var binding: FragmentDepartmentsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDepartmentsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.departmentsRV.adapter = adapter
        initObservers()
        viewModel.getDepartmentsList()
    }

    private fun initObservers(){
        viewModel.departmentsListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Loading -> {
                    binding.progressbar.visible()
                }
                is State.Success -> {
                    binding.progressbar.gone()
                    adapter.setData(it.success?:ArrayList())
                }
                is State.Error -> {
                    binding.progressbar.gone()
                    Toast.makeText(requireContext(), it.error?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
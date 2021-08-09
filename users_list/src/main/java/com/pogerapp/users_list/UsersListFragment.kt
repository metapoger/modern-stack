package com.pogerapp.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pogerapp.core.dp
import com.pogerapp.core.entity.State
import com.pogerapp.core.view.MarginDecoration
import com.pogerapp.users_list.databinding.FragmentUsersListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val viewModel by viewModels<UsersListViewModel>()

    private val args by navArgs<UsersListFragmentArgs>()
    private val departmentId by lazy{
        args.departmentId
    }

    private val adapter = UsersListAdapter{

    }

    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.usersListRV.addItemDecoration(MarginDecoration(16.dp))
        binding.usersListRV.adapter = adapter
        viewModel.getUsers(departmentId)
        observe()
    }

    private fun observe(){
        viewModel.usersLiveData.observe(viewLifecycleOwner){
            when(it) {
                is State.Success -> {
                    adapter.setUsers(it.success!!)
                }
                is State.Error -> {

                }
            }
        }
    }
}
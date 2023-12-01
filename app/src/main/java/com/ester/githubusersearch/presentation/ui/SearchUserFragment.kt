package com.ester.githubusersearch.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ester.githubusersearch.R
import com.ester.githubusersearch.databinding.FragmentSearchUserBinding
import com.ester.githubusersearch.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchUserFragment : Fragment(), SearchView.OnQueryTextListener, UserListAdapter.OnItemClickListener {

    private val viewModel: UserViewModel by viewModels()
    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setSearchView()
        setAdapter()
        userListAdapter.setListener(this)
    }

    private fun setSearchView() {
        binding.svUsername.setOnQueryTextListener(this)
        binding.svUsername.onActionViewExpanded()
        binding.svUsername.setIconifiedByDefault(true)
        binding.svUsername.isFocusable = true
        binding.svUsername.isIconified = false
        binding.svUsername.requestFocusFromTouch()
        binding.svUsername.queryHint = resources.getString(R.string.text_search_by_username)
    }

    private fun setAdapter() {
        userListAdapter = UserListAdapter()
        binding.rvUserList.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.search(query, 1, 50)
            viewModel.userSearchList.observe(viewLifecycleOwner) { searchResult ->
                searchResult?.let {
                    if (it.totalCount > 0) {
                        binding.tvErrorMessage.visibility = View.GONE
                        userListAdapter.submitData(it.items)
                    } else {
                        binding.tvErrorMessage.visibility = View.VISIBLE
                        binding.rvUserList.visibility = View.GONE
                    }
                }
            }
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onItemClicked(username: String) {
        navController.navigate(SearchUserFragmentDirections.actionSearchUserFragmentToUserDetailFragment(username))
    }
}
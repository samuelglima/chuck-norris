package com.example.chucknorris.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chucknorris.databinding.HomeFragmentBinding
import com.example.chucknorris.ui.home.adapter.HomeAdapter
import com.example.chucknorris.util.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var binding: HomeFragmentBinding
    private val mViewModel: HomeViewModel by viewModel()
    private val homeAdapter by lazy { HomeAdapter(::onItemClick) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = HomeFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel
        }
        return binding.root
    }

    private fun onItemClick(item: String) {
        mViewModel.setSelectedCategory(item) {
            findNavController().safeNavigate(HomeFragmentDirections.actionHomeFragmentToJokeFragment(item))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        mViewModel.get()
        initObservers()

    }

    private fun initView() {
        binding.rcLista.adapter = homeAdapter
    }

    private fun initObservers() {
        mViewModel.listCategories.observe(viewLifecycleOwner, Observer {
            homeAdapter.submitList(it)
        })
    }


}
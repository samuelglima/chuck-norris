package com.example.chucknorris.ui.joke

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.chucknorris.R
import com.example.chucknorris.databinding.HomeFragmentBinding
import com.example.chucknorris.databinding.JokeFragmentBinding
import com.example.chucknorris.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class JokeFragment : Fragment() {

    private val args: JokeFragmentArgs  by navArgs()
    private lateinit var binding: JokeFragmentBinding
    private val mViewModel: JokeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = JokeFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel
        }
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        args.category?.let {
            mViewModel.get(it)
        }
        initObservers()

    }

    private fun initView() {
        binding.tvCatedory.text = args.category.toUpperCase()

        binding.btNova.setOnClickListener {
            args.category?.let {
                mViewModel.get(it)
                binding.tvMessage.visibility = View.GONE
            }
        }
    }

    private fun initObservers() {
        mViewModel.joke.observe(viewLifecycleOwner, Observer { joke ->
            joke?.let {
                if (!it.value.isNullOrEmpty()) {
                    binding.tvMessage.text = it.value
                    binding.tvMessage.visibility = View.VISIBLE
                } else {
                    binding.tvMessage.text = "The server was slow to respond. Try again later."
                }
            }
        })
    }

}
package com.capstone.afeed.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.adapter.ArticleAdapter
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.databinding.FragmentHomeBinding
import com.capstone.afeed.ui.authentication.AuthenticationActivity
import com.capstone.afeed.ui.bottomsheet.ModalBottomSheet

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding
        get() = _binding ?: throw IllegalStateException(getString(R.string.illegal_state_exception))

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory.getInstance(requireContext())
    }

    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleAdapter()

        setupView()
        observeNews()
        setupAdapterToView()
    }

    private fun setupView() {
        with(binding) {
            infoHomeSection.btnElevatedSignNowButton.setOnClickListener {
                val intent =
                    Intent(requireContext(), AuthenticationActivity::class.java)
                startActivity(intent)
            }
            btnAFeeding.setOnClickListener {
                ModalBottomSheet(1).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnCustomizeIot.setOnClickListener {
                ModalBottomSheet(2).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnPhMonitoring.setOnClickListener {
                ModalBottomSheet(3).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnTemperatureMonitoring.setOnClickListener {
                ModalBottomSheet(4).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
        }
    }

    private fun setupAdapterToView() {
        with(binding) {
            rvArticles.apply {
                adapter = articleAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun observeNews() {
        homeViewModel.getAllNews().observe(requireActivity()) { result ->
            when (result) {
                is ResponseState.Error -> {
                    binding.progressCircularArticle.visibility = View.GONE
                    showError(result.error)
                }

                is ResponseState.Loading -> {
                    binding.progressCircularArticle.visibility = View.VISIBLE
                }

                is ResponseState.Success -> {
                    binding.progressCircularArticle.visibility = View.GONE
                    articleAdapter.submitList(result.data.articles)
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
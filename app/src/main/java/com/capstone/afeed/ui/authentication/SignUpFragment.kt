package com.capstone.afeed.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.FragmentSignUpBinding
import com.capstone.afeed.ui.dialog.ErrorDialogFragment
import com.capstone.afeed.ui.dialog.SuccessDialogFragment

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException(getString(R.string.illegal_state_exception))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val signIn = LoginFragment()
        val fragmentManager = parentFragmentManager
        with(binding) {
            tvSignIn.setOnClickListener {
                fragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_auth, signIn, LoginFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            }
            btnSignUpSave.setOnClickListener {
                SuccessDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    ErrorDialogFragment.TAG
                )
                fragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_auth, signIn, LoginFragment::class.java.simpleName)
                    commit()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
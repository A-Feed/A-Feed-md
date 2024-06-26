package com.capstone.afeed.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.FragmentLoginBinding
import com.capstone.afeed.ui.dialog.ErrorDialogFragment
import com.capstone.afeed.ui.dialog.SuccessDialogFragment
import com.capstone.afeed.ui.main.MainActivity

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException(getString(R.string.illegal_state_exception))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnLoginSignIn.setOnClickListener {
                SuccessDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    ErrorDialogFragment.TAG
                )
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            signUpHere.setOnClickListener {
                val signUpFragment = SignUpFragment()
                val fragmentManager = parentFragmentManager
                fragmentManager.beginTransaction().apply {
                    replace(
                        R.id.fragment_auth,
                        signUpFragment,
                        SignUpFragment::class.java.simpleName
                    )
                    addToBackStack(null)
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
package com.capstone.afeed.ui.fishpondform.editfishpondform

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.databinding.FragmentEditFishPondFormBinding
import com.capstone.afeed.ui.dialog.ErrorDialogFragment
import com.capstone.afeed.ui.dialog.FishpondEditOrDeleteDialogFragment
import com.capstone.afeed.ui.dialog.SuccessDialogFragment
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.capstone.afeed.ui.monitoring.RegisteredFishpondAdapter

class EditFishPondFormFragment : Fragment() {
    private var _binding: FragmentEditFishPondFormBinding? = null
    private val binding get() = _binding!!
    private val fishPondFormViewModel: FishPondFormViewModel by activityViewModels {
        FishPondFormViewModelFactory.getInstance(
            requireContext()
        )
    }
    private lateinit var registeredFishpondAdapter: RegisteredFishpondAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditFishPondFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupAdapter()
        setupRecylerView()

    }

    private fun setupObserver() {
        fishPondFormViewModel.getListFishPond().observe(requireActivity()) {
            with(binding) {
                when (it) {
                    is ResponseState.Error -> {

                    }

                    ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        registeredFishpondAdapter.submitList(it.data.data)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        registeredFishpondAdapter = RegisteredFishpondAdapter()
        registeredFishpondAdapter.addNewListener =
            object : RegisteredFishpondAdapter.AddNewListener {
                override fun onClickItem(item: GetListFishPondResponse.Pond) {
                    val fishpondEditOrDeleteDialogFragment = FishpondEditOrDeleteDialogFragment()
                    val args = Bundle()
                    args.putString(FishpondEditOrDeleteDialogFragment.TITLE_CARD_EXTRAS,item.fishpondName)
                    fishpondEditOrDeleteDialogFragment.arguments = args
                    fishpondEditOrDeleteDialogFragment.addNewListener = object :
                        FishpondEditOrDeleteDialogFragment.AddNewListener {
                        override fun onClickEdit() {
                            fishPondFormViewModel.setFishPondId(item.fishpondId.toInt())
                            fishPondFormViewModel.initializeDataForEditMode(item.fishpondId.toInt())
                                .observe(requireActivity()) {
                                    when (it) {
                                        is ResponseState.Error -> {

                                        }

                                        ResponseState.Loading -> {

                                        }

                                        is ResponseState.Success -> {
                                            fishPondFormViewModel.populateDataForEditMode(it.data)
                                            fishPondFormViewModel.fishpondData.observe(requireActivity()){
                                                Log.i("datasfishpond",it.toString())
                                            }
                                            findNavController().navigate(EditFishPondFormFragmentDirections.actionEditFishPondFormFragmentToFishpondFormNavigation())
                                            fishpondEditOrDeleteDialogFragment.dismiss()
                                        }
                                    }
                                }
                        }

                        override fun onClickDelete() {
                            fishPondFormViewModel.postRegisterFishPondDelete(item.fishpondId.toInt()).observe(requireActivity()){ data ->
                                when(data){
                                    is ResponseState.Error -> {
                                        showErrorDialog(data.error)
                                    }
                                    ResponseState.Loading -> {
//                                        progressBarFishpondSaveProgressBar.visibility = View.VISIBLE
                                    }
                                    is ResponseState.Success -> {
                                        showSuccessDialog(data.data.status,data.data.message)
                                        setupObserver()
                                    }
                                }
                            }
                            fishpondEditOrDeleteDialogFragment.dismiss()
                        }
                    }
                    fishpondEditOrDeleteDialogFragment.show(requireActivity().supportFragmentManager,FishpondEditOrDeleteDialogFragment.TAG)
                }

            }
    }

    private fun showErrorDialog( msg : String){
//        binding.progressBarFishpondSaveProgressBar.visibility = View.GONE
        val errorDialog =  ErrorDialogFragment()
        val args = Bundle()
        args.putString(ErrorDialogFragment.MESSAGE_DESCRIPTION, msg.toString())

        errorDialog.arguments = args

        errorDialog.show(
            requireActivity().supportFragmentManager,
            ErrorDialogFragment.TAG
        )

    }

    private fun showSuccessDialog(msgTittle : String, msg : String){
//        binding.progressBarFishpondSaveProgressBar.visibility = View.GONE
        val successDialog =  SuccessDialogFragment()
        val args = Bundle()
        args.putString(SuccessDialogFragment.MESSAGE_TITLE, msgTittle.toString())
        args.putString(SuccessDialogFragment.MESSAGE_DESCRIPTION, msg.toString())

        successDialog.arguments = args

        successDialog.show(
            requireActivity().supportFragmentManager,
            SuccessDialogFragment.TAG
        )

    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewRegisteredFishpond.apply {
                adapter = registeredFishpondAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL, false
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
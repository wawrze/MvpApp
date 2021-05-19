package com.wawra.mvpapp.ui.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wawra.mvpapp.databinding.FragmentSampleBinding
import com.wawra.mvpapp.domain.models.SampleModel
import com.wawra.mvpapp.presentation.main.SamplePresentationModel
import com.wawra.mvpapp.presentation.main.SamplePresenter
import com.wawra.mvpapp.presentation.main.SampleView
import com.wawra.mvpapp.ui.base.BaseFragment

typealias BaseMvpFragment = BaseFragment<SamplePresentationModel, SampleView, SamplePresenter>

class SampleFragment : SampleView, BaseMvpFragment() {

    private var binding: FragmentSampleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun showSuccess() {
        Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_LONG).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
    }

    override fun createPresentationModel() = SamplePresentationModel(
        SampleModel(1L, "name")
    )
}

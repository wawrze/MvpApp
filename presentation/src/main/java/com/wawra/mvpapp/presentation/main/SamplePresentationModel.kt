package com.wawra.mvpapp.presentation.main

import com.wawra.mvpapp.domain.models.SampleModel
import java.io.Serializable

data class SamplePresentationModel(
    val sampleModel: SampleModel
) : Serializable

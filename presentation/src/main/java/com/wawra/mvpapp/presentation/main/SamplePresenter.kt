package com.wawra.mvpapp.presentation.main

import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.base.BasePresenter
import javax.inject.Inject

typealias BaseMvpPresenter = BasePresenter<SamplePresentationModel, SampleView>

class SamplePresenter @Inject constructor(
    private val useCaseFactory: UseCaseFactory
) : BaseMvpPresenter() {

    override fun start(view: SampleView, presentationModel: SamplePresentationModel) {
        super.start(view, presentationModel)
        useCaseFactory.provideGetAndSaveSampleModelUseCase().execute()
            .subscribe(
                {
                    if (it) view.showSuccess() else view.showError(Exception())
                },
                { view.showError(it) }
            )
            .addToDisposables()
    }
}

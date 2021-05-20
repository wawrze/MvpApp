package com.wawra.mvpapp.ui.base

import android.os.Bundle
import java.io.Serializable

class PresentationModelSerializer<M> {

    @Suppress("UNCHECKED_CAST")
    fun restorePresentationModel(
        savedInstanceState: Bundle?,
        presentationModelKey: String
    ): M? {
        if (savedInstanceState != null) {
            val potentialPresentationModel = savedInstanceState.get(presentationModelKey)
            try {
                return potentialPresentationModel as M
            } catch (ex: ClassCastException) {
                throw IllegalStateException(
                    "We expected a presentationModel saved in the bundle under the key: " +
                            "\"$presentationModelKey\", " +
                            "but was: $potentialPresentationModel"
                )
            }
        }
        return null
    }

    fun savePresentationModel(
        outState: Bundle,
        presentationModelKey: String,
        presentationModel: M
    ) {
        when (presentationModel) {
            is Serializable -> outState.putSerializable(
                presentationModelKey,
                presentationModel as Serializable
            )
            else -> throw IllegalArgumentException(
                "Your presentation model must either implement Parcelable or Serializable: " +
                        "${(presentationModel as Any)::class.qualifiedName}"
            )
        }
    }
}

package com.dawoud.elarabychallenge.domain.model.searchScreen

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.dawoud.elarabychallenge.BR
import com.google.gson.annotations.SerializedName

class SearchRequist :BaseObservable() {
    @get:Bindable
    @SerializedName("query")
    var query: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.query)
        }
}
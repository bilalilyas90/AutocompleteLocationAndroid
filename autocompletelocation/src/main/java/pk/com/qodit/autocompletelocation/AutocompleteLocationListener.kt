package pk.com.qodit.autocompletelocation

import com.google.android.libraries.places.api.model.AutocompletePrediction

interface AutocompleteLocationListener {
    fun onTextClear()

    fun onItemSelected(selectedPlace: AutocompletePrediction?)
}

package pk.com.qodit.autocompletelocation;

import com.google.android.libraries.places.api.model.AutocompletePrediction;

import java.util.List;

public interface OnSearchListener {
    void onSearch(String address, List<AutocompletePrediction> predictions);
}

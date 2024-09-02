package pk.com.qodit.autocompletelocation;


import androidx.annotation.NonNull;

import com.google.android.libraries.places.api.model.Place;

public interface OnPlaceLoadListener {
    void onPlaceLoad(@NonNull Place place);
}

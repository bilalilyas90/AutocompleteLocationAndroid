# AutocompleteLocationAndroid

Note: This library is version of jesualex/AutocompleteLocationAndroid on mavenCentral repo.

Kotlin library to implement PlaceAutocomplete in Android

<img src="art/init.png" width="200px" height="356px" />
<img src="art/autocomplete.png" width="200px" height="356px" />
<img src="art/place.png" width="200px" height="356px" />

## First Steps

In your Project build.gradle you must have the following

``` gradle
buildscript {
    ...
    repositories {
        ...
        mavenCentral()
    }
    ...
    dependencies {
        ...
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
    ...
}
```

In your App build.gradle you must have the following

``` gradle
...
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
...
dependencies {
    ...
    implementation "pk.com.qodit.autocompletelocation:autocompletelocation:$last_version"
}
```

**Get a Google Maps API Key and enabled the Google Places API for Android** *(Add your API Key in values)*:

```xml
  <resources>
    <string name="google_places_key"
        translatable="false"
        templateMergeStrategy="preserve">YOUR_KEY_HERE</string>
  </resources>
```
**Add the AutocompleteLocation into the layout:**
```xml
  <pk.com.qodit.autocompletelocation.AutocompleteLocation
      android:id="@+id/autocomplete_location"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"/>
```
**Set the listeners(All are optional for more comfort):** 
```java
public class MainActivity extends FragmentActivity
    implements AutocompleteLocation.AutocompleteLocationListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    ...
    AutocompleteLocation autoCompleteLocation =
        (AutocompleteLocation) findViewById(R.id.autocomplete_location);
        autoCompleteLocation.setAutoCompleteTextListener(this);
        autoCompleteLocation.setOnSearchListener(this);
  }
}
```
**AutoCompleteText Listener:**
```java
  @Override public void onTextClear() {
    mMap.clear();
  }

  @Override public void onItemSelected(AutocompletePrediction selectedPrediction) {
    Log.i(getClass().getSimpleName(), "A autocomplete has selected: ");
    logAutocomplete(selectedPrediction);
  }
```

**Place Listener(When this listener is set, after selecting a prediction, the associated place is automatically searched):**
```java
    ...
    AutocompleteLocation autoCompleteLocation =
        (AutocompleteLocation) findViewById(R.id.autocomplete_location);
    ...
    //Set placeListener to auto calculate Place object when a AutocompletePrediction has selected.
    autoCompleteLocation.setPlaceListener(this);
    
    //Optional set placeFields to receive only the fields what you need. By default return ID, NAME and LAT_LNG.
    autoCompleteLocation.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME););
```

```java
  @Override public void onPlaceLoad(@NonNull Place place) {
    addMapMarker(place.getLatLng());
  }
```

**Search Listener:**
```java
  @Override public void onSearch(String address, List<AutocompletePrediction> predictions) {
    for (AutocompletePrediction prediction : predictions) {
      logAutocomplete(prediction);
    }
  }
```

**Limit results by Country:**
```java
    ...
    AutocompleteLocation autoCompleteLocation =
        (AutocompleteLocation) findViewById(R.id.autocomplete_location);
    ...
    autoCompleteLocation.setCountry("Au");
```

```xml
  <pk.com.qodit.autocompletelocation.AutocompleteLocation
      android:id="@+id/autocomplete_location"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:countryCode="au" />
```

**Limit results by Bounds:**
```java
    ...
    AutocompleteLocation autoCompleteLocation =
        (AutocompleteLocation) findViewById(R.id.autocomplete_location);
    ...
    RectangularBounds bounds = RectangularBounds.newInstance(
            new LatLng(-33.880490, 151.184363),
            new LatLng(-33.858754, 151.229596)
    );
    
    autoCompleteLocation.setLocationBias(bounds);
```

**Get Place by Id:**
```java
    PlaceUtils.getPlace(context, "placeId", new OnPlaceLoadListener() {
      @Override
      public void onPlaceLoad(@NonNull Place place) {
        addMapMarker(place.getLatLng());
      }
    });
```

# Style it!
Attributes for custom AutocompleteLocation
* __background_layout__
* __closeIcon__

```xml
  <pk.com.qodit.autocompletelocation.AutocompleteLocation
      android:id="@+id/autocomplete_location"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:background_layout="@drawable/bg_rounded_accent"
      app:closeIcon="@drawable/ic_close" />
```

## Example

To get a small example, please [download this repo](/) add your Google API Key in values and execute it

# License
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

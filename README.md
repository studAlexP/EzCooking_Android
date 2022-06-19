# EzCooking

EzCooking is an Android App which searches recipes based on spoken ingredients from the EzCooking
Smart Scale

## Getting Started

These instructions will give you a copy of the project up and running on your local machine for
development and testing purposes.

### Prerequisites

To fully use this app the EzCooking Smart Scale is needed and a!

- [EzCooking_Smart_Scale](https://github.com/studAlexP/EzCooking_Device)
- [TheMealDB API Key](https://www.themealdb.com/api.php)

### Installing

To clone and run this application, you'll need [Git](https://git-scm.com/)
and [Android Studio](https://developer.android.com/studio/) (which comes with the necessary tools to
run this app) installed on your system. From your command line:

```
# Clone this repository

$ git clone https://github.com/studAlexP/EzCooking_Android.git
```

To run this app a Constants.kt file, which contains the URLs and API Key, is needed. From your
command line:

```
# Create Constants.kt file

$ cd EzCooking_Android/app/src/main/java/com/example/ezcooking

$ mkdir utils

$ touch Constans.kt
```
The Constants.kt file should look like the following:
```
package com.example.ezcooking.utils

object Constants {
    const val SCALE_URL = PERSONAL_SCALE_URL
    private const val API_KEY = API_KEY
    const val MEAL_URL_INGREDIENTS =
        "https://www.themealdb.com/api/json/v2/$API_KEY/filter.php"
    const val MEAL_URL_ID =
        "https://www.themealdb.com/api/json/v2/$API_KEY/lookup.php"
}
```

## Authors

- **Alexander Pruka** - *Backend Engineer* -
  [studAlexP](https://github.com/studAlexP)
- **Bozana Markulj** - *Backend Engineer and Frontend Assistance* -
  [BozanaMa](https://github.com/BozanaMa)
- **Sahani Peiris** - *Frontend Desinger* -
  [Sahani97](https://github.com/Sahani97)


## Acknowledgments

- Thanks to TheMealDB for providing us the API

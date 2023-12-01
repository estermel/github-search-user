# github-search-user
Demo app for searching user in GitHub with GitHub API


### run the app
- clone this project from `master` branch by opening your terminal and type:
 
  `git clone https://github.com/estermel/github-search-user.git`
- open this project in Android Studio
- rebuild the project
- run the project
- this project can both be running on emulator or real device


### android SDK
- android minSdk 24
- android compileSdk & targetSdk 34


### APIs used
- base_url : `https://api.github.com`
- search user: GET `{base_url}/search/users?q={login}&page={page_number}&per_page={total_search_result}`
- get user detail: GET `{base_url}/users/{user}`


### architecture pattern
MVVM (Model-View-ViewModel)


### tech stack
- kotlin
- hilt
- coroutines
- glide
- androidx
- retrofit
- okhttp
- moshi
- material
- navigation
- live data
- room


### limitations
- only get 1 page, with 50 search results (users) in 1 page
- not covered with unit testing yet
- no infinite scroll
- no chucker usage

# MySoothe

![Workflow result](https://github.com/matiascalvo/compose-mysoothe/workflows/CI/badge.svg)


## :bulb: Motivation and Context
This is an app made to test Jetpack compose.

The design of this app comes from an
old [Android Dev Challenge](https://android-developers.googleblog.com/2021/03/android-dev-challenge-3.html) back in
March 2021

## Development

* UI written in [Jetpack Compose](https://developer.android.com/jetpack/compose).
* Uses [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html).
* Uses [Hilt](https://dagger.dev/hilt/) for dependency injection.
* Uses [Gradle version catalog TOML file](https://docs.gradle.org/current/userguide/platforms.html).
* This project uses [detekt](https://detekt.dev/) as static code analysis tool and 
  [spotless](https://github.com/diffplug/spotless) for checking code style 
  (Kotlin with [ktlint](https://github.com/pinterest/ktlint), XMLs and Gradle Files).
 * Uses [Github Actions](https://github.com/features/actions) as CI/CD.

## :camera_flash: Screenshots

### 🌞 Light Mode
Welcome | Log in | Home
--- | --- | --- |
<img src="/screenshots/welcome_light.png" width="260"> | <img src="/screenshots/login_light.png" width="260"> | <img src="/screenshots/home_light.png" width="260">

### 🌚 Dark Mode
Welcome | Log in | Home
--- | --- | --- |
<img src="/screenshots/welcome_dark.png" width="260"> | <img src="/screenshots/login_dark.png" width="260"> | <img src="/screenshots/home_dark.png" width="260">

## Using the app

Valid credentials:

Email | Password
--- | --- |
test@test.com | test
your@email.com | your
a@a.com | aaaa

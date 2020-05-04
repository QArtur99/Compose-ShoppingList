# Compose-ShoppingList
Compose-ShoppingList is a sample project that presents a way of implementing UI components with Jetpack Compose.

### Screenshots
![image](https://user-images.githubusercontent.com/25232443/81019836-c9677e00-8e67-11ea-9bab-ed997b149e21.jpg)

### Used Tech
* [Kotlin](https://kotlinlang.org/)
* [MVVM](https://developer.android.com/jetpack/docs/guide)
* [Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI.
* [Ambient](https://developer.android.com/reference/kotlin/androidx/compose/Ambient) - Compose passes data through the composition tree.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Provide sophisticated tools to handle concurrency.
* [Declarative UI Patterns](https://www.youtube.com/watch?v=VsStyq4Lzxo)
* [Koin](https://insert-koin.io) - Runtime framework for dependency injection.
* [Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
* [Room](https://developer.android.com/topic/libraries/architecture/room) - Access your app's SQLite database with in-app objects and compile-time checks.
* [Test](https://developer.android.com/training/testing/) - An Android testing framework for unit and runtime UI tests.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks.
* [ktlint](https://ktlint.github.io/) - Enforce Kotlin coding styles.

### Features
* Handle back press
* Handle configuration changes
* ActionBar menu
* Drawer menu
* Add shopping lists/products
* Archive/Unarchive lists
* Delete products

### Report issues
Something not working quite as expected? Do you need a feature that has not been implemented yet? Check the [issue tracker](https://github.com/QArtur99/Compose-ShoppingList/issues) and add a new one if your problem is not already listed. Please try to provide a detailed description of your problem, including the steps to reproduce it.

### Contribute
Awesome! If you would like to contribute with a new feature or submit a bugfix, fork this repo and send a pull request. Please, make sure all the [unit tests](https://github.com/QArtur99/Compose-ShoppingList/tree/master/app/src/test/java/com/artf/shoppinglistcompose), [integration tests](https://github.com/QArtur99/Compose-ShoppingList/tree/master/app/src/androidTest/java/com/artf/shoppinglistcompose)  & `./gradlew spotlessApply` are passing before submitting and add new ones in case you introduced new features.

### How to run the project in development mode
* Clone or download repository as a zip file.
* Open project in Android Studio.
* Run 'app' `SHIFT+F10`.

#### Android Studio IDE setup 
Compose-ShoppingList uses [ktlint](https://ktlint.github.io/) to enforce Kotlin coding styles.
Here's how to configure it for use with Android Studio (instructions adapted from the ktlint [README](https://github.com/shyiko/ktlint/blob/master/README.md)):
* Close Android Studio if it's open
* Download ktlint using these [installation instructions](https://github.com/shyiko/ktlint/blob/master/README.md#installation)    
* Inside the project root directory run: `./ktlint --apply-to-idea-project --android`    
* Remove ktlint if desired: `rm ktlint`
* Start Android Studio

### License
Compose-ShoppingList is released under the MIT license. See [LICENSE](./LICENSE) for details.
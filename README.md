## Mercado Libre Code Challenge
This repository contains code challenge from Mercado Libre, created by Mario Valdez.

## Description

This application works like Mercado Libre app for search products.

The main screen has an input search and a list of categories to search more products(logic will be add in other time), it works when you type in the input the product that you want to search(query).

The product list screen shows the list of products found, you can see the image of the product, name, price, discount, months interest-free quantity and amount, name of the seller and you can click into the product to show details. If you want you can search on product again cause you have an input search in your view.

In the product detail screen you only can see the product image, name, discount, original price, price with discount if is the case, discount, months interest-free quantity and amount.

## Endpoints used
- The site used is MLM for Mercado Libre Mexico
- /sites/$site/search?q=$query : Used to search a list of products that match with the query keyword.
- /sites/$site/categories : Used to search a list of categories in ML.

## Permissions of the App
- Internet

## Architecture
The application implements a [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), with the aim of:
- **Independency of frameworks:** The architecture doesn't depend on the existence of some library of feature laden software.
- **Testability:** The business rules can be tested without the UI, Database, Web Server, or any other external element.
- **Independency of UI:** The UI can change easily, without changing the rest of the system, e.g. Jetpack Compose.
- **Independency of Database:** The business rules are not bound to the database.
- **Independency of any external agency:** The business rules simply don't know anything at all about the outside world.

The following diagram describes in depth the concrete implementation of our architecture:
![Architecture](https://github.com/MarioValdezDev/meli_code_challenge/blob/main/images/architecture.png)

## Tech Stack
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
    - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- [Ktlint](https://ktlint.github.io/) - Kotlin linter for format standardization and code inspection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Android Studio](https://developer.android.com/studio) - IDE version Android Studio Flamingo | 2022.2.1 patch 2
- [Unit Test](https://junit.org/) For Unit Testing
- [Expresso](https://developer.android.com/training/testing/espresso?hl=es-419) For UI Testing


## Git Branch Strategy
We used [Trunk-based development (TBD)](https://launchdarkly.com/blog/introduction-to-trunk-based-development/) as the branching strategy.

The idea of this strategy is that all developers integrate their changes directly to a shared trunk every day (in our case, our `main` branch), a shared trunk that is always in a releasable state. No matter what a developer might do on their local repository, at least once each day, they must integrate their code. This practice forces each developer to regularly see and react to the changes being made by their teammates in version control, which drives collaboration around the quality and state of the codebase as a near-constant activity.

### Branch Naming
In order to have a great administration with our branches, we have established a convention that will allow us to identify them more quickly, the structure is as follows:

    type/username/short-description

Where
- `type`: The type of contribution you are about to make, we have at least 4.
    - `core`: Important changes to the project, an update in some fundamental part of the application, change in some configuration or addition of a specific library.
    - `feature`: Any new development that adds functionality to the project.
    - `fix`: The solution to a bug.
    - `release`: When bumping the `versionCode` and the `versionName` for the signature of a productive build.
- `username`: Your GitHub username.
- `short-description`: A brief description separated by `-` that help us to identify the objective of this branch.

**e.g.**

    feature/mariovaldezdev/add-new-feature

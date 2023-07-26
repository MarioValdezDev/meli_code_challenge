## Mercado Libre Code Challenge
This repository contains code challenge from Mercado Libre, created by Mario Valdez.

## Architecture
The application implements a [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), with the aim of:
- **Independency of frameworks:** The architecture doesn't depend on the existence of some library of feature laden software.
- **Testability:** The business rules can be tested without the UI, Database, Web Server, or any other external element.
- **Independency of UI:** The UI can change easily, without changing the rest of the system, e.g. Jetpack Compose.
- **Independency of Database:** The business rules are not bound to the database.
- **Independency of any external agency:** The business rules simply don't know anything at all about the outside world.

The following diagram describes in depth the concrete implementation of our architecture:


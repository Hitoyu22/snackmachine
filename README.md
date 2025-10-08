# Rover Examen Project

This project has been migrated from Maven to Gradle.

## Completing the Gradle Setup

To complete the Gradle setup, you need to download the Gradle Wrapper JAR file:

1. Download the Gradle Wrapper JAR file from the official Gradle repository:
   https://github.com/gradle/gradle/raw/v7.6.0/gradle/wrapper/gradle-wrapper.jar

2. Place the downloaded file in the `gradle/wrapper/` directory of this project.

3. After placing the file, you can run Gradle commands using the wrapper:
   - On Windows: `.\gradlew.bat build`
   - On Unix-like systems: `./gradlew build`

## Building the Project

Once the Gradle Wrapper is set up, you can build the project using:

```
./gradlew build
```

## Running the Application

To run the Spring Boot application:

```
./gradlew bootRun
```

## Running Tests

To run tests:

```
./gradlew test
```

## Project Structure

This is a Spring Boot application with a React frontend.

- Backend: Java 17 with Spring Boot 2.7.5
- Frontend: React (separate Vite project under `frontend/`) and some legacy Thymeleaf templates

## Frontend (React + Vite + Bulma)

A standalone React app lives in `frontend/`. It uses Vite and Bulma CSS (via CDN).

Commands (run from the `frontend/` directory):

- Install deps: `npm install`
- Start dev server: `npm run dev`
- Build for production: `npm run build`
- Preview production build: `npm run preview`

The app renders a single page with a scrollable list of Bulma cards.


<a href="https://www.flaticon.com/fr/icones-gratuites/monstre" title="monstre icônes">Monstre icônes créées par Freepik - Flaticon</a>
<a href="https://www.flaticon.com/fr/icones-gratuites/alienation" title="aliénation icônes">Aliénation icônes créées par Vector Stall - Flaticon</a>
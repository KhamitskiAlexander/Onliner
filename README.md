# Mobile framework

## Table of contents

* [Environment Settings](#environment-settings)
    * [General](#general)
* [How to run tests](#how-to-run-tests)

## Environment Settings

### General

1. Java 11 or later
2. Git
3. Gradle

## How to run tests

1. Open desktop Docker app
2. From command line from project's root:
    ``docker-compose up -d``
![Docker Desktop](images/DockerDesktop.png)


3. From command line from project's root:
   - For local run: ``./gradlew cleanUp executeUITests`` 
   - For remote run: ``./gradlew cleanUp executeUITests -Dbrowser='remote'``
   You can open Selenoid Grid on the next local host: http://localhost:4444/ui#/sessions
![Selenium Grid](images/SeleniumGrid.png)


4. Generate Allure Report:
    ``./gradlew allureReport``
   You can open Allure Report here: ``build/reports/allure-report/allureReport/index.html``
![Allure Report](images/AllureReport.png)

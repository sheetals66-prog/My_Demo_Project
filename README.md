# MyDemoMyntra — Automation Framework for Myntra

This repository contains the MyDemoMyntra automation framework (MyntraOriginal). It is a Selenium + Cucumber + TestNG based test automation project built with Maven.

## Quick summary
- Java 21, Maven, Selenium 4, Cucumber 7, TestNG, Allure reporting, Log4j2 for logging, AShot for screenshots and Apache POI for Excel support.
- Feature files (Cucumber) are located under `src/test/resources/features`.
- Test/Runners are under `src/test/java` (maven-surefire is configured to include `**/*Runner.java`).

## Prerequisites
- Java JDK 21 installed and JAVA_HOME set.
- Maven (3.6+) installed and on PATH.
- A browser driver (ChromeDriver/GeckoDriver) available or a driver manager in the project. If the framework uses a driver-manager library, it will download drivers automatically.
- (Optional) Allure CLI installed to view reports locally (`allure` command). The project includes the Allure Maven plugin which can also serve reports.

## Build & run tests
Open a command prompt in the project root (`c:\Users\Rahul\eclipse-workspace\MyntraOriginal`) and run:

- Run full test suite (default):

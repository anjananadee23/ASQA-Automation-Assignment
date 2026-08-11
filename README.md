# AutomationExercise.com – Selenium + TestNG + POM Automation

Automates 5 scenarios (mapped to the site's own official test case numbers at
https://automationexercise.com/test_cases) against https://automationexercise.com/:

| Test Class | Scenario | Site's Official Test Case |
|---|---|---|
| RegistrationTests | TC02 – Register with valid details | Test Case 1: Register User |
| LoginTests | TC04 – Valid login | Test Case 2: Login User with correct email and password |
| LoginTests | TC05 – Invalid login | Test Case 3: Login User with incorrect email and password |
| SearchTests | TC08 – Search product by keyword | Test Case 9: Search Product |
| CartTests | TC11 – Add product to cart | Test Case 12: Add Products in Cart |

## Prerequisites
- Java 17+ (`java -version`)
- Maven 3.8+ (`mvn -version`)
- Google Chrome installed (WebDriverManager auto-downloads the matching ChromeDriver — needs internet on first run)

## Setup (IntelliJ / Eclipse)
Open this folder as a Maven project — the IDE detects `pom.xml` automatically.

## Setup (VS Code)
1. Install the "Extension Pack for Java" (includes Maven + Test Runner for Java support).
2. File → Open Folder → select this folder. Wait for Java/Maven to finish indexing (bottom-right progress).
3. Run tests from the terminal (see below), or use the "Run Test" CodeLens links that appear above each `@Test` method.

## Before your first run
Open `src/test/java/tests/LoginTests.java` and replace `VALID_EMAIL` / `VALID_PASSWORD`
with an account that actually exists. Easiest approach: run `RegistrationTests` once,
note the generated email from the console, and reuse it here.

## Run all tests
```bash
mvn clean test
```

## Run a single class
```bash
mvn test -Dtest=SearchTests
```

## Reports
- TestNG's default HTML report: `test-output/index.html`
- Screenshots of failed tests: `test-output/screenshots/`

## Debugging Challenge (Step 7)
`SignupLoginPage.java` contains the login button locator
`By.cssSelector("button[data-qa='login-button']")`. To reproduce the assignment's
debugging exercise:
1. Temporarily change it to an invalid value, e.g. `"button[data-qa='loginBtn']"`.
2. Run `mvn test -Dtest=LoginTests` and observe the `NoSuchElementException` / `TimeoutException`.
3. Capture a screenshot of the failure.
4. Revert the locator to the correct value, re-run, and capture the passing result.

## Notes on Locators
AutomationExercise.com is purpose-built for automation practice and documents stable
`data-qa` attributes on its key form elements, which this framework relies on. Always
**Inspect Element** on the live page before your final run in case the markup has
changed since this project was written.

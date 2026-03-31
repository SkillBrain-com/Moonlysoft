# Java Serenity BDD Framework

Test Automation Framework built with **Serenity BDD**, **Cucumber**, **JUnit 4**, and **Maven**.
Supports UI (Selenium) and API (REST-assured) testing with multi-environment configuration.

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Maven | 3.x | Build & dependency management |
| Serenity BDD | 4.3.4 | Reporting, WebDriver management, step instrumentation |
| Cucumber | (via Serenity) | BDD feature files & step definitions |
| JUnit | 4.13.2 | Test runner |
| REST-assured | (via Serenity) | API test HTTP client |
| AssertJ | 3.25.3 | Fluent assertions |
| Logback | 1.4.14 | Logging |

---

## Project Structure

```
src/test/
├── java/
│   ├── actions/
│   │   └── PostsApiActions.java        # Serenity @Step actions for the Posts API
│   ├── config/
│   │   └── EnvironmentConfig.java      # Reads active environment .properties file
│   ├── hooks/
│   │   └── CucumberHooks.java          # @BeforeAll / @Before / @After / @AfterAll hooks
│   ├── pages/
│   │   └── HomePage.java               # Serenity PageObject for the home page
│   ├── runners/
│   │   └── CucumberTestRunner.java     # JUnit entry point (@RunWith CucumberWithSerenity)
│   ├── steps/
│   │   ├── HomePageStepDefinitions.java
│   │   └── PostsApiStepDefinitions.java
│   └── utils/
│       ├── FileUtils.java              # Clears report folder before suite
│       └── LogUtils.java              # Truncates test-execution.log before suite
│
└── resources/
    ├── environments/
    │   ├── test.properties             # Test environment config
    │   └── stage.properties            # Stage environment config
    ├── features/
    │   ├── api/
    │   │   └── posts.feature           # Posts API scenarios
    │   └── ui/
    │       └── home.feature            # Home page UI scenarios
    ├── logback-test.xml                # Logback logging configuration
    └── serenity.conf                   # Serenity / WebDriver configuration
```

---

## Environment Configuration

The active environment is selected at runtime via `-Denvironment=<name>` (default: `test`).

`EnvironmentConfig` loads `src/test/resources/environments/<env>.properties` and exposes:

| Method | Property key | Description |
|---|---|---|
| `getBaseUrl()` | `base.url` | Web application URL |
| `getApiUrl()` | `api.url` | API base URL |
| `getBrowser()` | `browser` | Browser to use |

### Available Environments

| Environment | File | Notes |
|---|---|---|
| `test` | `test.properties` | Default — points to JSONPlaceholder |
| `stage` | `stage.properties` | Runs headless (`headless=true`) |
| `local` | `local.properties` | Not committed (in `.gitignore`) |

---

## Running Tests

### Run all tests
```bash
mvn test
```

### Filter by tag
```bash
mvn test -Dcucumber.filter.tags=@smoke
mvn test -Dcucumber.filter.tags=@api
mvn test -Dcucumber.filter.tags="@api and not @notfound"
```

### Select environment
```bash
mvn test -Denvironment=stage
mvn test -Denvironment=local -Dcucumber.filter.tags=@smoke
```

---

## Cucumber Tags

| Tag | Scope | Description |
|---|---|---|
| `@ui` | Feature | All UI tests |
| `@api` | Feature | All API tests |
| `@smoke` | Scenario | Smoke / sanity checks |
| `@test` | Scenario | General regression tests |
| `@get` | Scenario | HTTP GET scenarios |
| `@create` | Scenario | HTTP POST / create scenarios |
| `@notfound` | Scenario | 404 / not-found scenarios |

---

## Serenity Reports

Reports are generated automatically after `mvn test` into `report/serenity/`.

Open the report:
```bash
open report/serenity/index.html
```

Before each suite run, `CucumberHooks.@BeforeAll` automatically:
- Clears the `report/` folder via `FileUtils.clearReportFolder()`
- Truncates `logs/test-execution.log` via `LogUtils.clearLogFile()`

---

## WebDriver Configuration

Configured in `src/test/resources/serenity.conf`:

- Driver: **Chrome** (auto-downloaded via `autodownload = true`)
- Screenshots: after every step (`AFTER_EACH_STEP`)
- Browser restarted between scenarios (`restart.browser.each.scenario = true`)
- Timeout: 10 000 ms

---

## Prerequisites

- Java 17+
- Maven 3.6+
- Google Chrome (or let Serenity auto-download the driver)

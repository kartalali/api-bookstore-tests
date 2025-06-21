# 📚 API Bookstore Tests

This repository contains automated test cases for a fictional Bookstore API. It includes a fully configured CI/CD environment using **Jenkins**, powered by **Docker**, with test groups designed for positive, negative, and edge-case scenarios.

---

## 🚀 Quick Start (Get up and running in minutes)

### 1. Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) must be installed.

### 2. Clone the Repository

```bash
git clone https://github.com/kartalali/api-bookstore-tests.git
cd api-bookstore-tests
```

### 3. Start Jenkins (Platform Agnostic: Works on Windows, macOS, and Linux)

```bash
docker-compose up -d
```

This will:
- Start a Jenkins container
- Automatically configure Jenkins using Configuration as Code (JCasC)
- Create a pipeline job named `bookstore-pipeline`
- Connect it to this GitHub repository and pipeline script

> No additional setup is needed.

---

## 🛠️ Access Jenkins UI

Once Docker is running, open your browser and go to:

```
http://localhost:8080
```

- **Username:** `admin`
- **Password:** `admin123`

> The admin user is created automatically during boot via `jenkins.yaml`.

You’ll see the `bookstore-pipeline` job already created and ready to run.

Click **“Build Now”** to start the CI pipeline. ✔️

---

## 🧪 Running Tests Manually (Optional)

If you'd prefer to run tests locally or outside Jenkins, you can use Maven.

### Run all test cases:
```bash
mvn clean test
```

### Run a specific test class:
```bash
mvn clean test -Dtest=BookHappyPathTest
```

Available test classes:
- `BookHappyPathTest`
- `BookNegativeTest`
- `BookEdgeCaseTest`

---

## 🧾 Test Logs & Reports

After tests run (either via Maven or Jenkins), HTML-based logs will be stored here:

```
src/test/html_logs/
```

Inside `html_logs`, results are organized:

- ✅ `PASS/` → all passed test cases
- ❌ `FAIL/` → all failed test cases

Each test's execution is logged individually in HTML format for easier traceability.

---

## ❗ Intentionally Failing Tests (For Bug Tracking)

Certain negative test cases are expected to fail **by design**—to illustrate known bugs or invalid system behavior.

### Example:
> A book with a **negative ID** (`id: -1`) should be rejected by the system.  
> However, the current API allows this, which is treated as a **known bug** and documented via automated test failure.

These failure cases help verify system behavior under invalid conditions and assist in regression validation.

---

## 📂 Folder Structure

```
├── Jenkinsfile               # CI pipeline script
├── jenkins.yaml              # Jenkins Configuration as Code
├── docker-compose.yml        # Jenkins container setup
├── src
│   └── test
│       └── java
│           └── tests
│               ├── BookHappyPathTest.java
│               ├── BookNegativeTest.java
│               └── BookEdgeCaseTest.java
└── src
    └── test
        └── html_logs
            ├── PASS/
            └── FAIL/
```

---

## 🙌 Credits

Created and maintained by [Ali Kartal](https://github.com/kartalali)  
Designed with cross-platform compatibility, scalability, and CI/CD automation in mind.
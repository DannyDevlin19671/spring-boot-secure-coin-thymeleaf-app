# 💰 CoinApp

CoinApp is a Spring Boot web application that allows users to manage a collection of collectible coins. It features authentication, form handling, persistent storage with JPA, and dynamic views using Thymeleaf.

---

## 🛠 Features

- ✅ User authentication and role-based access
- ✅ Manage coins (add, edit, delete)
- ✅ Associate coins with a mint (country)
- ✅ Search coins and filter by properties
- ✅ Error handling for missing coins
- ✅ Thymeleaf-powered UI
- ✅ In-memory H2 database for development/testing
- ✅ Comprehensive test suite with code coverage

---

## 🚀 Technologies Used

| Layer            | Stack                          |
|------------------|--------------------------------|
| Backend          | Java 17+, Spring Boot          |
| Security         | Spring Security (form login)   |
| Database         | Spring Data JPA + H2           |
| View Layer       | Thymeleaf templates            |
| Testing          | JUnit 5, Mockito, MockMvc      |
| Coverage         | JaCoCo                         |
| Build Tool       | Maven                          |

---

## 📦 Installation & Setup

### 🔧 Prerequisites

- Java 17 or higher
- Maven 3.8+
- (Optional) Docker for containerized DB setup later

### 🧪 Run with Maven

```bash
# Run tests and check coverage
mvn clean test jacoco:report

# Start the app
mvn spring-boot:run
```

Visit: [http://localhost:8080](http://localhost:8080)

---

## 👥 Default Users

| Username | Password     | Role  |
|----------|--------------|-------|
| `admin`  | `password123`| ADMIN |
| `user`   | `password123`| USER  |

These are seeded via a helper seeder in `test` profile.

---

## ✅ Running Tests

```bash
mvn test
```

All test classes are annotated with `@ActiveProfiles("test")` to load in-memory users and disable CSRF.

### 🔍 Test Coverage Report

After running tests, open the coverage report:

```bash
open target/site/jacoco/index.html
```

---

## 💡 API Overview (Thymeleaf UI)

| Route                  | Description                       |
|------------------------|-----------------------------------|
| `/`                    | Home page                         |
| `/login`               | Login form                        |
| `/coins`               | List coins (authenticated users)  |
| `/coins/new`           | Add a new coin                    |
| `/coins/edit/{id}`     | Edit coin details                 |
| `/coins/delete/{id}`   | Delete a coin                     |
| `/coins/details/{id}`  | Detailed view with error handler  |

---

## 💥 Error Handling

- Custom exception: `CoinNotFoundException`
- 404 template: `coin-not-found.html`

---

## 🧪 Profiles

- `dev`: Application default
- `test`: Loads in-memory DB, disables CSRF, and uses mock users

---

## 🎨 UI Styling

Custom CSS file:

```
src/main/resources/static/css/styles.css
```

Styled pages:

- Login page
- Coin list and forms
- Details view
- Error pages

---

## 📁 Project Structure

```
coinapp/
├── controller/         # Web controllers (MVC)
├── model/              # Entities: Coin, Mint, User
├── repository/         # Spring Data JPA Repos
├── security/           # Spring Security Config
├── data/seeder/        # Seeder for test users
├── templates/          # Thymeleaf templates
├── static/css/         # styles.css
└── tests/              # Unit + integration tests
```

---

## 📌 Future Enhancements

- ✅ Paginate coin list
- 🔒 JWT-based authentication
- 🌍 Internationalization (i18n)
- 🐳 Dockerized PostgreSQL setup

---

## 🧑‍💻 Author

**Daniel Devlin**  
*Software Developer & Technical Instructor*  
Visit: [Binevenagh IT Solutions](https://binevenaghitsolutions.co.uk)

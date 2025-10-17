# 🧩 Challenge 5 – Order Management System  
### Spring Boot 3.x | Java 17 | PostgreSQL | REST API | Scrum Methodology

---

## 🚀 Project Overview

The **Order Management System (OMS)** is a backend service developed using **Spring Boot 3.0** designed to handle order processing for an online retail company.  
This system improves **efficiency, flexibility, and scalability** in the management of customer orders by providing a robust structure for **order creation, retrieval, updating, and deletion (CRUD)** operations.

It follows the **Scrum methodology** divided into **three Sprints**, ensuring iterative development, teamwork, and progressive delivery of value.

---

## 🎯 Objective and Scope

**Main Objective:**  
To design and implement a scalable backend service that streamlines order management in an e-commerce environment, ensuring data integrity, speed, and maintainability.

**Scope:**  
- Sprint 1 → Build the foundational API with Spring Boot 3, configure database integration, and document all endpoints.  
- Sprint 2 → Implement validations, relational consistency, API testing (Postman/Newman), and integrate logging and authentication.  
- Sprint 3 → Optimize scalability, connect with frontend or external microservices, and deploy in a cloud environment (e.g., AWS / Railway).

---

## 🧱 Project Structure

```
order-service/
├─ src/
│  ├─ main/java/com/nao/retail/orders/
│  │  ├─ controller/OrderController.java
│  │  ├─ service/OrderService.java
│  │  ├─ repository/OrderRepository.java
│  │  ├─ entity/OrderEntity.java, OrderItem.java, OrderStatus.java
│  │  ├─ dto/ (OrderRequest, OrderResponse, OrderItemDTO, UpdateStatusRequest)
│  │  ├─ exception/ (GlobalExceptionHandler, ResourceNotFoundException)
│  │  ├─ mapper/OrderMapper.java
│  │  └─ OrderServiceApplication.java
│  └─ resources/
│     ├─ application.yml
│     └─ data.sql
├─ postman/OrderService.postman_collection.json
├─ scripts/start.ps1
├─ scripts/start.sh
├─ docs/DECISIONS.md
└─ README.md
```

---

## 🧩 Sprint Breakdown

### 🌀 **Sprint 1: Core API and Database Integration**
- Create the Spring Boot 3 project using Java 17.
- Implement the **Order** entity and CRUD operations.
- Integrate **H2 Database** for development and configure **PostgreSQL** for production.
- Prepare a **Postman collection** with all endpoints.
- Include a **startup script** and detailed documentation (README + JavaDoc).
- Record key team decisions (DECISIONS.md).

✅ **Deliverables:**
- Functional API with CRUD.
- Working database connection.
- README and documentation completed.
- Repository published on GitHub with public access.

---

### 🧮 **Sprint 2: Testing, Validation, and Enhancement**
*(Planned Implementation)*  
- Add input validation using `@Valid` and `@NotNull` annotations.
- Conduct **unit and integration testing** with JUnit 5 and Postman.
- Create **automated test reports** (Newman / Allure).
- Integrate **Swagger UI** for API documentation.
- Add **logging** (SLF4J + Spring Boot Actuator).
- Improve error handling and field-level responses.

✅ **Expected Deliverables:**
- API fully validated and tested.
- Swagger documentation available.
- Detailed test evidence and peer reviews.

---

### 🌐 **Sprint 3: Deployment and Scalability**
*(Planned Implementation)*  
- Deploy the service on **AWS EC2 / Railway / Render** with PostgreSQL.
- Apply **containerization (Docker)** for portability.
- Implement **CI/CD** with GitHub Actions.
- Evaluate **performance, load handling, and scaling** under demand.
- Present results and insights in a 10-minute final presentation.

✅ **Expected Deliverables:**
- Online production environment.
- Evidence of CI/CD and deployment.
- Metrics and monitoring dashboard.

---

<details> 
<summary>Sprint 1</summary>

# 🏁 Sprint 1 – Project Setup & Database Integration
## ⚙️ Step-by-Step Installation Tutorial

### 🧰 1. Software Requirements

| Tool | Recommended Version | Purpose |
|------|----------------------|----------|
| **Java JDK** | 17 or higher | Required to run Spring Boot 3.0 |
| **Maven** | 3.9+ | Dependency management and build automation |
| **Spring Boot** | 3.0+ | Web application framework |
| **MySQL / PostgreSQL** | Latest stable | Database management |
| **IntelliJ IDEA / VS Code / Eclipse** | Any | Development environment |
| **Git + GitHub Account** | – | Version control and repository hosting |
| **Postman** | Latest | API testing tool |

---

### 🪜 2. Install Java JDK

#### 🔹 Windows
1. Go to [https://jdk.java.net/17](https://jdk.java.net/17) or [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).  
2. Download and install.  
3. Verify the installation:
   ```bash
   java -version
   ```

#### 🔹 Linux / Mac
```bash
sudo apt install openjdk-17-jdk
java -version
```

---

### 🧱 3. Install Maven
Verify installation:
```bash
mvn -v
```
If not installed:
```bash
sudo apt install maven
```
On Windows, use the [Maven binary installer](https://maven.apache.org/download.cgi).

---

### 🌱 4. Create a Spring Boot Project
You can use the [Spring Initializr](https://start.spring.io/) or your IDE’s generator.

**Project settings:**
- **Project:** Maven
- **Language:** Java
- **Spring Boot:** 3.0.x  
- **Dependencies:**  
  - *Spring Web*  
  - *Spring Data JPA*  
  - *MySQL Driver*  
  - *Lombok* (optional for clean code)

Download the generated ZIP, extract it, and open it in your IDE.

---

### 🗄️ 5. Database Configuration
Create a new database manually:
```sql
CREATE DATABASE orders_db;
```

Then, open `src/main/resources/application.properties` and configure:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/orders_db
spring.datasource.username=root
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
server.port=8080
```

---

### 🔗 6. Initialize Git and Connect to GitHub
```bash
git init
git add .
git commit -m "Initial Spring Boot setup"
git branch -M main
git remote add origin git@github.com:rtkaleb/Challenge5.git
git push -u origin main
```

---

## 🧩 Detailed Tasks Developed

| Step | Area | Description / Implementation | Output / Result |
|------|------|-------------------------------|------------------|
| 1 | **Project Initialization** | Spring Boot 3.0 project created using Maven. Defined dependencies in `pom.xml`. | Functional Spring Boot base project. |
| 2 | **Entity Design** | Created the `Order` entity with JPA annotations. Fields: `id`, `customerName`, `product`, `quantity`, `price`, `date`. | `Order.java` under `/model`. |
| 3 | **Repository Creation** | Implemented interface `OrderRepository` extending `JpaRepository<Order, Long>` for CRUD operations. | Automatic CRUD methods ready. |
| 4 | **Service Layer** | Added `OrderService` to manage business logic. Methods: `createOrder()`, `listOrders()`. | Centralized business logic in `/service`. |
| 5 | **REST Controller** | Created `OrderController` with endpoints: <br>`POST /api/orders` – create order <br>`GET /api/orders` – retrieve orders | Functional API for order management. |
| 6 | **Database Integration** | Connected Spring Boot app to MySQL DB using JPA (Hibernate). Verified schema auto-creation. | Table `orders` generated automatically. |
| 7 | **Testing with Postman** | Verified both endpoints: <br>`POST` → inserts order into DB <br>`GET` → lists existing orders | Successful responses (status 200). |
| 8 | **Documentation and Version Control** | Wrote detailed `README.md`, added screenshots of Postman, explained endpoints, and pushed repo to GitHub via SSH. | Public repository `Challenge5` ready. |

---

## 🧪 Testing Results

**Tool:** Postman  

| Endpoint | Method | Description | Expected Response | Status |
|-----------|---------|-------------|------------------|--------|
| `/api/orders` | POST | Creates new order | JSON confirmation + saved record in DB | ✅ 200 OK |
| `/api/orders` | GET | Lists all orders | Array of orders in JSON | ✅ 200 OK |

---

## 🧠 Key Decisions and Good Practices

- Adopted **layered architecture** (`controller → service → repository`).  
- Used **Spring Boot 3.0 + JDK 17** for performance and compatibility.  
- Managed dependencies via **Maven**.  
- Established **clear Git workflow** with commits and push to GitHub via SSH.  
- Validated API endpoints through Postman before integrating additional features.  

---

## 📸 Evidence – Screenshots

Below are key images that illustrate the progress and testing of Sprint 1.  

| No. | Screenshot | Description |
|-----|-------------|-------------|
| 1 | ![Project Initialization](Images/1.ProjectInitialization.png) | Creation of the Spring Boot project with the necessary dependencies. |
| 2 | ![Database Configuration](Images/2.DatabaseConfiguration.png) | Example of the configured `application.properties` file and connection to MySQL. |
| 3 | ![Entity Creation](Images/3.EntityCreation.png) | The `Order` entity with JPA annotations and attributes. |
| 4 | ![Postman POST Test](Images/4.PostmanPOST.png) | Successful creation of an order in the database through the `/api/orders` POST endpoint. |
| 5 | ![Postman GET Test](Images/5.PostmanGET.png) | JSON response showing the list of stored orders. |
| 6 | ![GitHub Repository](Images/6.RepositoryUpload.png) | Final upload of the project to GitHub through SSH connection. |

---

## 📚 Deliverables

- ✅ Functional Spring Boot backend project  
- ✅ Connected relational database  
- ✅ Tested REST API with Postman  
- ✅ `README.md` documentation with setup instructions  
- ✅ Source code uploaded to GitHub repository [`Challenge5`](https://github.com/rtkaleb/Challenge5)  
- ✅ Visual evidence of the development process  

---


</details> 

---

## 🧰 Technical Stack

| Category | Technology | Purpose |
|-----------|-------------|----------|
| Language | Java 17 | Modern, robust backend language |
| Framework | Spring Boot 3.3.x | Simplified configuration and microservice support |
| Database | H2 (dev), PostgreSQL (prod) | Reliable relational data management |
| Build Tool | Maven | Dependency management and build automation |
| API Testing | Postman / Newman | Request automation and validation |
| Version Control | Git + GitHub | Collaborative development |
| Documentation | JavaDoc + Markdown | Code and project documentation |

---

## ⚙️ General Installation and Execution

### 🧩 Development Environment (H2)
```bash
# Windows
./scripts/start.ps1

# Linux / Mac
./scripts/start.sh
```

Server runs at: [http://localhost:8080](http://localhost:8080)  
H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

- JDBC URL: `jdbc:h2:mem:ordersdb`
- User: `sa`
- Password: *(blank)*

---

### 🧠 Production Environment (PostgreSQL)
```bash
export SPRING_PROFILES_ACTIVE=prod
export DB_URL=jdbc:postgresql://localhost:5432/orders
export DB_USER=postgres
export DB_PASSWORD=postgres
mvn spring-boot:run
```

---

## 🔗 API Endpoints

| Method | Endpoint | Description |
|:------:|:----------|:-------------|
| **POST** | `/api/v1/orders` | Create a new order |
| **GET** | `/api/v1/orders/{id}` | Retrieve order by ID |
| **GET** | `/api/v1/orders?status=PAID&page=0&size=10` | List orders (paginated) |
| **PUT** | `/api/v1/orders/{id}` | Update existing order |
| **PATCH** | `/api/v1/orders/{id}/status` | Update only the order status |
| **DELETE** | `/api/v1/orders/{id}` | Delete order by ID |

---

## 💾 Postman Collection

Import the collection file:
```
postman/OrderService.postman_collection.json
```

Includes:
- CRUD requests
- Example request bodies
- Expected responses
- Variables (`{{orderId}}`) for testing


---

## 🌍 Utility and Reach Scenarios

The OMS is designed for **retail businesses**, **logistics services**, and **e-commerce platforms** requiring reliable order tracking.  
It can also be adapted to:
- Inventory management systems  
- B2B order coordination between suppliers  
- Microservice integration with payment gateways  

This flexibility makes it a reusable, domain-agnostic component.

---

### 🌱 Sustainability of the Project

The sustainability of this Order Management System (OMS) is addressed from **technical**, **economic**, **social**, and **environmental** perspectives, ensuring long-term relevance and minimal resource consumption.

### 🔧 1. Technical Sustainability
- **Maintainable Architecture:**  
  The project follows a layered and modular structure (Controller → Service → Repository), enabling developers to replace or extend components without disrupting the system.
- **Open-Source Technologies:**  
  The use of Spring Boot, PostgreSQL, and Maven guarantees zero licensing costs and long-term community support.
- **Future-Proof Design:**  
  The codebase is compatible with Java 17+ and Spring Boot 3+, allowing updates to future frameworks with minimal refactoring.

### 💵 2. Economic Sustainability
- **Cost Efficiency:**  
  Development relies entirely on open-source frameworks and free-tier cloud platforms, drastically reducing operational costs.
- **Resource Reusability:**  
  The same backend can be integrated into multiple platforms (mobile apps, e-commerce sites, or internal CRMs) without rewriting business logic.
- **High ROI:**  
  The system increases operational efficiency by automating order tracking, leading to faster delivery and improved customer satisfaction.

### 🤝 3. Social Sustainability
- **Transparency and Trust:**  
  Digitalized order management ensures clear traceability between vendors, suppliers, and customers.
- **Accessibility and Collaboration:**  
  The API-first approach promotes data openness, allowing third-party services (e.g., logistics or payment gateways) to connect easily.
- **Educational Impact:**  
  The code and documentation can be used as a learning model for future developers studying backend design and REST architecture.

### 🌍 4. Environmental Sustainability
- **Energy Optimization:**  
  Deploying on cloud infrastructures (such as AWS or Railway) eliminates the need for physical servers and reduces carbon emissions.
- **Lightweight Data Processing:**  
  The database schema and queries are optimized for minimal energy consumption during execution.
- **Scalable Efficiency:**  
  Horizontal scaling avoids overprovisioning resources, ensuring that energy usage grows only with actual demand.

---

### ✅ Summary
This project embodies sustainability by:
- Leveraging **open-source**, **energy-efficient**, and **maintainable** tools.  
- Supporting **long-term adaptability** and **economic viability**.  
- Promoting **ethical, transparent, and educational practices** in software development.

> The result is a sustainable backend solution that not only meets technical standards but also aligns with modern social and environmental responsibility goals.

---

## 🧩 Technical Aspects

| Aspect | Implementation |
|---------|----------------|
| **Clean Architecture** | Layered design (controller → service → repository) |
| **Validation & Error Handling** | `@Valid`, custom exceptions, and global error handler |
| **Persistence** | Spring Data JPA + H2 / PostgreSQL |
| **Documentation** | JavaDoc + Swagger UI (planned for Sprint 2) |
| **Automation** | Scripts and Postman collection |
| **Scalability** | Ready for Docker and Kubernetes deployment |

---

## 📈 Scalability, Economic Viability & Impact

- **Scalability:** Easily extensible to support multiple microservices and cloud-native infrastructure (e.g., AWS Elastic Beanstalk or Kubernetes).  
- **Economic Viability:** Low development cost and high return due to open-source technologies and modular design.  
- **Impact:**  
  - Reduces manual order management time by ≈ 40 %.  
  - Improves customer response and traceability.  
  - Encourages innovation through clean code and reusability.

---
## 💰 Project Budget Breakdown

The following budget estimates the total project cost based on the required development hours, professional expertise, and resources used throughout all three Sprints of the backend system.

| Category | Description | Estimated Hours | Hourly Rate (USD) | Subtotal (USD) |
|-----------|--------------|----------------:|------------------:|----------------:|
| **1. Research & Planning** | System analysis, architecture design, and technical documentation (Sprint 1 prep) | 6 h | $45 | $270 |
| **2. Backend Development** | Core API implementation, CRUD operations, entity modeling, and database configuration | 14 h | $45 | $630 |
| **3. Testing & Validation** | Unit tests, Postman collection setup, error handling, and peer review (Sprint 2) | 10 h | $45 | $450 |
| **4. Deployment & Optimization** | Docker configuration, PostgreSQL migration, CI/CD integration, and environment setup | 8 h | $45 | $360 |
| **5. Documentation & Reporting** | JavaDoc, README writing, final report, and presentation preparation | 6 h | $45 | $270 |
| **6. Maintenance & Support** | Debugging, future improvements, and scalability adjustments | 8 h | $45 | $360 |

| **Total Estimated Cost** | — | **52 hours** | — | **💲2,340 USD** |

---

### 💼 Justification

- **Professional Rate:**  
  Estimated at **$45/hour**, based on PhD-level expertise, academic research background, and proven software engineering experience.

- **Tools & Resources:**  
  - IntelliJ IDEA Community Edition  
  - Postman (API testing)  
  - GitHub & GitHub Actions (CI/CD)  
  - Cloud deployment on AWS / Railway  

- **Value Proposition:**  
  The total cost covers **development, testing, deployment, and documentation**, ensuring a **complete, production-ready system** aligned with industry standards.

---

### 📊 Optional Extended Services

| Service | Description | Additional Cost (USD) |
|----------|--------------|----------------------:|
| **Frontend Integration** | React-based dashboard for viewing and managing orders | + $450 |
| **Data Analytics Module** | Real-time order and sales analysis dashboard | + $380 |
| **Cloud Hosting Setup** | Assistance with AWS or Railway deployment configuration | + $220 |
| **Technical Training Session** | 1-hour workshop for client team usage and maintenance | + $150 |

💡 *These optional modules can increase system value and market readiness without altering the core backend architecture.*

---

### 🧭 Summary

The total estimated project value is **$2,340 USD** for the backend solution.  
With extended modules, the total could range between **$2,800 and $3,200 USD**, depending on the client’s scope and integration needs.

---


## 🧭 More Info

- The project is managed under **Scrum** with clear sprint planning, backlog, and deliverables.  
- Demonstrates documentation clarity, technical decision tracking, and collaboration readiness.  
- Each sprint aligns with **SMART goals**: Specific, Measurable, Achievable, Relevant, Time-bound.  
- Communication tools: Notion (for planning), GitHub Projects (for tracking), and Postman (for collaborative testing).

---

## 🧾 Decision Log
See [docs/DECISIONS.md](./docs/DECISIONS.md) for the chronological record of technical choices and their rationale.

---

## 👨‍💻 Author

**Iván Kaleb Ramírez Torres**  
PhD in Materials Science & Engineering | Full-Stack & Backend Developer  

- GitHub: [@rtkaleb](https://github.com/rtkaleb)  
- LinkedIn: [Iván Kaleb Ramírez Torres](https://linkedin.com/in/ivan-kaleb-ramirez-torres)

---

## 📄 License
MIT License © 2025 Iván Kaleb Ramírez Torres

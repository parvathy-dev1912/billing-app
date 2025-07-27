# Billing App (Spring Boot + MySQL)

Production-ready backend billing system using **Spring Boot**, **MySQL**, and **Lombok**.

## ✅ Features
- Auto-increment bill number
- Item master data
- Create bill with customer details & items
- Calculates totals automatically
- Clean DTOs, REST APIs

## 🛠 Setup

1. **Database**
   - Create schema: `billing_db`
   - Insert initial items:
     ```sql
     INSERT INTO item(name, rate) VALUES
     ('Item A', 100.0), ('Item B', 150.0), ('Item C', 200.0),
     ('Item D', 250.0), ('Item E', 300.0), ('Item F', 350.0),
     ('Item G', 400.0), ('Item H', 450.0), ('Item I', 500.0), ('Item J', 550.0);
     ```

2. **Configure DB**
   - Edit `src/main/resources/application.yml` and set your DB username/password.

3. **Build & Run**
   ```bash
   mvn clean install
   mvn spring-boot:run

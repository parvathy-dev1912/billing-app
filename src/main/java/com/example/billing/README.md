# 🧾 Billing App (Spring Boot + MySQL + Angular)

A small billing backend built with Spring Boot (Java 17), following production-level clean architecture.

## ✅ Features
- Auto-increment bill number
- Store bills & items in MySQL
- Calculates total amount automatically
- Ready for Angular frontend integration
- CORS enabled

---

## ⚙️ Tech Stack
- Java 17
- Spring Boot 3.5.x
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## 📦 Project Structure


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
   Edit `src/main/resources/application.yml` and set your DB username/password.

3. **Build & Run**
```bash
mvn clean install
mvn spring-boot:run

## 📦 API Endpoints

| Method | Endpoint                  | Description                    |
|-------|---------------------------|--------------------------------|
| GET   | /api/bills/next-bill-number | Get next bill number         |
| GET   | /api/bills/items          | Get all items                  |
| POST  | /api/bills                | Create new bill                |

## 🧪 Postman Collection
Import `postman_collection.json` into Postman to test APIs.
# Spring Boot RESTful API Assignment  
**Modules 1–3: Introduction to Spring Boot & REST Controllers**

👩‍🎓 **Student ID:** 26262  
📦 **Repository:** restFull_api_26262  
🛠 **Technology:** Spring Boot, Java, Maven, Postman  

---

## 📌 Overview

This repository contains a collection of **RESTful APIs built using Spring Boot** as part of the practical assignment for Modules 1–3.  
Each question is implemented as a **separate Spring Boot project**, following REST principles, proper HTTP methods, and status codes.

✅ No service or repository layers were used  
✅ In-memory data structures (`List`, `ArrayList`)  
✅ APIs tested using Postman  
✅ Clean code and proper documentation included  

---

## 📁 Project Structure

SpringbootAssignment/
├── question1-library-api/
├── question2-student-api/
├── question3-restaurant-api/
├── question4-ecommerce-api/
├── question5-task-api/
└── bonus-userprofile-api/


Each project contains:
- `controller` package
- `model` package
- `README.md` (project-specific)
- `screenshots/` (Postman testing evidence)

---

## 🧪 Testing

All APIs were tested using **Postman**.  
Each project includes a `screenshots/` folder containing evidence of:
- Successful API calls
- Correct HTTP status codes (200, 201, 204, 404)
- Proper request/response bodies

---

# 📚 Question Breakdown

---

## ✅ Question 1: Library Book Management API

**Description:**  
REST API to manage library books.

**Key Endpoints:**
- GET `/api/books`
- GET `/api/books/{id}`
- GET `/api/books/search?title={title}`
- POST `/api/books`
- DELETE `/api/books/{id}`

**Features:**
- In-memory list with sample books
- Search by title
- Proper HTTP status codes

📂 Folder: `question1-library-api`

---

## ✅ Question 2: Student Registration API

**Description:**  
API for managing student registration and information.

**Key Endpoints:**
- GET `/api/students`
- GET `/api/students/{studentId}`
- GET `/api/students/major/{major}`
- GET `/api/students/filter?gpa={minGpa}`
- POST `/api/students`
- PUT `/api/students/{studentId}`

**Testing Scenarios:**
- Filter by Computer Science major
- Filter students with GPA ≥ 3.5

📂 Folder: `question2-student-api`

---

## ✅ Question 3: Restaurant Menu API

**Description:**  
API for managing restaurant menu items.

**Key Endpoints:**
- GET `/api/menu`
- GET `/api/menu/{id}`
- GET `/api/menu/category/{category}`
- GET `/api/menu/available`
- GET `/api/menu/search?name={name}`
- POST `/api/menu`
- PUT `/api/menu/{id}/availability`
- DELETE `/api/menu/{id}`

**Features:**
- Multiple categories (Appetizer, Main Course, Dessert, Beverage)
- Availability toggling

📂 Folder: `question3-restaurant-api`

---

## ✅ Question 4: E-Commerce Product API

**Description:**  
Comprehensive product catalog API with advanced filtering.

**Key Endpoints:**
- GET `/api/products`
- GET `/api/products/{productId}`
- GET `/api/products/category/{category}`
- GET `/api/products/brand/{brand}`
- GET `/api/products/search?keyword={keyword}`
- GET `/api/products/price-range?min={min}&max={max}`
- GET `/api/products/in-stock`
- POST `/api/products`
- PUT `/api/products/{productId}`
- PATCH `/api/products/{productId}/stock`
- DELETE `/api/products/{productId}`

**Features:**
- Pagination support
- Price range filtering
- Stock management

📂 Folder: `question4-ecommerce-api`

---

## ✅ Question 5: Task Management API

**Description:**  
Simple task/to-do list management API.

**Key Endpoints:**
- GET `/api/tasks`
- GET `/api/tasks/{taskId}`
- GET `/api/tasks/status?completed={true|false}`
- GET `/api/tasks/priority/{priority}`
- POST `/api/tasks`
- PUT `/api/tasks/{taskId}`
- PATCH `/api/tasks/{taskId}/complete`
- DELETE `/api/tasks/{taskId}`

**Features:**
- Priority levels (LOW, MEDIUM, HIGH)
- Mark tasks as completed

📂 Folder: `question5-task-api`

---

## ⭐ Bonus Question: User Profile API

**Description:**  
Advanced user profile management API with custom response wrapper.

**Features:**
- CRUD operations
- Search by username, country, age range
- Activate / deactivate users
- Custom response object (`ApiResponse<T>`)

**Sample Response:**
```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 1,
    "username": "john_doe"
  }
}

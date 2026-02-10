# E-Commerce Product Catalog REST API

A Spring Boot REST API for managing an e-commerce product catalog with comprehensive search and filter capabilities.

## Features

- Complete CRUD operations for products
- Pagination support
- Search by keyword
- Filter by category, brand, price range, and stock availability
- In-memory storage with 12 pre-loaded sample products

## API Endpoints

### 1. Get All Products (with optional pagination)
```
GET /api/products
GET /api/products?page=0&limit=5
```

### 2. Get Product by ID
```
GET /api/products/{productId}
```

### 3. Get Products by Category
```
GET /api/products/category/{category}
Example: GET /api/products/category/Electronics
```

### 4. Get Products by Brand
```
GET /api/products/brand/{brand}
Example: GET /api/products/brand/Apple
```

### 5. Search Products by Keyword
```
GET /api/products/search?keyword={keyword}
Example: GET /api/products/search?keyword=phone
```

### 6. Get Products by Price Range
```
GET /api/products/price-range?min={min}&max={max}
Example: GET /api/products/price-range?min=100&max=500
```

### 7. Get In-Stock Products
```
GET /api/products/in-stock
```

### 8. Add New Product
```
POST /api/products
Content-Type: application/json

{
    "name": "Product Name",
    "description": "Product Description",
    "price": 99.99,
    "category": "Category",
    "stockQuantity": 10,
    "brand": "Brand"
}
```

### 9. Update Product
```
PUT /api/products/{productId}
Content-Type: application/json

{
    "name": "Updated Name",
    "description": "Updated Description",
    "price": 199.99,
    "category": "Updated Category",
    "stockQuantity": 20,
    "brand": "Updated Brand"
}
```

### 10. Update Stock Quantity
```
PATCH /api/products/{productId}/stock?quantity={quantity}
Example: PATCH /api/products/1/stock?quantity=100
```

### 11. Delete Product
```
DELETE /api/products/{productId}
```

## HTTP Status Codes

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST request
- `204 No Content` - Successful DELETE request
- `404 Not Found` - Resource not found

## Pre-loaded Sample Products

The API comes with 12 sample products across different categories:

1. **iPhone 15 Pro** - Electronics, Apple, $999.99, Stock: 50
2. **Samsung Galaxy S24** - Electronics, Samsung, $899.99, Stock: 45
3. **MacBook Pro 16** - Computers, Apple, $2499.99, Stock: 20
4. **Dell XPS 15** - Computers, Dell, $1799.99, Stock: 30
5. **Sony WH-1000XM5** - Audio, Sony, $399.99, Stock: 100
6. **AirPods Pro** - Audio, Apple, $249.99, Stock: 150
7. **Nike Air Max** - Footwear, Nike, $129.99, Stock: 200
8. **Adidas Ultraboost** - Footwear, Adidas, $179.99, Stock: 0 (Out of stock)
9. **LG OLED TV 55** - Electronics, LG, $1499.99, Stock: 15
10. **Samsung QLED TV 65** - Electronics, Samsung, $1299.99, Stock: 25
11. **Logitech MX Master 3** - Accessories, Logitech, $99.99, Stock: 80
12. **Kindle Paperwhite** - Electronics, Amazon, $139.99, Stock: 60

## Running the Application

### Using Maven Wrapper (Windows)
```bash
mvnw.cmd spring-boot:run
```

### Using Maven Wrapper (Unix/Mac)
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## Running Tests

### Using Maven Wrapper (Windows)
```bash
mvnw.cmd test
```

### Using Maven Wrapper (Unix/Mac)
```bash
./mvnw test
```

## Testing with cURL

### Get all products
```bash
curl http://localhost:8080/api/products
```

### Get products with pagination
```bash
curl "http://localhost:8080/api/products?page=0&limit=5"
```

### Get product by ID
```bash
curl http://localhost:8080/api/products/1
```

### Search products
```bash
curl "http://localhost:8080/api/products/search?keyword=phone"
```

### Get products by category
```bash
curl http://localhost:8080/api/products/category/Electronics
```

### Get products by brand
```bash
curl http://localhost:8080/api/products/brand/Apple
```

### Get products by price range
```bash
curl "http://localhost:8080/api/products/price-range?min=100&max=500"
```

### Get in-stock products
```bash
curl http://localhost:8080/api/products/in-stock
```

### Add new product
```bash
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"New Product\",\"description\":\"Description\",\"price\":99.99,\"category\":\"Test\",\"stockQuantity\":10,\"brand\":\"TestBrand\"}"
```

### Update product
```bash
curl -X PUT http://localhost:8080/api/products/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"price\":199.99,\"category\":\"Updated\",\"stockQuantity\":20,\"brand\":\"UpdatedBrand\"}"
```

### Update stock
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock?quantity=100"
```

### Delete product
```bash
curl -X DELETE http://localhost:8080/api/products/1
```

## Technology Stack

- Java 21
- Spring Boot 4.1.0-M1
- Spring Web MVC
- Maven
- JUnit 5 (for testing)

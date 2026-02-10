# E-Commerce Product Catalog API

## Start Application
```bash
mvnw.cmd spring-boot:run
```
Server runs on: http://localhost:8080

## Test Endpoints

### 1. Get All Products
```bash
curl http://localhost:8080/api/products
```

### 2. Get Products with Pagination
```bash
curl "http://localhost:8080/api/products?page=0&limit=5"
```

### 3. Get Product by ID
```bash
curl http://localhost:8080/api/products/1
```

### 4. Get Products by Category
```bash
curl http://localhost:8080/api/products/category/Electronics
```

### 5. Get Products by Brand
```bash
curl http://localhost:8080/api/products/brand/Apple
```

### 6. Search Products
```bash
curl "http://localhost:8080/api/products/search?keyword=phone"
```

### 7. Get Products by Price Range
```bash
curl "http://localhost:8080/api/products/price-range?min=100&max=500"
```

### 8. Get In-Stock Products
```bash
curl http://localhost:8080/api/products/in-stock
```

### 9. Add New Product
```bash
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\":\"New Product\",\"description\":\"Test\",\"price\":99.99,\"category\":\"Test\",\"stockQuantity\":10,\"brand\":\"TestBrand\"}"
```

### 10. Update Product
```bash
curl -X PUT http://localhost:8080/api/products/1 -H "Content-Type: application/json" -d "{\"name\":\"Updated iPhone\",\"description\":\"Updated\",\"price\":1099.99,\"category\":\"Electronics\",\"stockQuantity\":60,\"brand\":\"Apple\"}"
```

### 11. Update Stock
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock?quantity=100"
```

### 12. Delete Product
```bash
curl -X DELETE http://localhost:8080/api/products/12
```

## Pre-loaded Products (12 total)
- iPhone 15 Pro, Samsung Galaxy S24, MacBook Pro, Dell XPS 15
- Sony Headphones, AirPods Pro, Nike Air Max, Adidas Ultraboost (out of stock)
- LG OLED TV, Samsung QLED TV, Logitech Mouse, Kindle Paperwhite

## HTTP Status Codes
- 200 OK - Successful GET/PUT/PATCH
- 201 Created - Successful POST
- 204 No Content - Successful DELETE
- 404 Not Found - Resource not found

# Manual Testing Guide

## Start the Application

```bash
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## Test All Endpoints

### 1. GET All Products
```bash
curl http://localhost:8080/api/products
```

### 2. GET All Products with Pagination
```bash
curl "http://localhost:8080/api/products?page=0&limit=5"
curl "http://localhost:8080/api/products?page=1&limit=5"
```

### 3. GET Product by ID
```bash
curl http://localhost:8080/api/products/1
curl http://localhost:8080/api/products/5
```

### 4. GET Products by Category
```bash
curl http://localhost:8080/api/products/category/Electronics
curl http://localhost:8080/api/products/category/Computers
curl http://localhost:8080/api/products/category/Audio
curl http://localhost:8080/api/products/category/Footwear
```

### 5. GET Products by Brand
```bash
curl http://localhost:8080/api/products/brand/Apple
curl http://localhost:8080/api/products/brand/Samsung
curl http://localhost:8080/api/products/brand/Sony
curl http://localhost:8080/api/products/brand/Nike
```

### 6. Search Products by Keyword
```bash
curl "http://localhost:8080/api/products/search?keyword=phone"
curl "http://localhost:8080/api/products/search?keyword=laptop"
curl "http://localhost:8080/api/products/search?keyword=wireless"
curl "http://localhost:8080/api/products/search?keyword=TV"
```

### 7. GET Products by Price Range
```bash
curl "http://localhost:8080/api/products/price-range?min=0&max=200"
curl "http://localhost:8080/api/products/price-range?min=200&max=500"
curl "http://localhost:8080/api/products/price-range?min=500&max=1000"
curl "http://localhost:8080/api/products/price-range?min=1000&max=3000"
```

### 8. GET In-Stock Products
```bash
curl http://localhost:8080/api/products/in-stock
```

### 9. POST - Add New Product
```bash
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Google Pixel 8\",\"description\":\"Latest Google smartphone\",\"price\":699.99,\"category\":\"Electronics\",\"stockQuantity\":30,\"brand\":\"Google\"}"
```

### 10. PUT - Update Product
```bash
curl -X PUT http://localhost:8080/api/products/1 ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"iPhone 15 Pro Max\",\"description\":\"Updated Apple flagship\",\"price\":1099.99,\"category\":\"Electronics\",\"stockQuantity\":60,\"brand\":\"Apple\"}"
```

### 11. PATCH - Update Stock Quantity
```bash
curl -X PATCH "http://localhost:8080/api/products/8/stock?quantity=50"
curl -X PATCH "http://localhost:8080/api/products/3/stock?quantity=15"
```

### 12. DELETE - Delete Product
```bash
curl -X DELETE http://localhost:8080/api/products/11
```

## Expected HTTP Status Codes

- **200 OK**: Successful GET, PUT, PATCH
- **201 Created**: Successful POST
- **204 No Content**: Successful DELETE
- **404 Not Found**: Resource not found

## Sample Test Scenarios

### Scenario 1: Filter Electronics under $1000
```bash
curl http://localhost:8080/api/products/category/Electronics
curl "http://localhost:8080/api/products/price-range?min=0&max=1000"
```

### Scenario 2: Find all Apple products in stock
```bash
curl http://localhost:8080/api/products/brand/Apple
curl http://localhost:8080/api/products/in-stock
```

### Scenario 3: Search and update stock
```bash
curl "http://localhost:8080/api/products/search?keyword=Adidas"
curl -X PATCH "http://localhost:8080/api/products/8/stock?quantity=100"
curl http://localhost:8080/api/products/8
```

### Scenario 4: Add, update, and delete a product
```bash
# Add
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Test Product\",\"description\":\"Test\",\"price\":99.99,\"category\":\"Test\",\"stockQuantity\":10,\"brand\":\"Test\"}"

# Update (use the returned ID, e.g., 13)
curl -X PUT http://localhost:8080/api/products/13 ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Updated Test\",\"description\":\"Updated\",\"price\":149.99,\"category\":\"Test\",\"stockQuantity\":20,\"brand\":\"Test\"}"

# Delete
curl -X DELETE http://localhost:8080/api/products/13
```

## Pre-loaded Test Data

The API includes 12 products:
- 5 Electronics (Apple, Samsung, LG, Amazon)
- 2 Computers (Apple, Dell)
- 2 Audio (Sony, Apple)
- 2 Footwear (Nike, Adidas - one out of stock)
- 1 Accessories (Logitech)

Price range: $99.99 - $2499.99

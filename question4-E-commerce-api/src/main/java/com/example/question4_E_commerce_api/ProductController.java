package com.example.question4_E_commerce_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public ProductController() {
        products.put(nextId, new Product(nextId++, "iPhone 15 Pro", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.put(nextId, new Product(nextId++, "Samsung Galaxy S24", "Flagship Android phone", 899.99, "Electronics", 45, "Samsung"));
        products.put(nextId, new Product(nextId++, "MacBook Pro", "Powerful laptop", 2499.99, "Computers", 20, "Apple"));
        products.put(nextId, new Product(nextId++, "Dell XPS 15", "High-performance laptop", 1799.99, "Computers", 30, "Dell"));
        products.put(nextId, new Product(nextId++, "Sony Headphones", "Noise-cancelling headphones", 399.99, "Audio", 100, "Sony"));
        products.put(nextId, new Product(nextId++, "AirPods Pro", "Wireless earbuds", 249.99, "Audio", 150, "Apple"));
        products.put(nextId, new Product(nextId++, "Nike Air Max", "Running shoes", 129.99, "Footwear", 200, "Nike"));
        products.put(nextId, new Product(nextId++, "Adidas Ultraboost", "Athletic shoes", 179.99, "Footwear", 0, "Adidas"));
        products.put(nextId, new Product(nextId++, "LG OLED TV", "4K OLED television", 1499.99, "Electronics", 15, "LG"));
        products.put(nextId, new Product(nextId++, "Samsung QLED TV", "Quantum dot TV", 1299.99, "Electronics", 25, "Samsung"));
        products.put(nextId, new Product(nextId++, "Logitech Mouse", "Wireless mouse", 99.99, "Accessories", 80, "Logitech"));
        products.put(nextId, new Product(nextId++, "Kindle Paperwhite", "E-reader", 139.99, "Electronics", 60, "Amazon"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        List<Product> list = new ArrayList<>(products.values());
        if (page != null && limit != null) {
            int start = page * limit;
            int end = Math.min(start + limit, list.size());
            if (start >= list.size()) return ResponseEntity.ok(Collections.emptyList());
            return ResponseEntity.ok(list.subList(start, end));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = products.get(productId);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = products.values().stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = products.values().stream()
            .filter(p -> p.getBrand().equalsIgnoreCase(brand))
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = products.values().stream()
            .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) || 
                        p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Product> result = products.values().stream()
            .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = products.values().stream()
            .filter(p -> p.getStockQuantity() > 0)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.put(product.getProductId(), product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        if (!products.containsKey(productId)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        product.setProductId(productId);
        products.put(productId, product);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        Product product = products.get(productId);
        if (product == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        product.setStockQuantity(quantity);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        if (!products.containsKey(productId)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        products.remove(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

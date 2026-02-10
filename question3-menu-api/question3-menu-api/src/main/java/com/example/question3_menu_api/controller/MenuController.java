package com.example.question3_menu_api.controller;

import com.example.question3_menu_api.model.MenuItem;
import com.example.question3_menu_api.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems(@RequestParam(required = false) Boolean available) {
        if (available != null && available) {
            return ResponseEntity.ok(menuService.getAvailableMenuItems());
        }
        return ResponseEntity.ok(menuService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuService.getMenuItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableItems() {
        return ResponseEntity.ok(menuService.getAvailableMenuItems());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(menuService.getMenuItemsByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItems(@RequestParam String name) {
        return ResponseEntity.ok(menuService.searchMenuItemsByName(name));
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.addMenuItem(menuItem));
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        return menuService.toggleAvailability(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        return menuService.deleteMenuItem(id) 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.notFound().build();
    }
}

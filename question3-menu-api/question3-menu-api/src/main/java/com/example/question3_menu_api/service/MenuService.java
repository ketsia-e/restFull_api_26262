package com.example.question3_menu_api.service;

import com.example.question3_menu_api.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final Map<Long, MenuItem> menuItems = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(33);

    public MenuService() {
        menuItems.put(1L, new MenuItem(1L, "Caesar Salad", "Fresh romaine lettuce with parmesan and croutons", 8.99, "Appetizer", true));
        menuItems.put(2L, new MenuItem(2L, "Bruschetta", "Toasted bread with tomatoes, garlic, and basil", 7.50, "Appetizer", true));
        menuItems.put(3L, new MenuItem(3L, "Spring Rolls", "Crispy vegetable spring rolls with sweet chili sauce", 6.99, "Appetizer", true));
        menuItems.put(4L, new MenuItem(4L, "Garlic Bread", "Toasted bread with garlic butter and herbs", 5.50, "Appetizer", true));
        menuItems.put(5L, new MenuItem(5L, "Chicken Wings", "Spicy buffalo wings with ranch dressing", 10.99, "Appetizer", true));
        menuItems.put(6L, new MenuItem(6L, "Mozzarella Sticks", "Fried mozzarella with marinara sauce", 7.99, "Appetizer", true));
        menuItems.put(7L, new MenuItem(7L, "Nachos", "Tortilla chips with cheese, jalapeños, and salsa", 9.50, "Appetizer", true));
        menuItems.put(8L, new MenuItem(8L, "Soup of the Day", "Chef's special soup", 6.50, "Appetizer", true));
        
        menuItems.put(9L, new MenuItem(9L, "Grilled Salmon", "Atlantic salmon with lemon butter sauce", 24.99, "Main Course", true));
        menuItems.put(10L, new MenuItem(10L, "Ribeye Steak", "12oz premium ribeye with garlic butter", 32.99, "Main Course", true));
        menuItems.put(11L, new MenuItem(11L, "Vegetarian Pasta", "Penne with seasonal vegetables in marinara sauce", 16.99, "Main Course", false));
        menuItems.put(12L, new MenuItem(12L, "Chicken Parmesan", "Breaded chicken with marinara and mozzarella", 19.99, "Main Course", true));
        menuItems.put(13L, new MenuItem(13L, "Beef Burger", "Angus beef burger with fries", 14.99, "Main Course", true));
        menuItems.put(14L, new MenuItem(14L, "Shrimp Scampi", "Garlic butter shrimp over linguine", 22.99, "Main Course", true));
        menuItems.put(15L, new MenuItem(15L, "BBQ Ribs", "Slow-cooked pork ribs with BBQ sauce", 26.99, "Main Course", true));
        menuItems.put(16L, new MenuItem(16L, "Margherita Pizza", "Fresh mozzarella, tomato, and basil", 15.99, "Main Course", true));
        
        menuItems.put(17L, new MenuItem(17L, "Chocolate Lava Cake", "Warm chocolate cake with vanilla ice cream", 9.99, "Dessert", true));
        menuItems.put(18L, new MenuItem(18L, "Tiramisu", "Classic Italian coffee-flavored dessert", 8.50, "Dessert", true));
        menuItems.put(19L, new MenuItem(19L, "Cheesecake", "New York style cheesecake with berry compote", 8.99, "Dessert", true));
        menuItems.put(20L, new MenuItem(20L, "Apple Pie", "Homemade apple pie with cinnamon", 7.50, "Dessert", true));
        menuItems.put(21L, new MenuItem(21L, "Ice Cream Sundae", "Three scoops with toppings", 6.99, "Dessert", true));
        menuItems.put(22L, new MenuItem(22L, "Crème Brûlée", "Vanilla custard with caramelized sugar", 9.50, "Dessert", true));
        menuItems.put(23L, new MenuItem(23L, "Brownie", "Chocolate brownie with nuts", 6.50, "Dessert", true));
        menuItems.put(24L, new MenuItem(24L, "Panna Cotta", "Italian cream dessert with fruit", 8.99, "Dessert", true));
        
        menuItems.put(25L, new MenuItem(25L, "Fresh Lemonade", "Homemade lemonade with mint", 4.50, "Beverage", true));
        menuItems.put(26L, new MenuItem(26L, "Iced Tea", "Freshly brewed iced tea", 3.50, "Beverage", true));
        menuItems.put(27L, new MenuItem(27L, "Coffee", "Hot brewed coffee", 3.00, "Beverage", true));
        menuItems.put(28L, new MenuItem(28L, "Cappuccino", "Espresso with steamed milk", 4.50, "Beverage", true));
        menuItems.put(29L, new MenuItem(29L, "Orange Juice", "Freshly squeezed orange juice", 4.99, "Beverage", true));
        menuItems.put(30L, new MenuItem(30L, "Soda", "Coca-Cola, Sprite, or Fanta", 2.99, "Beverage", true));
        menuItems.put(31L, new MenuItem(31L, "Milkshake", "Chocolate, vanilla, or strawberry", 5.99, "Beverage", true));
        menuItems.put(32L, new MenuItem(32L, "Sparkling Water", "Mineral water with lemon", 3.50, "Beverage", true));
    }

    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems.values());
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return Optional.ofNullable(menuItems.get(id));
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        return menuItems.values().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<MenuItem> getAvailableMenuItems() {
        return menuItems.values().stream()
                .filter(MenuItem::isAvailable)
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchMenuItemsByName(String name) {
        return menuItems.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public MenuItem addMenuItem(MenuItem menuItem) {
        menuItem.setId(idCounter.getAndIncrement());
        menuItems.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public Optional<MenuItem> toggleAvailability(Long id) {
        MenuItem item = menuItems.get(id);
        if (item != null) {
            item.setAvailable(!item.isAvailable());
            return Optional.of(item);
        }
        return Optional.empty();
    }

    public boolean deleteMenuItem(Long id) {
        return menuItems.remove(id) != null;
    }
}

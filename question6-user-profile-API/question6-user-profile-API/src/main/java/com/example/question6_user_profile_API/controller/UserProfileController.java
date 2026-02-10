package com.example.question6_user_profile_API.controller;

import com.example.question6_user_profile_API.model.ApiResponse;
import com.example.question6_user_profile_API.model.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {
    
    private final List<UserProfile> userProfiles = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile userProfile) {
        userProfile.setUserId(idCounter.getAndIncrement());
        userProfiles.add(userProfile);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User profile created successfully", userProfile));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", userProfiles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long id) {
        return userProfiles.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(user -> ResponseEntity.ok(new ApiResponse<>(true, "User found", user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long id, @RequestBody UserProfile updatedProfile) {
        for (int i = 0; i < userProfiles.size(); i++) {
            if (userProfiles.get(i).getUserId().equals(id)) {
                updatedProfile.setUserId(id);
                userProfiles.set(i, updatedProfile);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile updated successfully", updatedProfile));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        boolean removed = userProfiles.removeIf(u -> u.getUserId().equals(id));
        if (removed) {
            return ResponseEntity.ok(new ApiResponse<>(true, "User profile deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found", null));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@PathVariable String username) {
        List<UserProfile> results = userProfiles.stream()
                .filter(u -> u.getUsername().toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", results));
    }

    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> results = userProfiles.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", results));
    }

    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(
            @RequestParam int minAge, @RequestParam int maxAge) {
        List<UserProfile> results = userProfiles.stream()
                .filter(u -> u.getAge() >= minAge && u.getAge() <= maxAge)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", results));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long id) {
        return userProfiles.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setActive(true);
                    return ResponseEntity.ok(new ApiResponse<>(true, "User profile activated successfully", user));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long id) {
        return userProfiles.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .map(user -> {
                    user.setActive(false);
                    return ResponseEntity.ok(new ApiResponse<>(true, "User profile deactivated successfully", user));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, "User not found", null)));
    }
}

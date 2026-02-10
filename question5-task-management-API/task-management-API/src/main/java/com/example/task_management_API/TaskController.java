package com.example.task_management_API;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public TaskController() {
        tasks.put(1L, new Task(1L, "Complete project documentation", "Write comprehensive API documentation", false, "HIGH", "2024-12-31"));
        tasks.put(2L, new Task(2L, "Review code", "Review pull requests", false, "MEDIUM", "2024-12-25"));
        tasks.put(3L, new Task(3L, "Fix bugs", "Fix reported bugs in production", true, "HIGH", "2024-12-20"));
        tasks.put(4L, new Task(4L, "Update dependencies", "Update project dependencies", false, "LOW", "2025-01-15"));
        counter.set(4L);
    }

    @GetMapping(produces = "application/json")
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = tasks.get(taskId);
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam boolean completed) {
        return tasks.values().stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());
    }

    @GetMapping("/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable String priority) {
        return tasks.values().stream()
                .filter(task -> priority.equalsIgnoreCase(task.getPriority()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setTaskId(counter.incrementAndGet());
        tasks.put(task.getTaskId(), task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        if (!tasks.containsKey(taskId)) {
            return ResponseEntity.notFound().build();
        }
        updatedTask.setTaskId(taskId);
        tasks.put(taskId, updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setCompleted(true);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        if (!tasks.containsKey(taskId)) {
            return ResponseEntity.notFound().build();
        }
        tasks.remove(taskId);
        return ResponseEntity.noContent().build();
    }
}

package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.findAll());
    }

    @GetMapping("/Admin/{title}")
    public ResponseEntity<String> getAdminDTOByTitle(@PathVariable String title) {
        Optional<Course> course = courseService.findByTitle(title);
        if (course.isPresent()) {
            return ResponseEntity.ok().body(courseService.getCourseAdminDTO(title));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/public/{title}")
    public ResponseEntity<String> getPublicDTOByTitle(@PathVariable String title) {
        Optional<Course> course = courseService.findByTitle(title);
        if (course.isPresent()) {
            return ResponseEntity.ok().body(courseService.getCoursePublicDTO(title));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok().body(courseService.save(course));
    }

    @PutMapping("/{title}")
    public ResponseEntity<Course> updateCourse(@PathVariable String title, @RequestBody Course updatedCourse) {
        return ResponseEntity.ok().body(courseService.updateCourse(title, updatedCourse));
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable long id) {
        courseService.delete(id);
    }

}
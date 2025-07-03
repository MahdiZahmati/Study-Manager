package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService extends GenericService<Course, Long>{

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
    }

    public Optional<Course> findByTitle(String title) {
        return courseRepository.findByTitle(title);
    }

    public Course updateCourse(String title, Course updatedCourse) {
        return courseRepository.findByTitle(title).map(course -> {
                    course.setTitle(updatedCourse.getTitle());
                    course.setUnit(updatedCourse.getUnit());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new RuntimeException("Course not found"));
    }

}

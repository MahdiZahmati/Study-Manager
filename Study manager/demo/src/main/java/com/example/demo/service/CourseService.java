package com.example.demo.service;

import com.example.demo.DTO.CourseAdminDTO;
import com.example.demo.DTO.CoursePublicDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService extends GenericService<Course, Long>{

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        super(courseRepository);
        this.courseRepository = courseRepository;
    }

    private CoursePublicDTO mapToPublicDTO(Course course) {
        String title = course.getTitle();
        int unit = course.getUnit();
        String deptName = course.getDepartment() != null
                ? course.getDepartment().getName()
                : null;
        List<String> profNames = course.getProfessorList().stream()
                .map(p -> p.getFirstName() + " " + p.getLastName())
                .collect(Collectors.toList());
        return new CoursePublicDTO(title, unit, deptName, profNames);
    }

    private CourseAdminDTO mapToAdminDTO(Course course) {
        Long id = course.getId();
        String title = course.getTitle();
        int unit = course.getUnit();
        String deptName = course.getDepartment() != null
                ? course.getDepartment().getName() : null;
        List<Professor> professors = course.getProfessorList();
        List<Student> students = course.getStudentList();
        return new CourseAdminDTO(id, title, unit, deptName, students, professors);
    }

    @Transactional(readOnly = true)
    public List<CoursePublicDTO> getAllCoursePublicDTO() {
        return findAll().stream()
                .map(this::mapToPublicDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CourseAdminDTO> getAllCourseAdminDTO() {
        return findAll().stream()
                .map(this::mapToAdminDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CoursePublicDTO getCoursePublicDTO(String title) {
        Course course = courseRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToPublicDTO(course);
    }

    @Transactional(readOnly = true)
    public CourseAdminDTO getCourseAdminDTO(String title) {
        Course course = courseRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToAdminDTO(course);
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

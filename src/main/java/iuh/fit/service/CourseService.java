package iuh.fit.service;

import iuh.fit.dtos.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCoursesWithLecturerCount();
    CourseDTO getCourseById(Integer id);
    CourseDTO createCourse(CourseDTO courseDTO);
    void deleteCourse(Integer id);
}
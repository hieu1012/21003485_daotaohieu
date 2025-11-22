package iuh.fit.service.impl;


import iuh.fit.dtos.CourseDTO;
import iuh.fit.entities.Course;
import iuh.fit.exceptions.BadRequestException;
import iuh.fit.exceptions.ResourceNotFoundException;
import iuh.fit.repositories.CourseRepository;
import iuh.fit.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCoursesWithLecturerCount() {
        return courseRepository.findAll().stream()
                .map(course -> CourseDTO.builder()
                        .id(course.getId())
                        .courseCode(course.getCourseCode())
                        .courseName(course.getCourseName())
                        .credit(course.getCredit())
                        .lecturerCount((long) course.getLecturers().size())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học với ID: " + id));

        return CourseDTO.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .credit(course.getCredit())
                .lecturerCount((long) course.getLecturers().size())
                .build();
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByCourseCode(courseDTO.getCourseCode())) {
            throw new BadRequestException("Mã khóa học đã tồn tại: " + courseDTO.getCourseCode());
        }

        Course course = Course.builder()
                .courseCode(courseDTO.getCourseCode())
                .courseName(courseDTO.getCourseName())
                .credit(courseDTO.getCredit())
                .build();

        Course savedCourse = courseRepository.save(course);

        return CourseDTO.builder()
                .id(savedCourse.getId())
                .courseCode(savedCourse.getCourseCode())
                .courseName(savedCourse.getCourseName())
                .credit(savedCourse.getCredit())
                .lecturerCount(0L)
                .build();
    }

    @Override
    @Transactional
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học với ID: " + id));

        if (!course.getLecturers().isEmpty()) {
            throw new BadRequestException("Không thể xóa khóa học đã có giảng viên phụ trách");
        }

        courseRepository.delete(course);
    }
}
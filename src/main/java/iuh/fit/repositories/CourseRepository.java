package iuh.fit.repositories;

import iuh.fit.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    boolean existsByCourseCode(String courseCode);

    @Query("SELECT c, COUNT(l) FROM Course c LEFT JOIN c.lecturers l GROUP BY c")
    List<Object[]> findCoursesWithLecturerCount();
}
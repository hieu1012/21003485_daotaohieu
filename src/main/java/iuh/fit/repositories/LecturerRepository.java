package iuh.fit.repositories;

import iuh.fit.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    List<Lecturer> findByCourses_Id(Integer courseId);
    boolean existsByLecturerCode(String lecturerCode);
}
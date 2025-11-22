package iuh.fit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_code", length = 20, nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    @Column(name = "credit")
    private Integer credit;

    @ManyToMany(mappedBy = "courses")
    @Builder.Default
    private Set<Lecturer> lecturers = new HashSet<>();
}
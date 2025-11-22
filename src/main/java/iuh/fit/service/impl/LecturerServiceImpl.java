package iuh.fit.service.impl;


import iuh.fit.dtos.LecturerDTO;
import iuh.fit.repositories.LecturerRepository;
import iuh.fit.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Override
    public List<LecturerDTO> getLecturersByCourseId(Integer courseId) {
        return lecturerRepository.findByCourses_Id(courseId).stream()
                .map(lecturer -> LecturerDTO.builder()
                        .id(lecturer.getId())
                        .lecturerCode(lecturer.getLecturerCode())
                        .lecturerName(lecturer.getLecturerName())
                        .email(lecturer.getEmail())
                        .department(lecturer.getDepartment())
                        .build())
                .collect(Collectors.toList());
    }
}
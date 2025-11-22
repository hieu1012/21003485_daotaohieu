package iuh.fit.service;

import iuh.fit.dtos.LecturerDTO;

import java.util.List;

public interface LecturerService {
    List<LecturerDTO> getLecturersByCourseId(Integer courseId);
}
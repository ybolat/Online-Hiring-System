package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllByUserId(Long id);
}

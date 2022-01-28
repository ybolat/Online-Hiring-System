package kz.edu.astanait.diplomawork.service.serviceInterface.hiring;

import kz.edu.astanait.diplomawork.model.hiring.Development;

import java.util.List;

public interface DevelopmentService {

    List<Development> getAllByUserId(Long id);
}

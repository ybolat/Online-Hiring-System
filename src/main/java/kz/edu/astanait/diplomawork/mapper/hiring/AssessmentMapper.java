package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.AssessmentDtoResponse;
import kz.edu.astanait.diplomawork.model.hiring.Assessment;

import java.util.Objects;

public class AssessmentMapper {

    public static AssessmentDtoResponse assessmentToDtoResponse(Assessment assessment) {

        AssessmentDtoResponse assessmentDtoResponse = new AssessmentDtoResponse();
        assessmentDtoResponse.setId(assessment.getId());
        if(Objects.nonNull(assessment.getRequest())) assessmentDtoResponse.setRequest(RequestMapper.requestToDto(assessment.getRequest()));
        if(Objects.nonNull(assessment.getVote())) assessmentDtoResponse.setVote(assessment.getVote());
        return assessmentDtoResponse;
    }
}

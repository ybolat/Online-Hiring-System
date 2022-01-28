package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.AssessmentDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.model.hiring.Assessment;

import java.util.Objects;

public class AssessmentMapper {

    public static AssessmentDtoResponse assessmentDtoResponse(Assessment assessment) {

        AssessmentDtoResponse assessmentDtoResponse = new AssessmentDtoResponse();
        assessmentDtoResponse.setId(assessment.getId());
        if(Objects.nonNull(assessment.getCommission())) assessmentDtoResponse.setCommission(CommissionMapper.commissionToDto(assessment.getCommission()));
        if(Objects.nonNull(assessment.getRequest())) assessmentDtoResponse.setRequest(RequestMapper.requestToDto(assessment.getRequest()));
        if(Objects.nonNull(assessment.getRate())) assessmentDtoResponse.setRate(assessment.getRate());
        return assessmentDtoResponse;
    }
}

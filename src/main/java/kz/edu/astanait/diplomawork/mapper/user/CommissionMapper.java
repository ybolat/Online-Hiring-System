package kz.edu.astanait.diplomawork.mapper.user;

import kz.edu.astanait.diplomawork.dto.responseDto.user.CommissionDtoResponse;
import kz.edu.astanait.diplomawork.mapper.security.RoleMapper;
import kz.edu.astanait.diplomawork.model.user.Commission;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class CommissionMapper {

    public static CommissionDtoResponse commissionToDto(Commission commission) {
        CommissionDtoResponse commissionDtoResponse = new CommissionDtoResponse();
        commissionDtoResponse.setId(commission.getId());
        if(Objects.nonNull(commission.getRole())) commissionDtoResponse.setRole(RoleMapper.roleToDto(commission.getRole()));
        if(Strings.isNotBlank(commission.getEmail())) commissionDtoResponse.setEmail(commission.getEmail());
        return commissionDtoResponse;
    }
}

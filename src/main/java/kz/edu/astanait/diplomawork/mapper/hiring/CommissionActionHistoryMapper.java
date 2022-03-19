package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CommissionActionHistoryDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.CommissionMapper;
import kz.edu.astanait.diplomawork.model.hiring.CommissionActionHistory;

import java.util.Objects;

public class CommissionActionHistoryMapper {
    public static CommissionActionHistoryDtoResponse commissionActionHistoryToDto (CommissionActionHistory commissionActionHistory){
        CommissionActionHistoryDtoResponse commissionActionHistoryDtoResponse = new CommissionActionHistoryDtoResponse();
        commissionActionHistoryDtoResponse.setId(commissionActionHistory.getId());
        if(Objects.nonNull(commissionActionHistory.getCommission())) commissionActionHistoryDtoResponse.setCommission(CommissionMapper::commissionToDto(commissionActionHistory.getCommission()));
        if(Objects.nonNull(commissionActionHistory.getRequest())) commissionActionHistoryDtoResponse.setRequest(commissionActionHistory.getRequest());
        if(Objects.nonNull(commissionActionHistory.getIsVote())) commissionActionHistoryDtoResponse.setIsVote(commissionActionHistory.getIsVote());
    }
}

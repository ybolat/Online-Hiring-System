package kz.edu.astanait.diplomawork.mapper.hiring;

import kz.edu.astanait.diplomawork.dto.responseDto.hiring.CertificateDtoResponse;
import kz.edu.astanait.diplomawork.mapper.user.UserProfessionalInfoMapper;
import kz.edu.astanait.diplomawork.model.hiring.Certificate;
import org.apache.logging.log4j.util.Strings;
import java.util.Objects;

public class CertificateMapper {

    public static CertificateDtoResponse certificateToDto (Certificate certificate){
        CertificateDtoResponse certificateDtoResponse = new CertificateDtoResponse();
        certificateDtoResponse.setId(certificate.getId());
        if(Objects.nonNull(certificate.getUserProfessionalInfo())) certificateDtoResponse.setUserProfessionalInfoDtoResponse(UserProfessionalInfoMapper.userProfessionalInfoToDto(certificate.getUserProfessionalInfo()));
        if(Strings.isNotBlank(certificate.getCertificate())) certificateDtoResponse.setCertificate(certificate.getCertificate());
        return certificateDtoResponse;
    }
}

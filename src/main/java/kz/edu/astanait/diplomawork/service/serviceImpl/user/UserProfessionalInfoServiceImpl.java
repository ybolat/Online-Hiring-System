package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import kz.edu.astanait.diplomawork.repository.user.UserProfessionalInfoRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfessionalInfoServiceImpl implements UserProfessionalInfoService {

    private final UserProfessionalInfoRepository userProfessionalInfoRepository;

    @Autowired
    public UserProfessionalInfoServiceImpl(UserProfessionalInfoRepository userProfessionalInfoRepository) {
        this.userProfessionalInfoRepository = userProfessionalInfoRepository;
    }

    @Override
    public Optional<UserProfessionalInfo> getById(Long id) {
        return userProfessionalInfoRepository.findById(id);
    }

    @Override
    public UserProfessionalInfo getByIdThrowException(Long id) {
        return this.getById(id)
                .orElseThrow(() -> new CustomNotFoundException(String
                        .format(ExceptionDescription.CustomNotFoundException, "User professional info", "id", id)));
    }
}

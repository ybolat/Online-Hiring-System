package kz.edu.astanait.diplomawork.service.serviceImpl.user;

import kz.edu.astanait.diplomawork.repository.user.UserProfessionalInfoRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.user.UserProfessionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfessionalInfoServiceImpl implements UserProfessionalInfoService {

    private final UserProfessionalInfoRepository userProfessionalInfoRepository;

    @Autowired
    public UserProfessionalInfoServiceImpl(UserProfessionalInfoRepository userProfessionalInfoRepository) {
        this.userProfessionalInfoRepository = userProfessionalInfoRepository;
    }
}

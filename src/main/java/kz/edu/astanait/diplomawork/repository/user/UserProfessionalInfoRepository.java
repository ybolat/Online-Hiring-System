package kz.edu.astanait.diplomawork.repository.user;

import kz.edu.astanait.diplomawork.model.user.UserProfessionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfessionalInfoRepository extends JpaRepository<UserProfessionalInfo, Long> {
}

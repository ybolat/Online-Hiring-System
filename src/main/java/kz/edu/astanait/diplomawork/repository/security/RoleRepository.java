package kz.edu.astanait.diplomawork.repository.security;

import kz.edu.astanait.diplomawork.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

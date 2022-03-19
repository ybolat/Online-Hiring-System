package kz.edu.astanait.diplomawork.service.serviceInterface.security;

import kz.edu.astanait.diplomawork.model.security.Role;
import java.util.Optional;

public interface RoleService {

    Optional<Role> getById(Long id);

    Optional<Role> getByName(String roleName);

    Role getByIdThrowException(Long id);

    Role getByNameThrowException(String name);
}

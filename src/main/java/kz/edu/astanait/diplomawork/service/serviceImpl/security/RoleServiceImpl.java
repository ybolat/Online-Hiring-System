package kz.edu.astanait.diplomawork.service.serviceImpl.security;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomNotFoundException;
import kz.edu.astanait.diplomawork.model.security.Role;
import kz.edu.astanait.diplomawork.repository.security.RoleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> getById(Long id) {
        return this.roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        return this.roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(
                () -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "role", "id", id)));
    }

    @Override
    public Role getByNameThrowException(String name) {
        return this.getByName(name).orElseThrow(
                () -> new CustomNotFoundException(String.format(ExceptionDescription.CustomNotFoundException, "role", "name", name)));
    }
}

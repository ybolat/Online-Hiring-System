package kz.edu.astanait.diplomawork.service.serviceImpl.security;

import kz.edu.astanait.diplomawork.repository.security.RoleRepository;
import kz.edu.astanait.diplomawork.service.serviceInterface.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}

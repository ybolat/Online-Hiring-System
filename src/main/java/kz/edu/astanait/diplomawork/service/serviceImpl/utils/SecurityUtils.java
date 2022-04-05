package kz.edu.astanait.diplomawork.service.serviceImpl.utils;

import kz.edu.astanait.diplomawork.exception.ExceptionDescription;
import kz.edu.astanait.diplomawork.exception.domain.CustomException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SecurityUtils {

    public static void checkAccessByPrincipal(String email, Principal principal) {
        if(!email.equals(principal.getName())) throw new CustomException(String.format(ExceptionDescription.AccessException, "update"));
    }
}

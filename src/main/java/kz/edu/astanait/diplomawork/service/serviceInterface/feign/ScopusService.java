package kz.edu.astanait.diplomawork.service.serviceInterface.feign;

import java.security.Principal;

public interface ScopusService {

    boolean getAuthorInformation(Principal principal);

    void getScopusInformation(Principal principal);
}

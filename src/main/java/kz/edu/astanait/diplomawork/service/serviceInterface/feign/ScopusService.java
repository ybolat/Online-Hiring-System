package kz.edu.astanait.diplomawork.service.serviceInterface.feign;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicReference;

public interface ScopusService {

    AtomicReference<Boolean> getAuthorInformation(Principal principal);

    Boolean getScopusInformation(Principal principal);
}

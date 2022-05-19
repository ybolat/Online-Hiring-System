package kz.edu.astanait.diplomawork.constant;

public class SecurityConstant {

    public static final String [] PERMITTED_URL = {"/api/v1/user/authorization/**", "/api/v1/user/registration/**", "/api/v1/user/activate/**", "/api/v1/pin-code/**", "/api/v1/user/forgot-password/**", "/api/v1/hiring/vacancy/get-all", "/swagger-ui/**", "/api-docs", "/api-docs.yaml", "/swagger-ui.html"};
}

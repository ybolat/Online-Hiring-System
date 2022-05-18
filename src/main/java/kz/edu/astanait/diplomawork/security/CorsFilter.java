//package kz.edu.astanait.diplomawork.security;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
////        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, PUT");
////        response.setHeader("Access-Control-Allow-Headers", "*");
////        response.addHeader("Access-Control-Expose-Headers", "*");
////        filterChain.doFilter(request, response);
////    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, PUT");
//        resp.setHeader("Access-Control-Allow-Headers", "*");
//        resp.setHeader("Access-Control-Expose-Headers", "*");
//        filterChain.doFilter(request, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
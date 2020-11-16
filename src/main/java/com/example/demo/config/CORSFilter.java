//package com.example.demo.config;
//
//import javax.servlet.*;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class CORSFilter  implements Filter {
//    // This is to be replaced with a list of domains allowed to access the server
//    //You can include more than one origin here
//    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200");
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        // Lets make sure that we are working with HTTP (that is, against HttpServletRequest and HttpServletResponse objects)
//        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
//            HttpServletRequest request = (HttpServletRequest) req;
//            HttpServletResponse response = (HttpServletResponse) res;
//
//            // Access-Control-Allow-Origin
//            String origin = request.getHeader("Origin");
//            response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "");
//            response.setHeader("Vary", "Origin");
//
//            // Access-Control-Max-Age
//            response.setHeader("Access-Control-Max-Age", "3600");
//
//            // Access-Control-Allow-Credentials
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//
//            // Access-Control-Allow-Methods
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//
//            // Access-Control-Allow-Headers
//            response.setHeader("Access-Control-Allow-Headers",
//                    "Origin, X-Requested-With, Content-Type, Accept, " + "X-CSRF-TOKEN");
//        }
//
//        chain.doFilter(req, res);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//}

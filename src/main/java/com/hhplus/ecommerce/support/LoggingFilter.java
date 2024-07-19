package com.hhplus.ecommerce.support;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
@WebFilter("/*")
public class LoggingFilter implements Filter {

    private static final String[] EXCLUDED_PATHS = {
            "/public/"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 코드
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();

        // 예외확인
        for (String excludedPath : EXCLUDED_PATHS) {
            if (requestURI.startsWith(excludedPath)) {
                return;
            }
        }

        LoggedHttpServletRequest loggedRequest = new LoggedHttpServletRequest(httpRequest);
        log.info("[{}] Request Method: {}, URI: {}, Body: {}",
                httpRequest.getRequestURI(),
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                loggedRequest.getBody());

        try {
            chain.doFilter(request, response);
        } finally {
            LoggedHttpServletResponse loggedResponse = new LoggedHttpServletResponse(httpResponse);
            log.info("[{}] Response Status: {}, Body: {}",
                    httpRequest.getRequestURI(),
                    loggedResponse.getStatus(),
                    loggedResponse.getBody());
        }
    }

    @Override
    public void destroy() {
        // 필터 소멸 코드
    }

    // Wrapper for request to log the request body
    private static class LoggedHttpServletRequest extends HttpServletRequestWrapper {

        private final String body;

        public LoggedHttpServletRequest(HttpServletRequest request) throws IOException {
            super(request);
            StringWriter writer = new StringWriter();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                reader.lines().forEach(writer::write);
            }
            this.body = writer.toString();
        }

        public String getBody() {
            return body;
        }
    }

    // Wrapper for response to log the response body
    private static class LoggedHttpServletResponse extends HttpServletResponseWrapper {

        private final StringWriter responseWriter = new StringWriter();
        private final PrintWriter writer = new PrintWriter(responseWriter);

        public LoggedHttpServletResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return writer;
        }

        public String getBody() {
            writer.flush();
            return responseWriter.toString();
        }

        @Override
        public void setStatus(int sc) {
            super.setStatus(sc);
            // Optionally log the status code here if needed
        }
    }
}

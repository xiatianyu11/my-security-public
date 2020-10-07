package com.my.secuirty.core.validate.code;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.my.secuirty.core.properties.SecurityConstants;
import com.my.secuirty.core.properties.SecurityProperties;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	
	@Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
    private SecurityProperties securityProperties;

	@Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrls(), ValidateCodeType.SMS);

    }

    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ","))
                    .forEach(url -> urlMap.put(url, type));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getValidateCodeType(request);

        if (type != null) {
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }


    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.GET.name())) {
            for (Map.Entry<String, ValidateCodeType> entry : urlMap.entrySet()) {
                if (antPathMatcher.match(entry.getKey(), request.getRequestURI())) {
                    result = entry.getValue();
                }
            }
        }
        return result;
    }

}

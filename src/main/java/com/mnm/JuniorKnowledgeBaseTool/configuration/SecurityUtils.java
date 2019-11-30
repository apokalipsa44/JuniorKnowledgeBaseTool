package com.mnm.JuniorKnowledgeBaseTool.configuration;

import com.vaadin.flow.server.ServletHelper;
import com.vaadin.flow.shared.ApplicationConstants;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SecurityUtils {
    public SecurityUtils() {
    }
    public static String getUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public static boolean isAccessGranted(Class<?> securedClass) {
        Secured secured = AnnotationUtils.findAnnotation(securedClass, Secured.class);
                if(secured == null) {
                    return true;
                }

        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
                if(userAuthentication == null) {
                    return false;
                }

        List<String> allowedRoles = Arrays.asList(secured.value());
        return userAuthentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .anyMatch(allowedRoles::contains);
    }

    public static boolean isLogged() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication() != null &&!(context.getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static boolean isFrameworkInternalRequest(HttpServletRequest request) {
        String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
        return parameterValue != null && Stream.of(ServletHelper.RequestType.values())
                .anyMatch(requestType -> requestType.getIdentifier().equals(parameterValue));
    }
}

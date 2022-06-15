package com.prabhat.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenFilter extends ZuulFilter{
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    private static final int      FILTER_ORDER =  1;
    private static final boolean  SHOULD_FILTER=true;

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthTokenPresent(){
    	OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
      if (authDetails.getTokenValue() != null && authDetails.getTokenType()!=null){
          return true;
      }

      return false;
    }

    public Object run() {
    	OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
    	filterUtils.forwardAuthToken(authDetails.getTokenType().concat(" ").concat(authDetails.getTokenValue()));
    	
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug(String.format("Processing incoming request for {}.",  ctx.getRequest().getRequestURI()));
        return null;
    }
}
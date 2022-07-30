package com.prabhat.zuulsvr.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

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
    	try {
    		OAuth2AuthenticationDetails authDetails = (OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
    		filterUtils.forwardAuthToken(authDetails.getTokenType().concat(" ").concat(authDetails.getTokenValue()));

    		RequestContext ctx = RequestContext.getCurrentContext();
    		logger.info("Processing incoming request for {}",  ctx.getRequest().getRequestURI());
    		if (ctx.getRequest().getContentLength() > 0 ) {
    			ObjectMapper mapper = new ObjectMapper();
    			logger.info("Incoming request for {}",  mapper.writeValueAsString(CharStreams.toString(ctx.getRequest().getReader())));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
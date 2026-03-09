package agroscience.fields.controllers;

import agroscience.fields.security.TokenUserContext;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SecurityController {

  default TokenUserContext token() {
    return (TokenUserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}

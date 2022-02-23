package de.zitzmanncedric.dhgepa3.controllers;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class InfoController {

    @GetMapping("/info")
    public String getUserInfo(Model model, Principal principal) {
        KeycloakAuthenticationToken kp = (KeycloakAuthenticationToken) principal;
        SimpleKeycloakAccount simpleKeycloakAccount = (SimpleKeycloakAccount) kp.getDetails();

        AccessToken token = simpleKeycloakAccount.getKeycloakSecurityContext().getToken();

        model.addAttribute("id", token.getId());
        model.addAttribute("username", token.getPreferredUsername());
        model.addAttribute("firstName", token.getGivenName());
        model.addAttribute("lastName", token.getFamilyName());
        model.addAttribute("email", token.getEmail());
        return "info";
    }

}

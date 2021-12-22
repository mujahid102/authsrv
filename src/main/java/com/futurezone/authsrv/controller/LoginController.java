package com.futurezone.authsrv.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {


    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //Not using
    @Deprecated
    @RequestMapping(value="/invalidateToken", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, String> logout(@RequestParam(name = "access_token") String accessToken) {
        //LOGGER.debug("Invalidating token {}", accessToken);
        System.out.println("Invalidating token {}" + accessToken);
        consumerTokenServices.revokeToken(accessToken);
        Map<String, String> ret = new HashMap<>();
        ret.put("access_token", accessToken);
        return ret;
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

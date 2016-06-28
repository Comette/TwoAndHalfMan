package br.com.crescer.wallet.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Hedo
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    String toLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login?logout";
    }
}

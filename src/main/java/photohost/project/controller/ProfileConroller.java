package photohost.project.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photohost.project.dao.UserDAO;
import photohost.project.modul.user.AuthUser;

@Controller

public class ProfileConroller {
    UserDAO userDAO = new UserDAO();
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public ModelAndView profilePage(Model model) throws Exception {
        model.addAttribute("login", AuthUser.getAuthUser().getLogin());
        model.addAttribute("email", AuthUser.getAuthUser().getEmail());

        return new ModelAndView("/profile");
    }

    @RequestMapping(value = "/profile/changePassword")
    public ModelAndView changePassword(@RequestParam("password") String newPassword) throws Exception {
        userDAO.changePassword(AuthUser.getAuthUser().getLogin(),newPassword);
        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(value = "/profile/changeEmail")
    public ModelAndView changeEmail(@RequestParam("email") String newEmail,RedirectAttributes attributes) throws Exception {
        try {
            userDAO.changeEmail(AuthUser.getAuthUser().getLogin(),newEmail);
            return new ModelAndView("redirect:/profile");
        }catch (Exception e){
            attributes.addFlashAttribute("err_msg","Данная почта занята!");
            return new ModelAndView("redirect:/profile");
        }

    }
}

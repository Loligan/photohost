package photohost.project.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.UserDAO;
import photohost.project.entity.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginPage(Model model) {
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/login/error",method = RequestMethod.GET)
    public ModelAndView loginPageError(RedirectAttributes attributes) {
        attributes.addFlashAttribute("err_msg","Неверный логин или пароль");
        return new ModelAndView("redirect:/login");
    }

}

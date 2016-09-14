package photohost.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.ImageDAO;
import photohost.project.dao.UserDAO;
import photohost.project.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterConroller {
    UserDAO userDAO = new UserDAO();
    AlbumDAO albumDAO = new AlbumDAO();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerGet() {
        return new ModelAndView("register","command",new User());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerPost(@ModelAttribute(value = "User") User user, ModelMap model,RedirectAttributes attributes) throws Exception {
        String login = new String(user.getLogin().getBytes("ISO-8859-1"),"UTF-8");
        String password = new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
        String email = new String(user.getEmail().getBytes("ISO-8859-1"),"UTF-8");
        try {

            User newUser = userDAO.createUser(login, password, email);
            albumDAO.createAlbum("Общие", newUser);
            model.addAttribute("login", user.getLogin());
            attributes.addFlashAttribute("ok_msg","Пользователь "+login+" успешно зарегистрирован. Пожалуйста авторизуйтесь");
            return "redirect:/login";
        }
        catch (Exception e){
            attributes.addFlashAttribute("err_msg","Данный логин или почтовый ящик занят!");
            return "redirect:/register";
        }
    }
}

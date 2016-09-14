package photohost.project.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.ImageDAO;
import photohost.project.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    Logger log = Logger.getLogger(AdminController.class);
    AlbumDAO albumDAO = new AlbumDAO();
    ImageDAO imageDAO = new ImageDAO();
    UserDAO userDAO = new UserDAO();
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        model.addAttribute("name",name);

        model.addAttribute("numerAlbum",albumDAO.numberOfAlbums());
        model.addAttribute("numerImage", imageDAO.numberOfImages());
        model.addAttribute("numerUser", userDAO.numberOfUsers());
        log.info("admin: "+user.getUsername()+" entering in /admin page");
        return "admin";
    }
}

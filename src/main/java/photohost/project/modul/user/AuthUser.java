package photohost.project.modul.user;

import org.springframework.security.core.context.SecurityContextHolder;
import photohost.project.dao.UserDAO;
import photohost.project.entity.User;

public class AuthUser {

    public static User getAuthUser () throws Exception {
        UserDAO userDAO = new UserDAO();
        org.springframework.security.core.userdetails.User user_secutiry = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDAO.searchUser(user_secutiry.getUsername());
        return user;
    }
}

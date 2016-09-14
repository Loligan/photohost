package photohost.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import photohost.project.dao.UserDAO;
import photohost.project.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = null;
        Set<GrantedAuthority> roles = new HashSet();
        try {
            String EncodeLogin = new String(login.getBytes("ISO-8859-1"),"UTF-8");
            user = userDAO.searchUser(EncodeLogin);
            String EncodePassword = new String(user.getPassword().getBytes("ISO-8859-1"),"UTF-8");
            roles.add(new SimpleGrantedAuthority(user.getPermissions()));
            UserDetails userDetails =
                    new org.springframework.security.core.userdetails.User(EncodeLogin, EncodePassword, roles);
            return userDetails;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

}

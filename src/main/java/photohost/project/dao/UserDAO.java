package photohost.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import photohost.project.entity.Album;
import photohost.project.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserDAO extends DAO{
    public User createUser(String username, String password, String email) throws Exception {
        try {
            begin();
            password = codePasswordSHA1(password);
            User user = new User(username, password, email);
            getSession().save(user);
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Could not create user "+username,e);
        }
    }

    public User searchUser (String username) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where login = :username");
            q.setString("username",username);
            User user = (User)q.uniqueResult();
            commit();
            return user;
        }catch (HibernateException e){
            rollback();
            throw new Exception("Could not get user "+username,e);
        }
    }


    public void deleteUser(User user) throws Exception {
        try {
            begin();
            getSession().delete(user); /*Удаление пользователя*/
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not delete user " + user.getLogin(), e);
        }
    }


    public boolean changePassword (String login, String newPassword) throws Exception {
        try {
            begin();
            newPassword = codePasswordSHA1(newPassword);
            Query q = getSession().createQuery("from User where login = :login");
            q.setString("login",login);
            User user = (User)q.uniqueResult();
            user.setPassword(newPassword);
            getSession().update(user);
            commit();
            return true;
        }catch (HibernateException e){
            rollback();
            throw new Exception("Could not get user "+login,e);
        }
    }

    public boolean changeEmail (String login, String newEmail) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where login = :login");
            q.setString("login",login);
            User user = (User)q.uniqueResult();
            user.setEmail(newEmail);
            getSession().update(user);
            commit();
            return true;
        }catch (HibernateException e){
            rollback();
            throw new Exception("Could not get user "+login,e);
        }
    }

     public static String codePasswordSHA1(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        password = String.format("%032x", new java.math.BigInteger(1, digest));
        return password;
    }

    public long numberOfUsers(){
        try {
            begin();
            Object result = getSession().createCriteria(User.class).setProjection(Projections.rowCount()).uniqueResult();
            long count = Long.parseLong(result.toString());
            commit();
            return count;
        }catch (HibernateException e){
            rollback();
            return 0;
        }
    }
}
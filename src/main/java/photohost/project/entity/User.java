package photohost.project.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
    private long id_user;
    private String login;
    private String password;
    private String email;
    private String permissions;

    public User(){};

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.permissions = "USER";
    }
    @Column(unique = true)
    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    public long getId_user() {
        return id_user;
    }

    protected void setId_user(long id) {
        this.id_user = id;
    }
}

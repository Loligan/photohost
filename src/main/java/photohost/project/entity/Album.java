package photohost.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album {
    private long id_album;
    private String name;
    private User user;

    public Album() {
    }

    public Album(String name, User user) throws Exception {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue

    public long getId_album() {
        return id_album;
    }

    public void setId_album(long id_album) {
        this.id_album = id_album;
    }
}

package photohost.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    private long id_image;
    private String name;
    private String source;
    private Album album;

    public Image() {
    }

    public Image(String name, String source, Album album) throws Exception {
        this.name = name;
        this.source = source;
        this.album = album;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "album_id_album")

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Id
    @GeneratedValue

    public long getId_image() {
        return id_image;
    }

    public void setId_image(long id_image) {
        this.id_image = id_image;
    }
}

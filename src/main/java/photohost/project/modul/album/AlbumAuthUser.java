package photohost.project.modul.album;


import photohost.project.dao.AlbumDAO;
import photohost.project.entity.Album;
import photohost.project.entity.User;
import photohost.project.modul.user.AuthUser;

import java.util.List;

public class AlbumAuthUser {
    private AlbumDAO albumDAO = new AlbumDAO();

    public List<Album> getAllAlbumAuthUser(User user){
        return albumDAO.getAllUserAlbums(user);
    }

    public List<String> getAllNameAlbumAuthUser(User user){
        return albumDAO.getNamesAllUserAlbums(user);
    }



    public List<String> getAllNameImageInAlbumAuthUser(Album album){
        return albumDAO.getNamesAllUserImagesInAlbums(album);
    }
}

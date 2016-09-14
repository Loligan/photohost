package photohost.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import photohost.project.entity.Album;
import photohost.project.entity.Image;
import photohost.project.entity.User;
import photohost.project.modul.file.LocalFile;

import java.util.List;


public class AlbumDAO extends DAO {
    public Album createAlbum(String name, User user) throws Exception {
        try {
            List <String> albums = getNamesAllUserAlbums(user);
            for(String album_name:albums){
                if (name.equals(album_name)){
                    throw new Exception("Error, name blocked");
                }
            }
            begin();
            Album album = new Album(name,user);
            getSession().save(album);
            commit();
            return album;
        }catch (HibernateException e){
            rollback();
            throw  new Exception("no no", e);
        }
    }

    public Album searchAlbum(String name, User user) throws Exception {
        try {
            begin();
            Query albumQuery = getSession().createQuery("from Album where name = :name and user.id_user=:id_user");
            albumQuery.setString("name",name);
            albumQuery.setLong("id_user", user.getId_user());
            Album album = (Album)albumQuery.uniqueResult();
            commit();
            return album;
        }catch (HibernateException e){
            rollback();
            throw new Exception("search Error",e);
        }
    }

    public boolean renameAlbum(Album album,String newName,User user) throws Exception {
        try {
            List <String> albums = getNamesAllUserAlbums(user);
            for(String album_name:albums){
                if (newName.equals(album_name)){
                    throw new Exception("Error, name blocked");
                }
            }
            begin();
            album.setName(newName);
            getSession().update(album);
            commit();
            return true;
        }catch (HibernateException e){
            rollback();
            return false;
        }
    }

    public List<Album> getAllUserAlbums(User user){
        try {
            begin();

            Query albumQuery = getSession().createQuery("from Album where user= :user ");
            albumQuery.setParameter("user",user);
            List <Album> albums = albumQuery.list();

            commit();
            return albums;
        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getNamesAllUserAlbums(User user){
        try {
            begin();

            Query albumQuery = getSession().createQuery("select name from Album where user= :user ");
            albumQuery.setParameter("user",user);
            List <String> albums = albumQuery.list();

            commit();
            return albums;
        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Image> getAllUserImagesInAlbums(Album album){
        try {
            begin();

            Query imagesQuery = getSession().createQuery("from Image where album= :album");
            imagesQuery.setParameter("album",album);
            List<Image> images= imagesQuery.list();

            commit();
            return images;
        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            return null;
        }
    }
    public List<String> getNamesAllUserImagesInAlbums(Album album){
        try {
            begin();

            Query imagesQuery = getSession().createQuery("select name from Image where album= :album");
            imagesQuery.setParameter("album",album);
            List<String> images= imagesQuery.list();

            commit();
            return images;
        }catch (HibernateException e){
            rollback();
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteAlbum(Album album){
        try {
            List<Image> images = getAllUserImagesInAlbums(album);
            for(Image img:images){
                begin();
                LocalFile.DeleteFileFromLocal(img.getSource());
                getSession().delete(img);
                commit();
            }
            begin();
            getSession().delete(album);
            commit();
            return true;
        } catch (HibernateException e) {
            rollback();
            return false;
        }
    }

    public long numberOfAlbums(){
        try {
            begin();
            Object result = getSession().createCriteria(Album.class).setProjection(Projections.rowCount()).uniqueResult();
            long count = Long.parseLong(result.toString());
            commit();
            return count;
        }catch (HibernateException e){
            rollback();
            return 0;
        }
    }
}

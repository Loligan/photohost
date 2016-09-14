package photohost.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import photohost.project.entity.Album;
import photohost.project.entity.Image;
import photohost.project.modul.file.LocalFile;


public class ImageDAO extends DAO{
    public Image createImage(String name,String source, Album album) throws Exception {
        try {
            begin();
            Image image = new Image(name,source,album);
            getSession().save(image);
            commit();
            return image;
        }catch (HibernateException e){
            e.printStackTrace();
            rollback();
            throw  new Exception("no no", e);
        }
    }

    public Image searchImage(String name) throws Exception {
        try {
            begin();
            Query imageQuery = getSession().createQuery("from Image where name = :name");
            imageQuery.setString("name", name);
            Image image = (Image) imageQuery.uniqueResult();
            return image;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("no no", e);
        }
    }
    public boolean deleteImage(Image image){
        begin();
        try {
            LocalFile.DeleteFileFromLocal(image.getSource());
        getSession().delete(image);
        commit();
        return true;
        }catch (Exception e){
            System.out.println("no delete image");
            rollback();
            return false;
        }
    }

    public long numberOfImages(){
        try {
            begin();
            Object result = getSession().createCriteria(Image.class).setProjection(Projections.rowCount()).uniqueResult();
            long count = Long.parseLong(result.toString());
            commit();
            return count;
        }catch (HibernateException e){
            rollback();
            return 0;
        }
    }

    public void movingToAnotherAlbum(Image image, Album album){
        try {
            begin();
            image.setAlbum(album);
            getSession().update(image);
            commit();
        }catch (HibernateException e){
            rollback();
        }
    }


}

package photohost.project.modul.file;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.ImageDAO;
import photohost.project.dao.UserDAO;
import photohost.project.entity.Album;
import photohost.project.entity.User;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by meldon on 18.05.16.
 */
public class FileUploadAuthUser implements FileUpload {
    ImageDAO imageDAO = new ImageDAO();
    AlbumDAO albumDAO = new AlbumDAO();
    UserDAO userDAO = new UserDAO();
    public boolean writeFileOnLocalFolder(MultipartFile file, String fileName, String rootPath, Album album) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                String fileDir = (dir.getAbsolutePath() + File.separator + fileName).replace("/", "//");
                imageDAO.createImage(fileName, fileDir, album);

                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public Album getAlbumAuthUser(String albumName) throws Exception {

        org.springframework.security.core.userdetails.User user_secutiry = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDAO.searchUser(user_secutiry.getUsername());;
//                Album album = albumDAO.searchAlbum(album_name);

        Album album = albumDAO.searchAlbum(albumName,user);
        return album;
    }
}

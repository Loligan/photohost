package photohost.project.modul.file;

import org.springframework.web.multipart.MultipartFile;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.ImageDAO;
import photohost.project.dao.UserDAO;
import photohost.project.entity.Album;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by meldon on 18.05.16.
 */
public class FileUploadAnonimousUser implements FileUpload {
    private ImageDAO imageDAO = new ImageDAO();
    private AlbumDAO albumDAO = new AlbumDAO();
    private UserDAO userDAO = new UserDAO();

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
        Album album = albumDAO.searchAlbum(albumName,
                                            userDAO.searchUser("ANONIMOUS"));
        return album;
    }
}

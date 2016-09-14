package photohost.project.modul.file;

import org.springframework.web.multipart.MultipartFile;
import photohost.project.entity.Album;

import java.util.UUID;

/**
 * Created by meldon on 18.05.16.
 */
public interface FileUpload {
    public static final String ROOT_PATH = "//home//meldon//photohosting_store//img";

    boolean writeFileOnLocalFolder(MultipartFile file, String fileName, String rootPath, Album album);

    Album getAlbumAuthUser(String albumName) throws Exception;

    static String generateFileName(){
        String fileName;
        UUID id1 = UUID.randomUUID();
        fileName = id1.toString();
        return fileName;
    }

}

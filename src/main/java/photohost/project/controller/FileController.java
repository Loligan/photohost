package photohost.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photohost.project.entity.Album;
import photohost.project.modul.file.FileUpload;
import photohost.project.modul.file.FileUploadAuthUser;
import photohost.project.modul.user.AuthUser;


@Controller
public class FileController {
    private static final Logger log = Logger.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile/{album_name}", method = RequestMethod.POST)
    private String uploadFile(@RequestParam("file") MultipartFile file,@PathVariable("album_name") String album_name) throws Exception {
        String fileName = FileUpload.generateFileName();
        FileUpload fileUpload = new FileUploadAuthUser();
        Album album = fileUpload.getAlbumAuthUser(album_name);

        if (fileUpload.writeFileOnLocalFolder(file, fileName, FileUpload.ROOT_PATH+"//"+AuthUser.getAuthUser().getLogin()+"//"+album_name, album)) {
            log.info("file "+fileName+" upload by "+AuthUser.getAuthUser().getLogin()+" to"+album_name);
            return "redirect:/image/" + fileName;
        }

        return "error";
    }
}
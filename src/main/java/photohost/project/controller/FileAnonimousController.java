package photohost.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import photohost.project.entity.Album;
import photohost.project.modul.file.FileUpload;
import photohost.project.modul.file.FileUploadAnonimousUser;

@Controller
public class FileAnonimousController {
    private static final Logger log = Logger.getLogger(FileAnonimousController.class);
    FileUpload fileUpload = new FileUploadAnonimousUser();

    @RequestMapping(value = "/uploadAnonimousFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String fileName = FileUpload.generateFileName();
        Album album = fileUpload.getAlbumAuthUser("ANONIMOUS");
        if (fileUpload.writeFileOnLocalFolder(file, fileName, FileUpload.ROOT_PATH+"//ANONIMOUS", album)) {
            log.info("file "+fileName+" upload Anonimous user");
            return "redirect:/image/" + fileName;
        }
        return "error";

    }
}

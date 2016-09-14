package photohost.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import photohost.project.dao.AlbumDAO;
import photohost.project.dao.ImageDAO;
import photohost.project.entity.Album;
import photohost.project.entity.Image;
import photohost.project.modul.album.AlbumAuthUser;
import photohost.project.modul.user.AuthUser;

import java.io.File;
import java.util.List;

@Controller
public class ImageConroller {
    private static final ImageDAO imageDAO = new ImageDAO();
    private static final AlbumDAO albumDAO = new AlbumDAO();
    private static final Logger log = Logger.getLogger(ImageConroller.class);

    @RequestMapping(value = "/image/{image_name}",method = RequestMethod.GET)
    public String imagePage(@PathVariable("image_name") String image_name,Model model){
        model.addAttribute("img_link","/img/"+image_name);
        model.addAttribute("img_view","/image/"+image_name);
        log.info("image "+image_name+" view");
        return "image";
    }

    @RequestMapping(value = "/drop/{album_name}/{image_name}",method = RequestMethod.POST)
    public String imageDrop(@PathVariable("image_name") String image_name,
                            @PathVariable("album_name") String album_name) throws Exception {

        Image image = imageDAO.searchImage(image_name);
        imageDAO.deleteImage(image);
        log.info("image "+image_name+" droped");
        return "redirect:/album/"+new String(album_name.getBytes("UTF-8"),"LATIN1");

    }

    @RequestMapping(value = "/moving/{image_name}/{album_name}", method = RequestMethod.POST)
    public String movingAlbum(@PathVariable("image_name") String image_name,
                              @PathVariable("album_name") String album_name) throws Exception {
        Album newAlbum = albumDAO.searchAlbum(album_name, AuthUser.getAuthUser());
        Image image = imageDAO.searchImage(image_name);
        imageDAO.movingToAnotherAlbum(image,newAlbum);
        log.info("image "+image_name+" moving to '"+album_name+"'");
        return "redirect:/album/"+new String(album_name.getBytes("UTF-8"),"LATIN1");
    }
}

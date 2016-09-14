package photohost.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photohost.project.dao.AlbumDAO;
import photohost.project.entity.Album;
import photohost.project.entity.User;
import photohost.project.modul.album.AlbumAuthUser;
import photohost.project.modul.user.AuthUser;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AlbumController {

    Logger log = Logger.getLogger(AlbumController.class);
    AlbumDAO albumDAO = new AlbumDAO();
    AlbumAuthUser albumAuthUser = new AlbumAuthUser();
    public static final String ALBUM_NAME_STANDART = "Общие";

    @RequestMapping(value = "/album/{album_name}", method = RequestMethod.GET)
    public ModelAndView getTestForm(Model model, @PathVariable("album_name") String album_name,RedirectAttributes attributes) throws Exception {
        try {
        List<String> albums = albumAuthUser.getAllNameAlbumAuthUser(AuthUser.getAuthUser());


        Album album = albumDAO.searchAlbum(album_name,
                AuthUser.getAuthUser());
        List<String> images = albumAuthUser.getAllNameImageInAlbumAuthUser(album);

        model.addAttribute("albumsNameList", albums);
        model.addAttribute("name", AuthUser.getAuthUser().getLogin());
        model.addAttribute("images_album", images);

        log.info(album.getName()+ "album get list images");

        return new ModelAndView("/album");

        }catch (Exception e){
            attributes.addFlashAttribute("err_msg","Альбома "+album_name+" не существует!");
            return new ModelAndView("redirect:/album/"+new String(ALBUM_NAME_STANDART.getBytes("UTF-8"),"LATIN1"));
        }
    }

    @RequestMapping(value = "/create_album", method = RequestMethod.POST)
    public ModelAndView createAlbum(@RequestParam("name") String nameISO, RedirectAttributes mod) throws UnsupportedEncodingException {
        String name = new String(nameISO.getBytes("ISO-8859-1"),"UTF-8");
        try {
            User user = AuthUser.getAuthUser();
            albumDAO.createAlbum(name, user);
            ModelAndView model = new ModelAndView("redirect:/album/"+nameISO);

            mod.addFlashAttribute("ok_msg","Альбом "+name+" успешно создан");
            log.info("album '"+name+"' created from  "+user.getLogin()+" user");
            return model;
        }catch (Exception e){
            ModelAndView model = new ModelAndView("redirect:/album/"+new String(ALBUM_NAME_STANDART.getBytes("UTF-8"),"LATIN1"));
            mod.addFlashAttribute("err_msg","Альбом с таким именем существует!");
            return model;

        }

    }

    @RequestMapping(value = "/drop_album/{album_name}", method = RequestMethod.POST)
    public ModelAndView dropAlbum(@PathVariable("album_name") String album_name, RedirectAttributes attributes) throws Exception {
        if(album_name.equals(ALBUM_NAME_STANDART)){
            attributes.addFlashAttribute("err_msg","Данный альбом невозможно удалить!");
            log.error("album '" + album_name + "' owned by " + AuthUser.getAuthUser().getLogin() + "' not droped");
            return new ModelAndView("redirect:/album/"+new String(ALBUM_NAME_STANDART.getBytes("UTF-8"),"LATIN1"));
        }else {
            Album album = albumDAO.searchAlbum(album_name, AuthUser.getAuthUser());
            ModelAndView model = new ModelAndView("redirect:/album/"+ALBUM_NAME_STANDART);
            model.addObject("info_drop", albumDAO.deleteAlbum(album));

            attributes.addFlashAttribute("ok_msg", "Альбом " + album_name + " успешно удален");
            log.info("album '" + album_name + "' owned by " + AuthUser.getAuthUser().getLogin() + "' droped");
            return new ModelAndView("redirect:/album/"+new String(ALBUM_NAME_STANDART.getBytes("UTF-8"),"LATIN1"));
        }
    }

    @RequestMapping(value = "/rename/{album_name}", method = RequestMethod.POST)
    public ModelAndView renameAlbum(@PathVariable("album_name") String album_name, @RequestParam("new_album_name") String newAlbumNameISO,RedirectAttributes attributes) throws Exception {
        String newAlbumName = new String(newAlbumNameISO.getBytes("ISO-8859-1"),"UTF-8");
        if(album_name.equals(ALBUM_NAME_STANDART)){
            attributes.addFlashAttribute("err_msg","Имя данного альбома невозможно изменить!");
            return new ModelAndView("redirect:/album/"+new String(ALBUM_NAME_STANDART.getBytes("UTF-8"),"LATIN1"));
        }

        try {
            Album album = albumDAO.searchAlbum(album_name,AuthUser.getAuthUser());
            albumDAO.renameAlbum(album,newAlbumName,AuthUser.getAuthUser());
            ModelAndView model = new ModelAndView("redirect:/album/"+newAlbumNameISO);
            log.info("album '"+newAlbumNameISO+"' owned by "+AuthUser.getAuthUser().getLogin()+" rename on '"+newAlbumName+"'");
            attributes.addFlashAttribute("ok_msg", "Альбом  '" + album_name + "' успешно переименован в '" + newAlbumName + "'");
            return model;
        }catch (Exception e){
            attributes.addFlashAttribute("err_msg","Данное имя уже занято!");
            log.info("album '"+newAlbumNameISO+"' owned by "+AuthUser.getAuthUser().getLogin()+"not rename rename");
            return new ModelAndView("redirect:/album/"+album_name);
        }

    }

}

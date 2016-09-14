package photohost.project.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import photohost.project.dao.ImageDAO;
import photohost.project.entity.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;


@Controller
@ComponentScan
public class ImgController {
    private ImageDAO imageDAO = new ImageDAO();

    @RequestMapping(value = "/img/{image_name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception {

        Image image = imageDAO.searchImage(image_name);
        File img = new File(image.getSource());
        BufferedImage original_img = ImageIO.read(img);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(original_img,"jpg",baos);
        byte[] imageContent = baos.toByteArray();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }
}

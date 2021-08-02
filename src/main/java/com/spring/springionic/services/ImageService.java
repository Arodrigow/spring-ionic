package com.spring.springionic.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.spring.springionic.services.exceptions.FileException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    public BufferedImage getJpgFromFile(MultipartFile uploadedFile){
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
        if(!"png".equals(ext) && !"jpg".equals(ext)){
            throw new FileException("Only PNG and JPG allowed");
        }

        try{
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
            if("png".equals(ext)){
                img = pngToJpg(img);
            }
            return img;
        }catch(IOException e){
            throw new FileException("Error reading the file");
        }
    }

    private BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extension){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Error reading tile");
        }
    }
}

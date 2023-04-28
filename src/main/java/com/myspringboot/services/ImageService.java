package com.myspringboot.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myspringboot.services.exceptions.FileException;

@Service
public class ImageService {
	
    public InputStream getInputStream(BufferedImage buf, String extension) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buf, extension, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }
    
    public BufferedImage getJpgImageFromFile(MultipartFile mf) throws IOException {
    	String ext = FilenameUtils.getExtension(mf.getOriginalFilename());
    	if (!"png".equals(ext) && !"jpg".equals(ext)) {
    		throw new FileException("Somente imagens JPG e PNG são permitidas");
    	}
    	BufferedImage buf = ImageIO.read(mf.getInputStream());
    	if ("png".equals(ext)) {
    		buf = pngToJpg(buf);
    	}
    	return buf;
    }
	
	private BufferedImage pngToJpg(BufferedImage buf) {
		BufferedImage jpgImage = new BufferedImage(buf.getWidth(), buf.getHeight(), 
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(buf, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public BufferedImage cropSquare(BufferedImage buf) {
		int min = (buf.getHeight() <= buf.getWidth()) ? buf.getHeight(): buf.getWidth();
		return Scalr.crop(buf, 
				(buf.getWidth()/2) - (min/2), 
				(buf.getHeight()/2) - (min/2),
				min,
				min);
	}
	public BufferedImage resize(BufferedImage buf, int size) {
		return Scalr.resize(buf, Scalr.Method.ULTRA_QUALITY ,size);
	}

}

package duan1.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import duan1.config.Config;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

public class NextImage {
    public ImageIcon load(String url, int w, int h) throws Exception {
        if(url.contains("http")) { //* NETWORK */
            String[] imagePath = url.split("/");
            String fileName = imagePath[imagePath.length - 1];
    
            ImageIcon image;
            image = loadCache(fileName, w, h);
    
            if(image == null) image = loadNetwork(url, fileName, w, h);
    
            return image;
        }else{ //* LOCAL */
            URL filePath = new File(url).toURI().toURL();
            ImageIcon imageIcon = new ImageIcon(filePath);
            Image image = imageIcon.getImage();
            image = getScaledImage(image, w, h);

            imageIcon = new ImageIcon(image);

            return imageIcon;
        }
    }

    private ImageIcon loadCache(String fileName, int w, int h) throws MalformedURLException {
        File imageFile = new File(Config.CACHE_PATH + fileName);
        URL filePath = new File(Config.CACHE_PATH + fileName).toURI().toURL();

        if(imageFile.exists() && !imageFile.isDirectory()) { 
            ImageIcon imageIcon = new ImageIcon(filePath);
            Image image = imageIcon.getImage();
            image = getScaledImage(image, w, h);

            imageIcon = new ImageIcon(image);

            return imageIcon;
        }

        return null;
    }

    private ImageIcon loadNetwork(String url, String fileName, int w, int h) throws IOException {
        //* LOAD IMAGE */
        URL imageUrl = new URL(url);
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        Image image = imageIcon.getImage();
        image = getScaledImage(image, w, h);

        imageIcon = new ImageIcon(image);

        //* CACHE IMAGE */
        cacheImage(fileName, imageIcon.getImage());
        
        return imageIcon;
    }

    private void cacheImage(String fileName, Image image) throws IOException {
        //* CONVER IMAGE */
        Image img = image;
        String[] fileContentTypeSplited = URLConnection.guessContentTypeFromName(new File(fileName).getName()).split("/");
        String fileType = fileContentTypeSplited[1];
        BufferedImage bufferImage = new BufferedImage(image.getWidth(null), image.getHeight(null), fileType == "png" ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        File cacheImage = new File(Config.CACHE_PATH + fileName);

        Graphics2D g2 = bufferImage.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        //* CREATE CACHE FOLDER */
        Files.createDirectories(Paths.get(Config.CACHE_PATH));

        ImageIO.write(bufferImage, fileType, cacheImage);
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }
}

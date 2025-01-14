package com.gviktor.controller.io;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

public class ImageIOManager {

    public static Image loadImage(String url) {
        Image img = null;
        try {
            FileInputStream istream = new FileInputStream(url);
            img = new Image(istream);
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if( null==img) {
                return img;
            }else{
                return img;
            }
        }
    }

    public static void saveImage(String filePath,Image image) throws IOException {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        BufferedImage bufferedImage = getBufferedImage(image, width, height);

        File file = new File(filePath);
        String format = "png";
        // Write BufferedImage to file
        System.out.println("writing image");
        ImageIO.write(bufferedImage, format, file);
    }

    public static String generateRandomImageName(){
        String chars ="abcdefghijklmnopqrstz";
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        System.out.println(random.nextInt(chars.length()));
        System.out.println(random.nextInt(chars.length()));
        System.out.println(random.nextInt(chars.length()));
        System.out.println(random.nextInt(chars.length()));
        System.out.println(random.nextInt(chars.length()));
        System.out.println(random.nextInt(chars.length()));

        result.append("output")
                .append(chars.charAt(random.nextInt(chars.length())))
                .append(chars.charAt(random.nextInt(chars.length())))
                .append(chars.charAt(random.nextInt(chars.length())))
                .append(chars.charAt(random.nextInt(chars.length())))
                .append(".png");
        return result.toString();
    }
    private static BufferedImage getBufferedImage(Image image, int width, int height) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        PixelReader pixelReader = image.getPixelReader();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        byte[] buffer = new byte[width * height * 4];
        pixelReader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = (y * width + x) * 4;
                int argb = ((buffer[i + 3] & 0xFF) << 24) | // Alpha
                        ((buffer[i + 2] & 0xFF) << 16) | // Red
                        ((buffer[i + 1] & 0xFF) << 8)  | // Green
                        (buffer[i] & 0xFF);             // Blue
                bufferedImage.setRGB(x, y, argb);
            }
        }
        return bufferedImage;
    }

}

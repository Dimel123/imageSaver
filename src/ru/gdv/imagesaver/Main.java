package ru.gdv.imagesaver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {

    private static Window window;

    private static BufferedImage image;

    public static void main(String[] args){
        window = new Window(640, 480);
    }

    public static void setImage(URL url){
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setImage(File file){
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveImage(File file, String format){
        try {
            ImageIO.write(image, format, file);
            file.renameTo(new File(file.getPath()+"."+format));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Window getWindow() {
        return window;
    }

    public static void setWindow(Window window) {
        Main.window = window;
    }

    public static BufferedImage getImage() {
        return image;
    }

    public static void setImage(BufferedImage image) {
        Main.image = image;
    }


}

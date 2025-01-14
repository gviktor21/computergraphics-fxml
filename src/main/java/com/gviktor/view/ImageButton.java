package com.gviktor.view;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class ImageButton extends Button {
    private Image image;
    private ImageView imageView;

    public ImageButton(String URL){
        super();
        image = ImageButton.loadImage(URL);
        this.imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        this.setGraphic(imageView);
        setAlignment(Pos.CENTER);
        setCache(true);
        setPrefSize(5,5);
    }

    private static Image loadImage(String url) {
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
}

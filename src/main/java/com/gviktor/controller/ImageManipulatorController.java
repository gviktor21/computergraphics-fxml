package com.gviktor.controller;

import com.gviktor.controller.io.ImageIOManager;
import com.gviktor.model.DrawingTriangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

//Todo Refactor it
public class ImageManipulatorController extends CanvasController {

    private String imageUrl = "images/IMG_20220626_094222.jpg";

    public ImageManipulatorController(GraphicsContext graphicsContext, Canvas drawingCanvas, ToolBar toolBar) {
        super(graphicsContext, drawingCanvas, toolBar);
    }

    @Override
    public void drawScene() {
        System.out.println("Draw image");
        Image image = ImageIOManager.loadImage(imageUrl);
        Image edgedetected = matToImage(doCanny(imageToMat(image),10,3));
        //Image imageToShow = new Image("file:images/IMG_20220626_094247.jpg", 800, 800, true, false);
        ImageView imageView = new ImageView(edgedetected);
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        imageView.setPreserveRatio(true);
        createEdgedetectedImage(image,30,2);
        createEdgedetectedImage(image,20,5);
        createEdgedetectedImage(image,30,4);
        createEdgedetectedImage(image,50,6);
        createEdgedetectedImage(image,70,1);
        createEdgedetectedImage(image,80,2);
        createEdgedetectedImage(image,45,2);
        createEdgedetectedImage(image,55,4);
        createEdgedetectedImage(image,70,2);

        graphicsContext.drawImage(imageView.getImage(),0,0);
    }

    private void createEdgedetectedImage(Image image,int threshold,int multiplier) {
        Image edgedetected2 = matToImage(doCanny(imageToMat(image),threshold,multiplier));
        saveImage(edgedetected2);
    }

    private static void saveImage(Image edgedetected) {
        try {
            ImageIOManager.saveImage("images/"+ImageIOManager.generateRandomImageName(), edgedetected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Mat doCanny(Mat image,int treshold,int multiplier)
    {
        // init
        Mat grayImage = new Mat();
        Mat detectedEdges = new Mat();

        // convert to grayscale
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // reduce noise with a 3x3 kernel
        Imgproc.blur(grayImage, detectedEdges, new Size(3, 3));

        // canny detector, with ratio of lower:upper threshold of 3:1
        Imgproc.Canny(detectedEdges, detectedEdges, treshold, treshold * multiplier);

        // using Canny's output as a mask, display the result
        Mat dest = new Mat();
        image.copyTo(dest, detectedEdges);

        return dest;
    }
    private Mat imageToMat(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        byte[] buffer = new byte[width * height * 4];
        PixelReader reader = image.getPixelReader();
        WritablePixelFormat<ByteBuffer> format = WritablePixelFormat.getByteBgraInstance();
        reader.getPixels(0, 0, width, height, format, buffer, 0, width * 4);
        Mat mat = new Mat(height, width, CvType.CV_8UC4);
        mat.put(0, 0, buffer);
        return mat;
    }
    public Image matToImage(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    private Image greyImage(Image original){
        return null;
    }
    @Override
    public void initScene() {
        System.out.println("init scene");
        graphicsContext.setFill(Color.GREENYELLOW);
        graphicsContext.fillRect(0,0,750,750);
        graphicsContext.setStroke(Color.BLACK);
    }

    @Override
    public void clear() {

    }

}

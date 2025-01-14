package com.gviktor.controller;

import com.gviktor.model.DrawingTriangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class DrawingTriangleController extends CanvasController {


    public DrawingTriangleController(GraphicsContext graphicsContext, Canvas drawingCanvas, ToolBar toolBar) {
        super(graphicsContext, drawingCanvas, toolBar);
    }

    @Override
    public void drawScene() {
        DrawingTriangle drawingTriangle = new DrawingTriangle();
        float p=0.05f;
        drawingTriangle.generatePoints((float) drawingCanvas.getWidth(), (float) drawingCanvas.getHeight(),p,1-p);
        drawingTriangle.drawFigure(graphicsContext);
        graphicsContext.setStroke(Color.BLUE);
        DrawingTriangle drawingTriangle2 = new DrawingTriangle();
        p=0.1f;
        drawingTriangle2.generatePoints((float) drawingCanvas.getWidth(), (float) drawingCanvas.getHeight(),p,1-p);
        //drawingTriangle2.drawFigure(graphicsContext);
    }

    @Override
    public void initScene() {
        graphicsContext.setFill(Color.GREENYELLOW);
        graphicsContext.fillRect(0,0,750,750);
        graphicsContext.setStroke(Color.BLACK);
    }

    @Override
    public void clear() {

    }

}

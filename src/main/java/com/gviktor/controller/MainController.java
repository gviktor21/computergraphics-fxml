package com.gviktor.controller;

import com.gviktor.util.CoordinateComputer;
import com.gviktor.view.ImageButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private Canvas drawingCanvas;

    @FXML
    private ToolBar mainToolBar;

    private CoordinateComputer coordinateComputer;

    private CanvasControllerOperator canvasControllerOperator;
    float pixelSize=1.0f;

    public void initialize() {
        CoordinateComputer.centerX= (int) (drawingCanvas.getWidth()/2);
        CoordinateComputer.centerY= (int) (drawingCanvas.getHeight()/2);
        CoordinateComputer.pixelSize=pixelSize;
        CoordinateComputer.maxY= (int) drawingCanvas.getHeight();

        GraphicsContext graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.GREENYELLOW);
        graphicsContext.fillRect(0,0,750,750);
        canvasControllerOperator = new CanvasControllerOperator();
        mainToolBar.getItems().add(new ImageButton("icons/new.png"));

    }
    private void drawTriangles(GraphicsContext graphicsContext){
      canvasControllerOperator.applyController(new DrawingTriangleController(graphicsContext, drawingCanvas, mainToolBar));
      canvasControllerOperator.getCurrentController().drawScene();

    }
    @FXML
    public void handleMenu(){
        drawTriangles(drawingCanvas.getGraphicsContext2D());
    }

    public void handleCanvasClick(MouseEvent mouseEvent) {
        double x,y;
        x= mouseEvent.getX();
        y= mouseEvent.getY();
        System.out.println(x+", "+y);
    }

    public void interpolation(ActionEvent actionEvent) {
        canvasControllerOperator.applyController(new InterpolationController(drawingCanvas.getGraphicsContext2D(),drawingCanvas, mainToolBar));
    }

    public void polygonDrawer(ActionEvent actionEvent) {
        canvasControllerOperator.applyController(new DefinePolyController(drawingCanvas.getGraphicsContext2D(),drawingCanvas, mainToolBar));
    }
    public void imageManipulator(ActionEvent actionEvent) {
        canvasControllerOperator.applyController(new ImageManipulatorController(drawingCanvas.getGraphicsContext2D(),drawingCanvas, mainToolBar));
        canvasControllerOperator.getCurrentController().drawScene();
    }
}
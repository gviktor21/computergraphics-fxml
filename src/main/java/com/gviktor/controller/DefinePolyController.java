package com.gviktor.controller;

import com.gviktor.controller.listener.DefinePolygonEventHandler;
import com.gviktor.controller.toolbar.DefinePolyToolBarController;
import com.gviktor.model.Polygon;
import com.gviktor.model.PolygonType;
import com.gviktor.model.PolygonDrawer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DefinePolyController extends CanvasController{

    private PolygonDrawer polygonDrawer;
    private DefinePolyToolBarController toolBarController;
    private DefinePolygonEventHandler definePolygonEventHandler;

    public DefinePolyController(GraphicsContext graphicsContext, Canvas drawingCanvas, ToolBar toolBar) {
        super(graphicsContext, drawingCanvas, toolBar);
    }

    @Override
    public void drawScene() {

    }

    @Override
    public void initScene() {
        GraphicsContext graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setFill(Color.BLACK);
        Polygon polygon = new Polygon(PolygonType.FIRST_SELF_CONTAINED) {
            @Override
            public void updateVertexes() {
            }

        };
        polygonDrawer = new PolygonDrawer(polygon);
        toolBarController = new DefinePolyToolBarController(drawingCanvas,getToolBar(),polygonDrawer);
        definePolygonEventHandler = new DefinePolygonEventHandler(drawingCanvas,polygonDrawer);
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED,definePolygonEventHandler);
    }

    @Override
    public void clear() {
        drawingCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, definePolygonEventHandler);
        toolBarController.clear();
    }
}

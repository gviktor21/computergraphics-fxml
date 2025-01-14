package com.gviktor.controller.listener;

import com.gviktor.model.Point2D;
import com.gviktor.model.Polygon;
import com.gviktor.model.PolygonDrawer;
import com.gviktor.util.CoordinateComputer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DefinePolygonEventHandler implements EventHandler<MouseEvent> {

    private Canvas  drawingCanvas;
    private PolygonDrawer polygonDrawer;

    public DefinePolygonEventHandler(Canvas drawingCanvas, PolygonDrawer polygonDrawer) {
        this.drawingCanvas = drawingCanvas;
        this.polygonDrawer = polygonDrawer;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        GraphicsContext graphicsContext = drawingCanvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.GREENYELLOW);
        graphicsContext.fillRect(0,0,750,750);
        graphicsContext.setFill(Color.RED);
        Polygon polygon = polygonDrawer.getPolygon();

        Point2D point2D = new Point2D(CoordinateComputer.deviceToLogicalIsoTropicX((int) mouseEvent.getX()) , CoordinateComputer.deviceToLogicalIsoTropicY((int) mouseEvent.getY()));
        polygon.addVertex(point2D);
        polygonDrawer.paint(drawingCanvas.getGraphicsContext2D());
        if (polygonDrawer.getPolygon().isReady()){
            drawingCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,this);
        }
    }
}

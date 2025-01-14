package com.gviktor.controller.listener;

import com.gviktor.model.Point2D;
import com.gviktor.model.Polygon;
import com.gviktor.model.PolygonDrawer;
import com.gviktor.util.CoordinateComputer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class PointInPolygonEventHandler implements EventHandler<MouseEvent> {

    private Canvas drawingCanvas;
    private PolygonDrawer polygonDrawer;

    public PointInPolygonEventHandler(Canvas drawingCanvas, PolygonDrawer polygonDrawer) {
        this.drawingCanvas = drawingCanvas;
        this.polygonDrawer = polygonDrawer;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Point2D point2D = new Point2D(CoordinateComputer.deviceToLogicalIsoTropicX((int) mouseEvent.getX()) , CoordinateComputer.deviceToLogicalIsoTropicY((int) mouseEvent.getY()));;
        boolean result = CoordinateComputer.insidePolygon(point2D,polygonDrawer.getPolygon());
        System.out.println("Point is inside Polygon: "+ result);
    }
}
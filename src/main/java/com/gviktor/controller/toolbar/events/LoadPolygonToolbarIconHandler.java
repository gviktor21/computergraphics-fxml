package com.gviktor.controller.toolbar.events;

import com.gviktor.controller.io.PolygonIOManager;
import com.gviktor.model.Polygon;
import com.gviktor.model.PolygonDrawer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

public class LoadPolygonToolbarIconHandler implements EventHandler<MouseEvent> {

    private PolygonDrawer polygonDrawer;
    private Canvas canvas;
    public LoadPolygonToolbarIconHandler(PolygonDrawer polygonDrawer, Canvas canvas) {
        this.polygonDrawer = polygonDrawer;
        this.canvas = canvas;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            Polygon polygon = PolygonIOManager.loadPolygon("kmkm.poly");
            System.out.println(polygon.getVertexes().size());
            polygonDrawer = new PolygonDrawer(polygon);
            polygonDrawer.paint(canvas.getGraphicsContext2D());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
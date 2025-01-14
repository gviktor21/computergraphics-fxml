package com.gviktor.controller.toolbar.events;

import com.gviktor.controller.listener.PointInPolygonEventHandler;
import com.gviktor.model.PolygonDrawer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class PointInPolygonToolbarIconHandler implements EventHandler<MouseEvent> {

    private PolygonDrawer polygonDrawer;

    private PointInPolygonEventHandler pointInPolygonEventHandler;

    private Canvas canvas;

    public PointInPolygonToolbarIconHandler(PolygonDrawer polygonDrawer, Canvas canvas) {
        this.polygonDrawer = polygonDrawer;
        this.pointInPolygonEventHandler = new PointInPolygonEventHandler(canvas,polygonDrawer);
        this.canvas = canvas;

    }

    private void clear(){
        if (pointInPolygonEventHandler != null){
            canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED,pointInPolygonEventHandler);
        }
    }

    @Override
    public void handle(MouseEvent event) {
        if (polygonDrawer.getPolygon().isReady() && pointInPolygonEventHandler == null){
            System.out.println("now pointInPolygon tests");
            pointInPolygonEventHandler = new PointInPolygonEventHandler(canvas,polygonDrawer);
            canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,pointInPolygonEventHandler);
        }
    }
}
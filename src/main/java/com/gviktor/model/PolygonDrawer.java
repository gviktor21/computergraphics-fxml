package com.gviktor.model;

import com.gviktor.util.CoordinateComputer;
import javafx.scene.canvas.GraphicsContext;


public class PolygonDrawer {
    private final Polygon polygon;

    public PolygonDrawer( Polygon polygon) {
        this.polygon = polygon;
    }

    public void paint(GraphicsContext graphicsContext) {
        int n = polygon.size();
        if(n>0) {
            for(int i = 0; i < n;i++) {
                Point2D b =(Point2D) polygon.getVertexOfIndex(i);
                int x = CoordinateComputer.logicalToDeviceIsotropicX(b.getX());
                int y = CoordinateComputer.logicalToDeviceIsotropicY(b.getY());
                graphicsContext.fillRect(x - 2,y - 2,4,4);
            }
        }
        if(n >= 2 ) {
            drawPolygon(graphicsContext);
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void drawPolygon(GraphicsContext graphicsContext) {
        polygon.updateVertexes();
        int n = polygon.size();
        Point2D a =(Point2D) polygon.getVertexOfIndex(0);
        int x,y;
        for(int i = 1; i < n;i++) {
            Point2D b =(Point2D) polygon.getVertexOfIndex(i);
            x =CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
            y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
            a=b;
            graphicsContext.strokeLine(x,y,CoordinateComputer.logicalToDeviceIsotropicX(b.getX()), CoordinateComputer.logicalToDeviceIsotropicY(b.getY()));
        }
        if (polygon.isReady()){
            int last_x =CoordinateComputer.logicalToDeviceIsotropicX(a.getX());
            int last_y = CoordinateComputer.logicalToDeviceIsotropicY(a.getY());
            Point2D first = (Point2D) polygon.getVertexOfIndex(0);
            graphicsContext.strokeLine(last_x,last_y,CoordinateComputer.logicalToDeviceIsotropicX(first.getX()), CoordinateComputer.logicalToDeviceIsotropicY(first.getY()));
        }
    }
}

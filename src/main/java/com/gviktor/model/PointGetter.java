package com.gviktor.model;

import com.gviktor.util.CoordinateComputer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class PointGetter  implements EventHandler<MouseEvent> {

    private List<Point> pointList;

    public PointGetter(){
        pointList = new ArrayList<>();
    }
    @Override
    public void handle(MouseEvent event) {
        Point2D point2D = new Point2D(CoordinateComputer.deviceToLogicalIsoTropicY((int) event.getX()) ,CoordinateComputer.logicalToDeviceIsotropicY((int) event.getY()));
        pointList.add(point2D);
    }
}

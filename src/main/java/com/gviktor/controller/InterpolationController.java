package com.gviktor.controller;

import com.gviktor.model.Point2D;
import com.gviktor.model.Polygon;
import com.gviktor.model.interpolation.Bezier;
import com.gviktor.model.interpolation.Interpolation;
import com.gviktor.model.PolygonDrawer;
import com.gviktor.model.interpolation.fittingmethods.CurveFittingMethod;
import com.gviktor.model.interpolation.fittingmethods.FittingMethodFactory;
import com.gviktor.util.CoordinateComputer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class InterpolationController extends CanvasController {

    private EventHandler<MouseEvent> eventHandler;

    public InterpolationController(GraphicsContext graphicsContext, Canvas drawingCanvas, ToolBar toolBar) {
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
        Interpolation interpolation = new Bezier();
        Polygon curve = FittingMethodFactory.createCurve(CurveFittingMethod.BEZIER);
        PolygonDrawer polygonDrawer = new PolygonDrawer(curve);
        eventHandler = mouseEvent -> {
            graphicsContext.setFill(Color.GREENYELLOW);
            graphicsContext.fillRect(0,0,750,750);
            graphicsContext.setFill(Color.RED);
            System.out.println("The click: "+(int)mouseEvent.getX()+", "+(int)mouseEvent.getY());
            Point2D point2D = new Point2D(CoordinateComputer.deviceToLogicalIsoTropicX((int) mouseEvent.getX()) , CoordinateComputer.deviceToLogicalIsoTropicY((int) mouseEvent.getY()));
            interpolation.addControlPoint(point2D);
            curve.addVertex(point2D);
            polygonDrawer.paint(drawingCanvas.getGraphicsContext2D());
        };
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
    }

    @Override
    public void clear() {
        drawingCanvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
}

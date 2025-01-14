package com.gviktor.controller.toolbar;

import com.gviktor.controller.toolbar.events.LoadPolygonToolbarIconHandler;
import com.gviktor.controller.toolbar.events.PointInPolygonToolbarIconHandler;
import com.gviktor.controller.toolbar.events.SavePolygonToolbarIconHandler;
import com.gviktor.model.PolygonDrawer;
import com.gviktor.view.ImageButton;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;


public class DefinePolyToolBarController {

    private ImageButton polygonButton;
    private ImageButton pointInPolygonButton;

    private ImageButton loadButton;
    private ImageButton saveButton;
    private PointInPolygonToolbarIconHandler pointInPolygonToolbarIconHandler;
    private SavePolygonToolbarIconHandler  savePolygonToolbarIconHandler;
    private LoadPolygonToolbarIconHandler loadPolygonToolbarIconHandler;
    private ToolBar toolBar;
    private Canvas canvas;
    private PolygonDrawer polygonDrawer;

    public DefinePolyToolBarController(Canvas canvas, ToolBar toolBar,PolygonDrawer polygonDrawer) {
        this.toolBar = toolBar;
        this.canvas = canvas;
        this.polygonDrawer = polygonDrawer;

        polygonButton = new ImageButton("icons/polygon.png");
        pointInPolygonButton = new ImageButton("icons/pointInPolygon.png");
        saveButton = new ImageButton("icons/save.jpg");
        loadButton = new ImageButton("icons/new.png");

        pointInPolygonToolbarIconHandler = new PointInPolygonToolbarIconHandler(polygonDrawer,canvas);
        savePolygonToolbarIconHandler = new SavePolygonToolbarIconHandler(polygonDrawer,canvas);
        loadPolygonToolbarIconHandler = new LoadPolygonToolbarIconHandler(polygonDrawer,canvas);
        toolBar.getItems().addAll(polygonButton,pointInPolygonButton,loadButton,saveButton);

        pointInPolygonButton.addEventHandler(MouseEvent.MOUSE_CLICKED,pointInPolygonToolbarIconHandler);
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,savePolygonToolbarIconHandler);
        pointInPolygonButton.addEventHandler(MouseEvent.MOUSE_CLICKED,pointInPolygonToolbarIconHandler);
        loadButton.addEventHandler(MouseEvent.MOUSE_CLICKED,loadPolygonToolbarIconHandler);
    }
    public void clear(){
        toolBar.getItems().removeAll(polygonButton,pointInPolygonButton,loadButton,saveButton);

        saveButton.removeEventHandler(MouseEvent.MOUSE_CLICKED,savePolygonToolbarIconHandler);
        pointInPolygonButton.removeEventHandler(MouseEvent.MOUSE_CLICKED,pointInPolygonToolbarIconHandler);
        loadButton.removeEventHandler(MouseEvent.MOUSE_CLICKED,loadPolygonToolbarIconHandler);
    }

}

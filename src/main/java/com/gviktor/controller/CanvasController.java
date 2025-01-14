package com.gviktor.controller;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToolBar;

public abstract class CanvasController {
    protected GraphicsContext graphicsContext;
    protected Canvas drawingCanvas;

    private ToolBar toolBar;

    public CanvasController(GraphicsContext graphicsContext, Canvas drawingCanvas, ToolBar toolBar) {
        this.graphicsContext = graphicsContext;
        this.drawingCanvas = drawingCanvas;
        this.toolBar =toolBar;
        initScene();
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public Canvas getDrawingCanvas() {
        return drawingCanvas;
    }

    public void setDrawingCanvas(Canvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
    }

    public abstract void drawScene();
    public abstract void initScene();
    public abstract void clear();

    public ToolBar getToolBar() {
        return toolBar;
    }
}

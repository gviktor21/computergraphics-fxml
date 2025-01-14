package com.gviktor.controller;

public class CanvasControllerOperator {

    //private CanvasController previousController;
    private CanvasController currentController;

    public void applyController(CanvasController canvasController){
        if (currentController != null){
            currentController.clear();
        }
        currentController = canvasController;
    }

    public CanvasController getCurrentController() {
        return currentController;
    }
}

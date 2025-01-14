package com.gviktor.controller.toolbar.events;

import com.gviktor.controller.SavePolygonDialogController;
import com.gviktor.controller.io.PolygonIOManager;
import com.gviktor.model.PolygonDrawer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

public class SavePolygonToolbarIconHandler implements EventHandler<MouseEvent> {

    private PolygonDrawer polygonDrawer;
    private Canvas canvas;
    public SavePolygonToolbarIconHandler(PolygonDrawer polygonDrawer, Canvas canvas) {
        this.polygonDrawer = polygonDrawer;
        this.canvas = canvas;
    }

    @Override
    public void handle(MouseEvent mouseEvent){
        Dialog<ButtonType> addDialog = new Dialog<>();
        addDialog.setTitle("Save Polygon");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SavePolygonDialog.fxml"));
        try{
            addDialog.getDialogPane().setContent(loader.load());
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        addDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = addDialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK) {
            SavePolygonDialogController controller = loader.getController();
            String name = controller.getPolygonName();
            if (name != null) {
                PolygonIOManager.writePolygon(name+".poly",polygonDrawer.getPolygon());
            }
        }
    }
}
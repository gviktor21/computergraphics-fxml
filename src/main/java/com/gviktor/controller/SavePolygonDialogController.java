package com.gviktor.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SavePolygonDialogController {

    @FXML
    TextField text_polygonName;

    public String getPolygonName(){
        return text_polygonName.getText();
    }
}

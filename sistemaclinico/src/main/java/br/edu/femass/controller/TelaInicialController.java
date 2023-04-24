package br.edu.femass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaInicialController {
  @FXML
  private Label TxtDoctorCare;

  @FXML
  private void handleButton_AdmAction(ActionEvent event) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/Administrador.fxml"));

      Scene scene = new Scene(root);
      Stage stage = new Stage();

      stage.setTitle("Administrador");
      stage.setScene(scene);
      stage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  private void handleButton_AtdAction(ActionEvent event) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/Atendente.fxml"));

      Scene scene = new Scene(root);
      Stage stage = new Stage();

      stage.setTitle("Atendente");
      stage.setScene(scene);
      stage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

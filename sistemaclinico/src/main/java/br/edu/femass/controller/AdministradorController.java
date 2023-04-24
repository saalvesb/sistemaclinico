package br.edu.femass.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdministradorController {
  @FXML
  private Label TxtDoctorCare;

  @FXML
  private Label label;

  @FXML
  private void handleButton_EspecAction(ActionEvent event) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));

      Scene scene = new Scene(root);
      Stage stage = new Stage();

      stage.setTitle("Especialidade");
      stage.setScene(scene);
      stage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  private void handleButton_PlanoAction(ActionEvent event) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlanoSaude.fxml"));

      Scene scene = new Scene(root);
      Stage stage = new Stage();

      stage.setTitle("Plano de Saude");
      stage.setScene(scene);
      stage.show();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

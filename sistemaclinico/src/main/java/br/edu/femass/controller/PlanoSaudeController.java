package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;

//import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PlanoSaudeController implements Initializable {
  @FXML
  private TextField TxtPlano;

  @FXML
  private ListView<PlanoSaude> listaPlanos;

  private PlanoSaudeDao planoSaudeDao = new PlanoSaudeDao();

  @FXML
  private void BtnExcluir_Click(ActionEvent event) throws StreamWriteException, IOException {
    PlanoSaude planoSaude = listaPlanos.getSelectionModel().getSelectedItem();
    if (planoSaude == null)
      return;

    try {
      if (planoSaudeDao.excluir(planoSaude) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível excluir a conta selecionado");
      }
      exibirPlanos();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void BtnGravar_Click(ActionEvent event) {
    try {
      PlanoSaude planoSaude = new PlanoSaude(
          TxtPlano.getText());

      if (planoSaudeDao.gravar(planoSaude) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível gravar plano de Saude");
        return;
      }

      TxtPlano.setText("");

      exibirPlanos();

    } catch (Exception e) {
      DiversosJavaFx.exibirMensagem(e.getMessage());
    }
  }

  public void exibirPlanos() {
    try {
      ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoSaudeDao.buscarAtivos());
      listaPlanos.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirPlanos();
  }

}

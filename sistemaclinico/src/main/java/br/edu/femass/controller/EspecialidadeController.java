package br.edu.femass.controller;

import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;

import java.io.IOException;
import java.net.URL;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EspecialidadeController implements Initializable {
  @FXML
  private TextField TxtEspecialidade;

  @FXML
  private ListView<Especialidade> listaEspecialidade;

  private EspecialidadeDao especialidadeDao = new EspecialidadeDao();

  @FXML
  private void BtnExcluir_Click(ActionEvent event) throws StreamWriteException, IOException {
    Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
    if (especialidade == null)
      return;

    try {
      if (especialidadeDao.excluir(especialidade) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível excluir a conta selecionado");
      }
      exibirEspecialidades();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void BtnGravar_Click(ActionEvent event) {
    try {
      Especialidade especialidade = new Especialidade(
          TxtEspecialidade.getText());

      if (especialidadeDao.gravar(especialidade) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível gravar cliente");
        return;
      }

      TxtEspecialidade.setText("");

      exibirEspecialidades();

    } catch (Exception e) {
      DiversosJavaFx.exibirMensagem(e.getMessage());
    }
  }

  public void exibirEspecialidades() {
    try {
      ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos());
      listaEspecialidade.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirEspecialidades();
  }
}

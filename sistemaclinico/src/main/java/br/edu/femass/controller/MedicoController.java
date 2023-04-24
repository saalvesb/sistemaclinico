package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MedicoController implements Initializable {
  @FXML
  private TextField TxtIdMedico;

  @FXML
  private TextField TxtNome;

  @FXML
  private TextField TxtCpf;

  @FXML
  private TextField TxtEmail;

  @FXML
  private TextField TxtTelefone;

  @FXML
  private ComboBox<Especialidade> CboEspecialidades;

  @FXML
  private ListView<Medico> listaMedicos;

  private MedicoDao medicoDao = new MedicoDao();

  private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();

  @FXML
  private void listaMedicomouseClicked(MouseEvent event) {
    exibirDados();
  }

  @FXML
  private void BtnGravar_Click(ActionEvent event) {
    try {
      Medico medico = new Medico(
          TxtNome.getText(),
          TxtCpf.getText(),
          TxtEmail.getText(),
          TxtTelefone.getText(),
          CboEspecialidades.getSelectionModel().getSelectedItem());

      TxtIdMedico.setText(medico.getId().toString());

      if (medicoDao.gravar(medico) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível gravar médico");
        return;
      }

      TxtCpf.setText("");
      TxtEmail.setText("");
      TxtIdMedico.setText("");
      TxtNome.setText("");
      TxtTelefone.setText("");
      CboEspecialidades.setValue(null);

      exibirMedicos();

    } catch (Exception e) {
      DiversosJavaFx.exibirMensagem(e.getMessage());
    }
  }

  @FXML
  private void BtnExcluir_Click(ActionEvent event) throws StreamWriteException, IOException {
    Medico medico = listaMedicos.getSelectionModel().getSelectedItem();
    if (medico == null)
      return;

    try {
      if (medicoDao.excluir(medico) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível excluir o medico selecionado");
      }
      exibirMedicos();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void BtnNovo_Click(ActionEvent event) {
    TxtCpf.setText("");
    TxtEmail.setText("");
    TxtIdMedico.setText("");
    TxtNome.setText("");
    TxtTelefone.setText("");
    CboEspecialidades.setValue(null);
  }

  private void exibirDados() {
    Medico medico = listaMedicos.getSelectionModel().getSelectedItem();
    if (medico == null)
      return;

    TxtNome.setText(medico.getNome());
    TxtCpf.setText(medico.getCpf());
    TxtEmail.setText(medico.getEmail());
    TxtTelefone.setText(medico.getTelefone().get(0));
    TxtIdMedico.setText(medico.getId().toString());
  }

  private void exibirMedicos() {
    try {
      ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
      listaMedicos.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void exibirEspecialidades() {
    try {
      ObservableList<Especialidade> data = FXCollections.observableArrayList(
          especialidadeDao.buscarAtivos());
      CboEspecialidades.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirEspecialidades();
    exibirMedicos();
  }
}

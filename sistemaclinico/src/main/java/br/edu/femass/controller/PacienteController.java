package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.Dao;
//import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PacienteController implements Initializable {

  @FXML
  private TextField TxtId;

  @FXML
  private TextField TxtNome;

  @FXML
  private TextField TxtCpf;

  @FXML
  private TextField TxtEndereco;

  @FXML
  private TextField TxtEmail;

  @FXML
  private TextField TxtTelefone;

  @FXML
  private ListView<Paciente> listaPaciente;

  @FXML
  private ComboBox<PlanoSaude> CboPlanos;

  private PacienteDao pacienteDao = new PacienteDao();

  private Dao<PlanoSaude> planoSaudeDao = new PlanoSaudeDao();

  @FXML
  private void listaPaciente_mouseClicked(MouseEvent event) {
    exibirDados();
  }

  @FXML
  private void listaPaciente_keyPressed(KeyEvent event) {
    exibirDados();
  }

  private void exibirDados() {
    Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
    if (paciente == null)
      return;

    TxtCpf.setText(paciente.getCpf());
    TxtEmail.setText(paciente.getEmail());
    TxtEndereco.setText(paciente.getEndereco());
    TxtId.setText(paciente.getId().toString());
    TxtNome.setText(paciente.getNome());
    TxtTelefone.setText(paciente.getTelefones().get(0));
    CboPlanos.setValue(paciente.getPlanoSaude());
  }

  @FXML
  private void BtnExcluir_Click(ActionEvent event) throws StreamWriteException, IOException {
    Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
    if (paciente == null)
      return;

    try {
      if (pacienteDao.excluir(paciente) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível excluir o paciente selecionado");
      }
      exibirPacientes();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void BtnGravar_Click(ActionEvent event) {
    try {
      Paciente paciente = new Paciente(
          TxtCpf.getText(),
          TxtNome.getText(),
          TxtTelefone.getText(),
          CboPlanos.getSelectionModel().getSelectedItem());

      paciente.setEmail(TxtEmail.getText());
      paciente.setEndereco(TxtEndereco.getText());
      TxtId.setText(paciente.getId().toString());

      if (pacienteDao.gravar(paciente) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível gravar cliente");
        return;
      }

      TxtCpf.setText("");
      TxtEmail.setText("");
      TxtEndereco.setText("");
      TxtId.setText("");
      TxtNome.setText("");
      TxtTelefone.setText("");

      exibirPacientes();

    } catch (Exception e) {
      DiversosJavaFx.exibirMensagem(e.getMessage());
    }
  }

  @FXML
  private void BtnNovo_Click(ActionEvent event) {
    TxtCpf.setText("");
    TxtEmail.setText("");
    TxtEndereco.setText("");
    TxtId.setText("");
    TxtNome.setText("");
    TxtTelefone.setText("");
    CboPlanos.setValue(null);
  }

  public void exibirPacientes() {
    try {
      ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos());
      listaPaciente.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void exibirPlanos() {
    try {
      ObservableList<PlanoSaude> data = FXCollections.observableArrayList(
          planoSaudeDao.buscarAtivos());
      CboPlanos.setItems(data); // preenche a combo box
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirPlanos();
    exibirPacientes();
  }

}

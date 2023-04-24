package br.edu.femass.controller;

import java.util.ResourceBundle;

import java.net.URL;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AgendaController implements Initializable {
  @FXML
  private Label labelData;

  @FXML
  private Label labelHora;

  @FXML
  private TextField TxtData;

  @FXML
  private TextField TxtHora;

  @FXML
  private ComboBox<Medico> CboMedicos;

  @FXML
  private ComboBox<Paciente> CboPacientes;

  @FXML
  private ComboBox<Especialidade> CboEspecialidades;

  @FXML
  private ListView<Agenda> listaAgenda;

  private Dao<Paciente> pacienteDao = new PacienteDao();
  private MedicoDao medicoDao = new MedicoDao();
  private AgendaDao agendaDao = new AgendaDao();
  // private Dao<Agenda> agendaDao = new AgendaDao();
  private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();

  @FXML
  private void selecionar_Especialidade(ActionEvent event) {
    Especialidade especialidade = CboEspecialidades.getSelectionModel().getSelectedItem();
    exibirMedicos(especialidade);
  }

  public void exibirEspecialidades() {
    try {
      ObservableList<Especialidade> data = FXCollections.observableArrayList(
          especialidadeDao.buscarAtivos());
      CboEspecialidades.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void exibirMedicos(Especialidade especialidade) {
    try {
      ObservableList<Medico> data = FXCollections.observableArrayList(
          medicoDao.buscarEspecialidade(especialidade));
      CboMedicos.setItems(data); // preenche a combo box
    } catch (Exception ex) {
      ex.printStackTrace();

    }
  }

  @FXML
  private void BtnExcluir_Click(ActionEvent event) {
    Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
    if (agenda == null)
      return;

    try {
      if (agendaDao.excluir(agenda) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível excluir o agendamento selecionado");
      }
      exibirAgendamentos();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void BtnGravar_Click(ActionEvent event) {
    try {
      Agenda agenda = new Agenda(
          TxtData.getText(),
          TxtHora.getText(),
          CboPacientes.getSelectionModel().getSelectedItem(),
          CboMedicos.getSelectionModel().getSelectedItem(),
          CboEspecialidades.getSelectionModel().getSelectedItem());

      if (agendaDao.gravar(agenda) == false) {
        DiversosJavaFx.exibirMensagem("Não foi possível gravar o agendamento");
        return;
      }

      TxtData.setText("");
      TxtHora.setText("");

      exibirAgendamentos();
    } catch (Exception e) {
      DiversosJavaFx.exibirMensagem(e.getMessage());
    }
  }

  public void exibirAgendamentos() {
    try {
      ObservableList<Agenda> data = FXCollections.observableArrayList(
          agendaDao.buscarAtivos());
      listaAgenda.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void exibirPacientes() {
    try {
      ObservableList<Paciente> data = FXCollections.observableArrayList(
          pacienteDao.buscarAtivos());
      CboPacientes.setItems(data); // preenche a combo box
    } catch (Exception ex) {
      ex.printStackTrace();

    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirPacientes();
    exibirEspecialidades();
    exibirAgendamentos();
  }

}

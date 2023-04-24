package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class AgendaMedicoController implements Initializable {

  @FXML
  private ComboBox<Medico> CboMedicos;

  @FXML
  private ListView<Agenda> listaAgenda;

  private MedicoDao medicoDao = new MedicoDao();
  private AgendaDao agendaDao = new AgendaDao();

  @FXML
  private void OnAction_Medicos(ActionEvent event) {
    Medico medico = CboMedicos.getSelectionModel().getSelectedItem();
    if (medico == null)
      return;
    exibirAgenda(medico);
  }

  private void exibirMedicos() {
    try {
      ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
      CboMedicos.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void exibirAgenda(Medico medico) {
    try {
      ObservableList<Agenda> data = FXCollections.observableArrayList(
          agendaDao.buscarAgendaMedico(medico));
      listaAgenda.setItems(data);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    exibirMedicos();
  }


}

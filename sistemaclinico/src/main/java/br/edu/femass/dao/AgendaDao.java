package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;

public class AgendaDao extends Persist implements Dao<Agenda> {

  public AgendaDao() {
    super("Agenda.json");
  }

  public List<Agenda> buscarAgendaMedico(Medico medico) throws DatabindException {
    List<Agenda> agendas = buscarAtivos(); //X Set busca();

    List<Agenda> medicos = agendas
        .stream()
        .filter(agenda -> agenda.getMedico().equals(medico))
        .collect(Collectors.toList());

    return medicos;
  }

  public List<Agenda> buscarDatas() throws DatabindException {
    List<Agenda> agendas = buscarAtivos();

    List<Agenda> datas = agendas
        .stream()
        .filter(agenda -> agenda.getData().equals(null))
        .collect(Collectors.toList());

    return datas;
  }

  @Override
  public boolean gravar(Agenda objeto) throws StreamWriteException, IOException {
    Set<Agenda> agendas = busca();
    boolean gravou = agendas.add(objeto);

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
    return gravou;
  }

  @Override
  public boolean excluir(Agenda objeto) throws StreamWriteException, IOException {
    Set<Agenda> agendas = busca();
    for (Agenda agendaSelecionada : agendas) {
      if (agendaSelecionada.equals(objeto)) {
        agendaSelecionada.setAtivo(false);
      }
    }

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
    return true;
  }

  @Override
  public Set<Agenda> busca() throws DatabindException {
    try {
      Set<Agenda> agendas = objectMapper.readValue(arquivo, new TypeReference<Set<Agenda>>() {
      }); // foi no arquivo e chamou todos os clientes
      Agenda.atualizarUltimoId(agendas);
      return agendas;
    } catch (IOException ex) {
      return new HashSet<Agenda>();
    }
  }

  @Override
  public List<Agenda> buscarAtivos() throws DatabindException {
    Set<Agenda> agendas = busca();

    List<Agenda> agendasAtivas = agendas
        .stream()
        .filter(agenda -> agenda.getAtivo().equals(true))
        .collect(Collectors.toList());

    return agendasAtivas;
  }

  /*
   * public List<Agenda> buscarAgenda(Medico medico) throws DatabindException {
   * List<Agenda> agendas = this.buscarAtivos();
   * List<Agenda> medicos = agendas
   * .stream()
   * .filter(agenda -> agenda.getData().equals(medico))
   * .collect(Collectors.toList());
   * 
   * return medicos;
   * }
   */

}

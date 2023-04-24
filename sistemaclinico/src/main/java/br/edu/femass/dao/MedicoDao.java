package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico> {

  public MedicoDao() {
    super("Medicos.json");
  }

  public List<Medico> buscarEspecialidade(Especialidade especialidade) throws DatabindException {
    List<Medico> medicos = buscarAtivos();

    List<Medico> especialista = medicos
        .stream()
        .filter(medico -> medico.getEspecialidade().contains(especialidade))
        .collect(Collectors.toList());

    return especialista;
  }

  @Override
  public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
    Set<Medico> medicos = busca();
    boolean gravou = medicos.add(objeto);

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
    return gravou;
  }

  @Override
  public boolean excluir(Medico objeto) throws StreamWriteException, IOException {
    Set<Medico> medicos = busca();
    for (Medico medicoSelecionado : medicos) {
      if (medicoSelecionado.equals(objeto)) {
        medicoSelecionado.setAtivo(false);
      }
    }

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
    return true;
  }

  @Override
  public Set<Medico> busca() throws DatabindException {
    try {
      Set<Medico> medicos = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>() {
      }); // foi no arquivo e chamou todos os clientes
      Medico.atualizarUltimoId(medicos);
      return medicos;
    } catch (IOException ex) {
      return new HashSet<Medico>();
    }
  }

  @Override
  public List<Medico> buscarAtivos() throws DatabindException {
    Set<Medico> medicos = busca();

    List<Medico> medicosAtivos = medicos
        .stream()
        .filter(medico -> medico.getAtivo().equals(true))
        .collect(Collectors.toList());

    return medicosAtivos;
  }

}

package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persist implements Dao<Paciente> { // trocou alguns clientes por objeto, eu
  // deixei cliente mesmo

  public PacienteDao() {
    super("Pacientes.json");
  }

  public boolean gravar(Paciente objeto) throws StreamWriteException, IOException {
    Set<Paciente> pacientes = busca(); // o Set não permite que tenha dois atributos iguais, como o cpf
    boolean gravou = pacientes.add(objeto); // mesma coisa que acontece no excluir

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes); // pega a lista de clientes
    // e grava no arquivo e deixa bonito
    return gravou;
  }

  public boolean excluir(Paciente paciente) throws StreamWriteException, IOException {
    Set<Paciente> pacientes = busca();
    // boolean gravou = clientes.remove(cliente); // pra saber se quando não cria um
    // cpf igual, se gravou ou não. Então vai testar aqui pq o remove me retorna um
    // boolean, pra se gravou ou não

    for (Paciente pacienteSelecionado : pacientes) {
      if (pacienteSelecionado.equals(paciente)) {
        pacienteSelecionado.setAtivo(false);
      }
    }

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
    return true;
  }

  // vai no arquivo, pega todo mundo e adiciona
  public Set<Paciente> busca() throws DatabindException {
    try {
      Set<Paciente> pacientes = objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>() {
      }); // foi no arquivo e chamou todos os clientes
      Paciente.atualizarUltimoId(pacientes); // Atuliza
      return pacientes;
    } catch (IOException ex) {
      return new HashSet<Paciente>();
    }

  }

  public List<Paciente> buscarAtivos() throws DatabindException {
    Set<Paciente> pacientes = busca();

    // forma de filtrar uma collection sem precisar interar por ela
    List<Paciente> pacienteAtivos = pacientes // sem precisar fazer um for e ir pegando os objetos e ir jogando em
        // outra lista
        .stream()
        .filter(paciente -> paciente.getAtivo().equals(true))// esse primeiro elemento cliente é cada
        .collect(Collectors.toList()); // converte em lista

    return pacienteAtivos;
  }
}

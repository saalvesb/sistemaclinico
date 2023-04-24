package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude> {

  public PlanoSaudeDao() {
    super("PlanoSaude.json");
  }

  @Override
  public boolean gravar(PlanoSaude objeto) throws StreamWriteException, IOException {
    Set<PlanoSaude> planos = busca();
    boolean gravou = planos.add(objeto);

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
    return gravou;
  }

  @Override
  public boolean excluir(PlanoSaude objeto) throws StreamWriteException, IOException {
    Set<PlanoSaude> planos = busca();
    for (PlanoSaude planoSelecionada : planos) {
      if (planoSelecionada.equals(objeto)) {
        planoSelecionada.setAtivo(false);
      }
    }

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
    return true;
  }

  @Override
  public Set<PlanoSaude> busca() throws DatabindException {
    try {
      Set<PlanoSaude> planos = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoSaude>>() {
      }); // foi no arquivo e chamou todos os clientes
      return planos;
    } catch (IOException ex) {
      return new HashSet<PlanoSaude>();
    }
  }

  @Override
  public List<PlanoSaude> buscarAtivos() throws DatabindException {
    Set<PlanoSaude> planos = busca();

    List<PlanoSaude> planosAtivos = planos
        .stream()
        .filter(plano -> plano.getAtivo().equals(true))
        .collect(Collectors.toList());

    return planosAtivos;
  }
}

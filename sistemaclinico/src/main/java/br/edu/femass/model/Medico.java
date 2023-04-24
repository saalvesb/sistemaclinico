package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.femass.diversos.Validador;

public class Medico {
  private Long id;
  private String nome;
  private String cpf;
  private String email;
  private Boolean ativo;
  private List<String> telefones = new ArrayList<String>();
  private List<Especialidade> especialidades = new ArrayList<Especialidade>();

  private static Long ultimoCodigo = 0L;

  public Medico() {
  }

  public Medico(
  String nome, 
  String cpf, 
  String email, 
  String telefone, 
  Especialidade especialidade){
  
    if (Validador.validarCPF(cpf) == false)
      throw new IllegalArgumentException("CPF inválido");
      
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.telefones.add(telefone);
    this.especialidades.add(especialidade);
    this.ativo = true;

    this.id = ultimoCodigo + 1;
    ultimoCodigo++;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void adicionarTelefone(String telefone) {
    this.telefones.add(telefone);
  }

  public void removerTelefone(String telefone) throws Exception {
    if (telefones.size() == 1) {
      throw new Exception("O paciente tem que ter pelo menos um telefone");
    }
    this.telefones.remove(telefone);
  }

  public List<String> getTelefone() {
    return telefones;
  }

  public void adicionarEspecialidade(Especialidade especialidade) {
    this.especialidades.add(especialidade);
  }

  public void removerEspecialidades(Especialidade especialidade) throws Exception {
    if (especialidades.size() == 1) {
      throw new Exception("O paciente tem que ter pelo menos uma especialidade");
    }
    this.especialidades.remove(especialidade);
  }

  public List<Especialidade> getEspecialidade() {
    return especialidades;
  }

  public static void atualizarUltimoId(Set<Medico> medicos) {
    for (Medico medico : medicos) {
      if (medico.getId().longValue() > ultimoCodigo) {
        ultimoCodigo = medico.getId();
      }
    }
  }

  @Override
  public String toString() {
    return "Medico(a): " + nome + "\nEspecialidade: " + especialidades;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Medico other = (Medico) obj;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    return true;
  }
}

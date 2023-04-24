package br.edu.femass.model;

import java.util.Set;

public class Agenda {
  private Long id;
  private String data;
  private String hora;
  protected Boolean ativo;
  private Paciente paciente;
  private Medico medico;
  private Especialidade especialidade;

  private static Long ultimoCodigo = 0L;

  public Agenda() {
  }

  public Agenda(String data, String hora, Paciente paciente, Medico medico, Especialidade especialidade) {
    this.data = data;
    this.hora = hora;
    this.ativo = true;
    this.paciente = paciente;
    this.medico = medico;
    this.especialidade = especialidade;

    this.id = ultimoCodigo + 1;
    ultimoCodigo++;
  }

  public Long getId() {
    return id;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public Medico getMedico() {
    return medico;
  }

  public Especialidade getEspecialidade() {
    return especialidade;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  @Override
  public String toString() {
    return data + " - " + hora + "\nPaciente: " + paciente + " \n " + medico;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((data == null) ? 0 : data.hashCode());
    result = prime * result + ((hora == null) ? 0 : hora.hashCode());
    result = prime * result + ((medico == null) ? 0 : medico.hashCode());
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
    Agenda other = (Agenda) obj;
    if (data == null) {
      if (other.data != null)
        return false;
    } else if (!data.equals(other.data))
      return false;
    if (hora == null) {
      if (other.hora != null)
        return false;
    } else if (!hora.equals(other.hora))
      return false;
    if (medico == null) {
      if (other.medico != null)
        return false;
    } else if (!medico.equals(other.medico))
      return false;
    return true;
  }

  public static void atualizarUltimoId(Set<Agenda> agendas) {
    for (Agenda agenda : agendas) {
      if (agenda.getId().longValue() > ultimoCodigo) {
        ultimoCodigo = agenda.getId();
      }
    }
  }

}

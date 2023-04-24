package br.edu.femass.model;

import java.util.Set;

public class Especialidade {
  private String especialidades;
  protected Boolean ativo;
  private Long id;

  private static Long ultimoCodigo = 0L;

  public Especialidade() {
  }

  public Especialidade(String especialidades) {
    this.especialidades = especialidades;
    this.ativo = true;

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

  public String getEspecialidades() {
    return especialidades;
  }

  public void setEspecialidades(String especialidades) {
    this.especialidades = especialidades;
  }

  public static void atualizarUltimoId(Set<Especialidade> especialidades) {
    for (Especialidade especialidade : especialidades) {
      if (especialidade.getId().longValue() > ultimoCodigo) {
        ultimoCodigo = especialidade.getId();
      }
    }
  }

  @Override
  public String toString() {
    return especialidades;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((especialidades == null) ? 0 : especialidades.hashCode());
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
    Especialidade other = (Especialidade) obj;
    if (especialidades == null) {
      if (other.especialidades != null)
        return false;
    } else if (!especialidades.equals(other.especialidades))
      return false;
    return true;
  }
}

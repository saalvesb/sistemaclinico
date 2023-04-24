package br.edu.femass.model;

import java.util.Set;

public class PlanoSaude {
  private Long id;
  private String planoSaude;
  private Boolean ativo;

  private static Long ultimoCodigo = 0L;

  public PlanoSaude() {
  }

  public PlanoSaude(String planoSaude) {
    this.planoSaude = planoSaude;
    this.ativo = true;

    this.id = ultimoCodigo + 1;
    ultimoCodigo++;
  }

  public Long getId() {
    return id;
  }

  public String getPlanoSaude() {
    return planoSaude;
  }

  public void setPlanoSaude(String planoSaude) {
    this.planoSaude = planoSaude;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  @Override
  public String toString() {
    return this.getPlanoSaude();
  }

  public static void atualizarUltimoId(Set<PlanoSaude> planos) {
    for (PlanoSaude plano : planos) {
      if (plano.getId().longValue() > ultimoCodigo) {
        ultimoCodigo = plano.getId();
      }
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((planoSaude == null) ? 0 : planoSaude.hashCode());
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
    PlanoSaude other = (PlanoSaude) obj;
    if (planoSaude == null) {
      if (other.planoSaude != null)
        return false;
    } else if (!planoSaude.equals(other.planoSaude))
      return false;
    return true;
  }

}

package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {

  private Paciente paciente;

  @BeforeEach
  void setUp() {
    paciente = new Paciente(
        "53105575049",
        "Teste",
        "22 99999-9999",
        new PlanoSaude("Unimed"));
  }

  @Test
  void construtorCpfIncorreto() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Paciente(
            "123456789",
            "Teste",
            "22 99999-9999",
            new PlanoSaude("Unimed")));

  }

  @Test
  void pacienteCriadoComUmTelefone() {
    assertEquals(1, paciente.getTelefones().size());
  }

  @Test
  void pacienteComDoisTelefones() {
    paciente.adicionarTelefone("1234458388");
    assertEquals(2, paciente.getTelefones().size());
  }

  @Test
  void pacienteRemoverUmTelefone() throws Exception {
    assertThrows(Exception.class,
        () -> paciente.removerTelefone("22 99999-9999"));
  }

  @Test
  void pacienteRemoverUmTelefoneTendoDois() throws Exception {
    paciente.adicionarTelefone("122344668");
    paciente.removerTelefone("22 99999-9999");

    assertEquals(1, paciente.getTelefones().size());
  }
}

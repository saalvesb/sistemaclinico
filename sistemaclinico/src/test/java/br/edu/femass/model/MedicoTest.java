package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoTest {
  private Medico medico;

    @BeforeEach
    void setUp() {
      medico = new Medico(
          "Teste",
          "29137428071",
          "teste@teste",
          "22 99999-9999",
          new Especialidade("Dentista"));
    }
  
    @Test
    void construtorCpfIncorreto() {
      assertThrows(
          IllegalArgumentException.class,
          () -> new Medico(
            "Teste",
              "123456789",
              "teste@teste",
              "22 99999-9999",
              new Especialidade("Dentista")));
  
    }
  
    @Test
    void medicoCriadoComUmTelefone() {
      assertEquals(1, medico.getTelefone().size());
    }
  
    @Test
    void medicoComDoisTelefones() {
      medico.adicionarTelefone("1234458388");
      assertEquals(2, medico.getTelefone().size());
    }
  
    @Test
    void medicoRemoverUmTelefone() throws Exception {
      assertThrows(Exception.class,
          () -> medico.removerTelefone("22 99999-9999"));
    }
  
    @Test
    void medicoRemoverUmTelefoneTendoDois() throws Exception {
      medico.adicionarTelefone("122344668");
      medico.removerTelefone("22 99999-9999");
  
      assertEquals(1, medico.getTelefone().size());
    }

    @Test
    void medicoCriadoComUmaEspecialidade() {
      assertEquals(1, medico.getEspecialidade().size());
    }
  
  }
  

package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import interfaces.Atividade;

public class EstagioTest {

	@Test
	void testCriarEstagioComPoucoTempoAcumulado() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Estagio("descrição", "link", 299, "empresa");
		}).getMessage();
		assertEquals("A unidade de tempo acumulada não é suficiente", mensagem);
	}

	@Test
	void testCriarEstagioComNomeDaEmpresaNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Estagio("descrição", "link", 300, null);
		}).getMessage();
		assertEquals("A especificação da atividade não pode ser nula", mensagem);
	}

	@Test
	void testCriarEstagioComNomeDaEmpresaVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Estagio("descrição", "link", 300, "");
		}).getMessage();
		assertEquals("A especificação da atividade não pode ser vazia", mensagem);
	}

	@Test
	void testCalcularCreditosEstagio() {
		Atividade atividadeComplementar = new Estagio("descrição", "link", 300, "empresa");
		assertEquals(5, atividadeComplementar.creditos());
	}

	@Test
	void testCalcularCreditosMaximosEstagio() {
		Atividade atividadeComplementar = new Estagio("descrição", "link", 1200, "empresa");
		assertEquals(18, atividadeComplementar.creditos());
	}
}

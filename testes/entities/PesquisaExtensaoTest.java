package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import interfaces.Atividade;

public class PesquisaExtensaoTest {

	@Test
	void testCriarPesquisaExtensaoComPoucoTempoAcumulado() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new PesquisaExtensao("descrição", "link", 0, "empresa");
		}).getMessage();
		assertEquals("A unidade de tempo acumulada não é suficiente", mensagem);
	}

	@Test
	void testCriarPesquisaExtensaoComSubtipoInvalido() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new PesquisaExtensao("descrição", "link", 2, "UmSubtipoInvalido");
		}).getMessage();
		assertEquals("Subtipo inválido para Pesquisa e Extensão", mensagem);
	}

	@Test
	void testCalcularCreditosPesquisaExtensao() {
		Atividade atividadeComplementar = new PesquisaExtensao("descrição", "link", 12, "pet");
		assertEquals(10, atividadeComplementar.creditos());
	}

	@Test
	void testCalcularCreditosMaximosPesquisaExtensao() {
		Atividade atividadeComplementar = new PesquisaExtensao("descrição", "link", 100, "pet");
		assertEquals(18, atividadeComplementar.creditos());
	}
}

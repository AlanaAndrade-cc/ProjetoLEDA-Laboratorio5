package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import interfaces.Atividade;

public class RepresentacaoEstudantilTest {

	@Test
	void testCriarRepresentacaoEstudantilComPoucoTempoAcumulado() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new RepresentacaoEstudantil("descrição", "link", 0, "empresa");
		}).getMessage();
		assertEquals("A unidade de tempo acumulada não é suficiente", mensagem);
	}

	@Test
	void testCriarRepresentacaoEstudantilComSubtipoInvalido() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new RepresentacaoEstudantil("descrição", "link", 2, "UmSubtipoInvalido");
		}).getMessage();
		assertEquals("Subtipo inválido para Representação Estudantil", mensagem);
	}

	@Test
	void testCalcularCreditosRepresentacaoEstudantil() {
		Atividade atividadeComplementar = new RepresentacaoEstudantil("descrição", "link", 1, "comissao");
		assertEquals(2, atividadeComplementar.creditos());
	}

	@Test
	void testCalcularCreditosMaximosRepresentacaoEstudantil() {
		Atividade atividadeComplementar = new RepresentacaoEstudantil("descrição", "link", 3, "comissao");
		assertEquals(2, atividadeComplementar.creditos());
	}
}

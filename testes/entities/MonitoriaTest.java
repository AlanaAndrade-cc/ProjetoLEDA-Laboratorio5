package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import interfaces.Atividade;

public class MonitoriaTest {

	@Test
	void testCriarMonitoriaComPoucoTempoAcumulado() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Monitoria("descrição", "link", 0, "empresa");
		}).getMessage();
		assertEquals("A unidade de tempo acumulada não é suficiente", mensagem);
	}

	@Test
	void testCriarMonitoriaComDisciplinaNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Monitoria("descrição", "link", 2, null);
		}).getMessage();
		assertEquals("A especificação da atividade não pode ser nula", mensagem);
	}

	@Test
	void testCriarMonitoriaComDisciplinaVazia() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Monitoria("descrição", "link", 2, "");
		}).getMessage();
		assertEquals("A especificação da atividade não pode ser vazia", mensagem);
	}

	@Test
	void testCalcularCreditosMonitoria() {
		Atividade atividadeComplementar = new Monitoria("descrição", "link", 3, "disciplina");
		assertEquals(12, atividadeComplementar.creditos());
	}

	@Test
	void testCalcularCreditosMaximosMonitoria() {
		Atividade atividadeComplementar = new Monitoria("descrição", "link", 5, "disciplina");
		assertEquals(16, atividadeComplementar.creditos());
	}
}

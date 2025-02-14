package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Elemento;

public class ReferenciaTest {

	private Elemento elemento;

	@BeforeEach
	void setUp() {
		this.elemento = new Referencia("título", "fonte", 2024, true, 5);
	}

	@Test
	void testCriarReferenciaComTituloNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Referencia(null, "fonte", 2024, true, 5);
		}).getMessage();
		assertEquals("O título não pode ser nulo", mensagem);
	}

	@Test
	void testCriarReferenciaComTituloVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("", "fonte", 2024, true, 5);
		}).getMessage();
		assertEquals("O título não pode ser vazio", mensagem);
	}

	@Test
	void testCriarReferenciaComFonteNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Referencia("título", null, 2024, true, 5);
		}).getMessage();
		assertEquals("A fonte não pode ser nula", mensagem);
	}

	@Test
	void testCriarReferenciaComFonteVazia() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("título", "", 2024, true, 5);
		}).getMessage();
		assertEquals("A fonte não pode ser vazia", mensagem);
	}

	@Test
	void testCriarReferenciaComAnoNegativo() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("título", "fonte", -1, true, 5);
		}).getMessage();
		assertEquals("O ano deve ser um valor positivo", mensagem);
	}

	@Test
	void testCriarReferenciaComImportanciaMenorQueMinimo() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("título", "fonte", 2024, true, 0);
		}).getMessage();
		assertEquals("A importância deve ser um valor entre 1 e 5", mensagem);
	}

	@Test
	void testCriarReferenciaComImportanciaMaiorQueMaximo() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Referencia("título", "fonte", 2024, true, 6);
		}).getMessage();
		assertEquals("A importância deve ser um valor entre 1 e 5", mensagem);
	}

	@Test
	void testValorBonificacao() {
		assertEquals(15, elemento.valorBonificacao());
	}

	@Test
	void testValorBonificacaoReferenciaNaoConferida() {
		Elemento outro = new Referencia("título", "fonte", 2024, false, 5);
		assertEquals(0, outro.valorBonificacao());
	}

	@Test
	void testExibeDetalhes() {
		assertEquals("Referência: título fonte, ano: 2024. Importância: 5", elemento.exibeDetalhes());
	}

	@Test
	void testToString() {
		assertEquals("Referência: título fonte, ano: 2024.", elemento.toString());
	}
}

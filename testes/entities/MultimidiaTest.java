package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Elemento;

public class MultimidiaTest {

	private Elemento elemento;

	@BeforeEach
	void setUp() {
		this.elemento = new Multimidia("link", "cabeçalho", 300);
	}

	@Test
	void testCriarMultimidiaComLinkNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Multimidia(null, "cabeçalho", 300);
		}).getMessage();
		assertEquals("O link não pode ser nulo", mensagem);
	}

	@Test
	void testCriarMultimidiaComLinkVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Multimidia("", "cabeçalho", 300);
		}).getMessage();
		assertEquals("O link não pode ser vazio", mensagem);
	}

	@Test
	void testCriarMultimidiaComCabecalhoNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Multimidia("link", null, 300);
		}).getMessage();
		assertEquals("O cabeçalho não pode ser nulo", mensagem);
	}

	@Test
	void testCriarMultimidiaComCabecalhoVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Multimidia("link", "", 300);
		}).getMessage();
		assertEquals("O cabeçalho não pode ser vazio", mensagem);
	}

	@Test
	void testCriarMultimidiaComTempoNegativo() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Multimidia("link", "cabeçalho", -2);
		}).getMessage();
		assertEquals("O tempo deve ser um valor positivo", mensagem);
	}

	@Test
	void testValorBonificacao() {
		assertEquals(25, elemento.valorBonificacao());
	}

	@Test
	void testExibeDetalhes() {
		assertEquals("Link: link, cabeçalho (300 segundos)", elemento.exibeDetalhes());
	}

	@Test
	void testToString() {
		assertEquals("Link: link, cabeçalho", elemento.toString());
	}
}

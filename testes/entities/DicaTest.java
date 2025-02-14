package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DicaTest {

	private Usuario user1;
	private Usuario user2;
	private Dica dica;

	@BeforeEach
	void setUp() {
		this.user1 = new Usuario("Fulano", "123.456.789-00", "1234", "12345679");
		this.user1 = new Usuario("Ciclano", "987-654-321-00", "4567", "987654321");
		this.dica = new Dica(user1, "Monitoria");
	}

	@Test
	void testAdicionarTextoComAutorInvalido() {
		assertFalse(dica.adicionaTexto(user2, "Um texto qualquer"));
	}

	@Test
	void testAdicionarMultimidiaComAutorInvalido() {
		assertFalse(dica.adicionaMultimidia(user2, "link", "cabeçalho", 300));
	}

	@Test
	void testAdicionarReferenciaComAutorInvalido() {
		assertFalse(dica.adicionaReferencia(user2, "título", "fonte", 2024, true, 5));
	}

	@Test
	void testAdicionarTextoComAutorValido() {
		assertTrue(dica.adicionaTexto(user1, "Um texto qualquer"));
	}

	@Test
	void testAdicionarMultimidiaComAutorValido() {
		assertTrue(dica.adicionaMultimidia(user1, "link", "cabeçalho", 300));
	}

	@Test
	void testAdicionarReferenciaComAutorValido() {
		assertTrue(dica.adicionaReferencia(user1, "título", "fonte", 2024, true, 5));
	}

	@Test
	void testBonificaAutor() {
		assertEquals(0, user1.getBonificacao());
		dica.adicionaMultimidia(user1, "link", "cabeçalho", 300);
		assertEquals(25, user1.getBonificacao());
		dica.adicionaReferencia(user1, "título", "fonte", 2024, true, 5);
		assertEquals(40, user1.getBonificacao());
	}
}

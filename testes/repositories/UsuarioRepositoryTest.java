package repositories;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Usuario;

public class UsuarioRepositoryTest {

	private UsuarioRepository repository;
	private Usuario usuarioA;
	private Usuario usuarioB;
	private Usuario usuarioC;
	private Usuario usuarioD;
	private Usuario usuarioE;

	@BeforeEach
	void setUp() {
		this.repository = new UsuarioRepository();
		this.usuarioA = new Usuario("Alana Vanessa", "123.456.789-00", "147258", "123210882");
		this.usuarioB = new Usuario("Camila Rodrigues", "234.567.891-00", "258369", "123210240");
		this.usuarioC = new Usuario("Joao Victor", "345.678.912-00", "369147", "123210456");
		this.usuarioD = new Usuario("Lorena Nascimento", "456.789.123-00", "159753", "123211071");
		this.usuarioE = new Usuario("Lukas Soares", "567.891.234-00", "357951", "123210298");
	}

	@Test
	void testAdicionaEstudanteSucesso() {
		assertTrue(repository.adicionaEstudante(usuarioA.getCpf(), usuarioA));
		assertTrue(repository.adicionaEstudante(usuarioB.getCpf(), usuarioB));
	}

	@Test
	void testAdicionaEstudanteDuplicado() {
		repository.adicionaEstudante(usuarioC.getCpf(), usuarioC);
		assertFalse(repository.adicionaEstudante(usuarioC.getCpf(), usuarioC));
	}

	@Test
	void testListaEstudantesOrdemAlfabetica() {
		repository.adicionaEstudante(usuarioE.getCpf(), usuarioE);
		repository.adicionaEstudante(usuarioC.getCpf(), usuarioC);
		repository.adicionaEstudante(usuarioA.getCpf(), usuarioA);

		String[] estudantes = repository.listaEstudantes();
		assertEquals(usuarioA.toString(), estudantes[0]);
		assertEquals(usuarioC.toString(), estudantes[1]);
		assertEquals(usuarioE.toString(), estudantes[2]);
	}

	@Test
	void testListaEstudantesRankingDicas() {
		repository.adicionaEstudante(usuarioA.getCpf(), usuarioA);
		repository.adicionaEstudante(usuarioD.getCpf(), usuarioD);
		repository.adicionaEstudante(usuarioB.getCpf(), usuarioB);

		usuarioD.recebeBonificacao(70);
		usuarioB.recebeBonificacao(20);

		String[] estudantes = repository.listaEstudantesRankingDicas();
		assertEquals(usuarioD.toString(), estudantes[0]);
		assertEquals(usuarioB.toString(), estudantes[1]);
		assertEquals(usuarioA.toString(), estudantes[2]);
	}

	@Test
	void testBuscaEstudanteSucesso() {
		repository.adicionaEstudante(usuarioA.getCpf(), usuarioA);
		Usuario encontrado = repository.buscaEstudante("123.456.789-00", "147258");

		assertNotNull(encontrado);
		assertEquals(encontrado, usuarioA);
	}

	@Test
	void testBuscaEstudanteSenhaIncorreta() {
		repository.adicionaEstudante(usuarioA.getCpf(), usuarioA);

		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			repository.buscaEstudante("123.456.789-00", "147853");
		}).getMessage();
		assertEquals("Usuário ou senha inválidos", mensagem);
	}

	@Test
	void testBuscaEstudanteNaoCadastrado() {
		repository.adicionaEstudante(usuarioA.getCpf(), usuarioA);

		String mensagem = assertThrows(NullPointerException.class, () -> {
			repository.buscaEstudante("456.789.123-00", "159753");
		}).getMessage();
		assertEquals("O usuário não pode ser nulo", mensagem);
	}

}

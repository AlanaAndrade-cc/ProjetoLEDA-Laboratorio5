package repositories;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Dica;
import entities.Usuario;

public class DicaRepositoryTest {

	private DicaRepository repository;
	private Usuario usuarioA;
	private Usuario usuarioB;
	private Dica dica1;
	private Dica dica2;
	private Dica dica3;
	private Dica dica4;

	@BeforeEach
	void setUp() {
		this.repository = new DicaRepository();
		this.usuarioA = new Usuario("Alana Vanessa", "123.456.789-00", "147258", "123210882");
		this.usuarioB = new Usuario("Camila Rodrigues", "234.567.891-00", "258369", "123210240");
		this.dica1 = new Dica(usuarioA, "Monitoria");
		this.dica2 = new Dica(usuarioA, "PesquisaExtensao");
		this.dica3 = new Dica(usuarioB, "Estagio");
	}

	@Test
	void testAdicionaDicaSucesso() {
		assertEquals(1, repository.adicionaDica(dica1));
		assertEquals(2, repository.adicionaDica(dica2));
		assertEquals(3, repository.adicionaDica(dica3));
	}

	@Test
	void testAdicionaDicaNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			repository.adicionaDica(dica4);
		}).getMessage();
		assertEquals("A dica não pode ser nula", mensagem);
	}

	@Test
	void testListaDicasListaVazia() {
		assertArrayEquals(new String[0], repository.listaDicas());
	}

	@Test
	void testListaDicasComDicas() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		String[] lista = repository.listaDicas();
		assertEquals("Autor: Alana Vanessa, tema: Monitoria", lista[0]);
		assertEquals("Autor: Camila Rodrigues, tema: Estagio", lista[1]);
	}

	@Test
	void testListaDicasDetalhes() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		dica1.adicionaTexto(usuarioA, "Prepare materiais antes dos atendimentos.");

		String[] detalhes = repository.listaDicasDetalhes();
		assertEquals("Autor: Alana Vanessa\nPrepare materiais antes dos atendimentos. (41 caracteres)", detalhes[0]);
		assertEquals("Autor: Camila Rodrigues", detalhes[1]);
	}

	@Test
	void testListaDica() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		assertEquals("Autor: Camila Rodrigues, tema: Estagio", repository.listaDica(2));
	}

	@Test
	void testListaDicaDetalhes() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		dica1.adicionaTexto(usuarioA, "Prepare materiais antes dos atendimentos.");

		assertEquals("Autor: Alana Vanessa\nPrepare materiais antes dos atendimentos. (41 caracteres)",
				repository.listaDicaDetalhes(1));
	}

	@Test
	void testBuscaDicaSucesso() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		Dica encontrada = repository.buscaDica(1);

		assertEquals(dica1, encontrada);
	}

	@Test
	void testBuscaDicaPosicaoInvalida() {
		repository.adicionaDica(dica1);
		repository.adicionaDica(dica3);

		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			repository.buscaDica(3);
		}).getMessage();
		assertEquals("A posição da dica é inválida", mensagem);
	}

}

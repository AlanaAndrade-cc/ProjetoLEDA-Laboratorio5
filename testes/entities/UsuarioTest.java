package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

	private Usuario usuarioBase;

	@BeforeEach
	void setUp() {
		this.usuarioBase = new Usuario("Fulano", "123.456.789-00", "1234", "123456789");
	}

	@Test
	void testCriarUsuarioComNomeNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Usuario(null, "cpf", "senha", "matricula");
		}).getMessage();
		assertEquals("O nome do estudante não pode ser nulo", mensagem);
	}

	@Test
	void testCriarUsuarioComNomeVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("", "cpf", "senha", "matricula");
		}).getMessage();
		assertEquals("O nome do estudante não pode ser vazio", mensagem);
	}

	@Test
	void testCriarUsuarioComCPFNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Usuario("nome", null, "senha", "matricula");
		}).getMessage();
		assertEquals("O CPF do estudante não pode ser nulo", mensagem);
	}

	@Test
	void testCriarUsuarioCoCPFVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("nome", "", "senha", "matricula");
		}).getMessage();
		assertEquals("O CPF do estudante não pode ser vazio", mensagem);
	}

	@Test
	void testCriarUsuarioComSenhaNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Usuario("nome", "cpf", null, "matricula");
		}).getMessage();
		assertEquals("A senha do estudante não pode ser nula", mensagem);
	}

	@Test
	void testCriarUsuarioCoSenhaVazia() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("nome", "cpf", "", "matricula");
		}).getMessage();
		assertEquals("A senha do estudante não pode ser vazia", mensagem);
	}

	@Test
	void testCriarUsuarioCoSenhaPequena() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("nome", "cpf", "sen", "matricula");
		}).getMessage();
		assertEquals("A senha do estudante deve ter, pelo menos, 4 caracteres", mensagem);
	}

	@Test
	void testCriarUsuarioComMatriculaNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Usuario("nome", "cpf", "senha", null);
		}).getMessage();
		assertEquals("A matrícula do estudante não pode ser nula", mensagem);
	}

	@Test
	void testCriarUsuarioCoMatriculaVazia() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Usuario("nome", "cpf", "senha", "");
		}).getMessage();
		assertEquals("A matrícula do estudante não pode ser vazia", mensagem);
	}

	@Test
	void testAlterarSenhaComSenhaAntigaIncorreta() {
		assertFalse(usuarioBase.alteraSenha("senha", "novaSenha"));
	}

	@Test
	void testAlterarSenhaComSenhaAntigaCorreta() {
		assertTrue(usuarioBase.alteraSenha("1234", "novaSenha"));
	}

	@Test
	void testAlterarSenhaComSenhaNovaPequena() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			usuarioBase.alteraSenha("1234", "12");
		}).getMessage();
		assertEquals("A senha do estudante deve ter, pelo menos, 4 caracteres", mensagem);
		;
	}

	@Test
	void testApresentacaoEmRelatorio() {
		assertEquals("Nome: Fulano, CPF: 123.456.789-00 - matrícula 123456789", usuarioBase.apresentacaoEmRelatorio());
	}

	@Test
	void testExibeBonificacao() {
		assertEquals("Nome: Fulano, matrícula 123456789\nBonificação atual: 0", usuarioBase.exibeBonificacao());
	}

	@Test
	void testToString() {
		assertEquals("Nome: Fulano, matrícula 123456789", usuarioBase.toString());
	}

	@Test
	void testEqualsComMesmoUsuario() {
		assertTrue(usuarioBase.equals(usuarioBase));
	}

	@Test
	void testEqualsComOutroUsuarioComMesmoCpf() {
		Usuario outro = new Usuario("Ciclano", "123.456.789-00", "4567", "987654321");
		assertTrue(usuarioBase.equals(outro));
	}

	@Test
	void testEqualsComOutroUsuarioComDiferenteCpf() {
		Usuario outro = new Usuario("Fulano", "123.456.789-10", "1234", "123456789");
		assertFalse(usuarioBase.equals(outro));
	}
}

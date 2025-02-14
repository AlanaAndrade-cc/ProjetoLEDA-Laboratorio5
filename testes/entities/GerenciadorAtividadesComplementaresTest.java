package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadorAtividadesComplementaresTest {

	private Usuario estudante;

	@BeforeEach
	void setTup() {
		this.estudante = new Usuario("Fulano", "123.456.789-00", "1234", "12345679");
	}

	@Test
	void testCriarAtividadeComplementarComDescricaoNula() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Estagio(null, "link", 300, "empresa");
		}).getMessage();
		assertEquals("A descrição da atividade não pode ser nula", mensagem);
	}

	@Test
	void testCriarAtividadeComplementarComDescricaoVazia() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Monitoria("", "link", 3, "disciplina");
		}).getMessage();
		assertEquals("A descrição da atividade não pode ser vazia", mensagem);
	}

	@Test
	void testCriarAtividadeComplementarComLinkNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new RepresentacaoEstudantil("descrição", null, 2, "diretoria");
		}).getMessage();
		assertEquals("O link da atividade não pode ser nulo", mensagem);
	}

	@Test
	void testCriarAtividadeComplementarComLinkVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new PesquisaExtensao("descrição", "", 20, "pet");
		}).getMessage();
		assertEquals("O link da atividade não pode ser vazio", mensagem);
	}

	@Test
	void testCriarAtividadeDeTipoInvalidoEmUsuario() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			this.estudante.criaAtividadeComplementar("UmTipoInvalido", "descrição", "link", 301, "disciplina");
		}).getMessage();
		assertEquals("Tipo inválido para Atividades Complementares", mensagem);
	}

	@Test
	void testCalcularCreditoTipoDeAtividade() {
		assertEquals(0, this.estudante.calculaCreditos("Estagio"));
		this.estudante.criaAtividadeComplementar("Estagio", "descrição", "link", 300, "empresa");
		assertEquals(5, this.estudante.calculaCreditos("Estagio"));
	}

	@Test
	void testVerificarSeEstudanteAtingiuMeta() {
		this.estudante.criaAtividadeComplementar("Monitoria", "descrição", "link", 4, "disciplina");
		this.estudante.criaAtividadeComplementar("Estagio", "descrição", "link", 300, "empresa");
		assertFalse(this.estudante.atingiuMeta());
		this.estudante.criaAtividadeComplementar("PesquisaExtensao", "descrição", "link", 12, "pet");
		this.estudante.criaAtividadeComplementar("RepresentacaoEstudantil", "descrição", "link", 1, "comissao");
		assertTrue(this.estudante.atingiuMeta());
	}

	@Test
	void testGerarRelatorioFinalSemMetaAlcancada() {
		this.estudante.criaAtividadeComplementar("Monitoria", "descrição", "link", 4, "disciplina");
		this.estudante.criaAtividadeComplementar("Estagio", "descrição", "link", 300, "empresa");
		assertEquals("Ainda não atingiu a meta de créditos", this.estudante.relatorioFinal());
	}
}

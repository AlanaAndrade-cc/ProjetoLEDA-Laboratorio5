package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.Elemento;

public class TextoTest {

	private Elemento elemento;

	@BeforeEach
	void setUp() {
		this.elemento = new Texto("No Curso antigo (PPC 1999) o Estágio era um componente curricular (disciplina) "
				+ "optativo de 10 créditos e tinha como pré-requisito a disciplina de Engenharia de Software. "
				+ "A partir do PPC 2017 o estágio não é mais uma disciplina, sendo assim Não-Obrigatório. "
				+ "O tempo dedicado a essa atividade será contabilizado como Atividades Complementares "
				+ "conforme resolução específica.");
	}

	@Test
	void testCriarTextoComTextoNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Texto(null);
		}).getMessage();
		assertEquals("O texto não pode ser nulo", mensagem);
	}

	@Test
	void testCriarTextoComTextoVazio() {
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Texto("");
		}).getMessage();
		assertEquals("O texto não pode ser vazio", mensagem);
	}

	@Test
	void testValorBonificacao() {
		assertEquals(37, elemento.valorBonificacao());
	}

	@Test
	void testExibeDetalhes() {
		assertEquals("No Curso antigo (PPC 1999) o Estágio era um componente curricular (disciplina) "
				+ "optativo de 10 créditos e tinha como pré-requisito a disciplina de Engenharia de Software. "
				+ "A partir do PPC 2017 o estágio não é mais uma disciplina, sendo assim Não-Obrigatório. "
				+ "O tempo dedicado a essa atividade será contabilizado como Atividades Complementares "
				+ "conforme resolução específica. (371 caracteres)", elemento.exibeDetalhes());
	}

	@Test
	void testToString() {
		assertEquals("No Curso antigo (PPC 1999) o Estágio era um componente curricular (disciplina) "
				+ "optativo de 10 créditos e tinha como pré-requisito a disciplina de Engenharia de Software. "
				+ "A partir do PPC 2017 o estágio não é mais uma disciplina, sendo assim Não-Obrigatório. "
				+ "O tempo dedicado a essa atividade será contabilizado como Atividades Complementares "
				+ "conforme resolução específica.", elemento.toString());
	}
}

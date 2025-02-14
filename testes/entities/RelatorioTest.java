package entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.TipoAtividadeComplementar;

public class RelatorioTest {

	private Usuario estudante;
	private Map<TipoAtividadeComplementar, Integer> sumarizacao;

	@BeforeEach
	void setUp() {
		this.estudante = new Usuario("Fulano", "123.456.789-00", "1234", "123456789");
		this.sumarizacao = new HashMap<>();
		this.sumarizacao.put(TipoAtividadeComplementar.MONITORIA, 14);
		this.sumarizacao.put(TipoAtividadeComplementar.PESQUISAEXTENSAO, 10);
		this.sumarizacao.put(TipoAtividadeComplementar.ESTAGIO, 12);
		this.sumarizacao.put(TipoAtividadeComplementar.REPRESENTACAOESTUDANTIL, 2);
	}

	@Test
	void testCriarRelatorioComEstudanteNulo() {
		String mensagem = assertThrows(NullPointerException.class, () -> {
			new Relatorio(null, this.sumarizacao);
		}).getMessage();
		assertEquals("O usuário não pode ser nulo", mensagem);
	}

	@Test
	void testCriarRelatorioComSumarizacaoIncompleta() {
		this.sumarizacao.remove(TipoAtividadeComplementar.REPRESENTACAOESTUDANTIL);
		String mensagem = assertThrows(IllegalArgumentException.class, () -> {
			new Relatorio(this.estudante, this.sumarizacao);
		}).getMessage();
		assertEquals("A sumarização precisa ter todos os tipos de Atividade Complementar", mensagem);
	}

	@Test
	void testToString() {
		Relatorio relatorio = new Relatorio(estudante, sumarizacao);

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String hoje = LocalDate.now().format(formato);

		assertEquals("Nome: Fulano, CPF: 123.456.789-00 - matrícula 123456789, data " + hoje + "\n"
				+ "Créditos por atividades:\n" + "Estagio: 12\n" + "Monitoria: 14\n" + "PesquisaExtensao: 10\n"
				+ "RepresentacaoEstudantil: 2\n" + "Créditos totais: 38", relatorio.toString());
	}
}

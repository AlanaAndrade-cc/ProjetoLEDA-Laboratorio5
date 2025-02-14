package entities;

import java.util.HashMap;
import java.util.Map;

import enums.TipoAtividadeComplementar;
import interfaces.Atividade;

public class GerenciadorAtividadesComplementares {

	private Usuario estudante;
	private final int META_DE_CREDITOS;
	private Map<String, Atividade> atividadesComplementares;
	private Map<String, Relatorio> historicoRelatorios;

	private static int contador = 0;

	public GerenciadorAtividadesComplementares(Usuario estudante) {
		this.estudante = estudante;
		this.META_DE_CREDITOS = 22;
		this.atividadesComplementares = new HashMap<>();
		this.historicoRelatorios = new HashMap<>();
	}

	/**
	 * Cria uma nova atividade complementar, dependendo do tipo de atividade
	 * passada.
	 * 
	 * @param tipoAtividade    o tipo da atividade complementar
	 * @param descricao        a descrição da atividade
	 * @param linkDocumentacao o link da documentação comprobatória
	 * @param unidadeAcumulada a unidade de tempo da atividade
	 * @param especificidade   o campo específico da atividade
	 * @return o código da atividade criada
	 */
	public String criaAtividadeComplementar(String tipoAtividade, String descricao, String linkDocumentacao,
			int unidadeAcumulada, String especificidade) {
		String codigoAtividade = geraCodigoAtividade();

		switch (TipoAtividadeComplementar.fromTipo(tipoAtividade)) {
		case PESQUISAEXTENSAO:
			atividadesComplementares.put(codigoAtividade,
					new PesquisaExtensao(descricao, linkDocumentacao, unidadeAcumulada, especificidade));
			break;
		case MONITORIA:
			atividadesComplementares.put(codigoAtividade,
					new Monitoria(descricao, linkDocumentacao, unidadeAcumulada, especificidade));
			break;
		case ESTAGIO:
			atividadesComplementares.put(codigoAtividade,
					new Estagio(descricao, linkDocumentacao, unidadeAcumulada, especificidade));
			break;
		case REPRESENTACAOESTUDANTIL:
			atividadesComplementares.put(codigoAtividade,
					new RepresentacaoEstudantil(descricao, linkDocumentacao, unidadeAcumulada, especificidade));
			break;
		default:
			throw new IllegalArgumentException("Tipo inválido para Atividades Complementares");
		}
		return codigoAtividade;
	}

	/**
	 * Altera a descrição de uma atividade complementar específica.
	 * 
	 * @param codigoAtividade o código da atividade
	 * @param descricao       a nova descrição
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alteraDescricaoAtividade(String codigoAtividade, String descricao) {
		Atividade atividadeComplementar = atividadesComplementares.get(codigoAtividade);
		atividadeComplementar.setDescricao(descricao);
		return true;
	}

	/**
	 * Altera o link da documentação de uma atividade complementar específica.
	 * 
	 * @param codigoAtividade  o código da atividade
	 * @param linkDocumentacao o novo link
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alteraLinkAtividade(String codigoAtividade, String linkDocumentacao) {
		Atividade atividadeComplementar = atividadesComplementares.get(codigoAtividade);
		atividadeComplementar.setLinkDocumentacao(linkDocumentacao);
		return true;
	}

	/**
	 * Calcula os créditos acumulados para um tipo específico de atividade
	 * complementar.
	 * 
	 * @param tipoAtividade o tipo de atividade (e.g., "pesquisaextensao",
	 *                      "monitoria")
	 * @return O total de créditos acumulados para o tipo de atividade
	 */
	public int calculaCreditos(String tipoAtividade) {
		int creditos = 0;
		TipoAtividadeComplementar tipo = TipoAtividadeComplementar.fromTipo(tipoAtividade);
		Class<? extends Atividade> classe = tipo.getClasse();

		for (Atividade atividadeComplementar : atividadesComplementares.values()) {
			if (classe.isAssignableFrom(atividadeComplementar.getClass())) {
				creditos += atividadeComplementar.creditos();
			}
		}
		return creditos;
	}

	/**
	 * Gera um mapa de créditos, que conta a quantidade de créditos para cada tipo
	 * de atividade complementar
	 * 
	 * @return uma String com cada tipo de atividade complementar e os respectivos
	 *         créditos totais
	 */
	public String exibeMapaCreditos() {
		Map<TipoAtividadeComplementar, Integer> mapa = mapaCreditos();
		String mapaString = "";
		for (TipoAtividadeComplementar key : mapaCreditos().keySet()) {
			mapaString += "\n" + key.getTipo() + ": " + mapa.get(key) + "/" + key.getCreditosMaximos();
		}
		return mapaString.trim();
	}

	/**
	 * Verifica se o usuário atingiu a meta de créditos.
	 * 
	 * @return true se tiver atingido a meta, false caso contrário
	 */
	public boolean atingiuMeta() {
		return this.calculaCreditos() >= this.META_DE_CREDITOS;
	}

	/**
	 * Verifica se o usuário atingiu a meta de créditos para um tipo específico de
	 * atividade.
	 * 
	 * @return true se tiver atingido a meta, false caso contrário
	 */
	public boolean atingiuMeta(String tipoAtividade) {
		int creditos = calculaCreditos(tipoAtividade);
		TipoAtividadeComplementar tipo = TipoAtividadeComplementar.fromTipo(tipoAtividade);
		return creditos >= tipo.getCreditosMaximos();
	}

	/**
	 * Gera um relatório final contendo informações sobre o usuário e os créditos
	 * totais acumulados. Se o usuário não atingiu a meta de créditos, uma mensagem
	 * informando isso será retornada.
	 *
	 * @return uma String representando o relatório final gerado, ou uma mensagem
	 *         informando que a meta de créditos não foi atingida
	 */
	public String relatorioFinal() {
		if (!atingiuMeta()) {
			return "Ainda não atingiu a meta de créditos";
		}
		return new Relatorio(this.estudante, mapaCreditos()).toString();
	}

	/**
	 * Gera um relatório final para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade. Se o usuário não atingiu a meta de créditos para esse tipo, uma
	 * mensagem informando isso será retornada.
	 *
	 * @param tipoAtividade o tipo de atividade para a qual o relatório final é
	 *                      gerado
	 * @return uma String representando o relatório final gerado para o tipo de
	 *         atividade, ou uma mensagem informando que a meta não foi atingida
	 */
	public String relatorioFinal(String tipoAtividade) {
		TipoAtividadeComplementar tipo = TipoAtividadeComplementar.fromTipo(tipoAtividade);
		if (!atingiuMeta(tipoAtividade)) {
			return "Ainda não atingiu a meta de créditos para a atividade do tipo " + tipoAtividade;
		}
		return new RelatorioPorAtividade(this.estudante, mapaCreditos(), tipo).toString();
	}

	/**
	 * Gera um relatório parcial contendo informações sobre o usuário e a quantidade
	 * de créditos acumulados, com a opção de salvar esse relatório no histórico.
	 *
	 * @param salvar indica se o relatório deve ser salvo no histórico ou não
	 * @return uma String representando o relatório parcial gerado
	 */
	public String relatorioParcial(boolean salvar) {
		Relatorio relatorio = new Relatorio(this.estudante, mapaCreditos());
		if (salvar) {
			salvaRelatorio(relatorio);
		}
		return relatorio.toString();
	}

	/**
	 * Gera um relatório parcial para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade, com a opção de salvar esse relatório no histórico
	 *
	 * @param tipoAtividade o tipo de atividade para a qual o relatório é gerado
	 * @param salvar        indica se o relatório deve ser salvo no históricoou não
	 * @return uma String representando o relatório parcial gerado para o tipo de
	 *         atividade
	 */
	public String relatorioParcial(String tipoAtividade, boolean salvar) {
		TipoAtividadeComplementar tipo = TipoAtividadeComplementar.fromTipo(tipoAtividade);
		RelatorioPorAtividade relatorio = new RelatorioPorAtividade(this.estudante, mapaCreditos(), tipo);
		if (salvar) {
			salvaRelatorio(relatorio);
		}
		return relatorio.toString();
	}

	/**
	 * Retorna uma lista de todos os relatórios salvos no histórico, formatada como
	 * uma única String, onde cada relatório é separado por uma nova linha.
	 *
	 * @return uma String contendo todos os relatórios salvos no histórico
	 */
	public String listaHistorico() {
		String lista = "";
		for (Relatorio relatorio : historicoRelatorios.values()) {
			lista += "\n" + relatorio.toString();
		}
		return lista.trim();
	}

	/**
	 * Exclui um relatório do histórico com base na data fornecida.
	 *
	 * @param data a data do relatório a ser excluído
	 * @return true se o relatório foi excluído com sucesso, false caso contrário
	 */
	public boolean excluiRelatorio(String data) {
		if (this.historicoRelatorios.containsKey(data)) {
			this.historicoRelatorios.remove(data);
			return true;
		}
		return false;
	}

	private String geraCodigoAtividade() {
		return this.estudante.getCpf() + "_" + ++contador;
	}

	private int calculaCreditos() {
		int creditos = 0;
		for (Atividade atividadeComplementar : atividadesComplementares.values()) {
			creditos += atividadeComplementar.creditos();
		}

		return creditos;
	}

	private Map<TipoAtividadeComplementar, Integer> mapaCreditos() {
		Map<TipoAtividadeComplementar, Integer> mapa = new HashMap<>();
		for (TipoAtividadeComplementar tipo : TipoAtividadeComplementar.values()) {
			mapa.put(tipo, calculaCreditos(tipo.getTipo()));
		}
		return mapa;
	}

	private void salvaRelatorio(Relatorio relatorio) {
		this.historicoRelatorios.put(relatorio.data(), relatorio);
	}
}

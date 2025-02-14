package entities;

import interfaces.Atividade;
import validators.ValidadorAtividade;

/**
 * Representação abstrata de uma uma atividade complementar.
 * 
 * Esta classe implementa a interface Atividade.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public abstract class AtividadeComplementar implements Atividade {

	private String descricao;
	private String linkDocumentacao;
	private int unidadeAcumulada;

	/**
	 * Cria uma nova atividade complementar.
	 *
	 * @param descricao        a descrição da atividade complementar
	 * @param linkDocumentacao o link da documentação da atividade
	 * @param unidadeAcumulada a unidade acumulada para a atividade
	 * @throws NullPointerException     se descricao, linkDocumentacao ou
	 *                                  unidadeAcumulada forem nulos
	 * @throws IllegalArgumentException se descricao, linkDocumentacao ou
	 *                                  unidadeAcumulada forem inválidos
	 */
	public AtividadeComplementar(String descricao, String linkDocumentacao, int unidadeAcumulada) {
		ValidadorAtividade.validaDescricao(descricao);
		ValidadorAtividade.validaLink(linkDocumentacao);

		this.descricao = descricao;
		this.linkDocumentacao = linkDocumentacao;
		this.unidadeAcumulada = unidadeAcumulada;
	}

	@Override
	public void setDescricao(String descricao) {
		ValidadorAtividade.validaDescricao(descricao);
		this.descricao = descricao;
	}

	@Override
	public void setLinkDocumentacao(String linkDocumentacao) {
		ValidadorAtividade.validaLink(linkDocumentacao);
		this.linkDocumentacao = linkDocumentacao;
	}

	/**
	 * Calcula e retorna o total de créditos concedidos pela atividade complementar.
	 *
	 * @return o total de créditos, limitado ao máximo permitido
	 */
	@Override
	public int creditos() {
		int creditos = (int) (this.unidadeAcumulada * creditosPorUnidade());
		return (creditos <= creditosMaximos()) ? creditos : creditosMaximos();
	}

	/**
	 * Retorna uma representação textual da atividade complementar.
	 *
	 * @return uma String com a descrição, o link da documentação e a unidade
	 *         acumulada da atividade.
	 */
	@Override
	public String toString() {
		return "Descricao: " + this.descricao + ", link da documentação: " + this.linkDocumentacao
				+ "\nUnidade de tempo: " + this.unidadeAcumulada;
	}
}

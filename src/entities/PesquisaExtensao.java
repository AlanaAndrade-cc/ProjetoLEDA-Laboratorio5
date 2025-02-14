package entities;

import enums.SubtipoDePesquisaExtensao;
import enums.TipoAtividadeComplementar;
import validators.ValidadorAtividade;

/**
 * Classe que representa uma pesquisa e extensão como uma atividade
 * complementar.
 * 
 * Esta classe estende AtividadeComplementar.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class PesquisaExtensao extends AtividadeComplementar {

	private SubtipoDePesquisaExtensao subtipo;

	/**
	 * Cria uma nova pesquisa e extensão.
	 *
	 * @param descricao        a descrição da pesquisa e extensão
	 * @param linkDocumentacao o link da documentação da pesquisa e extensão
	 * @param unidadeAcumulada a unidade acumulada para a pesquisa e extensão,
	 *                         contabilizada em meses
	 * @param subtipo          o subtipo de pesquisa e extensão
	 * @throws NullPointerException     se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou subtipo forem nulos
	 * @throws IllegalArgumentException se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou subtipo forem inválidos
	 */
	public PesquisaExtensao(String descricao, String linkDocumentacao, int unidadeAcumulada, String subtipo) {
		super(descricao, linkDocumentacao, unidadeAcumulada);
		ValidadorAtividade.validaUnidadeAcumulada(unidadeAcumulada, 1);
		this.subtipo = SubtipoDePesquisaExtensao.fromSubtipo(subtipo);
	}

	/**
	 * Retorna o número máximo de créditos que a pesquisa e extensão pode conceder,
	 * que é 18.
	 *
	 * @return o número máximo de créditos
	 */
	public int creditosMaximos() {
		return TipoAtividadeComplementar.PESQUISAEXTENSAO.getCreditosMaximos();
	}

	/**
	 * Retorna o número de créditos concedidos por unidade de tempo, que é 10
	 * créditos a cada ano.
	 *
	 * @return o número de créditos por unidade
	 */
	public double creditosPorUnidade() {
		return (10.0 / 12.0);
	}

	/**
	 * Retorna uma representação textual da atividade complementar.
	 *
	 * @return uma String com as informações do supertipo, além do subtipo
	 */
	@Override
	public String toString() {
		return super.toString() + " meses. Tipo: Pesquisa e Extensão, subtipo: " + this.subtipo + "\nCréditos: "
				+ super.creditos();
	}
}

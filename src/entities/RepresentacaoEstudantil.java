package entities;

import enums.SubtipoDeRepresentacaoEstudantil;
import enums.TipoAtividadeComplementar;
import validators.ValidadorAtividade;

/**
 * Classe que representa uma representação estudantil como uma atividade
 * complementar.
 * 
 * Esta classe estende AtividadeComplementar.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class RepresentacaoEstudantil extends AtividadeComplementar {

	private SubtipoDeRepresentacaoEstudantil subtipo;

	/**
	 * Cria uma nova pesquisa e extensão.
	 *
	 * @param descricao        a descrição da pesquisa e extensão
	 * @param linkDocumentacao o link da documentação da pesquisa e extensão
	 * @param unidadeAcumulada a unidade acumulada para a pesquisa e extensão,
	 *                         contabilizada em anos
	 * @param subtipo          o subtipo de representação estudantil
	 * @throws NullPointerException     se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou subtipo forem nulos
	 * @throws IllegalArgumentException se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou subtipo forem inválidos
	 */
	public RepresentacaoEstudantil(String descricao, String linkDocumentacao, int unidadeAcumulada, String subtipo) {
		super(descricao, linkDocumentacao, unidadeAcumulada);
		ValidadorAtividade.validaUnidadeAcumulada(unidadeAcumulada, 1);
		this.subtipo = SubtipoDeRepresentacaoEstudantil.fromSubtipo(subtipo);
	}

	/**
	 * Retorna o número máximo de créditos que a representação estudantil pode
	 * conceder, que é 2.
	 *
	 * @return o número máximo de créditos
	 */
	public int creditosMaximos() {
		return TipoAtividadeComplementar.REPRESENTACAOESTUDANTIL.getCreditosMaximos();
	}

	/**
	 * Retorna o número de créditos concedidos por unidade de tempo, que é 2
	 * créditos a cada ano.
	 *
	 * @return o número de créditos por unidade
	 */
	public double creditosPorUnidade() {
		return 2;
	}

	/**
	 * Retorna uma representação textual da atividade complementar.
	 *
	 * @return uma String com as informações do supertipo, além do subtipo
	 */
	@Override
	public String toString() {
		return super.toString() + " anos. Tipo: Representação Estudantil, subtipo: " + this.subtipo + "\nCréditos: "
				+ super.creditos();
	}
}

package entities;

import enums.TipoAtividadeComplementar;
import validators.ValidadorAtividade;

/**
 * Classe que representa um estágio não-obrigaório como uma atividade
 * complementar.
 * 
 * Esta classe estende AtividadeComplementar.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Estagio extends AtividadeComplementar {

	private String nomeEmpresa;

	/**
	 * Cria um novo estágio.
	 *
	 * @param descricao        a descrição do estágio
	 * @param linkDocumentacao o link da documentação do estágio
	 * @param unidadeAcumulada a unidade acumulada para o estágio, contabilizado em
	 *                         horas
	 * @param nomeEmpresa      o nome da empresa onde o estágio é realizado
	 * @throws NullPointerException     se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou nomeEmpresa forem nulos
	 * @throws IllegalArgumentException se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou nomeEmpresa forem
	 *                                  inválidos
	 */
	public Estagio(String descricao, String linkDocumentacao, int unidadeAcumulada, String nomeEmpresa) {
		super(descricao, linkDocumentacao, unidadeAcumulada);
		ValidadorAtividade.validaUnidadeAcumulada(unidadeAcumulada, 300);
		ValidadorAtividade.validaEspecificacao(nomeEmpresa);
		this.nomeEmpresa = nomeEmpresa;
	}

	/**
	 * Retorna o número máximo de créditos que o estágio pode conceder, que é 18.
	 *
	 * @return o número máximo de créditos
	 */
	@Override
	public int creditosMaximos() {
		return TipoAtividadeComplementar.ESTAGIO.getCreditosMaximos();
	}

	/**
	 * Retorna o número de créditos concedidos por unidade de tempo, que é 1 crédito
	 * a cada 60 horas.
	 *
	 * @return o número de créditos por unidade
	 */
	@Override
	public double creditosPorUnidade() {
		return (1.0 / 60.0);
	}

	/**
	 * Retorna uma representação textual da atividade complementar.
	 *
	 * @return uma String com as informações do supertipo, além do nome da empresa
	 */
	@Override
	public String toString() {
		return super.toString() + " horas.\nTipo: Estágio Não-Obrigatório, instituição: " + this.nomeEmpresa
				+ "\nCréditos: " + super.creditos();
	}
}

package entities;

import enums.TipoAtividadeComplementar;
import validators.ValidadorAtividade;

/**
 * Classe que representa uma monitoria como uma atividade complementar.
 * 
 * Esta classe estende AtividadeComplementar.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Monitoria extends AtividadeComplementar {

	private String disciplina;

	/**
	 * Cria uma nova monitoria.
	 *
	 * @param descricao        a descrição da monitoria
	 * @param linkDocumentacao o link da documentação da monitoria
	 * @param unidadeAcumulada a unidade acumulada para a monitoria, contabilizada
	 *                         em semestres
	 * @param disciplina       o nome da disciplina da monitoria
	 * @throws NullPointerException     se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou disciplina forem nulos
	 * @throws IllegalArgumentException se descricao, linkDocumentacao,
	 *                                  unidadeAcumulada ou disciplina forem
	 *                                  inválidos
	 */
	public Monitoria(String descricao, String linkDocumentacao, int unidadeAcumulada, String disciplina) {
		super(descricao, linkDocumentacao, unidadeAcumulada);
		ValidadorAtividade.validaUnidadeAcumulada(unidadeAcumulada, 1);
		ValidadorAtividade.validaEspecificacao(disciplina);
		this.disciplina = disciplina;
	}

	/**
	 * Retorna o número máximo de créditos que a monitoria pode conceder, que é 16.
	 *
	 * @return o número máximo de créditos
	 */
	public int creditosMaximos() {
		return TipoAtividadeComplementar.MONITORIA.getCreditosMaximos();
	}

	/**
	 * Retorna o número de créditos concedidos por unidade de tempo, que é 4
	 * créditos a cada semestre.
	 *
	 * @return o número de créditos por unidade
	 */
	public double creditosPorUnidade() {
		return 4;
	}

	/**
	 * Retorna uma representação textual da atividade complementar.
	 *
	 * @return uma String com as informações do supertipo, além do nome da
	 *         disciplina
	 */
	@Override
	public String toString() {
		return super.toString() + " semestres. Tipo: Monitoria, disciplina: " + this.disciplina + "\nCréditos: "
				+ super.creditos();
	}
}

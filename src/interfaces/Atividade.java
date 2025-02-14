package interfaces;

/**
 * Interface que representa uma atividade. As classes que implementam esta
 * interface devem fornecer as funcionalidades para definir descrições, links de
 * documentação e calcular créditos associados às atividades.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public interface Atividade {

	/**
	 * Define a descrição da atividade.
	 *
	 * @param descricao a descrição da atividade
	 */
	public void setDescricao(String descricao);

	/**
	 * Define o link de documentação associado à atividade.
	 *
	 * @param linkDocumentacao o link da documentação
	 */
	public void setLinkDocumentacao(String linkDocumentacao);

	/**
	 * Retorna o número máximo de créditos que a atividade pode conceder.
	 *
	 * @return o número máximo de créditos
	 */
	public int creditosMaximos();

	/**
	 * Retorna o número de créditos concedidos por unidade de tempo da atividade.
	 *
	 * @return o número de créditos por unidade
	 */
	public double creditosPorUnidade();

	/**
	 * Retorna o total de créditos que a atividade concede.
	 *
	 * @return o total de créditos da atividade
	 */
	public int creditos();
}

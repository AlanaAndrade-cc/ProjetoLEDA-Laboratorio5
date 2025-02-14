package enums;

import entities.Estagio;
import entities.Monitoria;
import entities.PesquisaExtensao;
import entities.RepresentacaoEstudantil;
import interfaces.Atividade;

/**
 * Enumeração que representa os tipos de Atividades Complementares. Cada tipo
 * está associado a uma classe que implementa a interface Atividade e a uma
 * descrição textual do tipo.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public enum TipoAtividadeComplementar {

	PESQUISAEXTENSAO(PesquisaExtensao.class, "PesquisaExtensao", 18), MONITORIA(Monitoria.class, "Monitoria", 16),
	ESTAGIO(Estagio.class, "Estagio", 18),
	REPRESENTACAOESTUDANTIL(RepresentacaoEstudantil.class, "RepresentacaoEstudantil", 2);

	private Class<? extends Atividade> classe;
	private String tipo;
	private int creditosMaximos;

	/**
	 * Cria o enumerador.
	 *
	 * @param classe a classe que representa o tipo de atividade complementar
	 * @param tipo   a descrição do tipo de atividade complementar
	 */
	TipoAtividadeComplementar(Class<? extends Atividade> classe, String tipo, int creditosMaximos) {
		this.classe = classe;
		this.tipo = tipo;
		this.creditosMaximos = creditosMaximos;
	}

	public Class<? extends Atividade> getClasse() {
		return this.classe;
	}

	/**
	 * Retorna a descrição textual do tipo de atividade complementar.
	 *
	 * @return a descrição do tipo de atividade
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Retorna o valor máximo de créditos do tipo de atividade complementar.
	 *
	 * @return a quantidade de créditos máximos do tipo de atividade
	 */
	public int getCreditosMaximos() {
		return this.creditosMaximos;
	}

	/**
	 * Obtém o tipo de atividade complementar a partir da classe correspondente.
	 *
	 * @param classe a classe a ser verificada
	 * @return o tipo de atividade complementar correspondente
	 * @throws IllegalArgumentException se a classe não corresponder a nenhum tipo
	 */
	public static TipoAtividadeComplementar fromClasse(Class<? extends Atividade> classe) {
		for (TipoAtividadeComplementar tipo : TipoAtividadeComplementar.values()) {
			if (tipo.getClasse().isAssignableFrom(classe)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido para Atividades Complementares");
	}

	/**
	 * Obtém o tipo de atividade complementar a partir da descrição textual.
	 *
	 * @param tipoAtividade a descrição do tipo de atividade a ser verificada
	 * @return o tipo de atividade complementar correspondente
	 * @throws IllegalArgumentException se a descrição não corresponder a nenhum
	 *                                  tipo
	 */
	public static TipoAtividadeComplementar fromTipo(String tipoAtividade) {
		for (TipoAtividadeComplementar tipo : TipoAtividadeComplementar.values()) {
			if (tipo.getTipo().equalsIgnoreCase(tipoAtividade)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Tipo inválido para Atividades Complementares");
	}
}

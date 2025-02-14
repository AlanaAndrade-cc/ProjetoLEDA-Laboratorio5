package validators;

/**
 * Classe responsável pela validação das informações de uma
 * AtividadeComplementar.
 */

public class ValidadorAtividade extends Validador {

	/**
	 * Representação de várias mensagens de erro relacionadas à validação das
	 * informações de AtividadeComplementar.
	 */
	private static enum Erro {
		DESCRICAO_NULA("A descrição da atividade não pode ser nula"),
		DESCRICAO_VAZIA("A descrição da atividade não pode ser vazia"),
		LINK_NULO("O link da atividade não pode ser nulo"), LINK_VAZIO("O link da atividade não pode ser vazio"),
		UNIDADE_ACUMULADA_BAIXA("A unidade de tempo acumulada não é suficiente"),
		ESPECIFICACAO_NULA("A especificação da atividade não pode ser nula"),
		ESPECIFICACAO_VAZIA("A especificação da atividade não pode ser vazia");

		private final String mensagem;

		/**
		 * Cria o Enum
		 * 
		 * @param mensagem a mensagem relacionada ao erro específico
		 */
		Erro(String mensagem) {
			this.mensagem = mensagem;
		}

		/**
		 * Retorna a mensagem de erro
		 * 
		 * @return String com a mensagem de erro específica
		 */
		public String mensagem() {
			return this.mensagem;
		}
	}

	/**
	 * Valida se a descrição de atividade é válida.
	 * 
	 * A descrição deve ser uma string não nula e não vazia.
	 * 
	 * @param descricao a descrição a ser validada
	 * @throws NullPointerException     se descricao for null
	 * @throws IllegalArgumentException se descricao for vazia
	 */
	public static void validaDescricao(String descricao) {
		validaString(descricao, Erro.DESCRICAO_NULA.mensagem(), Erro.DESCRICAO_VAZIA.mensagem());
	}

	/**
	 * Valida se o link de atividade é válido.
	 * 
	 * O link deve ser uma string não nula e não vazia.
	 * 
	 * @param link o link a ser validado
	 * @throws NullPointerException     se link for null
	 * @throws IllegalArgumentException se link for vazio
	 */
	public static void validaLink(String link) {
		validaString(link, Erro.LINK_NULO.mensagem(), Erro.LINK_VAZIO.mensagem());
	}

	/**
	 * Valida se a unidade de tempo de atividade é válida.
	 * 
	 * @param unidadeAcumulada a unidade de tempo a ser validada
	 * @param unidadeMinima    a quantidade mínima para a unidade de tempo
	 * @throws IllegalArgumentException se unidadeAcumulada for menor que
	 *                                  unidadeMinima
	 */
	public static void validaUnidadeAcumulada(int unidadeAcumulada, int unidadeMinima) {
		validaInt(unidadeAcumulada, unidadeMinima, Erro.UNIDADE_ACUMULADA_BAIXA.mensagem());
	}

	/**
	 * Valida se a especificação de atividade é válida.
	 * 
	 * A especificação deve ser uma string não nula e não vazia.
	 * 
	 * @param especificao a especificação a ser validada
	 * @throws NullPointerException     se especificao for null
	 * @throws IllegalArgumentException se especificao for vazia
	 */
	public static void validaEspecificacao(String especificao) {
		validaString(especificao, Erro.ESPECIFICACAO_NULA.mensagem(), Erro.ESPECIFICACAO_VAZIA.mensagem());
	}
}

package validators;

import java.util.Map;

import enums.TipoAtividadeComplementar;

/**
 * Classe responsável pela validação das informações de um Relatorio.
 */

public class ValidadorRelatorio extends Validador {

	/**
	 * Representação de várias mensagens de erro relacionadas à validação das
	 * informações do Relatorio.
	 */
	private static enum Erro {
		SUMARIZACAO_INCOMPLETA("A sumarização precisa ter todos os tipos de Atividade Complementar");

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
	 * Valida se o mapa de sumarização tem todos os tipos de AtividadeComplementar.
	 * 
	 * @param sumarizacao o mapa a ser validado
	 * @throws IllegalArgumentException se algum tipo de Atividade Complementar não
	 *                                  estiver em sumarizacao
	 */
	public static void validaSumarizacao(Map<TipoAtividadeComplementar, Integer> sumarizacao) {
		for (TipoAtividadeComplementar tipo : TipoAtividadeComplementar.values()) {
			if (!sumarizacao.keySet().contains(tipo)) {
				throw new IllegalArgumentException(Erro.SUMARIZACAO_INCOMPLETA.mensagem());
			}
		}
	}
}

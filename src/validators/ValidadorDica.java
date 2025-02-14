package validators;

import entities.Dica;

/**
 * Classe responsável pela validação das informações de um Elemento.
 */

public class ValidadorDica extends Validador {

	/**
	 * Representação de várias mensagens de erro relacionadas à validação das
	 * informações de Elemento.
	 */
	private static enum Erro {
		TEXTO_NULO("O texto não pode ser nulo"), TEXTO_VAZIO("O texto não pode ser vazio"),
		TEXTO_GRANDE("O texto é de, no máximo, 500 caracteres"), LINK_NULO("O link não pode ser nulo"),
		LINK_VAZIO("O link não pode ser vazio"), CABECALHO_NULO("O cabeçalho não pode ser nulo"),
		CABECALHO_VAZIO("O cabeçalho não pode ser vazio"), TEMPO_PEQUENO("O tempo deve ser um valor positivo"),
		TITULO_NULO("O título não pode ser nulo"), TITULO_VAZIO("O título não pode ser vazio"),
		FONTE_NULA("A fonte não pode ser nula"), FONTE_VAZIA("A fonte não pode ser vazia"),
		ANO_PEQUENO("O ano deve ser um valor positivo"),
		IMPORTANCIA_INVALIDA("A importância deve ser um valor entre 1 e 5"),
		POSICAO_INVALIDA("A posição da dica é inválida"), DICA_NULA("A dica não pode ser nula");

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
	 * Valida se o texto do elemento é válido.
	 * 
	 * O texto deve ser uma string não nula e não vazia.
	 * 
	 * @param texto o texto a ser validado
	 * @throws NullPointerException     se texto for null
	 * @throws IllegalArgumentException se texto for vazio ou se passar de 500
	 *                                  caracteres
	 */
	public static void validaTexto(String texto) {
		validaString(texto, Erro.TEXTO_NULO.mensagem(), Erro.TEXTO_VAZIO.mensagem());
		if (texto.length() > 500) {
			throw new IllegalArgumentException(Erro.TEXTO_GRANDE.mensagem());
		}
	}

	/**
	 * Valida se o link do elemento é válido.
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
	 * Valida se o cabeçalho do elemento é válido.
	 * 
	 * O cabeçalho deve ser uma string não nula e não vazia.
	 * 
	 * @param cabecalho o cabeçalho a ser validado
	 * @throws NullPointerException     se cabecalho for null
	 * @throws IllegalArgumentException se cabecalho for vazio
	 */
	public static void validaCabecalho(String cabecalho) {
		validaString(cabecalho, Erro.CABECALHO_NULO.mensagem(), Erro.CABECALHO_VAZIO.mensagem());
	}

	/**
	 * Valida se o tempo do elemento é válido.
	 * 
	 * @param tempo       o tempo a ser validado
	 * @param tempoMinimo a quantidade mínima para tempo
	 * @throws IllegalArgumentException se tempo for menor que tempoMinimo
	 */
	public static void validaTempo(int tempo, int tempoMinimo) {
		validaInt(tempo, tempoMinimo, Erro.TEMPO_PEQUENO.mensagem());
	}

	/**
	 * Valida se o título do elemento é válido.
	 * 
	 * O título deve ser uma string não nula e não vazia.
	 * 
	 * @param titulo o título a ser validado
	 * @throws NullPointerException     se titulo for null
	 * @throws IllegalArgumentException se titulo for vazio
	 */
	public static void validaTitulo(String titulo) {
		validaString(titulo, Erro.TITULO_NULO.mensagem(), Erro.TITULO_VAZIO.mensagem());
	}

	/**
	 * Valida se a fonte do elemento é válido.
	 * 
	 * A fonte deve ser uma string não nula e não vazia.
	 * 
	 * @param fonte a fonte a ser validada
	 * @throws NullPointerException     se fonte for null
	 * @throws IllegalArgumentException se fonte for vazia
	 */
	public static void validaFonte(String fonte) {
		validaString(fonte, Erro.FONTE_NULA.mensagem(), Erro.FONTE_VAZIA.mensagem());
	}

	/**
	 * Valida se o ano do elemento é válido.
	 * 
	 * @param ano         o ano a ser validado
	 * @param tempoMinimo a quantidade mínima para ano
	 * @throws IllegalArgumentException se ano for menor que anoMinimo
	 */
	public static void validaAno(int ano, int anoMinimo) {
		validaInt(ano, 0, Erro.ANO_PEQUENO.mensagem());
	}

	/**
	 * Valida se a importância do elemento é válida.
	 * 
	 * @param importancia       a importância a ser validada
	 * @param importanciaMinima a quantidade mínima para importancia
	 * @param importanciaMaxima a quantidade máxima para importancia
	 * @throws IllegalArgumentException se importancia for menor que
	 *                                  importanciaMinima ou maior que
	 *                                  importanciaMaxima
	 */
	public static void validaImportancia(int importancia, int importanciaMinima, int importanciaMaxima) {
		validaInt(importancia, importanciaMinima, importanciaMaxima, Erro.IMPORTANCIA_INVALIDA.mensagem());
	}

	public static void validaPosicao(int posicao, int posicaoMaxima) {
		validaInt(posicao, 1, posicaoMaxima, Erro.POSICAO_INVALIDA.mensagem());
	}

	/**
	 * Valida se o objeto Dica é válido.
	 * 
	 * O objeto não pode ser nulo.
	 * 
	 * @param dica o objeto a ser validado
	 * @throws NullPointerException se dica for null
	 */
	public static void validaDica(Dica dica) {
		validaObjeto(dica, Erro.DICA_NULA.mensagem());
	}
}

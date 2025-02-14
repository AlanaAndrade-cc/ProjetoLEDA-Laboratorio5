package validators;

/**
 * Classe abstrata responsável por fornecer métodos de validação geral para
 * diferentes tipos de dados.
 */

public abstract class Validador {

	/**
	 * Valida se uma string é nula ou vazia.
	 *
	 * @param valor              a string a ser validada
	 * @param mensagemValorNulo  a mensagem de erro caso a string seja nula
	 * @param mensagemValorVazio a mensagem de erro caso a string seja vazia
	 * @throws NullPointerException     se a string for nula
	 * @throws IllegalArgumentException se a string for vazia
	 */
	protected static void validaString(String valor, String mensagemValorNulo, String mensagemValorVazio) {
		if (valor == null) {
			throw new NullPointerException(mensagemValorNulo);
		}
		if (valor.isBlank()) {
			throw new IllegalArgumentException(mensagemValorVazio);
		}
	}

	/**
	 * Valida se um objeto é nulo.
	 *
	 * @param objeto             o objeto a ser validado
	 * @param mensagemObjetoNulo a mensagem de erro caso o objeto seja nulo
	 * @throws NullPointerException se o objeto for nulo
	 */
	protected static <T> void validaObjeto(T objeto, String mensagemObjetoNulo) {
		if (objeto == null) {
			throw new NullPointerException(mensagemObjetoNulo);
		}
	}

	/**
	 * Valida se um valor inteiro é maior ou igual a um mínimo especificado.
	 *
	 * @param valor        o valor inteiro a ser validado
	 * @param minimo       o valor mínimo permitido
	 * @param mensagemErro a mensagem de erro caso o valor seja menor que o mínimo
	 * @throws IllegalArgumentException se o valor for menor que o mínimo
	 */
	protected static void validaInt(int valor, int minimo, String mensagemErro) {
		if (valor < minimo) {
			throw new IllegalArgumentException(mensagemErro);
		}
	}

	/**
	 * Valida se um valor inteiro está dentro de um intervalo especificado (mínimo e
	 * máximo).
	 *
	 * @param valor        o valor inteiro a ser validado
	 * @param minimo       o valor mínimo permitido
	 * @param maximo       o valor máximo permitido
	 * @param mensagemErro a mensagem de erro caso o valor esteja fora do intervalo
	 * @throws IllegalArgumentException se o valor for menor que o mínimo ou maior
	 *                                  que o máximo
	 */
	protected static void validaInt(int valor, int minimo, int maximo, String mensagemErro) {
		if (valor < minimo || valor > maximo) {
			throw new IllegalArgumentException(mensagemErro);
		}
	}
}

package entities;

import interfaces.Elemento;
import validators.ValidadorDica;

/**
 * Representa um elemento de texto, que possui um texto descritivo.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Texto implements Elemento {

	private String texto;

	/**
	 * Cria um novo elemento de Texto.
	 *
	 * @param texto o texto do elemento
	 * @throws NullPointerException     se texto for nulo
	 * @throws IllegalArgumentException se texto for vazio
	 */
	public Texto(String texto) {
		ValidadorDica.validaTexto(texto);
		this.texto = texto;
	}

	/**
	 * Calcula e retorna o valor da bonificação com base no tamanho do texto. A
	 * bonificação é calculada como 1 ponto a cada 10 caracteres, a partir de 100
	 * caracteres.
	 *
	 * @return o valor da bonificação calculado
	 */
	@Override
	public int valorBonificacao() {
		if (this.texto.length() < 100) {
			return 0;
		}

		return this.texto.length() / 10;
	}

	/**
	 * Exibe os detalhes do elemento, com o tamanho do texto.
	 *
	 * @return uma String representando os detalhes do elemento
	 */
	@Override
	public String exibeDetalhes() {
		return toString() + " (" + this.texto.length() + " caracteres)";
	}

	/**
	 * Retorna uma representação em String do texto, incluindo o texto descritivo.
	 *
	 * @return uma String com o texto
	 */
	@Override
	public String toString() {
		return this.texto;
	}
}

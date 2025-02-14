package entities;

import interfaces.Elemento;
import validators.ValidadorDica;

/**
 * Representa um elemento de referência, que possui um título, uma fonte, um
 * ano, uma flag de acesso e um grau de importância.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Referencia implements Elemento {

	private String titulo;
	private String fonte;
	private int ano;
	private boolean conferida;
	private int importancia;

	public Referencia(String titulo, String fonte, int ano, boolean conferida, int importancia) {
		ValidadorDica.validaTitulo(titulo);
		ValidadorDica.validaFonte(fonte);
		ValidadorDica.validaAno(ano, 1);
		ValidadorDica.validaImportancia(importancia, 1, 5);

		/**
		 * Cria um novo elemento de Referencia.
		 *
		 * @param titulo      o título da referência
		 * @param fonte       a fonte da referência
		 * @param ano         o ano da referência
		 * @param coferida    a flag de acesso à referência
		 * @param importancia o grau de importância da referência
		 * @throws NullPointerException     se titulo ou fonte forem nulos
		 * @throws IllegalArgumentException se titulo ou fonte forem vazios ou se ano ou
		 *                                  importancia foram inválidos
		 */
		this.titulo = titulo;
		this.fonte = fonte;
		this.ano = ano;
		this.conferida = conferida;
		this.importancia = importancia;
	}

	/**
	 * Calcula e retorna o valor da bonificação com base na verificação da
	 * referência. A bonificação é calculada como 15 pontos se a referência foi
	 * conferida.
	 *
	 * @return o valor da bonificação calculado
	 */
	@Override
	public int valorBonificacao() {
		return (this.conferida) ? 15 : 0;
	}

	/**
	 * Exibe os detalhes do elemento, com o grau de importância.
	 *
	 * @return uma String representando os detalhes do elemento
	 */
	@Override
	public String exibeDetalhes() {
		return toString() + " Importância: " + this.importancia;
	}

	/**
	 * Retorna uma representação em String da referência, incluindo o título, fonte
	 * e ano.
	 *
	 * @return uma String com o o título, fonte e ano
	 */
	@Override
	public String toString() {
		return "Referência: " + this.titulo + " " + this.fonte + ", ano: " + this.ano + ".";
	}
}

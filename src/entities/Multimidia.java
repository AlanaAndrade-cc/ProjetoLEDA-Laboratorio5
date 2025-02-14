package entities;

import interfaces.Elemento;
import validators.ValidadorDica;

/**
 * Representa um elemento de multimídia, que possui um link, um cabeçalho e um
 * tempo de duração, em segundos.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Multimidia implements Elemento {

	private String link;
	private String cabecalho;
	private int tempo;

	/**
	 * Cria um novo elemento de Multimidia.
	 *
	 * @param link      o link da multimídia
	 * @param cabecalho o cabeçalho ou título da multimídia
	 * @param tempo     o tempo de duração em segundos
	 * @throws NullPointerException     se link, cabeçalho ou tempo forem nulos
	 * @throws IllegalArgumentException se link, cabeçalho ou tempo forem vazios
	 */
	public Multimidia(String link, String cabecalho, int tempo) {
		ValidadorDica.validaLink(link);
		ValidadorDica.validaCabecalho(cabecalho);
		ValidadorDica.validaTempo(tempo, 1);

		this.link = link;
		this.cabecalho = cabecalho;
		this.tempo = tempo;
	}

	/**
	 * Calcula e retorna o valor da bonificação com base no tempo da multimídia. A
	 * bonificação é calculada como 5 pontos por minuto.
	 *
	 * @return o valor da bonificação calculado
	 */
	@Override
	public int valorBonificacao() {
		return (this.tempo / 60) * 5;
	}

	/**
	 * Exibe os detalhes do elemento, com o tempo de duração.
	 *
	 * @return uma String representando os detalhes do elemento
	 */
	@Override
	public String exibeDetalhes() {
		return toString() + " (" + this.tempo + " segundos)";
	}

	/**
	 * Retorna uma representação em String da multimídia, incluindo o link e
	 * cabeçalho.
	 *
	 * @return uma String com o link e cabeçalho da multimídia
	 */
	@Override
	public String toString() {
		return "Link: " + this.link + ", " + this.cabecalho;
	}
}

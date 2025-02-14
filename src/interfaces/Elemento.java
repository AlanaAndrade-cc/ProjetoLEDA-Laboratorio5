package interfaces;

/**
 * Interface que representa um elemento para compor Dica. As classes que
 * implementam esta interface devem fornecer as funcionalidades para calcular a
 * bonificação do elemento e para exibir os detalhes.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public interface Elemento {

	public int valorBonificacao();

	public String exibeDetalhes();
}

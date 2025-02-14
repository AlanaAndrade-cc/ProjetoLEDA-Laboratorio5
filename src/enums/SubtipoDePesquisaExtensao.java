package enums;

/**
 * Enumeração que representa os subtipos de Pesquisa e Extensão. Cada subtipo é
 * associado a uma descrição textual.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public enum SubtipoDePesquisaExtensao {

	PET("pet"), PIBIC("pibic"), PIVIC("pivic"), PIBITI("pibiti"), PIVITI("piviti"), PROBEX("probex"), PDI("pdi");

	private String subtipo;

	/**
	 * Cria o enumerador.
	 *
	 * @param subtipo a descrição do subtipo da representação estudantil
	 */
	SubtipoDePesquisaExtensao(String subtipo) {
		this.subtipo = subtipo;
	}

	private String getSubtipo() {
		return this.subtipo;
	}

	/**
	 * Obtém o subtipo de representação estudantil a partir da descrição
	 * correspondente.
	 *
	 * @param subtipo a descrição do subtipo a ser verificada
	 * @return o subtipo correspondente
	 * @throws IllegalArgumentException se a descrição não corresponder a nenhum
	 *                                  subtipo
	 */
	public static SubtipoDePesquisaExtensao fromSubtipo(String subtipo) {
		for (SubtipoDePesquisaExtensao tipo : SubtipoDePesquisaExtensao.values()) {
			if (tipo.getSubtipo().equalsIgnoreCase(subtipo)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Subtipo inválido para Pesquisa e Extensão");
	}
}

package enums;

/**
 * Enumeração que representa os subtipos de Representação Estudantil. Cada
 * subtipo é associado a uma descrição textual.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public enum SubtipoDeRepresentacaoEstudantil {

	DIRETORIA("diretoria"), COMISSAO("comissao");

	private String subtipo;

	/**
	 * Cria o enumerador.
	 *
	 * @param subtipo a descrição do subtipo da representação estudantil
	 */
	SubtipoDeRepresentacaoEstudantil(String subtipo) {
		this.subtipo = subtipo;
	}

	private String getDescricao() {
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
	public static SubtipoDeRepresentacaoEstudantil fromSubtipo(String subtipo) {
		for (SubtipoDeRepresentacaoEstudantil tipo : SubtipoDeRepresentacaoEstudantil.values()) {
			if (tipo.getDescricao().equalsIgnoreCase(subtipo)) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Subtipo inválido para Representação Estudantil");
	}
}

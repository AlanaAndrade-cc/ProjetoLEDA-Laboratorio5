package entities;

import java.util.Map;

import enums.TipoAtividadeComplementar;

public class RelatorioPorAtividade extends Relatorio {

	private TipoAtividadeComplementar tipoEspecifico;

	public RelatorioPorAtividade(Usuario estudante, Map<TipoAtividadeComplementar, Integer> sumarizacao,
			TipoAtividadeComplementar tipoEspecifico) {
		super(estudante, sumarizacao);
		this.tipoEspecifico = tipoEspecifico;
	}

	@Override
	public String toString() {
		String apresentacao = super.apresentacaoDoEstudante() + "\nCréditos por atividade:";
		apresentacao += "\n" + this.tipoEspecifico.getTipo() + ": "
				+ super.creditoPorTipoAtividade(this.tipoEspecifico);
		return apresentacao;
	}
}

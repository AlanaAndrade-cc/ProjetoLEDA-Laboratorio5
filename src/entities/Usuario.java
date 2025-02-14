package entities;

import java.util.Objects;

import validators.ValidadorUsuario;

/**
 * Representação de um usuário do sistema, possui informações como nome, cpf,
 * senha e matrícula. Além disso, cada usuário tem uma coleção de atividades
 * complementares, uma bonificação relacionada às dicas que o usuário fornece e
 * uma meta de créditos a ser atingida.
 * 
 * Um usuário é identificado unicamente pelo CPF.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Usuario implements Comparable<Usuario> {

	private String nome;
	private String cpf;
	private String senha;
	private String matricula;
	private int bonificacao;
	private GerenciadorAtividadesComplementares gerenciadorAtividades;;

	/**
	 * Cria um novo usuário, depois de validar todos os campos.
	 * 
	 * @param nome      o nome do usuário
	 * @param cpf       o CPF do usuário
	 * @param senha     a senha do usuário
	 * @param matricula a matrícula do usuário
	 * @throws NullPointerException     se nome, cpf, senha ou matricula forem nulos
	 * @throws IllegalArgumentException se nome, cpf, senha ou matricula forem
	 *                                  inválidos
	 */
	public Usuario(String nome, String cpf, String senha, String matricula) {
		ValidadorUsuario.validaNome(nome);
		ValidadorUsuario.validaCpf(cpf);
		ValidadorUsuario.validaSenha(senha);
		ValidadorUsuario.validaMatricula(matricula);

		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.bonificacao = 0;
		this.gerenciadorAtividades = new GerenciadorAtividadesComplementares(this);
	}

	public String getNome() {
		return this.nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public int getBonificacao() {
		return this.bonificacao;
	}

	/**
	 * Altera a senha do usuário, se a senha antiga for validada.
	 * 
	 * @param senhaAntiga a senha antiga do usuário
	 * @param senhaNova   a nova senha do usuário
	 * @return
	 */
	public boolean alteraSenha(String senhaAntiga, String senhaNova) {
		if (validaSenha(senhaAntiga)) {
			ValidadorUsuario.validaSenha(senhaNova);
			this.senha = senhaNova;
			return true;
		}

		return false;
	}

	/**
	 * Valida se a senha fornecida é a mesma que a atual.
	 * 
	 * @param senha a senha a ser validada
	 * @return true se a senha for igual à atual, false caso contrário
	 */
	public boolean validaSenha(String senha) {
		return this.senha.equals(senha);
	}

	/**
	 * Recebe bonificação e adiciona ao valor atual.
	 * 
	 * @param bonificacao o valor da bonificação a ser adicionado
	 */
	public void recebeBonificacao(int bonificacao) {
		this.bonificacao += bonificacao;
	}

	/**
	 * Cria uma nova atividade complementar, dependendo do tipo de atividade
	 * passada.
	 * 
	 * @param tipoAtividade    o tipo da atividade complementar
	 * @param descricao        a descrição da atividade
	 * @param linkDocumentacao o link da documentação comprobatória
	 * @param unidadeAcumulada a unidade de tempo da atividade
	 * @param especificidade   o campo específico da atividade
	 * @return o código da atividade criada
	 */
	public String criaAtividadeComplementar(String tipoAtividade, String descricao, String linkDocumentacao,
			int unidadeAcumulada, String especificidade) {
		return this.gerenciadorAtividades.criaAtividadeComplementar(tipoAtividade, descricao, linkDocumentacao,
				unidadeAcumulada, especificidade);
	}

	/**
	 * Altera a descrição de uma atividade complementar específica.
	 * 
	 * @param codigoAtividade o código da atividade
	 * @param descricao       a nova descrição
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alteraDescricaoAtividade(String codigoAtividade, String descricao) {
		return this.gerenciadorAtividades.alteraDescricaoAtividade(codigoAtividade, descricao);
	}

	/**
	 * Altera o link da documentação de uma atividade complementar específica.
	 * 
	 * @param codigoAtividade  o código da atividade
	 * @param linkDocumentacao o novo link
	 * @return true se a alteração foi bem-sucedida, false caso contrário
	 */
	public boolean alteraLinkAtividade(String codigoAtividade, String linkDocumentacao) {
		return this.gerenciadorAtividades.alteraLinkAtividade(codigoAtividade, linkDocumentacao);
	}

	/**
	 * Calcula os créditos acumulados para um tipo específico de atividade
	 * complementar.
	 * 
	 * @param tipoAtividade o tipo de atividade (e.g., "pesquisaextensao",
	 *                      "monitoria")
	 * @return O total de créditos acumulados para o tipo de atividade
	 */
	public int calculaCreditos(String tipoAtividade) {
		return this.gerenciadorAtividades.calculaCreditos(tipoAtividade);
	}

	/**
	 * Gera um mapa de créditos, que conta a quantidade de créditos para cada tipo
	 * de atividade complementar
	 * 
	 * @return uma String com cada tipo de atividade complementar e os respectivos
	 *         créditos totais
	 */
	public String geraMapaCreditos() {
		return this.gerenciadorAtividades.exibeMapaCreditos();
	}

	/**
	 * Verifica se o usuário atingiu a meta de créditos.
	 * 
	 * @return true se tiver atingido a meta, false caso contrário
	 */
	public boolean atingiuMeta() {
		return this.gerenciadorAtividades.atingiuMeta();
	}

	/**
	 * Verifica se o usuário atingiu a meta de créditos para um tipo específico de
	 * atividade.
	 * 
	 * @return true se tiver atingido a meta, false caso contrário
	 */
	public boolean atingiuMeta(String tipoAtividade) {
		return this.gerenciadorAtividades.atingiuMeta(tipoAtividade);
	}

	/**
	 * Gera um relatório final contendo informações sobre o usuário e os créditos
	 * totais acumulados. Se o usuário não atingiu a meta de créditos, uma mensagem
	 * informando isso será retornada.
	 *
	 * @return uma String representando o relatório final gerado, ou uma mensagem
	 *         informando que a meta de créditos não foi atingida
	 */
	public String relatorioFinal() {
		return this.gerenciadorAtividades.relatorioFinal();
	}

	/**
	 * Gera um relatório final para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade. Se o usuário não atingiu a meta de créditos para esse tipo, uma
	 * mensagem informando isso será retornada.
	 *
	 * @param tipoAtividade o tipo de atividade para a qual o relatório final é
	 *                      gerado
	 * @return uma String representando o relatório final gerado para o tipo de
	 *         atividade, ou uma mensagem informando que a meta não foi atingida
	 */
	public String relatorioFinal(String tipoAtividade) {
		return this.gerenciadorAtividades.relatorioFinal(tipoAtividade);
	}

	/**
	 * Gera um relatório parcial contendo informações sobre o usuário e a quantidade
	 * de créditos acumulados, com a opção de salvar esse relatório no histórico.
	 *
	 * @param salvar indica se o relatório deve ser salvo no histórico ou não
	 * @return uma String representando o relatório parcial gerado
	 */
	public String relatorioParcial(boolean salvar) {
		return this.gerenciadorAtividades.relatorioParcial(salvar);
	}

	/**
	 * Gera um relatório parcial para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade, com a opção de salvar esse relatório no histórico
	 *
	 * @param tipoAtividade o tipo de atividade para a qual o relatório é gerado
	 * @param salvar        indica se o relatório deve ser salvo no históricoou não
	 * @return uma String representando o relatório parcial gerado para o tipo de
	 *         atividade
	 */
	public String relatorioParcial(String tipoAtividade, boolean salvar) {
		return this.gerenciadorAtividades.relatorioParcial(tipoAtividade, salvar);
	}

	/**
	 * Retorna uma lista de todos os relatórios salvos no histórico, formatada como
	 * uma única String, onde cada relatório é separado por uma nova linha.
	 *
	 * @return uma String contendo todos os relatórios salvos no histórico
	 */
	public String listaHistorico() {
		return this.gerenciadorAtividades.listaHistorico();
	}

	/**
	 * Exclui um relatório do histórico com base na data fornecida.
	 *
	 * @param data a data do relatório a ser excluído
	 * @return true se o relatório foi excluído com sucesso, false caso contrário
	 */
	public boolean excluirRelatorio(String data) {
		return this.gerenciadorAtividades.excluiRelatorio(data);
	}

	/**
	 * Apresentação textutal para relatórios.
	 * 
	 * @return uma String com a apresentação desejada em relatórios com nome, CPF e
	 *         matrícula
	 */
	public String apresentacaoEmRelatorio() {
		return "Nome: " + this.nome + ", CPF: " + this.cpf + " - matrícula " + this.matricula;
	}

	/**
	 * Apresentação textual mais específica, que apresenta também a bonificação que
	 * o estudante tem.
	 * 
	 * @return a String de apresentação textual padrão, com a bonificação inclusva
	 */
	public String exibeBonificacao() {
		return this.toString() + "\nBonificação atual: " + this.bonificacao;
	}

	/**
	 * Apresentação textual do usuário, com o nome e matrícula.
	 * 
	 * @return a String de apresentação textual padrão
	 */
	@Override
	public String toString() {
		return "Nome: " + this.nome + ", matrícula " + this.matricula;
	}

	/**
	 * Retorna um código de hash para o objeto atual.
	 *
	 * O código de hash é baseado no CPF do usuário.
	 *
	 * @return um valor inteiro representando o código de hash do objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	/**
	 * Compara o objeto atual a outro objeto para verificar se eles são considerados
	 * iguais. A igualdade é determinada com base no CPF do usuário.
	 *
	 * @param obj o objeto a ser comparado com o objeto atual.
	 * @return true se os objetos forem considerados iguais; false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}

	/**
	 * Compara este usuário com outro usuário, baseando-se no nome.
	 * 
	 * @param obj o objeto a ser comparado com este usuário
	 * @return um valor negativo se este usuário vem antes do usuário fornecido,
	 *         zero se eles forem iguais, e um valor positivo se este usuário vem
	 *         depois do usuário fornecido, na ordem lexicográfica
	 * @throws NullPointerException se o objeto fornecido for nulo
	 */
	@Override
	public int compareTo(Usuario obj) {
		return this.nome.compareTo(obj.getNome());
	}
}

package controllers;

import entities.Usuario;
import repositories.UsuarioRepository;

/**
 * Classe responsável por gerenciar as interações entre os usuários externos e o
 * repositório de usuários.
 */

public class UsuarioController {

	private UsuarioRepository usuarioRepository;

	/**
	 * Cria um novo controlador de usuário.
	 */
	public UsuarioController() {
		this.usuarioRepository = new UsuarioRepository();
	}

	/**
	 * Cria um novo estudante e o adiciona ao repositório.
	 *
	 * @param nome      o nome do estudante
	 * @param cpf       o cpf do estudante
	 * @param senha     a senha do estudante
	 * @param matricula a matrícula do estudante
	 * @return true se o estudante foi criado com sucesso, false caso contrário
	 */
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return this.usuarioRepository.adicionaEstudante(cpf, new Usuario(nome, cpf, senha, matricula));
	}

	/**
	 * Lista todos os estudantes no repositório como um array de strings.
	 *
	 * @return um array de String contendo a representação de todos os estudantes,
	 *         se existir
	 */
	public String[] listarEstudantes() {
		return this.usuarioRepository.listaEstudantes();
	}

	/**
	 * Lista todos os estudantes ordenados por bonificação em ordem decrescente.
	 *
	 * @return um array de String contendo a representação dos estudantes, ordenados
	 *         pela bonificação
	 */
	public String[] listarEstudantesRankingDicas() {
		return this.usuarioRepository.listaEstudantesRankingDicas();
	}

	/**
	 * Altera a senha de um estudante.
	 *
	 * @param cpf         o cpf do estudante
	 * @param senhaAntiga a senha atual do estudante
	 * @param novaSenha   a nova senha que será definida
	 * @return true se a senha foi alterada com sucesso, false caso contrário
	 */
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		return buscaEstudante(cpf, senhaAntiga).alteraSenha(senhaAntiga, novaSenha);
	}

	/**
	 * Cria uma atividade complementar para o estudante.
	 *
	 * @param cpf              o cpf do estudante
	 * @param senha            a senha do estudante
	 * @param tipoAtividade    o tipo da atividade complementar
	 * @param descricao        uma descrição da atividade
	 * @param linkDocumentacao um link para a documentação da atividade
	 * @param unidadeAcumulada a quantidade de unidades acumuladas pela atividade
	 * @param especificidade   informações adicionais sobre a atividade
	 * @return o código da atividade
	 */
	public String criarAtividadeComplementarEstudante(String cpf, String senha, String tipoAtividade, String descricao,
			String linkDocumentacao, int unidadeAcumulada, String especificidade) {
		return buscaEstudante(cpf, senha).criaAtividadeComplementar(tipoAtividade, descricao, linkDocumentacao,
				unidadeAcumulada, especificidade);
	}

	/**
	 * Altera a descrição de uma atividade complementar existente.
	 *
	 * @param cpf             o cpf do estudante
	 * @param senha           a senha do estudante
	 * @param codigoAtividade o código da atividade a ser alterada
	 * @param descricao       a nova descrição da atividade
	 * @return true se a descrição foi alterada com sucesso, false caso contrário
	 */
	public boolean alterarDescricaoAtividadeEstudante(String cpf, String senha, String codigoAtividade,
			String descricao) {
		return buscaEstudante(cpf, senha).alteraDescricaoAtividade(codigoAtividade, descricao);
	}

	/**
	 * Altera o link de documentação de uma atividade complementar existente.
	 *
	 * @param cpf             o cpf do estudante
	 * @param senha           a senha do estudante
	 * @param codigoAtividade o código da atividade a ser alterada
	 * @param descricao       o novo link de documentação da atividade
	 * @return true se o link foi alterado com sucesso, false caso contrário
	 */
	public boolean alterarLinkAtividadeEstudante(String cpf, String senha, String codigoAtividade, String descricao) {
		return buscaEstudante(cpf, senha).alteraLinkAtividade(codigoAtividade, descricao);
	}

	/**
	 * Calcula os créditos acumulados por um estudante para um tipo de atividade
	 * específico.
	 *
	 * @param cpf           o cpf do estudante
	 * @param senha         a senha do estudante
	 * @param tipoAtividade o tipo da atividade cujos créditos devem ser calculados
	 * @return o número de créditos acumulados para a atividade especificada
	 */
	public int calcularCreditosAtividadeEstudante(String cpf, String senha, String tipoAtividade) {
		return buscaEstudante(cpf, senha).calculaCreditos(tipoAtividade);
	}

	/**
	 * Gera um mapa de créditos das atividades complementares do estudante.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return uma string representando o mapa de créditos
	 */
	public String gerarMapaCreditosEstudante(String cpf, String senha) {
		return buscaEstudante(cpf, senha).geraMapaCreditos();
	}

	/**
	 * Verifica se o estudante atingiu a meta de atividades complementares.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return true se o estudante atingiu a meta, false caso contrário
	 */
	public boolean verificarMetaEstudante(String cpf, String senha) {
		return buscaEstudante(cpf, senha).atingiuMeta();
	}

	/**
	 * Busca um estudante no repositório pelo CPF e senha.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return o usuário correspondente ao CPF e senha, ou null se não encontrado
	 */
	public Usuario buscaEstudante(String cpf, String senha) {
		return this.usuarioRepository.buscaEstudante(cpf, senha);
	}

	/**
	 * Gera um relatório final contendo informações sobre o usuário e os créditos
	 * totais acumulados. Se o usuário não atingiu a meta de créditos, uma mensagem
	 * informando isso será retornada.
	 *
	 * @return uma String representando o relatório final gerado, ou uma mensagem
	 *         informando que a meta de créditos não foi atingida
	 */
	public String gerarRelatorioFinalEstudante(String cpf, String senha) {
		return buscaEstudante(cpf, senha).relatorioFinal();
	}

	/**
	 * Gera um relatório final para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade. Se o usuário não atingiu a meta de créditos para esse tipo, uma
	 * mensagem informando isso será retornada.
	 *
	 * @param cpf           o cpf do estudante
	 * @param senha         a senha do estudante
	 * @param tipoAtividade o tipo de atividade para a qual o relatório final é
	 *                      gerado
	 * @return uma String representando o relatório final gerado para o tipo de
	 *         atividade, ou uma mensagem informando que a meta não foi atingida
	 */
	public String gerarRelatorioFinalEstudante(String cpf, String senha, String tipoAtividade) {
		return buscaEstudante(cpf, senha).relatorioFinal(tipoAtividade);
	}

	/**
	 * Gera um relatório parcial contendo informações sobre o usuário e a quantidade
	 * de créditos acumulados, com a opção de salvar esse relatório no histórico.
	 *
	 * @param cpf    o cpf do estudante
	 * @param senha  a senha do estudante
	 * @param salvar indica se o relatório deve ser salvo no histórico ou não
	 * @return uma String representando o relatório parcial gerado
	 */
	public String gerarRelatorioParcialEstudante(String cpf, String senha, boolean salvar) {
		return buscaEstudante(cpf, senha).relatorioParcial(salvar);
	}

	/**
	 * Gera um relatório parcial para um tipo específico de atividade, contendo
	 * informações sobre o usuário e a quantidade de créditos acumulados para essa
	 * atividade, com a opção de salvar esse relatório no histórico
	 *
	 * @param cpf           o cpf do estudante
	 * @param senha         a senha do estudante
	 * @param tipoAtividade o tipo de atividade para a qual o relatório é gerado
	 * @param salvar        indica se o relatório deve ser salvo no históricoou não
	 * @return uma String representando o relatório parcial gerado para o tipo de
	 *         atividade
	 */
	public String gerarRelatorioParcialEstudante(String cpf, String senha, boolean salvar, String tipoAtividade) {
		return buscaEstudante(cpf, senha).relatorioParcial(tipoAtividade, salvar);
	}

	/**
	 * Retorna uma lista de todos os relatórios salvos no histórico, formatada como
	 * uma única String, onde cada relatório é separado por uma nova linha.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return uma String contendo todos os relatórios salvos no histórico
	 */
	public String listarHistoricoEstudante(String cpf, String senha) {
		return buscaEstudante(cpf, senha).listaHistorico();
	}

	/**
	 * Exclui um relatório do histórico com base na data fornecida.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @param data  a data do relatório a ser excluído
	 * @return true se o relatório foi excluído com sucesso, false caso contrário
	 */
	public boolean excluirItemHistoricoEstudante(String cpf, String senha, String data) {
		return buscaEstudante(cpf, senha).excluirRelatorio(data);
	}
}

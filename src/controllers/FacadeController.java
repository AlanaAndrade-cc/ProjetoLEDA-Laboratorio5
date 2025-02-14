package controllers;

import enums.TipoAtividadeComplementar;

/**
 * Classe que atua como uma fachada para gerenciar interações entre os
 * controladores de usuários e dicas.
 */
public class FacadeController {

	private UsuarioController usuarioController;
	private DicaController dicaController;

	/**
	 * Cria um novo controlador fachada, inicializando os controladores de usuário e
	 * dica.
	 */
	public FacadeController() {
		this.usuarioController = new UsuarioController();
		this.dicaController = new DicaController(usuarioController);
	}

	/**
	 * Cria um novo estudante.
	 *
	 * @param nome      o nome do estudante
	 * @param cpf       o cpf do estudante
	 * @param senha     a senha do estudante
	 * @param matricula a matrícula do estudante
	 * @return true se o estudante foi criado com sucesso, false caso contrário
	 */
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return this.usuarioController.criarEstudante(nome, cpf, senha, matricula);
	}

	/**
	 * Lista todos os estudantes no repositório como um array de strings.
	 *
	 * @return um array de String contendo a representação de todos os estudantes,
	 *         se existir
	 */
	public String[] listarEstudantes() {
		return this.usuarioController.listarEstudantes();
	}

	/**
	 * Lista todos os estudantes ordenados por bonificação em ordem decrescente.
	 *
	 * @return um array de String contendo a representação dos estudantes, ordenados
	 *         pela bonificação
	 */
	public String[] listarEstudantesRankingDicas() {
		return this.usuarioController.listarEstudantesRankingDicas();
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
		return this.usuarioController.alterarSenhaEstudante(cpf, senhaAntiga, novaSenha);
	}

	/**
	 * Cria uma nova dica associada ao usuário identificado pelo CPF e senha
	 * fornecidos.
	 *
	 * @param cpf   o cpf do usuário autor da dica
	 * @param senha a senha do usuário autor da dica
	 * @param tema  o tema da nova dica
	 * @return o índice da dica adicionada, ou 0 se a dica não foi criada
	 */
	public int adicionarDica(String cpf, String senha, String tema) {
		return this.dicaController.criarDica(cpf, senha, tema);
	}

	/**
	 * Adiciona um elemento texto à dica na posição especificada.
	 *
	 * @param cpf     o cpf do usuário autor do texto
	 * @param senha   a senha do usuário autor do texto
	 * @param posicao a posição da dica a ser atualizada (1-based)
	 * @param texto   o texto a ser adicionado
	 * @return true se o texto foi adicionado com sucesso, false caso contrário
	 */
	public boolean adicionarElementoTextoDica(String cpf, int senha, int posicaoDica, String texto) {
		return this.dicaController.adicionarTextoDica(cpf, cpf, posicaoDica, texto);
	}

	/**
	 * Adiciona um elemento multimídia à dica na posição especificada.
	 *
	 * @param cpf       o cpf do usuário autor da multimídia
	 * @param senha     a senha do usuário autor da multimídia
	 * @param posicao   a posição da dica a ser atualizada (1-based)
	 * @param link      o link para a multimídia
	 * @param cabecalho o cabeçalho descritivo da multimídia
	 * @param tempo     a duração da multimídia em segundos
	 * @return true se a multimídia foi adicionada com sucesso, false caso contrário
	 */
	public boolean adicionarElementoMultimidiaDica(String cpf, int senha, int posicaoDica, String link,
			String cabecalho, int tempo) {
		return this.dicaController.adicionarMultimidiaDica(cpf, cpf, posicaoDica, link, cabecalho, tempo);
	}

	/**
	 * Adiciona um elemento referência à dica na posição especificada.
	 *
	 * @param cpf         o cpf do usuário autor da referência
	 * @param senha       a senha do usuário autor da referência
	 * @param posicao     a posição da dica a ser atualizada (1-based)
	 * @param titulo      o título da referência
	 * @param fonte       a fonte da referência
	 * @param ano         o ano de publicação da referência
	 * @param conferida   indica se a referência foi conferida
	 * @param importancia um valor que indica a importância da referência
	 * @return true se a referência foi adicionada com sucesso, false caso contrário
	 */
	public boolean adicionarElementoReferenciaDica(String cpf, int senha, int posicaoDica, String titulo, String fonte,
			int ano, boolean conferida, int importancia) {
		return this.dicaController.adicionarReferenciaDica(cpf, cpf, posicaoDica, titulo, fonte, ano, conferida,
				importancia);
	}

	/**
	 * Retorna uma lista das dicas como strings.
	 *
	 * @return um array de strings representando as dicas
	 */
	public String[] listarDicas() {
		return this.dicaController.listarDicas();
	}

	/**
	 * Retorna uma lista detalhada das dicas.
	 *
	 * @return um array de strings com os detalhes das dicas
	 */
	public String[] listarDicasDetalhes() {
		return this.dicaController.listarDicasDetalhes();
	}

	/**
	 * Retorna a representação em string da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return a representação em string da dica
	 */
	public String listarDica(int posicao) {
		return this.dicaController.listarDica(posicao);
	}

	/**
	 * Retorna os detalhes da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return os detalhes da dica
	 */
	public String listarDicaDetalhes(int posicao) {
		return this.dicaController.listarDicaDetalhes(posicao);
	}

	/**
	 * Cria uma atividade complementar do tipo Pesquisa e Extensão para o estudante.
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
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String descricao,
			String linkDocumentacao, int unidadeAcumulada, String subtipo) {
		String tipoAtividade = TipoAtividadeComplementar.PESQUISAEXTENSAO.getTipo();
		return this.usuarioController.criarAtividadeComplementarEstudante(cpf, senha, tipoAtividade, descricao,
				linkDocumentacao, unidadeAcumulada, subtipo);
	}

	/**
	 * Cria uma atividade complementar do tipo Monitoria para o estudante.
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
	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, String descricao,
			String linkDocumentacao, int unidadeAcumulada, String disciplina) {
		String tipoAtividade = TipoAtividadeComplementar.PESQUISAEXTENSAO.getTipo();
		return this.usuarioController.criarAtividadeComplementarEstudante(cpf, senha, tipoAtividade, descricao,
				linkDocumentacao, unidadeAcumulada, disciplina);
	}

	/**
	 * Cria uma atividade complementar do tipo Estágio para o estudante.
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
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, String descricao, String linkDocumentacao,
			int unidadeAcumulada, String nomeEmpresa) {
		String tipoAtividade = TipoAtividadeComplementar.PESQUISAEXTENSAO.getTipo();
		return this.usuarioController.criarAtividadeComplementarEstudante(cpf, senha, tipoAtividade, descricao,
				linkDocumentacao, unidadeAcumulada, nomeEmpresa);
	}

	/**
	 * Cria uma atividade complementar do tipo Representação Estudantil para o
	 * estudante.
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
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, String descricao,
			String linkDocumentacao, int unidadeAcumulada, String subtipo) {
		String tipoAtividade = TipoAtividadeComplementar.PESQUISAEXTENSAO.getTipo();
		return this.usuarioController.criarAtividadeComplementarEstudante(cpf, senha, tipoAtividade, descricao,
				linkDocumentacao, unidadeAcumulada, subtipo);
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
		return this.usuarioController.alterarDescricaoAtividadeEstudante(cpf, senha, codigoAtividade, descricao);
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
	public boolean alterarLinkDocumentacaoAtividadeEstudante(String cpf, String senha, String codigoAtividade,
			String linkDocumentacao) {
		return this.usuarioController.alterarLinkAtividadeEstudante(cpf, senha, codigoAtividade, linkDocumentacao);
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
	public int creditosAtividadeEstudante(String cpf, String senha, String tipoAtividade) {
		return this.usuarioController.calcularCreditosAtividadeEstudante(cpf, senha, tipoAtividade);
	}

	/**
	 * Gera um mapa de créditos das atividades complementares do estudante.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return uma string representando o mapa de créditos
	 */
	public String gerarMapaCreditosAtividadesEstudante(String cpf, String senha) {
		return this.usuarioController.gerarMapaCreditosEstudante(cpf, senha);
	}

	/**
	 * Verifica se o estudante atingiu a meta de atividades complementares.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return true se o estudante atingiu a meta, false caso contrário
	 */
	public boolean verificarMetaEstudante(String cpf, String senha) {
		return this.usuarioController.verificarMetaEstudante(cpf, senha);
	}

	/**
	 * Gera um relatório final contendo informações sobre o usuário e os créditos
	 * totais acumulados. Se o usuário não atingiu a meta de créditos, uma mensagem
	 * informando isso será retornada.
	 *
	 * @param cpf   o cpf do estudante
	 * @param senha a senha do estudante
	 * @return uma String representando o relatório final gerado, ou uma mensagem
	 *         informando que a meta de créditos não foi atingida
	 */
	public String gerarRelatorioFinalEstudante(String cpf, String senha) {
		return this.usuarioController.gerarRelatorioFinalEstudante(cpf, senha);
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
		return this.usuarioController.gerarRelatorioFinalEstudante(cpf, senha, tipoAtividade);
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
		return this.usuarioController.gerarRelatorioParcialEstudante(cpf, senha, salvar);
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
		return this.usuarioController.gerarRelatorioParcialEstudante(cpf, senha, salvar, tipoAtividade);
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
		return this.usuarioController.listarHistoricoEstudante(cpf, senha);
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
		return this.usuarioController.excluirItemHistoricoEstudante(cpf, senha, data);
	}
}

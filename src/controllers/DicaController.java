package controllers;

import entities.Dica;
import entities.Usuario;
import repositories.DicaRepository;

/**
 * Classe responsável por gerenciar as interações entre os usuários externos e o
 * repositório de dicas.
 */

public class DicaController {

	private UsuarioController usuarioController;
	private DicaRepository dicaRepository;

	/**
	 * Cria um novo controlador de dicas com o controlador de usuários fornecido.
	 *
	 * @param usuarioController o controlador de usuários a ser associado
	 */
	public DicaController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
		this.dicaRepository = new DicaRepository();
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
	public int criarDica(String cpf, String senha, String tema) {
		Usuario autor = usuarioController.buscaEstudante(cpf, senha);
		return dicaRepository.adicionaDica(new Dica(autor, tema));
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
	public boolean adicionarTextoDica(String cpf, String senha, int posicao, String texto) {
		Usuario autor = usuarioController.buscaEstudante(cpf, senha);
		Dica dicaProcurada = dicaRepository.buscaDica(posicao);
		return dicaProcurada.adicionaTexto(autor, texto);
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
	public boolean adicionarMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho,
			int tempo) {
		Usuario autor = usuarioController.buscaEstudante(cpf, senha);
		Dica dicaProcurada = dicaRepository.buscaDica(posicao);
		return dicaProcurada.adicionaMultimidia(autor, link, cabecalho, tempo);
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
	public boolean adicionarReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano,
			boolean conferida, int importancia) {
		Usuario autor = usuarioController.buscaEstudante(cpf, senha);
		Dica dicaProcurada = dicaRepository.buscaDica(posicao);
		return dicaProcurada.adicionaReferencia(autor, titulo, fonte, ano, conferida, importancia);
	}

	/**
	 * Retorna uma lista das dicas como strings.
	 *
	 * @return um array de strings representando as dicas
	 */
	public String[] listarDicas() {
		return dicaRepository.listaDicas();
	}

	/**
	 * Retorna uma lista detalhada das dicas.
	 *
	 * @return um array de strings com os detalhes das dicas
	 */
	public String[] listarDicasDetalhes() {
		return dicaRepository.listaDicasDetalhes();
	}

	/**
	 * Retorna a representação em string da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return a representação em string da dica
	 * @throws IllegalArgumentException se posicao for inválida
	 */
	public String listarDica(int posicao) {
		return dicaRepository.listaDica(posicao);
	}

	/**
	 * Retorna os detalhes da dica na posição especificada.
	 *
	 * @param posicao a posição da dica a ser retornada (1-based)
	 * @return os detalhes da dica
	 * @throws IllegalArgumentException se posicao for inválida
	 */
	public String listarDicaDetalhes(int posicao) {
		return dicaRepository.listaDicaDetalhes(posicao);
	}
}

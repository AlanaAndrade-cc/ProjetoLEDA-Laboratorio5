package repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import entities.Usuario;
import validators.ValidadorUsuario;

/**
 * Classe responsável por gerenciar e armazenar os usuários (estudantes) em um
 * repositório. Esta classe permite adicionar, buscar e listar estudantes, além
 * de manter a validação de usuários.
 */

public class UsuarioRepository {

	private Map<String, Usuario> estudantes;

	/**
	 * Cria um novo repositório de usuários.
	 */
	public UsuarioRepository() {
		this.estudantes = new TreeMap<>();
	}

	/**
	 * Adiciona um estudante ao repositório.
	 *
	 * @param cpf       o cpf do estudante
	 * @param estudante o objeto a ser adicionado
	 * @return true se o estudante foi adicionado com sucesso, false se já existe um
	 *         estudante com o mesmo CPF
	 * @throws NullPointerException se o estudante for nulo
	 */
	public boolean adicionaEstudante(String cpf, Usuario estudante) {
		ValidadorUsuario.validaUsuario(estudante);

		if (this.estudantes.containsKey(cpf)) {
			return false;
		}
		this.estudantes.put(cpf, estudante);
		return true;
	}

	/**
	 * Lista todos os estudantes no repositório como um array de strings.
	 *
	 * @return um array de String contendo a representação de todos os estudantes,
	 *         se existir
	 */
	public String[] listaEstudantes() {
		if (this.estudantes.size() == 0) {
			return new String[0];
		}
		List<Usuario> listaOrdenadaPeloNome = new ArrayList<>(this.estudantes.values());
		listaOrdenadaPeloNome.sort(null);
		return converteParaArrayDeString(listaOrdenadaPeloNome);
	}

	/**
	 * Lista todos os estudantes ordenados por bonificação em ordem decrescente.
	 *
	 * @return um array de String contendo a representação dos estudantes, ordenados
	 *         pela bonificação
	 */
	public String[] listaEstudantesRankingDicas() {
		if (this.estudantes.size() == 0) {
			return new String[0];
		}
		List<Usuario> listaOrdenadaPelaBonificacao = new ArrayList<>(this.estudantes.values());
		listaOrdenadaPelaBonificacao.sort(Comparator.comparing(Usuario::getBonificacao).reversed());
		return converteParaArrayDeString(listaOrdenadaPelaBonificacao);
	}

	/**
	 * Busca um estudante no repositório com base no CPF e senha fornecidos.
	 *
	 * @param cpf   o cpf do estudante a ser buscado
	 * @param senha a senha do estudante
	 * @return o estudante correspondente se encontrado e a senha for válida, null
	 *         caso contrário
	 * @throws NullPointerException     se o estudante não for encontrado
	 * @throws IllegalArgumentException se as credenciais forem inválidas
	 */
	public Usuario buscaEstudante(String cpf, String senha) {
		Usuario estudanteProcurado = this.estudantes.get(cpf);
		ValidadorUsuario.validaUsuario(estudanteProcurado);
		if (validaSenha(estudanteProcurado, senha)) {
			return estudanteProcurado;
		}

		throw new IllegalArgumentException("Usuário ou senha inválidos");
	}

	private boolean validaSenha(Usuario estudante, String senha) {
		return estudante.validaSenha(senha);
	}

	private <T> String[] converteParaArrayDeString(Collection<T> colecao) {
		String[] lista = new String[colecao.size()];
		int i = 0;
		for (T elemento : colecao) {
			lista[i++] = elemento.toString();
		}
		return lista;
	}
}

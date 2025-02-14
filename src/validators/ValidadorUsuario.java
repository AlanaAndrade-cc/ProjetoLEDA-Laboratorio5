package validators;

import entities.Usuario;

/**
 * Classe responsável pela validação das informações de um Usuario.
 */

public class ValidadorUsuario extends Validador {

	/**
	 * Representação de várias mensagens de erro relacionadas à validação das
	 * informações do Usuario.
	 */
	private static enum Erro {
		NOME_NULO("O nome do estudante não pode ser nulo"), NOME_VAZIO("O nome do estudante não pode ser vazio"),
		CPF_NULO("O CPF do estudante não pode ser nulo"), CPF_VAZIO("O CPF do estudante não pode ser vazio"),
		SENHA_NULA("A senha do estudante não pode ser nula"), SENHA_VAZIA("A senha do estudante não pode ser vazia"),
		SENHA_PEQUENA("A senha do estudante deve ter, pelo menos, 4 caracteres"),
		MATRICULA_NULA("A matrícula do estudante não pode ser nula"),
		MATRICULA_VAZIA("A matrícula do estudante não pode ser vazia"), USUARIO_NULO("O usuário não pode ser nulo");

		private final String mensagem;

		/**
		 * Cria o Enum
		 * 
		 * @param mensagem a mensagem relacionada ao erro específico
		 */
		Erro(String mensagem) {
			this.mensagem = mensagem;
		}

		/**
		 * Retorna a mensagem de erro
		 * 
		 * @return String com a mensagem de erro específica
		 */
		public String mensagem() {
			return this.mensagem;
		}
	}

	/**
	 * Valida se o nome de usuário é válido.
	 * 
	 * O nome deve ser uma string não nula e não vazia.
	 * 
	 * @param nome o nome a ser validado
	 * @throws NullPointerException     se nome for null
	 * @throws IllegalArgumentException se nome for vazio
	 */
	public static void validaNome(String nome) {
		validaString(nome, Erro.NOME_NULO.mensagem(), Erro.NOME_VAZIO.mensagem());
	}

	/**
	 * Valida se o CPF de usuário é válido.
	 * 
	 * O CPF deve ser uma string não nula e não vazia.
	 * 
	 * @param cpf o cpf a ser validado
	 * @throws NullPointerException     se cpf for null
	 * @throws IllegalArgumentException se cpf for vazio
	 */
	public static void validaCpf(String cpf) {
		validaString(cpf, Erro.CPF_NULO.mensagem(), Erro.CPF_VAZIO.mensagem());
	}

	/**
	 * Valida se a senha de usuário é válida.
	 * 
	 * A senha deve ser uma string não nula e não vazia. Além disso, deve ter, no
	 * mínimo, 4 caracteres.
	 * 
	 * @param senha a senha a ser validada
	 * @throws NullPointerException     se senha for null
	 * @throws IllegalArgumentException se senha for vazia ou se tiver menos que 4
	 *                                  caracteres
	 */
	public static void validaSenha(String senha) {
		validaString(senha, Erro.SENHA_NULA.mensagem(), Erro.SENHA_VAZIA.mensagem());
		if (senha.length() < 4) {
			throw new IllegalArgumentException(Erro.SENHA_PEQUENA.mensagem());
		}
	}

	/**
	 * Valida se o link de atividade é válido.
	 * 
	 * O link deve ser uma string não nula e não vazia.
	 * 
	 * @param link o link a ser validado
	 * @throws NullPointerException     se link for null
	 * @throws IllegalArgumentException se l for vazia
	 */
	public static void validaMatricula(String matricula) {
		validaString(matricula, Erro.MATRICULA_NULA.mensagem(), Erro.MATRICULA_VAZIA.mensagem());
	}

	/**
	 * Valida se o objeto Usuario é válido.
	 * 
	 * O objeto não pode ser nulo.
	 * 
	 * @param usuario o objeto a ser validado
	 * @throws NullPointerException se usuario for null
	 */
	public static void validaUsuario(Usuario usuario) {
		validaObjeto(usuario, Erro.USUARIO_NULO.mensagem());
	}
}

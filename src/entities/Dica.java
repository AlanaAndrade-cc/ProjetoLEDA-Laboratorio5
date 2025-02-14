package entities;

import java.util.ArrayList;
import java.util.List;

import enums.TipoAtividadeComplementar;
import interfaces.Elemento;
import validators.ValidadorUsuario;

/**
 * Representação de uma dica, associa um autor, um tema e uma lista de
 * elementos. As dicas podem conter textos, mídias ou referências, e o autor é
 * bonificado com base nos elementos adicionados.
 * 
 * @author Alana Vanessa Pimentel Toldo de Andrade, matrícula 123210882
 */

public class Dica {

	private Usuario autor;
	private TipoAtividadeComplementar tema;
	private List<Elemento> elementos;

	/**
	 * Cria uma nova Dica com o autor e tema especificados.
	 *
	 * @param autor o usuário que criou a dica
	 * @param tema  o tema da dica, que será convertido para um tipo de atividade
	 *              complementar
	 * @throws NullPointerException se o autor for nulo
	 */
	public Dica(Usuario autor, String tema) {
		ValidadorUsuario.validaUsuario(autor);

		this.autor = autor;
		this.tema = TipoAtividadeComplementar.fromTipo(tema);
		this.elementos = new ArrayList<>();
	}

	/**
	 * Adiciona um elemento do tipo texto à dica.
	 *
	 * @param autor o usuário que está adicionando o texto, apenas o autor da dica
	 *              pode adicionar elementos
	 * @param texto o conteúdo do texto a ser adicionado
	 * @return true se o texto foi adicionado com sucesso, false caso contrário
	 */
	public boolean adicionaTexto(Usuario autor, String texto) {
		if (!validaAutor(autor)) {
			return false;
		}
		Elemento novoElemento = new Texto(texto);
		elementos.add(novoElemento);
		bonificaAutor(novoElemento.valorBonificacao());
		return true;
	}

	/**
	 * Adiciona um elemento do tipo multimídia à dica.
	 *
	 * @param autor     o usuário que está adicionando o texto, apenas o autor da
	 *                  dica pode adicionar elementos
	 * @param link      o link para a multimídia
	 * @param cabecalho o cabeçalho descritivo da multimídia
	 * @param tempo     a duração da multimídia em segundos
	 * @return true se a multimídia foi adicionada com sucesso, false caso contrário
	 */
	public boolean adicionaMultimidia(Usuario autor, String link, String cabecalho, int tempo) {
		if (!validaAutor(autor)) {
			return false;
		}
		Elemento novoElemento = new Multimidia(link, cabecalho, tempo);
		elementos.add(novoElemento);
		bonificaAutor(novoElemento.valorBonificacao());
		return true;
	}

	/**
	 * Adiciona um elemento do tipo referência à dica.
	 *
	 * @param autor       o usuário que está adicionando o texto, apenas o autor da
	 *                    dica pode adicionar elementos
	 * @param titulo      o título da referência
	 * @param fonte       a fonte da referência
	 * @param ano         o ano de publicação da referência
	 * @param conferida   indica se a referência foi conferida
	 * @param importancia um valor que indica a importância da referência
	 * @return true se a referência foi adicionada com sucesso, false caso contrário
	 */
	public boolean adicionaReferencia(Usuario autor, String titulo, String fonte, int ano, boolean conferida,
			int importancia) {
		if (!validaAutor(autor)) {
			return false;
		}
		Elemento novoElemento = new Referencia(titulo, fonte, ano, conferida, importancia);
		elementos.add(novoElemento);
		bonificaAutor(novoElemento.valorBonificacao());
		return true;
	}

	/**
	 * Exibe os detalhes da dica, incluindo o autor e os elementos.
	 *
	 * @return uma representação em String dos detalhes da dica
	 */
	public String exibeDetalhes() {
		String apresentacao = "Autor: " + this.autor.getNome();
		for (Elemento e : elementos) {
			apresentacao += "\n" + e.exibeDetalhes();
		}
		return apresentacao;
	}

	/**
	 * Retorna uma representação em String da dica.
	 *
	 * @return uma String contendo o nome do autor e os detalhes dos elementos
	 */
	@Override
	public String toString() {
		String apresentacao = "Autor: " + this.autor.getNome() + ", tema: " + this.tema.getTipo();
		for (Elemento e : elementos) {
			apresentacao += "\n" + e.toString();
		}
		return apresentacao;
	}

	private boolean validaAutor(Usuario autor) {
		return this.autor.equals(autor);
	}

	private void bonificaAutor(int bonificacao) {
		this.autor.recebeBonificacao(bonificacao);
	}
}

import java.util.NoSuchElementException;

public class Pilha<E> {

	private Celula<E> topo;
	private Celula<E> fundo;

	public Pilha() {

		Celula<E> sentinela = new Celula<E>();
		fundo = sentinela;
		topo = sentinela;

	}

	public boolean vazia() {
		return fundo == topo;
	}

	public void empilhar(E item) {

		topo = new Celula<E>(item, topo);
	}

	public E desempilhar() {

		E desempilhado = consultarTopo();
		topo = topo.getProximo();
		return desempilhado;

	}

	public E consultarTopo() {

		if (vazia()) {
			throw new NoSuchElementException("Nao há nenhum item na pilha!");
		}

		return topo.getItem();

	}


	@Override
	public String toString(){
		StringBuilder texto = new StringBuilder();

		Celula<E> atual = topo;

		while (atual != fundo) {
			texto.append(atual.getItem() + ",\n");
			atual = atual.getProximo();
		}

		return texto.toString();
	}

	/**
	 * Cria e devolve uma nova pilha contendo os primeiros numItens elementos
	 * do topo da pilha atual.
	 * 
	 * Os elementos são mantidos na mesma ordem em que estavam na pilha original.
	 * Caso a pilha atual possua menos elementos do que o valor especificado,
	 * uma exceção será lançada.
	 *
	 * @param numItens o número de itens a serem copiados da pilha original.
	 * @return uma nova instância de Pilha<E> contendo os numItens primeiros elementos.
	 * @throws IllegalArgumentException se a pilha não contém numItens elementos.
	 */
	public Pilha<E> subPilha(int numItens) {
		
		Pilha<E> referencia = new Pilha<>();
		Pilha<E> subPilha = new Pilha<>();
		Celula<E> atual = topo;
		int cont = numItens;
		while(atual != fundo && cont > 0){
			referencia.empilhar(atual.getItem());
			atual = atual.getProximo();
			cont--;
		}
		while (referencia.vazia()) {
			subPilha.empilhar(referencia.desempilhar());
		}

		return subPilha;
	}
}
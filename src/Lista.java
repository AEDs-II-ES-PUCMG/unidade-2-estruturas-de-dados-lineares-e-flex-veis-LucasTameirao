import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<E> implements Iterable<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista() {
        Celula<E> sentinela = new Celula<>();
        primeiro = ultimo = sentinela;
        tamanho = 0;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public int tamanho() {
        return tamanho;
    }

    public void inserirFinal(E item) {
        Celula<E> nova = new Celula<>(item);
        ultimo.setProximo(nova);
        ultimo = nova;
        tamanho++;
    }

    public void inserirInicio(E item) {
        Celula<E> nova = new Celula<>(item, primeiro.getProximo());
        if (vazia()) ultimo = nova;
        primeiro.setProximo(nova);
        tamanho++;
    }

    public E removerInicio() {
        if (vazia()) throw new NoSuchElementException("Lista vazia!");
        Celula<E> aux = primeiro.getProximo();
        primeiro.setProximo(aux.getProximo());
        if (primeiro == ultimo) ultimo = primeiro;
        primeiro.setProximo(null);
        tamanho--;
        return primeiro.getItem();
    }

    public void imprimir() {
        if (vazia()) {
            System.out.println("A lista está vazia!");
        } else {
            Celula<E> aux = primeiro.getProximo();
            while (aux != null) {
                System.out.println(aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Celula<E> atual = primeiro.getProximo();

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E item = atual.getItem();
                atual = atual.getProximo();
                return item;
            }
        };
    }

    // Tarefa 1
    public E buscarPor(Comparator<E> criterioDeBusca, E item) {
        // TODO: Percorrer sequencialmente a lista e retornar o primeiro elemento
        //       equivalente a item segundo o criterioDeBusca (compare == 0).
        //       Retornar null caso nenhum elemento seja encontrado.
        Celula<E> aux = primeiro.getProximo();
        E encontrado = null;
        int encontrou = 1;
        while (aux != ultimo.getProximo() && encontrou != 0) {
           encontrou = criterioDeBusca.compare(item, aux.getItem());
           if (encontrou == 0) {
                encontrado = aux.getItem();
           }
           aux = aux.getProximo();
        }

        return encontrado;
    }

    // Tarefa 2
    public double somarMultiplicacoes() {
        // TODO: Para cada elemento, extrair o valor (extratorValor) e o fator (extratorFator),
        //       multiplicá-los e acumular no somatório final.
        //       Lançar IllegalStateException se a lista estiver vazia.

        double somatorio = 0;

        Celula<E> aux = primeiro.getProximo();
        while (aux != null) {
            ItemDePedido p = (ItemDePedido) aux.getItem();
            somatorio += p.getPrecoVenda() * p.getQuantidade();
                
            aux = aux.getProximo();
        }

        return somatorio;
    }

    // Tarefa 3
    public Lista<E> filtrar(Predicate<E> condicional) {
        // TODO: Criar e retornar uma nova lista contendo apenas os elementos
        //       para os quais condicional.test() retorna true.
        //       Lançar IllegalStateException se a lista estiver vazia.

        Lista<E> listaAux = new Lista<>();
        Celula<E> aux = primeiro.getProximo();

        while (aux != ultimo.getProximo()) {
            E item = aux.getItem();
            if (condicional.test(item)) {
                listaAux.inserirFinal(item);
            }
            aux = aux.getProximo();
        }

        return listaAux;
    }

    public E get(int i) {
        int cont = 0;
        Celula<E> aux = primeiro.getProximo();
        while (cont < i && aux != ultimo.getProximo()) {
            aux = aux.getProximo();
        }

        return aux.getItem();
    }
}

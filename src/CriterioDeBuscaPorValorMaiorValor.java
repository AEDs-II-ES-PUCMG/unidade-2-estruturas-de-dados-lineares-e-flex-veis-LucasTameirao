import java.util.Comparator;

public class CriterioDeBuscaPorValorMaiorValor implements Comparator<ItemDePedido> {

    @Override
    public int compare(ItemDePedido item1, ItemDePedido item2) {

        int maior;
        if (item1.getPrecoVenda() > item2.getPrecoVenda()) {
            maior = 1;
        }
        else if (item1.getPrecoVenda() < item2.getPrecoVenda()) {
            maior = -1;
        }
        else{
            maior = 0;
        }
        return maior;
        
    }
}

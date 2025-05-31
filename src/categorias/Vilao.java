package categorias;

public class Vilao extends Personagem {
    public Vilao (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este personagem é um vilão.";
    }
    
}

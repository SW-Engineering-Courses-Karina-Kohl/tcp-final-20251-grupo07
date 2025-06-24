package categorias;

public class SuperHeroi extends Personagem {
    public SuperHeroi (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este personagem é um Super-Heroi.";
    }
    
}

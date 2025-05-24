package categorias;

public class SuperHeroi extends Personagem {
    public SuperHeroi (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirdica () {
        return "Este personagem é Super-Heroi.";
    }
    
}

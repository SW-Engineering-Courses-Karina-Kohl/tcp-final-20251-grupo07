package categorias;

public class Animacao extends Personagem {
    public Animacao (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este personagem é animação.";
    }
    
}

package categorias;

public class Aves extends Animais {
    public Aves (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este animal é uma ave.";
    }
    
}

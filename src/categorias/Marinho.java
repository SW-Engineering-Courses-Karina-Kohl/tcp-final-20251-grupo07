package categorias;

public class Marinho extends Animais {
    public Marinho (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirDica () {
        return "Este animal é marinho.";
    }
    
}

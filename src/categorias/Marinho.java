package categorias;

public class Marinho extends Animais {
    public String nomecategoria;

    public Marinho (String nomecategoria) {
        super(nomecategoria);
    }

     @Override
    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirdica () {
        return "Este animal é marinho.";
    }
    
}

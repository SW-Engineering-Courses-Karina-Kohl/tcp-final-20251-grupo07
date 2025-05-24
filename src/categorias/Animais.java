package categorias;
import app.Categoria;

public abstract class Animais extends Categoria {
    public Animais (String nomecategoria) {
        super(nomecategoria);
    }

    public abstract String exibirdica();

    @Override

    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibircategoria () {
        return "Categoria: " + nomecategoria;
    }
    
}
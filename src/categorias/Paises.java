package categorias;
import app.Categoria;

public abstract class Paises extends Categoria {
    public Paises (String nomecategoria) {
        super(nomecategoria);
    }

    public abstract String exibirdica();

    @Override

    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibircategoria () {
        return "Categoria: " + nomecategoria;
    }
    
}

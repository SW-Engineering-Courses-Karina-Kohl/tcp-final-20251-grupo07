package categorias;
import app.Categoria;

public abstract class Personagem extends Categoria {
    public String nomecategoria;

    public Personagem (String nomecategoria) {
        super(nomecategoria);
    }

    public abstract String exibirdica();

    @Override

    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibircategoria () {
        return "Categoria: Personagem";
    }
    
}

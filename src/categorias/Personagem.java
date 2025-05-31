package categorias;
import app.Categoria;

public abstract class Personagem extends Categoria {
    public Personagem (String nomecategoria) {
        super(nomecategoria);
    }

    public abstract String exibirDica();

    @Override

    // Retorna o nome da categoria, impressão na tela é feita por outro método.
    public String exibirCategoria () {
        return "Categoria: " + nomecategoria;
    }
    
}

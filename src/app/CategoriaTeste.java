package app;
import categorias.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CategoriaTeste {

    @Test
    public void testConstrutorECampos() {
        Categoria categoria = new Aves("Animais");

        assertEquals("Animais", categoria.nomecategoria);
        assertEquals("Categoria: Animais", categoria.exibirCategoria());
        assertEquals("Este animal é uma ave.", categoria.exibirDica());
    }
}

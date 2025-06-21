package app;
import categorias.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PalavraTeste {

    @Test
    public void testCriacaoPalavra() {
        Categoria categoria = new America("Países");
        Palavra palavra = new Palavra("Brasil", 6, categoria);

        assertEquals("Brasil", palavra.getStringPalavra());
        assertEquals(6, palavra.getNumLetras());
    }

    @Test
    public void testContemLetraPresente() {
        Categoria categoria = new America("Países");
        Palavra palavra = new Palavra("Argentina", 9, categoria);

        assertTrue(palavra.contemLetra('A') || palavra.contemLetra('a'));
    }

    @Test
    public void testContemLetraAusente() {
        Categoria categoria = new America("Países");
        Palavra palavra = new Palavra("Argentina", 9, categoria);

        assertFalse(palavra.contemLetra('z'));
    }

    @Test
    public void testEscolhePalavraSecreta() {
        Categoria categoria = new America("Países");
        List<Palavra> lista = new ArrayList<>();
        lista.add(new Palavra("Chile", 5, categoria));
        lista.add(new Palavra("Peru", 4, categoria));

        Palavra secreta = Palavra.escolhePalavraSecreta(lista);

        assertNotNull(secreta);
        assertTrue(lista.contains(secreta));
    }

    @Test
    public void testEscolhePalavraSecretaListaVazia() {
        List<Palavra> lista = new ArrayList<>();
        Palavra secreta = Palavra.escolhePalavraSecreta(lista);
        assertNull(secreta);
    }

    @Test
    public void testEscolhePalavraSecretaListaNull() {
        Palavra secreta = Palavra.escolhePalavraSecreta(null);
        assertNull(secreta);
    }
}

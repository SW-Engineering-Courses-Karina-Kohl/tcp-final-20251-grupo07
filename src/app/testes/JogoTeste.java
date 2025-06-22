package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JogoTeste {

    private Jogo jogo;

    @BeforeEach
    public void setup() {
        jogo = new Jogo();
    }

    @Test
    public void testInicializacaoJogo() {
        assertEquals(6, jogo.getNumvidas(), "Jogo deve começar com 6 vidas");
        assertNotNull(jogo.getPalavra(), "Palavra não deve ser nula");
        assertTrue(jogo.getLetrasTestadas().isEmpty(), "Nenhuma letra deve ter sido testada");
    }

    @Test
    public void testAcertarLetraCorreta() {
        String palavra = jogo.getStringPalavra();
        char letraCorreta = palavra.charAt(0); // pega a primeira letra da palavra secreta

        int resultado = jogo.checaLetra(letraCorreta);
        assertEquals(1, resultado, "Deveria retornar 1 para letra correta");
        assertTrue(jogo.getLetrasTestadas().contains(letraCorreta), "Letra correta deveria estar nas testadas");
        assertEquals(6, jogo.getNumvidas(), "Número de vidas não deve mudar em acerto");
    }

    @Test
    public void testLetraErrada() {
        char letraErrada = 'Z'; // provavelmente não existe na palavra sorteada aleatoriamente

        int resultado = jogo.checaLetra(letraErrada);
        assertEquals(-1, resultado, "Deveria retornar -1 para letra errada");
        assertTrue(jogo.getLetrasTestadas().contains(letraErrada), "Letra errada deveria estar nas testadas");
        assertEquals(5, jogo.getNumvidas(), "Deveria perder uma vida");
    }

    @Test
    public void testLetraRepetidaNaoAlteraEstado() {
        char letra = jogo.getStringPalavra().charAt(0);
        jogo.checaLetra(letra); // primeira vez
        int resultadoSegundaTentativa = jogo.checaLetra(letra); // repetida

        assertEquals(0, resultadoSegundaTentativa, "Deveria retornar 0 para letra repetida");
        assertEquals(6, jogo.getNumvidas(), "Vidas não devem diminuir ao repetir letra já testada");
    }

    @Test
    public void testRetiraVida() {
        jogo.retiraVida();
        assertEquals(5, jogo.getNumvidas(), "Após retirar vida, deve ter 5 vidas restantes");
    }

    @Test
    public void testResetJogo() {
        jogo.checaLetra('Z'); // erra de propósito
        jogo.checaLetra('X');
        assertEquals(5, jogo.getNumvidas(), "Após errar duas letras, vidas devem ser 4");

        jogo.reset();
        assertEquals(6, jogo.getNumvidas(), "Após reset, deve voltar a 6 vidas");
        assertTrue(jogo.getLetrasTestadas().isEmpty(), "Após reset, letras testadas devem estar vazias");
        assertNotNull(jogo.getPalavra(), "Após reset, palavra não deve ser nula");
    }
}
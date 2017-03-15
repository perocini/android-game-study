package br.com.gpma.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import br.com.gpma.jumper.grafico.Painters;
import br.com.gpma.jumper.grafico.Tela;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class GameOver implements IElementos {

    private static final Paint COR = Painters.getPainterGameOver();
    private final Tela tela;

    public GameOver(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void desenhaNo(Canvas canvas) {
        String texto = "Game Over";
        float centroHorizontal = centralizaTexto(texto);
        canvas.drawText(texto, centroHorizontal, tela.getAltura()/2, COR);
    }

    private float centralizaTexto(String texto) {
        Rect limite = new Rect();
        COR.getTextBounds(texto, 0, texto.length(), limite);
        return tela.getLargura()/2 - (limite.right - limite.left)/2;
    }
}

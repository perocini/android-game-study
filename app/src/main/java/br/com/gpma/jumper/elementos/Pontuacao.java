package br.com.gpma.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.gpma.jumper.engine.Som;
import br.com.gpma.jumper.grafico.Painters;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Pontuacao implements IElementos {

    private static final Paint BRANCO = Painters.getPainterPontuacao();
    private int pontos;
    private Som som;

    public Pontuacao(Som som) {
        this.som = som;
        this.pontos = 0;
    }

    @Override
    public void desenhaNo(Canvas canvas) {
        canvas.drawText(String.valueOf(this.pontos), 30, 50, BRANCO);
    }

    public void aumentar() {
        this.pontos++;
        som.tocar(Som.PONTO);
    }
}

package br.com.gpma.jumper.grafico;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Painters {
    public static Paint getPainterPassaro() {
        Paint p = new Paint();
        p.setColor(0xFFFF0000); //VERMELHO
        return p;
    }

    public static Paint getPainterCano() {
        Paint p = new Paint();
        p.setColor(0xFF00FF00); //VERDE
        return p;
    }

    public static Paint getPainterPontuacao() {
        Paint p = new Paint();
        p.setColor(0xFFFFFFFF); //BRANCO
        p.setTextSize(60);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.setShadowLayer(3, 5, 5, Color.BLACK);
        return p;
    }

    public static Paint getPainterGameOver() {
        Paint p = new Paint();
        p.setColor(0xFFFF0000); //VERMELHO
        p.setTextSize(60);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.setShadowLayer(3, 5, 5, Color.BLACK);
        return p;
    }
}

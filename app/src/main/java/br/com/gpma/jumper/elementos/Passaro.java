package br.com.gpma.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.gpma.jumper.R;
import br.com.gpma.jumper.engine.Som;
import br.com.gpma.jumper.grafico.Painters;
import br.com.gpma.jumper.grafico.Tela;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Passaro implements IElementos {

    private static final int RAIO = 30;
    private static final int VELOCIDADE_QUEDA = 2;
    private static final int ALTURA_PULO = 100;
    private final Bitmap imgPassaro;
    private Som som;
    private int posicao;
    private static final Paint VERMELHO = Painters.getPainterPassaro();

    private int altura;
    private Tela tela;

    public Passaro(Context context, Tela tela, Som som) {
        this.tela = tela;
        this.altura = 100;
        this.posicao = 150;
        this.som = som;
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.imgPassaro = Bitmap.createScaledBitmap(bm, RAIO*2, RAIO*2, false);
    }

    @Override
    public void desenhaNo(Canvas canvas) {
        canvas.drawBitmap(this.imgPassaro, posicao - RAIO, altura - RAIO, null);
    }

    public int getX() {
        return this.posicao;
    }
    public int getY() {
        return this.altura;
    }
    public int getTopo() {
        return (int) (this.altura - RAIO);
    }
    public int getEsquerda() {
        return (int) (this.posicao - RAIO);
    }
    public int getDireita() {
        return (int) (this.posicao + RAIO);
    }
    public int getBaixo() {
        return (int) (this.altura + RAIO);
    }

    public void cair() {
        boolean chegouAoChao = (this.altura + RAIO) >= this.tela.getAltura();
        if(!chegouAoChao) {
            this.altura += VELOCIDADE_QUEDA;
        }
    }

    public void pular() {
        if(this.altura - ALTURA_PULO + RAIO < 0) {
            this.altura = RAIO;
        } else {
            this.altura -= ALTURA_PULO;
        }
        som.tocar(Som.PULO);
    }
}

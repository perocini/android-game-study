package br.com.gpma.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

import br.com.gpma.jumper.R;
import br.com.gpma.jumper.grafico.Painters;
import br.com.gpma.jumper.grafico.Tela;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Cano implements IElementos {

    private static final int TAMANHO_CANO = 150;
    private static final int LARGURA_CANO = 50;
    private static final Paint VERDE = Painters.getPainterCano();
    public static final int VARIACAO_ALTURA_CANO = 200;
    private final Tela tela;
    private final Bitmap imgCanoInferior;
    private final Bitmap imgCanoSuperior;
    private int posicao;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;

    public Cano(Context context, Tela tela, int posicao) {
        this.tela = tela;
        this.posicao = posicao;
        alturaDoCanoInferior = tela.getAltura() - TAMANHO_CANO - valorAleatorio();
        alturaDoCanoSuperior = 0 + TAMANHO_CANO + valorAleatorio();

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.imgCanoInferior = Bitmap.createScaledBitmap(bm, LARGURA_CANO, alturaDoCanoInferior, false);
        this.imgCanoSuperior = Bitmap.createScaledBitmap(bm, LARGURA_CANO, alturaDoCanoSuperior, false);
    }

    public int getPosicao() {
        return posicao;
    }

    private int valorAleatorio() {
        return new Random().nextInt(VARIACAO_ALTURA_CANO);
    }

    @Override
    public void desenhaNo(Canvas canvas) {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorNo(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas) {
        //canvas.drawRect(this.posicao, alturaDoCanoInferior, this.posicao + LARGURA_CANO, tela.getAltura(), VERDE);
        canvas.drawBitmap(this.imgCanoInferior, this.posicao, alturaDoCanoInferior, null);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas) {
        //canvas.drawRect(this.posicao, 0, this.posicao + LARGURA_CANO, alturaDoCanoSuperior, VERDE);
        canvas.drawBitmap(this.imgCanoSuperior, this.posicao, 0, null);
    }

    public void mover() {
        this.posicao -= 1;
    }

    public boolean saiuDaTela() {
        return (this.posicao + LARGURA_CANO < -5);
    }

    public boolean passouPeloPassaro(Passaro passaro) {
        int posCano = this.posicao + LARGURA_CANO;
        int posPassaro = passaro.getX();
        return posCano == posPassaro;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return passaro.getDireita() > this.getPosicao();
    }

    public boolean temColisaoVerticalCom(Passaro passaro) {
        return ((passaro.getTopo() < this.alturaDoCanoSuperior) || (passaro.getBaixo() > this.alturaDoCanoInferior));
    }

}

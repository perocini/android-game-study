package br.com.gpma.jumper.elementos;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import br.com.gpma.jumper.engine.Som;
import br.com.gpma.jumper.grafico.Tela;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Canos implements IElementos {

    public static final int DISTANCIA_ENTRE_CANOS = 100;
    public static final int QUANTIDADE_CANOS = 5;
    private final Tela tela;
    private final Pontuacao pontuacao;
    private final Passaro passaro;
    private final Context context;
    private int posicao;
    private List<Cano> canos = new ArrayList<Cano>();

    public Canos(Context context, Tela tela, Pontuacao pontuacao, Passaro passaro) {
        this.tela = tela;
        this.posicao = 300;
        this.pontuacao = pontuacao;
        this.passaro = passaro;
        this.context = context;
        inserirCanos();
    }

    private void inserirCanos() {
        for (int x = 0; x < QUANTIDADE_CANOS; x++) {
            posicao += DISTANCIA_ENTRE_CANOS + new Random().nextInt(100);
            Cano cano = new Cano(context, tela, posicao);
            canos.add(cano);
        }
    }

    @Override
    public void desenhaNo(Canvas canvas) {
        for (Cano cano: canos) {
            cano.desenhaNo(canvas);
        }
    }

    public void mover() {
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()) {
            Cano cano = iterator.next();
            cano.mover();
            if(cano.saiuDaTela()) {
                iterator.remove();
                Cano outroCano = new Cano(context, tela, getMaxPosicao() + DISTANCIA_ENTRE_CANOS + new Random().nextInt(100));
                iterator.add(outroCano);
            }
            if(cano.passouPeloPassaro(passaro)) {
                pontuacao.aumentar();
            }
        }
    }

    private int getMaxPosicao() {
        int maximo = 0;
        for (Cano cano: canos) {
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }

    public boolean temColisaoCom(Passaro passaro) {
        for (Cano cano : canos) {
            if(cano.temColisaoHorizontalCom(passaro)
                    && cano.temColisaoVerticalCom(passaro)) {
                return true;
            }
        }
        return false;
    }
}

package br.com.gpma.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.gpma.jumper.MainActivity;
import br.com.gpma.jumper.R;
import br.com.gpma.jumper.elementos.Cano;
import br.com.gpma.jumper.elementos.Canos;
import br.com.gpma.jumper.elementos.GameOver;
import br.com.gpma.jumper.elementos.Passaro;
import br.com.gpma.jumper.elementos.Pontuacao;
import br.com.gpma.jumper.grafico.Tela;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private final Som som;
    private boolean isRunning = true;
    private SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;
    private Canos canos;
    private Pontuacao pontuacao;
    private Context context;

    public Game(Context context) {
        super(context);
        this.tela = new Tela(context);
        this.context = context;
        this.som = new Som(context);
        setOnTouchListener(this);
        inicializarElementos();
    }

    private void inicializarElementos() {
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.bkg_ceu);
        this.background = Bitmap.createScaledBitmap(back, tela.getLargura(), tela.getAltura(), false);
        this.passaro =  new Passaro(context, tela, som);
        this.pontuacao = new Pontuacao(som);
        this.canos = new Canos(context, tela, pontuacao, passaro);
    }

    @Override
    public void run() {
        while (isRunning) {
            if(!holder.getSurface().isValid()) continue;
            // Trava o canvas
            Canvas canvas = holder.lockCanvas();

            //Desenho dos componentes do jogo
            canvas.drawBitmap(this.background,0,0,null);
            passaro.desenhaNo(canvas);
            passaro.cair();
            canos.desenhaNo(canvas);
            canos.mover();
            pontuacao.desenhaNo(canvas);

            if(new VerificadorDeColisao(passaro, canos).temColisao()) {
                new GameOver(tela).desenhaNo(canvas);
                som.tocar(Som.COLISAO);
                this.isRunning = false;
            }

            //Libera o canvas após desenho
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void iniciar() {
        this.isRunning = true;
    }

    public void cancelar() {
        this.isRunning = false;
    }

    public void reiniciar() {
        MainActivity activity = (MainActivity) context;
        activity.reiniciarJogo();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(this.isRunning) {
            passaro.pular();
        } else {
            reiniciar();
        }
        return false;
    }
}

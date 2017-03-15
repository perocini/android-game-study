package br.com.gpma.jumper.engine;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import br.com.gpma.jumper.R;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class Som {

    public static int PULO;
    public static int PONTO;
    public static int COLISAO;
    private SoundPool soundPool;
    private Context context;

    public Som(Context context) {
        this.context = context;
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        PULO = soundPool.load(context, R.raw.pulo, 1);
        PONTO = soundPool.load(context, R.raw.pontos, 1);
        COLISAO = soundPool.load(context, R.raw.colisao, 1);
    }

    public void tocar(int som) {
        soundPool.play(som,1,1,1,0,1);
    }
    public void tocarPulo() {
        soundPool.play(PULO,1,1,1,0,1);
    }
    public void tocarPonto() {
        soundPool.play(PONTO,1,1,1,0,1);
    }
    public void tocarColisao() {
        soundPool.play(COLISAO,1,1,1,0,1);
    }
}

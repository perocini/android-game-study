package br.com.gpma.jumper;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import br.com.gpma.jumper.engine.Game;

public class MainActivity extends Activity {

    private Game jogo;
    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jogo = new Game(this);
        container = (FrameLayout) findViewById(R.id.container);
        container.addView(jogo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        jogo.iniciar();
        new Thread(jogo).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jogo.cancelar();
    }

    public void reiniciarJogo() {
        container.removeView(this.jogo);
        this.jogo = new Game(this);
        container.addView(this.jogo);
        this.jogo.iniciar();
        new Thread(this.jogo).start();
    }
}

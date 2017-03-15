package br.com.gpma.jumper.engine;

import br.com.gpma.jumper.elementos.Canos;
import br.com.gpma.jumper.elementos.Passaro;

/**
 * Created by Gustavo on 07/05/2016.
 */
public class VerificadorDeColisao {

    private final Passaro passaro;
    private final Canos canos;

    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao() {
        return canos.temColisaoCom(passaro);
    }
}

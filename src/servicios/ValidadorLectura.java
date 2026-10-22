package servicios;

/**
 * Servicio que valida lecturas según el tipo de sensor.
 * Rangos: TEMPERATURA [-10,50], HUMEDAD [0,100], LUMINOSIDAD [0,2000].
 */
public class ValidadorLectura {

    public boolean esValida(String tipo, int valor) {
        return switch (tipo) {
            case "TEMPERATURA" -> valor >= -10 && valor <= 50;
            case "HUMEDAD" -> valor >= 0 && valor <= 100;
            case "LUMINOSIDAD" -> valor >= 0 && valor <= 2000;
            default -> false;
        };
    }
}

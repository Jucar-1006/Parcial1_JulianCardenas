package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo IoT que COMPONE sensores.
 * Los sensores se crean dentro del nodo (composición real).
 */
public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    /**
     * Crea y agrega un sensor del tipo indicado dentro del nodo.
     * Tipo: "TEMPERATURA", "HUMEDAD", "LUMINOSIDAD".
     * Retorna el sensor creado, o null si el tipo es inválido.
     */
    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        Sensor s = null;
        if (tipo.equals("TEMPERATURA")) {
            s = new SensorTemperatura(idSensor, capacidad);
        } else if (tipo.equals("HUMEDAD")) {
            s = new SensorHumedad(idSensor, capacidad);
        } else if (tipo.equals("LUMINOSIDAD")) {
            s = new SensorLuminosidad(idSensor, capacidad);
        }

        if (s != null) {
            sensores.add(s);
        }
        return s;
    }

    /**
     * Promedio de temperatura del nodo. Retorna 0 si no hay sensores de temperatura.
     * Pista: usar instanceof para filtrar.
     */
    public double promedioTemperatura() {
        double suma = 0;
        int cantidadTemp = 0;
        for (Sensor s : sensores) {
            if (s instanceof SensorTemperatura) {
                suma += s.promedioUltimasLecturas();
                cantidadTemp++;
            }
        }
        return cantidadTemp == 0 ? 0.0 : suma / cantidadTemp;
    }

    /**
     * Lista de sensores cuya última lectura está fuera de rango.
     */
    public List<Sensor> sensoresFueraDeRango() {
        List<Sensor> anomalos = new ArrayList<>();
        for (Sensor s : sensores) {
            if (s.getCantidadLecturas() > 0) {

                int idxUltima = (s.getIndiceActual() - 1 + s.getUltimasLecturas().length) % s.getUltimasLecturas().length;
                int ultimaLectura = s.getUltimasLecturas()[idxUltima];

                if (s.fueraDeRango(ultimaLectura)) {
                    anomalos.add(s);
                }
            }
        }
        return anomalos;
    }

    public String getId() { return id; }
    public ArrayList<Sensor> getSensores() { return sensores; }
}
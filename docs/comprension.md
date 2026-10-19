# Comprensión del diseño — Enunciado A
**Nombre:** Julián Camilo Cárdenas Villarraga

**Fecha:** 19/10/2026

---

Responde con tus palabras, basándote en `docs/UML-final.md`. Respuestas
concisas (2–4 líneas por pregunta). Este documento evidencia que entendiste el
contrato de diseño **antes** de programar.

---

### 1. Herencia y polimorfismo
¿Por qué `Sensor` es una clase abstracta y no una clase concreta? Menciona
qué método obliga a cada subclase a implementar y qué gana el sistema con eso.

> Es abstracta al contener características comunes de los sensores; pero 
> la forma de generar lecturas y válidar los rangos es diferente según el 
> tipo de sensor "generarLecturas()" y "fueraDeRango(v)". Esto anterior le permite 
> al sistema usar el polimorfismo para operar cualquier sensor de forma uniforme.
> 

---

### 2. Composición vs. agregación
Explica con tus palabras la diferencia entre `NodoIoT ◆— Sensor` (composición)
y `CentroMonitoreo ◇— NodoIoT` (agregación). Da un ejemplo de qué pasaría con
los sensores si se elimina el nodo, y qué pasaría con los nodos si se elimina
el centro.

> La composición tiene dependencia fuerte en el ciclo de vida: 
> Si se elimina un "NodoIoT", los objetos Sensor que tiene,
> también se eliminan porque se crean dentro de él. 
> 
> La agregación es más débil: si se elimina el CentroMonitoreo, 
> los objetos NodoIoT que poseía aún existiran de forma independiente fuera de
> Centro Monitoreo.

---

### 3. Asociación
En `TecnicoMantenimiento — NodoIoT`, ¿quién es dueño de quién? ¿Por qué se
modela como N:M y no como composición?

> Ninguno es dueño del otro en esta relación, existe cada uno de forma 
> independiente del otro. Se presenta como asociación (N:M) y no como 
> composición porque es una relación de "varios a varios" sin mutua propiedad exclusiva.

---

### 4. Dependencia
¿Por qué `Lectura` **depende** de `ValidadorLectura` en lugar de **tenerlo**
como atributo? ¿Qué diferencia práctica hay entre pasar un `ValidadorLectura`
como parámetro versus guardarlo en un campo?

> Lectura le da uso temporal a ValidadorLectura para verificar si
> una lectura es válida dentro del método "esValida()". Usarlo como
> parámetro en vez de guardarlo como atributo, indica que no comparten ciclo
> de vida, por lo cual la lógica de validación sea más rápida.

---

### 5. Listas
En este proyecto se usan dos estructuras de datos distintas:
- `int[] ultimasLecturas` en `Sensor`
- `ArrayList<Sensor>` en `NodoIoT`

Explica por qué en cada caso se eligió esa estructura y no la otra. ¿Qué
ventaja tiene el `int[]` con buffer circular frente a un `ArrayList` para el
historial de lecturas?

> Se usa ArrayList en "NodoIoT" porque la cantidad de sensores puede  de manera dinámica
> y la lista crece sola. En cambio, usamos el arreglo int[] ultimasLecturas en "Sensor" para 
> establecer un tamaño fijo de últimas lecturas. Esto permite realizar el "buffer circular", 
> porque al momento de llenarse, la nueva lectura la escribe en el espacio 
> de la lectura más vieja.
>

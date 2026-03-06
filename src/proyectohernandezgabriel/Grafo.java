/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohernandezgabriel;

/**
 * Esta clase sirve para manejar una red de proteinas usando un grafo.
 * Basicamente guarda nombres de proteinas y como se conectan entre ellas.
 * * @author Gabriel
 */
public class Grafo {

    public String[] proteinas;
    public int[][] matriz;
    public int numProteinas;
    private int max = 100;

    /**
     * Crea un grafo nuevo vacio con espacio para 100 proteinas.
     * Deja la matriz en cero para que no haya conexiones al principio.
     */
    public Grafo() {
        this.proteinas = new String[max];
        this.matriz = new int[max][max];
        this.numProteinas = 0;

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    /**
     * Busca en que posicion del arreglo esta guardada una proteina.
     * * @param nombre El nombre de la proteina que quieres buscar.
     * @return El numero de la posicion o -1 si no existe.
     */
    private int buscarIndice(String nombre) {
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null && proteinas[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Mete una proteina nueva al sistema si todavia hay espacio.
     * * @param nombre Como se llama la proteina que vas a meter.
     */
    public void agregarProteina(String nombre) {
        if (buscarIndice(nombre) == -1 && numProteinas < max) {
            proteinas[numProteinas] = nombre;
            numProteinas++;
        }
    }

    /**
     * Borra una proteina y quita todas las conexiones que tenia.
     * * @param nombre El nombre de la proteina que quieres sacar.
     */
    public void eliminarProteina(String nombre) {
        int idx = buscarIndice(nombre);
        if (idx != -1) {
            for (int i = 0; i < numProteinas; i++) {
                matriz[idx][i] = 0;
                matriz[i][idx] = 0;
            }
            proteinas[idx] = null;
        }
    }

    /**
     * Crea un camino o relacion entre dos proteinas con un valor de peso.
     * * @param p1 Primera proteina.
     * @param p2 Segunda proteina.
     * @param peso Que tan fuerte es la conexion (un numero).
     */
    public void conectar(String p1, String p2, int peso) {
        int i = buscarIndice(p1);
        int j = buscarIndice(p2);
        if (i != -1 && j != -1) {
            matriz[i][j] = peso;
            matriz[j][i] = peso;
            System.out.println("isjaoid");
        }
    }

    /**
     * Encuentra cual es la proteina mas popular, o sea, la que tiene mas conexiones.
     * * @return El nombre de la proteina que mas amigos tiene.
     */
    public String obtenerHubPrincipal() {
        int gradomax = -1;
        String hub = "";
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null) {
                int gradoact = 0;
                for (int j = 0; j < numProteinas; j++) {
                    if (matriz[i][j] > 0) {
                        gradoact++;
                    }
                }
                if (gradoact > gradomax) {
                    gradomax = gradoact;
                    hub = proteinas[i];
                }
            }
        }
        return hub;
    }

    /**
     * Agrupa las proteinas por familias usando una busqueda a lo ancho.
     * * @return Un texto con la lista de grupos encontrados.
     */
    public String bfs() {
        boolean[] visitado = new boolean[numProteinas];
        String resultado = "Complejos Protecos:\n";
        int conta = 1;

        for (int i = 0; i < numProteinas; i++) {
            if (!visitado[i] && proteinas[i] != null) {
                resultado += "Complejo " + conta + ": [ ";
                conta++;

                int[] cola = new int[numProteinas];
                int frente = 0, fin = 0;

                cola[fin++] = i;
                visitado[i] = true;

                while (frente < fin) {
                    int actual = cola[frente++];
                    resultado += proteinas[actual] + " ";

                    for (int vecino = 0; vecino < numProteinas; vecino++) {
                        if (matriz[actual][vecino] > 0 && !visitado[vecino]) {
                            visitado[vecino] = true;
                            cola[fin++] = vecino;
                        }
                    }
                }
                resultado += "]\n";
            }
        }
        return resultado;
    }

    /**
     * Busca el camino mas corto o facil entre dos proteinas.
     * * @param origen Desde donde empiezas.
     * @param destino A donde quieres llegar.
     * @return Un texto que dice toda la ruta y cuanto suma el peso total.
     */
    public String djakstra(String origen, String destino) {
        int inicio = buscarIndice(origen);
        int fin = buscarIndice(destino);
        if (inicio == -1 || fin == -1) {
            return "Protena no encontrada";
        }

        int[] distancias = new int[numProteinas];
        int[] ant = new int[numProteinas];
        boolean[] visitado = new boolean[numProteinas];

        for (int i = 0; i < numProteinas; i++) {
            distancias[i] = Integer.MAX_VALUE;
            ant[i] = -1;
            visitado[i] = false;
        }

        distancias[inicio] = 0;

        for (int i = 0; i < numProteinas; i++) {
            int u = -1;
            for (int j = 0; j < numProteinas; j++) {
                if (!visitado[j] && (u == -1 || distancias[j] < distancias[u])) {
                    u = j;
                }
            }

            if (u == -1 || distancias[u] == Integer.MAX_VALUE) {
                break;
            }
            visitado[u] = true;

            for (int v = 0; v < numProteinas; v++) {
                if (matriz[u][v] > 0) {
                    int alt = distancias[u] + matriz[u][v];
                    if (alt < distancias[v]) {
                        distancias[v] = alt;
                        ant[v] = u;
                    }
                }
            }
        }

        return djakstra_aux(ant, fin, distancias[fin]);
    }

    /**
     * Funcion de ayuda para armar el texto del camino encontrado por el djakstra.
     * * @param prev Arreglo con los pasos anteriores.
     * @param fin El punto de llegada.
     * @param dist La distancia final calculada.
     * @return El camino bien escrito en una frase.
     */
    private String djakstra_aux(int[] prev, int fin, int dist) {
        if (dist == Integer.MAX_VALUE) {
            return "No existe ruta metabolica";
        }
        String camino = "";
        for (int at = fin; at != -1; at = prev[at]) {
            if (camino.equals("")) {
                camino = proteinas[at];
            } else {
                camino = proteinas[at] + " -> " + camino;
            }
        }
        return "Ruta: " + camino + " (Resistencia: " + dist + ")";
    }

    /**
     * Otra forma de agrupar proteinas pero usando busqueda en profundidad.
     * * @return Texto con los complejos de proteinas encontrados.
     */
    public String dfs() {
        boolean[] visitado = new boolean[numProteinas];
        String resultado = "Complejos:\n";
        int conta = 1;

        for (int i = 0; i < numProteinas; i++) {
            if (!visitado[i] && proteinas[i] != null) {
                resultado += "Complejo " + conta + ": [ ";
                conta++;
                resultado = auxiliarDFS(i, visitado, resultado);
                resultado += "]\n";
            }
        }
        return resultado;
    }

    /**
     * Funcion de ayuda que se llama a si misma para recorrer el grafo hacia abajo.
     * * @param actual La proteina donde estas ahorita.
     * @param visitado El registro de quienes ya vimos.
     * @param res El texto que vamos armando.
     * @return El texto actualizado con las nuevas proteinas encontradas.
     */
    private String auxiliarDFS(int actual, boolean[] visitado, String res) {
        visitado[actual] = true;
        res += proteinas[actual] + " ";
        for (int v = 0; v < numProteinas; v++) {
            if (matriz[actual][v] > 0 && !visitado[v]) {
                res = auxiliarDFS(v, visitado, res);
            }
        }
        return res;
    }

    /**
     * Saca una lista limpia con solo los nombres de las proteinas que existen (sin huecos null).
     * * @return Un arreglo de palabras con los nombres.
     */
    public String[] obtenerNombresProteinas() {
        int contador = 0;
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null) {
                contador++;
            }
        }

        String[] nombres = new String[contador];
        int aux = 0;
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null) {
                nombres[aux] = proteinas[i];
                aux++;
            }
        }
        return nombres;
    }

    /**
     * Muestra toda la red en un texto largo para ver quien se conecta con quien.
     * * @return Un reporte gigante con toda la info de la matriz.
     */
    public String imprimir() {
    String reporte = "";
    
    if (numProteinas == 0) {
        return reporte + "El grafo está vacio";
    }

    for (int i = 0; i < numProteinas; i++) {
        if (proteinas[i] != null) {
            reporte = reporte + "\nPROTEINA: [" + proteinas[i] + "]\n";
            boolean tieneConexiones = false;

            for (int j = 0; j < numProteinas; j++) {
                if (matriz[i][j] > 0 && proteinas[j] != null) {
                    reporte = reporte + "  -> Conectada con: " + proteinas[j] + " (Peso: " + matriz[i][j] + ")\n";
                    tieneConexiones = true;
                }
            }

            if (!tieneConexiones) {
                reporte = reporte + "  (Sin conexiones activas)\n";
            }
            reporte = reporte + "---------------------------\n";
        }
    }
    return reporte;
}
}

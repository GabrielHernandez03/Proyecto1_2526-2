/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohernandezgabriel;

/**
 *
 * @author Gabriel
 */
public class Grafo {

    public String[] proteinas;
    public int[][] matriz;
    public int numProteinas;
    private int max = 100;

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

    private int buscarIndice(String nombre) {
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null && proteinas[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void agregarProteina(String nombre) {
        if (buscarIndice(nombre) == -1 && numProteinas < max) {
            proteinas[numProteinas] = nombre;
            numProteinas++;
        }
    }

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

    public void conectar(String p1, String p2, int peso) {
        int i = buscarIndice(p1);
        int j = buscarIndice(p2);
        if (i != -1 && j != -1) {
            matriz[i][j] = peso;
            matriz[j][i] = peso;
        }
    }

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


    
    public String djakstra(String origen, String destino) {
        int inicio = buscarIndice(origen);
        int fin = buscarIndice(destino);
        if (inicio == -1 || fin == -1){
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
                if (!visitado[j] && (u == -1 || distancias[j] < distancias[u])){
                    u = j;
                }
            }

            if (u == -1 || distancias[u] == Integer.MAX_VALUE){
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

}

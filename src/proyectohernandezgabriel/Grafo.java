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

    boolean dirigido;
    int maxNodos;
    int numVertices;
    boolean matrizAdy[][];

    public Grafo(int max_nodos, int num_vertices) {
        this.maxNodos = max_nodos;
        this.numVertices = num_vertices;
        dirigido = false;
        matrizAdy = new boolean[max_nodos][max_nodos];
    }

    public void insertaArista(int i, int j) {
        matrizAdy[i][j] = true;
        if (!dirigido) {
            matrizAdy[j][i] = matrizAdy[i][j];
        }
    }

    public void eliminarArista(int i, int j) {
        matrizAdy[i][j] = false;
        if (!dirigido) {
            matrizAdy[j][i] = false;
        }
    }

    public void insertaVertice(int n) {
        if (n > maxNodos - numVertices) {
            System.out.println("Error, se supera el número de nodos máximo");
        } else {
            for (int i = 0; i < numVertices + n; i++) {
                for (int j = numVertices; j < numVertices + n; j++) {
                    matrizAdy[i][j] = matrizAdy[j][i] = false;
                }
            }
            numVertices = numVertices + n;
        }
    }
}

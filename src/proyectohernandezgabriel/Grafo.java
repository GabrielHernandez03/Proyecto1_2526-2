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
    int max_nodos;
    int num_vertices;
    boolean matrizAdy[][];

    public Grafo(int max_nodos, int num_vertices) {
        this.max_nodos = max_nodos;
        this.num_vertices = num_vertices;
        dirigido = false;
        matrizAdy = new boolean [max_nodos][max_nodos];
    }
    
    
}

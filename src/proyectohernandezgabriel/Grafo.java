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
    private String[] proteinas;
    private double[][] matrizAdyacencia;
    private int numProteinas;
    private final int MAX_PROTEINAS = 100;

    public Grafo() {
        this.proteinas = new String[MAX_PROTEINAS];
        this.matrizAdyacencia = new double[MAX_PROTEINAS][MAX_PROTEINAS];
        this.numProteinas = 0;
        
        for (int i = 0; i < MAX_PROTEINAS; i++) {
            for (int j = 0; j < MAX_PROTEINAS; j++) {
                matrizAdyacencia[i][j] = 0.0;
            }
        }
    }

}
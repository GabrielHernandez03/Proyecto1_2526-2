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
    private double[][] matriz;
    private int numProteinas;
    private int max = 100;

    public Grafo() {
        this.proteinas = new String[max];
        this.matriz = new double[max][max];
        this.numProteinas = 0;
        
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                matriz[i][j] = 0.0;
            }
        }
    }
    
    private int buscarIndice(String nombre) {
        for (int i = 0; i < numProteinas; i++) {
            if (proteinas[i] != null && proteinas[i].equals(nombre)) return i;
        }
        return -1;
    }
    public void agregarProteina(String nombre) {
        if (buscarIndice(nombre) == -1 && numProteinas < max) {
            proteinas[numProteinas] = nombre;
            numProteinas++;
        }
    }

}
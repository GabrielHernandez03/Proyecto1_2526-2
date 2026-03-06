/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohernandezgabriel;

/**
 * Esta clase es como una ficha tecnica para cada proteina.
 * Solo guarda su nombre y que tan pesada es la conexion.
 *  q  Al final, esta clase NO se utilizo, pero decidi dejarla
 * * @author Gabriel
 */
public class Proteina {
    private String nombre;
    private int peso;

    /**
     * Sirve para saber como se llama la proteina.
     * * @return El nombre que tiene guardado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Se usa para ponerle o cambiarle el nombre a la proteina.
     * * @param nombre El nuevo nombre que le quieres poner.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Te dice cual es el valor del peso o fuerza que tiene.
     * * @return El numero del peso actual.
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Sirve para asignarle un valor numerico al peso.
     * * @param peso El numero que vas a guardar como peso.
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}
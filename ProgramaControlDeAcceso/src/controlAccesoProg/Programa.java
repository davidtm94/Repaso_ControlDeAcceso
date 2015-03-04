/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlAccesoProg;

/**
 *
 * @author david
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BaseDatos bbdd=new BaseDatos();
        bbdd.abrirConexion();
        /*
        bbdd.engadirControlAcceso("", 1, 8);
        bbdd.mostrarControlAcceso(1);
        bbdd.mostrarIncidencias(1);
        bbdd.mostrarPagasExtra(1);
               */ 
        bbdd.pecharConexion();
    }
    
}

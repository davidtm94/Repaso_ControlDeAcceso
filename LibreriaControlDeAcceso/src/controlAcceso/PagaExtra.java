/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlAcceso;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author david
 */
public class PagaExtra implements Serializable, PropertyChangeListener {

    Date data;
    int idTraballador;
    int numeroHorasExtra;

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private PropertyChangeSupport propertySupport;

    public PagaExtra() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public PagaExtra(Date data, int idTraballador, int numeroHorasExtra) {
        this.data = data;
        this.idTraballador = idTraballador;
        this.numeroHorasExtra = numeroHorasExtra;
        propertySupport = new PropertyChangeSupport(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //O segundo obxeto que vai recibir vai de ser de tipo ControlAcceso xa que é o que dispara o evento en ControlAcceso
        //Facemos o cast
        ControlAcceso ca = (ControlAcceso) evt.getNewValue();

        if (ca.getHorasTraballadas() >= 9) { //Traballou de mais, hai que pagar mais
            setData(ca.getData()); //Engadimoslle a data da incidencia procedente do evento xerado
            setIdTraballador(ca.getIdTraballador()); //Engadimoslle o id do traballador procedente do evento xerado
            setNumeroHorasExtra(ca.getHorasTraballadas() - 8);//Engadimoslle o nº de horas extra
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    //SETTERS E GETTERS
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdTraballador() {
        return idTraballador;
    }

    public void setIdTraballador(int idTraballador) {
        this.idTraballador = idTraballador;
    }

    public int getNumeroHorasExtra() {
        return numeroHorasExtra;
    }

    public void setNumeroHorasExtra(int numeroHorasExtra) {
        this.numeroHorasExtra = numeroHorasExtra;
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    
    @Override
    public String toString() {
        return "PagaExtra{" + "data=" + data + ", idTraballador=" + idTraballador + ", numeroHorasExtra=" + numeroHorasExtra + '}';
    }
}

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
public class Incidencias implements Serializable, PropertyChangeListener {

    //MOI IMPORTANTE Implementar PropertyChangelistener para detectar os cambios de propiedades
    Date data;
    String descricion;
    int idTraballador;
    boolean gardarIncidencia;
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private PropertyChangeSupport propertySupport;

    public Incidencias() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public Incidencias(Date data, String descricion, int idTraballador, boolean gardarIncidencia) {
        this.data = data;
        this.descricion = descricion;
        this.idTraballador = idTraballador;
        this.gardarIncidencia = gardarIncidencia;
        propertySupport = new PropertyChangeSupport(this);
    }

    //Sobreescribimos o metodo propertyChange, aqui realizamos as accions nos cambios de eventos.
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //O segundo obxeto que vai recibir vai de ser de tipo ControlAcceso xa que é o que dispara o evento en ControlAcceso
        //Facemos o cast
        ControlAcceso ca = (ControlAcceso) evt.getNewValue();

        if (ca.getHorasTraballadas() != 8) {
            setData(ca.getData()); //Engadimoslle a data da incidencia procedente do evento xerado
            setIdTraballador(ca.getIdTraballador()); //Engadimoslle o id do traballador procedente do evento xerado
            if (ca.getHorasTraballadas() < 8) { //Traballou de menos
                setDescricion("Tempo traballado por debaixo do minimo");
            } else{ //Traballou de mais
                setDescricion("Tempo extra traballado de " + (ca.getHorasTraballadas() - 8));
            }
            setGardarIncidencia(true);
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

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public int getIdTraballador() {
        return idTraballador;
    }

    public void setIdTraballador(int idTraballador) {
        this.idTraballador = idTraballador;
    }

    public boolean isGardarIncidencia() {
        return gardarIncidencia;
    }

    public void setGardarIncidencia(boolean gardarIncidencia) {
        this.gardarIncidencia = gardarIncidencia;
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    
    @Override
    public String toString() {
        return "Incidencias{" + "data=" + data + ", descripcion=" + descricion + ", idTraballador=" + idTraballador + ", gardarIncidencia=" + gardarIncidencia + '}';
    }
}

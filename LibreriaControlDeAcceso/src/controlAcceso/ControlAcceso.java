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
public class ControlAcceso implements Serializable {
    
    private Date data;
    private int idTraballador;
    private int horasTraballadas;
    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";
    private PropertyChangeSupport propertySupport;
    
    public ControlAcceso() {
        propertySupport = new PropertyChangeSupport(this);
    }
    //Constructor. Importante a propiedade propertySupport
    public ControlAcceso(Date data, int idTraballador, int horasTraballadas) {
        this.data = data;
        this.idTraballador = idTraballador;
        this.horasTraballadas = horasTraballadas;
        propertySupport = new PropertyChangeSupport(this);
    }
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    
    //SETTERS E GETTERS
    public void setHorasTraballadas(int horasTraballadas) {
        this.horasTraballadas = horasTraballadas; //Asignamos o novo valor
        
        //En caso de que o numero de horas traballadas sexa anormal xeramos un evento
        if(horasTraballadas!=8){ 
            propertySupport.firePropertyChange("Tempo traballado anormal ", null, this);
            //Primeiro parametro enviado: Descripcion do evento
            //Segundo parametro enviado: vello valor (no noso caso nada)
            //Terceiro paramerto enviado: novo valor (no noso caso enviamos o obxecto cos datos xa actualizados)
        }
    }
    
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

    public int getHorasTraballadas() {
        return horasTraballadas;
    }

    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    
    @Override
    public String toString() {
        return "ControlAcceso{" + "data=" + data + ", idTraballador=" + idTraballador + ", horasTraballadas=" + horasTraballadas + '}';
    }
}

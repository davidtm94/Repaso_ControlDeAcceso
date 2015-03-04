/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlAccesoProg;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import controlAcceso.*;
import java.util.Date;

/**
 *
 * @author david
 */
public class BaseDatos {

    private static final String DB_NAME = "controlAcceso.neodatis";
    private ODB baseDatos = null;

    public void abrirConexion() {
        try {
            ODB baseDatos = ODBFactory.open(DB_NAME);
        } catch (Exception erro) {
            System.out.println("Fallo ao abrir conexion"+erro.getMessage());
        }
    }

    public void pecharConexion() {
            try {
                baseDatos.close();
            } catch (Exception erro) {
                System.out.println("Fallo ao pechar conexion"+erro.getMessage());
            }
    }

    public void mostrarIncidencias(int idTraballador) {
        ICriterion condicion = Where.equal("idTraballador", idTraballador); //Condicion
        IQuery consulta = new CriteriaQuery(Incidencias.class, condicion); //Obxetos da clase Incidencias que cumplan esa condicion
        
        Objects<Incidencias> incidencias = baseDatos.getObjects(consulta);
        //Imprimimos os obxetos que cumplen esas condicions
        for(Incidencias in : incidencias)
            System.out.println(in); //O toString está modificado
    }

    public void mostrarControlAcceso(int idTraballador) {
        ICriterion condicion = Where.equal("idTraballador", idTraballador); //Condicion
        IQuery consulta = new CriteriaQuery(ControlAcceso.class, condicion); //Obxetos da clase ControlAcceso que cumplan esa condicion
        
        Objects<ControlAcceso> controlaccesos = baseDatos.getObjects(consulta);
        //Imprimimos os obxetos que cumplen esas condicions
        for(ControlAcceso ca : controlaccesos)
            System.out.println(ca); //O toString está modificado
    }

    public void mostrarPagasExtra(int idTraballador) {
        ICriterion condicion = Where.equal("idTraballador", idTraballador); //Condicion
        IQuery consulta = new CriteriaQuery(PagaExtra.class, condicion); //Obxetos da clase PagaExtra que cumplan esa condicion
        
        Objects<PagaExtra> pagasextra = baseDatos.getObjects(consulta);
        //Imprimimos os obxetos que cumplen esas condicions
        for(PagaExtra pg : pagasextra)
            System.out.println(pg); //O toString está modificado
    }

    public void engadirControlAcceso(String nomeBD, int idTraballador, int horasTraballadas) {
        //Crea un obxeto ControlAcceso
        ControlAcceso ca = new ControlAcceso(new Date(), idTraballador, horasTraballadas);

        //Crea os obxetos de Incidencia e Pagaextra
        Incidencias in = new Incidencias();
        PagaExtra pa = new PagaExtra();

        //Asocialle como oíntes de eventos ó anterior obxecto os obxectos Incidencias e PagaExtra correspondentes
        ca.addPropertyChangeListener(in); //Incidencias á escoita
        ca.addPropertyChangeListener(pa); //PagaExtra á escoita

        //Asígnalle ó obxecto ControlAcceso as horas traballadas --> Ás veces lanza un evento que recollerán os oíntes
        ca.setHorasTraballadas(horasTraballadas); //Xa está feito no constructor pero bueno...

        //Garda o obxecto ControlAcceso na BD
        baseDatos.store(ca);

        //Se é necesario, garda obxectos Incidencias e PagaExtra na BD
        
        if (in.isGardarIncidencia()) {//Produciuse unha incidencia >> gardamola
            baseDatos.store(in);
            if (pa.getNumeroHorasExtra() >= 1) { //Neste caso a paga extra existe, polo que a almacenamos
                baseDatos.store(pa);
            }
        }
    }

}

package Exercise;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * Encarrec
 * Created by: sheng
 * Date : 16/03/2021
 * Description:
 **/
public class Encarrec {
    int id;
    Date date;
    int cliente;

    /**Constructor**/


    public Encarrec(int id, Date date, int cliente) {
        this.id = id;
        this.date = date;
        this.cliente = cliente;
    }
    /** Getters y Setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }


    /** Metodo toString */

    @Override
    public String toString() {
        return "Encarrec{" +
                "id=" + id +
                ", date=" + date +
                ", cliente=" + cliente +
                '}';
    }
}

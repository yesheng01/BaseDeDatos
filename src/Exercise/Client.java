package Exercise;

/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * Client
 * Created by: sheng
 * Date : 16/03/2021
 * Description:
 **/
public class Client {
    private  int id;
    private  String nom;
    private  String apostal;
    private  String aelectronica;
    private  String telefon;
    /**Constructor**/
    public Client(int id,String nom, String apostal, String aelectronica,String telefon) {
        this.id = id;
        this.nom = nom;
        this.apostal = apostal;
        this.aelectronica = aelectronica;
        this.telefon = telefon;
    }

    /** Getters*/

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getApostal() {
        return apostal;
    }

    public String getAelectronica() {
        return aelectronica;
    }

    public String getTelefon() {
        return telefon;
    }

    /** Metodo toString */

    @Override
    public String toString() {
        return id+"\t"+nom+"\t"+apostal+"\t"+aelectronica+"\t"+telefon;
    }
}

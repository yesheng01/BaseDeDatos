package Exercise;

/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * EncarrecsProductes
 * Created by: sheng
 * Date : 18/03/2021
 * Description:
 **/
public class EncarrecsProductes {
    private int id_encarrec;
    private int id_producte;
    private int numArticles;

    public EncarrecsProductes(int id_encarrec, int id_producte, int numArticles) {
        this.id_encarrec = id_encarrec;
        this.id_producte = id_producte;
        this.numArticles = numArticles;
    }

    public int getId_encarrec() {
        return id_encarrec;
    }

    public void setId_encarrec(int id_encarrec) {
        this.id_encarrec = id_encarrec;
    }

    public int getId_producte() {
        return id_producte;
    }

    public void setId_producte(int id_producte) {
        this.id_producte = id_producte;
    }

    public int getNumArticles() {
        return numArticles;
    }

    public void setNumArticles(int numArticles) {
        this.numArticles = numArticles;
    }
}

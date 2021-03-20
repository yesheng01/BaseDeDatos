package Exercise;

/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * product
 * Created by: sheng
 * Date : 16/03/2021
 * Description:
 **/
public class product {

    int id;
    String nombre;
    float precio;
    int unitat;

    /**Constructor**/
    public product(int id,String nombre, float precio , int unitat){
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.unitat=unitat;
    }

    /** Getters y Setters*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getUnitat() {
        return unitat;
    }

    public void setUnitat(int unitat) {
        this.unitat = unitat;
    }

    @Override
    /** Metodo toString */
    public String toString() {
        return "product{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", unitat=" + unitat +
                '}';
    }
}

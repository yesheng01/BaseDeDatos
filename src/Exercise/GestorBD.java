package Exercise;
import java.util.*;
import java.sql.*;
/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * GestorBD
 * Created by: sheng
 * Date : 16/03/2021
 * Description:
 **/

public class GestorBD {

    Connection conn;
    /**Constructor**/
    public GestorBD() throws Exception{
        String url = "jdbc:mysql://localhost:3306/gestor";
        String user = "root", psw = "";
        this.conn=DriverManager.getConnection(url,user,psw);
    }

    /** Metodos */
    public void tancar () throws Exception{
        this.conn.close();
    }
    public int obtenirNouIDClient() throws Exception{
        //Buscar ID Maximo
        Statement cercaMaxId=conn.createStatement();
        ResultSet rs=cercaMaxId.executeQuery("SELECT MAX(ID) FROM CLIENT");
        if (rs.next()) return (1+rs.getInt(1));
        else return 1;
    }
    public int obtenirNouIDEncarrec() throws Exception{
        //Buscar ID Maximo
        Statement cercaMaxId=conn.createStatement();
        ResultSet rs=cercaMaxId.executeQuery("SELECT MAX(ID) FROM ENCARREC");
        if (rs.next()) return (1+rs.getInt(1));
        else return 1;
    }

    public int devolverIDProducto(String Nombre) throws SQLException{
        //Devuelve el ID del producto
        int id_Producte = 0;
        Statement cerca = conn.createStatement();
        ResultSet rs= cerca.executeQuery("SELECT * FROM PRODUCT WHERE NOMBRE='" + Nombre + "'");
        while (rs.next()) {
            id_Producte = rs.getInt("ID");
        }
        return id_Producte;
    }

    /**  Muestra los clientes que hay por nombre en el base de datos*/

    public List<Client> cercarClient(String nom) throws Exception{
        Statement cerca= conn.createStatement();
        ResultSet rs= cerca.executeQuery("SELECT * FROM CLIENT WHERE NOM='" + nom + "'");
        LinkedList<Client> llista=new LinkedList<Client>();
        while (rs.next()){
            llista.add(new Client(rs.getInt("ID"),rs.getString("NOM"),rs.getString("APOSTAL"),rs.getString("AELECTRONICO"),rs.getString("TELEFON")));
        }
        return llista;
    }
    /**  Muestra los encargos que hay por ID en el base de datos*/
    public List<Encarrec> cercarEncarrec(int ID) throws Exception{
        Statement cerca= conn.createStatement();
        ResultSet rs = cerca.executeQuery("SELECT * FROM ENCARREC WHERE ID='"+ID+"'");
        LinkedList<Encarrec> llista=new LinkedList<>();
        while (rs.next()){
            llista.add(new Encarrec(rs.getInt("ID"),rs.getTimestamp("DATE"),rs.getInt("CLIENTE")));
        }
        return llista;
    }
    /**  Muestra todos los productos que hay en el base de datos*/
    public List<product> cercarProducte() throws Exception{
        Statement cerca= conn.createStatement();
        ResultSet rs = cerca.executeQuery("SELECT * FROM PRODUCT");
        LinkedList<product> llista=new LinkedList<>();
        while (rs.next()){
            llista.add(new product(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getFloat("PRECIO") , rs.getInt("UNITAT") ));
        }
        return llista;
    }

    /**  Metodo para eliminar un encargo*/

    public void eliminarEncarrec(int id) throws SQLException {
        Statement eliminar = conn.createStatement();
        boolean r = eliminar.execute("DELETE FROM ENCARREC WHERE ID=" + id);
    }

    /**  Metodo para borrar el ID del encargo que esta relacionado*/

    public void borrarEncarrecProducte(int ID) throws Exception{
        Statement update = conn.createStatement();
        update.executeUpdate("DELETE FROM ENCARRECSPRODUCTES WHERE ID_ENCARREC ='" + ID + "'");
    }


    /**  Metodo para añadir un cliente*/

    public void  afegirClient (Client c) throws Exception{
        Statement upadte= conn.createStatement();
        String valors = c.getId()+", '"+c.getNom()+"','"+c.getApostal()+"','"+c.getAelectronica()+"','"+c.getTelefon()+"'";
        upadte.executeUpdate("INSERT INTO CLIENT VALUES("+valors+")");
    }
    /**  Metodo para añadir un producto*/
    public void afegirProducte(product e)throws Exception{
        Statement update=conn.createStatement();
        String valors =e.getId() + ",'"+e.getNombre()+"','"+e.getPrecio()+"','" + e.getUnitat() +"'";
        update.executeUpdate("INSERT  INTO PRODUCT VALUE ("+valors+")");
    }
    /**  Metodo para añadir un encargo*/
    public void afegirEncarrec(Encarrec o)throws Exception{
        Statement update=conn.createStatement();
        String valors = o.getId() + ",'"+o.getDate()+"','"+o.getCliente()+"'";
        update.executeUpdate("INSERT  INTO ENCARREC VALUE ("+valors+")");
    }

    /**  Metodo para añadir un encargo producto , este añade el id de producto y encargo y el numArticles que hay
     * Tambien lo que hace es actualizar luego en el producto*/

    public void afegirEncarrecsProductes(int id, String producto, int unitats) throws Exception {
        int id_Producte=devolverIDProducto(producto);
        Statement update= conn.createStatement();
        String valors = id +",'"+id_Producte+"','"+unitats+"'";
        update.executeUpdate("INSERT INTO ENCARRECSPRODUCTES VALUE("+valors+")");
        ActualizarProducteEncarrec(id_Producte,unitats);
    }

    /**  Actualiza el base de datos del producto restandole la unidad*/

    public void ActualizarProducteEncarrec(int producto, int numQuant) throws SQLException {
        Statement lliurar = conn.createStatement();
        boolean r = lliurar.execute("UPDATE PRODUCT SET UNITAT=UNITAT-" + numQuant + " WHERE ID='" + producto + "'" );
    }


}
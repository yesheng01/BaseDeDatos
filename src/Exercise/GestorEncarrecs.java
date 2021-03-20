package Exercise;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Exercise
 * Nombre_project: BaseDeDatos
 * GestorEncarrecs
 * Created by: sheng
 * Date : 16/03/2021
 * Description:
 **/

public class GestorEncarrecs {

    GestorBD gestor;
    BufferedReader entrada;
    /**Constructor**/
    public GestorEncarrecs() throws Exception{
        gestor=new GestorBD();
        entrada=new BufferedReader(new InputStreamReader(System.in));
    }

    /**Metodo de menus**/
    public void start() throws Exception{
        int opcio;
        while (0!=(opcio=menuPrincilap())){
            try {
                switch (opcio){
                    case 1:
                        cercarClient();
                        break;
                    case 2:
                        afegirClient();
                        break;
                    case 3:
                        afegirProducte();
                        break;

                    case 4:
                        cercarProducte();
                        break;
                    case 5:
                        afegirEncarrec();
                        break;
                    case 6:
                        eliminarEncarrec();
                        break;
                    case 7:
                        cercarEncarrec();
                        break;
                    default: mostrarDades("Opció incorrecta\n");
                }
            }catch (Exception e){
                mostrarDades("S'ha produit un error: "+e+"\n");
            }
        }
        gestor.tancar();
    }
    /**Metodo de como se muestran los menus**/
    private int menuPrincilap() throws Exception {
        String menu="\nQuina acció vols realitzar?\n"+"" +
                "[1] Cercar client\n"+
                "[2] Afegir client\n"+
                "[3] Afegir producte\n"+
                "[4] Mostrar producte\n"+
                "[5] Afegir un encarrec\n"+
                "[6] Eliminar un encarrec\n"+
                "[7] Mostrar un encarrec\n"+
                "[0] Sortir\n"+"Opcio>";
        String lin=entrarDades(menu);
        try {
            int opcio=Integer.parseInt(lin);
            return opcio;
        }catch (Exception e){
            return -1;
        }
    }

    //Amb els metodes entrarDades i mostrarDades, fem el codi independent de la interficie.
    // Si mai es fan canvis, nomes cal canviar aquests dos metodes.

    private String entrarDades(String pregunta) throws IOException{
        mostrarDades(pregunta);
        return entrarDades();
    }
    private  void mostrarDades(String dades) throws  IOException{
        System.out.print(dades);
    }
    private String entrarDades() throws IOException{
        String linea=entrada.readLine();
        if ("".equals(linea)) return null;
        return linea;
    }


    /**  Muestra los clientes que hay por nombre en el base de datos*/
    private void cercarClient() throws Exception{
        String nom=entrarDades("Introdueix el nom del client: "); if (null==nom) return;
        List<Client> llista= gestor.cercarClient(nom);
        Iterator it = llista.iterator();
        mostrarDades("Els clients trobats amb aquest nom son:\n" +
                "----------------------------\n");
        while (it.hasNext()){
            Client c=(Client) it.next();
            mostrarDades(c.toString()+"\n");
        }
    }
    /**  Muestra los encargos que hay por ID en el base de datos*/

    private void cercarEncarrec() throws Exception{
        int id=Integer.parseInt(entrarDades("Introdueix l'identificador de l'encarrec: "));
        if (0==id) return;
        List<Encarrec> llista=gestor.cercarEncarrec(id);
        Iterator it= llista.listIterator();
        mostrarDades("Els encarrecs trobats amb aquest ID son:\n−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−\n");
        while (it.hasNext()){
            Encarrec e=(Encarrec) it.next();
            mostrarDades(e.toString()+"\n");
        }
    }
    /**  Muestra todos los productos que hay en el base de datos*/
    private void cercarProducte() throws Exception{
        System.out.println("Esto es el producto: ");
        List<product> llista=gestor.cercarProducte();
        Iterator it= llista.listIterator();
        mostrarDades("Els producto encontrado es:\n−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−\n");
        while (it.hasNext()){
            product e=(product) it.next();
            mostrarDades(e.toString()+"\n");
        }
    }

    /**  Metodo para añadir un cliente*/
    public void afegirClient() throws Exception{
        mostrarDades("Introdueix dades del nou client (deixaen blanc per sortir).\n");
        String nom=entrarDades("Nom: "); if (null==nom) return;
        String apostal=entrarDades("Adreça postal: "); if (null==apostal) return;
        String aelectronica=entrarDades("E-mail: "); if (null==aelectronica) return;
        String telefon=entrarDades("Telefon: "); if (null==telefon) return;
        int id = gestor.obtenirNouIDClient();
        gestor.afegirClient(new Client(id,nom,apostal,aelectronica,telefon));
        mostrarDades("Operació completada satisfactoriament.\n");
    }
    /**  Metodo para añadir un producto*/
    public void afegirProducte()throws Exception{
        mostrarDades("Introduce el producto que quieres: \n");
        String nombre=entrarDades("Nombre: "); if (null==nombre) return;
        float precio=Float.parseFloat(entrarDades("precio: ")); if (0==precio) return;
        int unitat=Integer.parseInt(entrarDades("Unitats: ")); if (0 == unitat) return;
        int id = gestor.devolverIDProducto(nombre);
        gestor.afegirProducte(new product(id,nombre,precio,unitat));
        mostrarDades("Operació completada satisfactoriament.\n");
    }
    /**  Metodo para añadir un encargo*/
    public void afegirEncarrec() throws Exception{
        mostrarDades("Introdueix dades del nou encarrec (deixa en blac per sortir.)\n");
        int ID = gestor.obtenirNouIDEncarrec();
        //Le damos formato para poder introducir bien la fehca en la base de datos.
        Date fecha = new Date();
        Date data = (new Timestamp(fecha.getTime()));
        int IDCliente =Integer.parseInt(entrarDades("Introdueix el identificador del client: ")); if (0 == IDCliente) return;
        gestor.afegirEncarrec(new Encarrec(ID,data,IDCliente));
        afegirEncarrecProducte(ID);
        mostrarDades("Operació completada satisfactoriament.\n");
    }


    /**  Metodo para eliminar un encargo*/
    public void eliminarEncarrec() throws Exception{
        mostrarDades("Introduex 'ID de l'encarrec que es vol elminiar (deixa en blanc" +
                " per sortir).\n");
        int encarrec=Integer.parseInt(entrarDades("id: ")); if (0 == encarrec) return;
        gestor.borrarEncarrecProducte(encarrec);
        gestor.eliminarEncarrec(encarrec);
        mostrarDades("Operació completada satisfactòriament.\n");
    }
    /**  Metodo para añadir un encargo producto , este añade el id de producto y encargo y el numArticles que hay
     * Tambien lo que hace es actualizar luego en el producto*/
    public void afegirEncarrecProducte(int id_Encarrec) throws Exception {
        int NumProductes = Integer.parseInt(entrarDades("Numero de productes per aficar dins l'encàrrec: ")); if (0==NumProductes) return;
        for (int i = 0; i < NumProductes ; i++) {
            String Nombre = entrarDades("Nom del producte: "); if (null == Nombre) return;
            int cantidad=Integer.parseInt(entrarDades("Introdueix la quantitat del producte: ")); if (0==cantidad) return;
            gestor.afegirEncarrecsProductes(id_Encarrec,Nombre,cantidad);
        }
    }


    public static void main(String[] args) throws Exception {
        GestorEncarrecs gbd=new GestorEncarrecs();
        gbd.start();
    }
}
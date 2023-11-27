package ui;

import Models.Usuario;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UsuarioUi {
    // lista de usuarios

    private ArrayList<Usuario> misUsuarios = new ArrayList<Usuario>(); // declaro e inicio un arraylist
    private int contador = 0;  // contador para los usuarios
    private Usuario nuevoUsuario;
    private String nombreNew;

    public UsuarioUi(){
        // aca se arma el menu de la UI

        int opcion;
        while (true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "1. Alta Usuario" + "\n" +
                "2. Actualizar Usuario" + "\n" +
                "3. Listar Usuarios" + "\n" +
                "4. Buscar Usuario" + "\n" +
                "5. Baja Usuario" + "\n" +
                "6. Salir" + "\n"
            ));
               switch (opcion){
                   case 1: altaUsuario();
                        break;
                   case 2: actualizarUsuario();
                        break;
                   case 3: listarUsuarios();
                        break;
                   case 4: buscarUsuario();
                        break;
                   case 5: bajaUsuario();
                        break;
                   case 6: System.exit(0);
                        break;
                   default:
                       JOptionPane.showConfirmDialog(null,"opcion invalida");
               }

        }
    }

    public void altaUsuario(){
        // pide los datos del nuevo usuario, valida los mismos y agrega a la lista

        int idUsuario = 0;              // inicia el id del primer usuario en 0
        String nombreUsuario = "";
        Date fechaAlta = new Date();
        Date fechaModificacion = new Date();
        Date fechaNacimiento = null;
        String fechaNacimientoStr = "";
        String clave = "";

        idUsuario = contador;
        boolean datovalido = false;       // inicia la var. datovalido como false
        while (!datovalido){              // mientras la var. se mantenga NEGADO entra al while
            nombreUsuario = JOptionPane.showInputDialog(" ingrese el nombre del usuario");
            datovalido = validarNombreUsuario(nombreUsuario);
        }

        datovalido = false;
        while (!datovalido) {
            clave = JOptionPane.showInputDialog(" ingrese la clave");
            datovalido = validarClave(clave);
        }

            // aqui se utiliza el tipo String para la var. fechaDeNacimientoStr
            // ya que despues se convertira a tipo Dato
        datovalido = false;
        while (!datovalido) {

            // aqui si validarFechaNacimiento es correcta, se convierte a tipo Dato con el formato elegido
            fechaNacimientoStr = JOptionPane.showInputDialog(" ingrese la fecha de nacimiento");
            datovalido = validarFechaNacimiento(fechaNacimientoStr);
            if (datovalido){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  // formato elegido de tipo Dato
                try {
                    Date fechaNacimientoDate = dateFormat.parse(fechaNacimientoStr);      // para pasar una fecha string a Date se utiliza dateFormat.parse
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
            Usuario miUsuarioNew = new Usuario(idUsuario,nombreUsuario,fechaAlta,fechaModificacion,fechaNacimiento,clave);
            misUsuarios.add(miUsuarioNew);          // se añade el nuevo usuario al array list
            contador = contador + 1;                // incremento del contador de id's
    }


    public void actualizarUsuario(){
        // se pide el id del usuario a modificar, se ingresan los datos nuevos y se actualiza

        int idUsuario = 0;
        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del usuario"));
        Usuario miUsuario = misUsuarios.get(idUsuario);
        if(miUsuario != null){
            Date fechaHoy = new Date();
            miUsuario.setFechaModificacion(fechaHoy);       // a la var. fechaModificacion se le da el valor nuevo con tipo Dato
            String nombre = "";
            boolean datovalido = false;
            while (!datovalido){
                nombreNew = JOptionPane.showInputDialog("Ingresa el nuevo nombre de usuario");
                datovalido = validarNombreUsuario(nombreNew);
            }
            miUsuario.setNombreUsuario(nombreNew);
        }else{
            JOptionPane.showConfirmDialog(null, "No se encontro el usuario");
        }
        misUsuarios.set(idUsuario,miUsuario);       // al id que esta guardado en el arraylist, setea y pone los datos nuevos
    }


    public void listarUsuarios(){
        // mostar la lista de usuarios

        for (Usuario u: misUsuarios){       // se usa un for-each para listar los usuarios del arraylist
            System.out.println(u);
        }
    }


    public void buscarUsuario(){
        // pido un id, lo busco y lo muestro

        int idUsuario = 0;
        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del usuario"));
        System.out.println(misUsuarios.get(idUsuario));
    }


    public void bajaUsuario(){
        // busco un usuario y lo doy de baja

        int idUsuario = 0;
        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del usuario para la baja"));
        misUsuarios.remove(idUsuario);
    }


    private boolean validarNombreUsuario(String nombreUsuario){
        // para validar los datos se usa el bucle if

        if(nombreUsuario.length() > 2){     // si nombreUsuario tiene mas de 2 caracteres es valido
            return true;
        }else {
            return false;
        }
    }

    private boolean validarClave(String clave){
        // para validar los datos se usa el bucle if

        if(clave.length() > 2){     // si la clave tiene mas de 2 caracteres es valido
            return true;
        }else {
            return false;
        }
    }

    private boolean validarFechaNacimiento(String fechaNacimiento) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimientoDate = dateFormat.parse(fechaNacimiento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

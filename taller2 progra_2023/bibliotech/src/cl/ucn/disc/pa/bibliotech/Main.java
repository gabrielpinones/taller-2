/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech;

import cl.ucn.disc.pa.bibliotech.model.Libro;
import cl.ucn.disc.pa.bibliotech.model.Socio;
import cl.ucn.disc.pa.bibliotech.services.Sistema;
import cl.ucn.disc.pa.bibliotech.services.Utils;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.io.IOException;
import java.util.Objects;

/**
 * The Main.
 *
 * @author Programacion Avanzada.
 */
public final class Main {

    /**
     * The main.
     *
     * @param args to use.
     * @throws IOException en caso de un error.
     */
    public static void main(final String[] args) throws IOException {

        // inicializacion del sistema.
        Sistema sistema = new Sistema();

        StdOut.println(sistema.obtegerCatalogoLibros());

        String opcion = null;
        while (!Objects.equals(opcion, "2")) {

            StdOut.println("""
                    [*] Bienvenido a BiblioTech [*]
                                    
                    [1] Iniciar Sesion
                    [2] Salir
                    """);
            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> iniciarSesion(sistema);
                case "2" -> StdOut.println("¡Hasta Pronto!");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Inicia la sesion del Socio en el Sistema.
     *
     * @param sistema a utilizar.
     */
    private static void iniciarSesion(final Sistema sistema) {
        StdOut.println("[*] Iniciar sesion en BiblioTech [*]");
        StdOut.print("Ingrese su numero de socio: ");
        int numeroSocio = StdIn.readInt();
        StdIn.readLine();

        StdOut.print("Ingrese su contrasenia: ");
        String contrasenia = StdIn.readLine();

        // intento el inicio de session
        try {
            sistema.iniciarSession(numeroSocio, contrasenia);
        } catch (IllegalArgumentException ex) {
            StdOut.println("Ocurrio un error: " + ex.getMessage());
            return;
        }

        // mostrar menu principal
        menuPrincipal(sistema);
    }

    private static void menuPrincipal(final Sistema sistema) {
        String opcion = null;
        while (!Objects.equals(opcion, "4")) {
            StdOut.println("""
                    [*] BiblioTech [*]
                                        
                    [1] Prestamo de un libro
                    [2] Editar información
                    [3] Calificar libro
                                        
                    [4] Cerrar sesion
                    """);

            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> menuPrestamo(sistema);
                case "2" -> editarInformacion(sistema);
                case "3" -> calificacionLibro(sistema);
                case "4" -> sistema.cerrarSession();
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    private static void menuPrestamo(Sistema sistema) {
        StdOut.println("[*] Préstamo de un Libro [*]");
        StdOut.println(sistema.obtegerCatalogoLibros());

        StdOut.print("Ingrese el ISBN del libro a tomar prestado: ");
        String isbn = StdIn.readLine();

        try {
            sistema.realizarPrestamoLibro(isbn);
        } catch (IOException ex) {
            StdOut.println("Ocurrio un error, intente nuevamente: " + ex.getMessage());
        }
    }

    private static void editarInformacion(Sistema sistema) {

        String opcion = null;
        while (!Objects.equals(opcion, "3")) {

            StdOut.println("[*] Editar Perfil [*]");
            StdOut.println(sistema.obtenerDatosSocioLogeado());
            StdOut.println("""               
                    [1] Editar correo Electronico
                    [2] Editar Contraseña
                                        
                    [3] Volver atrás
                    """);
            StdOut.print("Escoja una opción: ");
            opcion = StdIn.readLine();

            switch (opcion) {
                case "1" -> editarCorreo(sistema);
                case "2" -> cambiarContrasenia(sistema);
                case "3" -> StdOut.println("Volviendo al menú anterior...");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * metodo para cambiar la contraseña
     * @param sistema
     */
    private static void cambiarContrasenia(Sistema sistema) {
        StdOut.println("Introduzca su nueva contraseña");
        String contrasenia = StdIn.readLine();
        sistema.getSocio().setContrasenia(contrasenia);
        StdOut.println("felicidades cambio su contraseña");
    }

    /**
     * metodo para cambiar el correo
     * @param sistema
     */
    private static void editarCorreo(Sistema sistema) {
        //pide el correo electronico
        StdOut.println("Introduzca su nuevo correo electronico");
        String correoElectronico = StdIn.readLine();
        //lo valido
        Utils.validarEmail(correoElectronico);
        //lo agrego
        sistema.getSocio().setCorreoElectronico(correoElectronico);
        StdOut.println("felicidades cambio su correo electronico");
    }

    public static void calificacionLibro(Sistema sistema){
        //pide el codigo isbn
        StdOut.println("Introduzca el codigo isbn del libro");
        String codigoIsbn = StdIn.readLine();
        //busca el libro x el codigo isbn
        Libro libro = sistema.buscarLibro(codigoIsbn);

        if(libro == null){
            StdOut.println("Libro no encontrado");
            return;
        }

        //verifica si el libro ya ha sifo calificado
        if (libro.getCalificacion() !=null){
            StdOut.println("este libro ya ha sido calificado");
        }

        //pide la calificacion del libro
        StdOut.println("Ingrese la calificación del libro (0-5):");
        double calificacion = StdIn.readDouble();
        if (calificacion < 0 || calificacion > 5){
            StdOut.println("Calificación inválida. Debe ser un valor entre 0 y 5.");
            return;
        }

        // se actualiza la cantidad de total de estrellas que ha recibido el libro sumandole la calificacion que se acaba de ingresar
        libro.setEstrellasTotal(libro.getEstrellasTotal() + calificacion);

        // se incrimenta el numero de calificaciones que ha recibido el libro en 1 para despúes calcular el promedio de calificaciones
        libro.setNum_Calificacion(libro.getNum_Calificacion() + 1);

        //saca el promedio de calificaicon
        double calificacionTotal = libro.getEstrellasTotal() / (double)libro.getNum_Calificacion();

        //redodndea el valor promedio del libro a una decima utilizando el metodo mathround y luego dividirlo x 10.0 para asegurarnos que
        //resultado sea de una sola cifra
        libro.setCalificacion(Math.round(calificacionTotal * 10.0)/ 10.0);

        //imprime el mensaje que confirma la calificacion del libro ha sido guardada
        StdOut.println("Calificación registrada correctamente. Calificación promedio: " + libro.getCalificacion());




    }
}

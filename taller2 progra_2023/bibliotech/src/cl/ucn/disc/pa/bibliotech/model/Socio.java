/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

import cl.ucn.disc.pa.bibliotech.services.Utils;

/**
 * Clase que representa a un Socio.
 *
 * @author Programacion Avanzada.
 */
public final class Socio {

    /**
     * Numero maximo de libros que puede tener el Socio.
     */
    private static final int NUMERO_LIBROS_MAXIMO = 5;

    /**
     * Nombre del socio.
     */
    private String nombre;

    /**
     * Apellido del socio.
     */
    private String apellido;

    /**
     * Email del socio.
     */
    private String correoElectronico;

    /**
     * Numero del socio.
     */
    private int numeroDeSocio;

    /**
     * Contrasenia del socio.
     */
    private String contrasenia;

    /**
     * Libros que el Socio tiene en prestamo (maximo 10).
     */
    private Libro[] librosEnPrestamo = new Libro[0];

    /**
     * The Constructor.
     *
     * @param nombre            del socio.
     * @param apellido          del socio.
     * @param correoElectronico del socio.
     * @param numeroDeSocio     del socio.
     * @param contrasenia       del socio.
     */
    public Socio(String nombre, String apellido, String correoElectronico, int numeroDeSocio, String contrasenia) {

        // metodo estatico para validar el nombre
        Utils.validarNombre(nombre);
        this.nombre = nombre;

        // metodo estatico para validar el apellido
        Utils.validarApellido(apellido);
        this.apellido = apellido;

        // metodo estatico para validacion de email.
        Utils.validarEmail(correoElectronico);
        this.correoElectronico = correoElectronico;

        //metodo para validar el numero de socio
        Utils.validarNumeroSocio(numeroDeSocio);
        this.numeroDeSocio = numeroDeSocio;

        //metodo estatico para validar contraseña
        Utils.validarContrasenia(contrasenia);
        this.contrasenia = contrasenia;
    }

    /**
     * @return el nombre del Socio.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return el apellido del Socio.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @return el nombre completo del Socio.
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * @return el correo electronico del Socio.
     */
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    /**
     * ser del correo electronico para poder cambiarñp
     * @param correoElectronico a cambiar
     */
    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }
    /**
     * @return el numero del Socio.
     */
    public int getNumeroDeSocio() {
        return this.numeroDeSocio;
    }

    /**
     * @return la contrasenia del Socio.
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

    /**
     * se habilita para poder cambiar la contraseña
     * @param contrasenia a cambiaar
     */
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }
    /**
     * Agrega un libro en prestamo al Socio.
     *
     * @param libro a agregar.
     */
    public void agregarLibro(final Libro libro) {
        // validacion
        if (this.librosEnPrestamo.length == NUMERO_LIBROS_MAXIMO) {
            throw new IllegalArgumentException("El Socio ya tiene la maxima cantidad de libros en prestamo: " + NUMERO_LIBROS_MAXIMO);
        }
        // agrego el libro
        Utils.append(this.librosEnPrestamo, libro);
    }

}

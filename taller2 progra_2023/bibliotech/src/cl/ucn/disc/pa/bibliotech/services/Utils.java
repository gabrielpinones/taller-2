/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.services;

import cl.ucn.disc.pa.bibliotech.model.Socio;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase que reune los metodos utilitarios.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class Utils {

    /**
     * The Email validator.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    /**
     * VALIDADOR DEL ISBN
     */
    private static final Pattern isbn_Pattern = Pattern.compile("\\b(?:\\d{9}[\\dX]|\\d{13})\\b");
    /**
     * validador de nombre
     */
    private static final Pattern nombre_Pattern = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    /**
     * vcalidador de apellido
     */
    private static final Pattern apellido_Pattern = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    /**
     * validador de numero de socio
     */
    private static final Pattern numeroSocio_Pattern =Pattern.compile("^[0-9]+$");
    /**
     * Constructor privado: nadie puede instanciar esta clase.
     */
    private Utils() {
        // nothing here
    }

    /**
     * Add theObject to theStaticArray.
     *
     * @param theStaticArray the array.
     * @param theObject      the object to append.
     * @param <T>            generic to use.
     * @return the static array.
     */
    public static <T> T[] append(T[] theStaticArray, T theObject) {
        // new arraylist
        List<T> theList = new ArrayList<>();
        // copy all the items from [] to the list
        Collections.addAll(theList, theStaticArray);
        // add the object
        theList.add(theObject);
        // return the static array
        return theList.toArray(theStaticArray);
    }

    /**
     * Valida un correo electronico, en caso de no ser valido se lanza una Exception.
     *
     * @param email a validar.
     */
    public static void validarEmail(final String email) {
        // el correo debe ser estructuralmente valido
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Correo Electronico no valido: " + email);
        }
    }

    /**
     * valida la contraseñea, no se requiere ningun tipo de restriccion
     * @param contrasenia a validar
     * @return retorna falso si la contraseña no tiene caracteres que no sean espacios o este vacia si es nulo
     * de lo contrario retorna true si la contraseña es valida
     */
    public static void validarContrasenia (String contrasenia) {
        //si la contraseña es nula o vacia no es valida
        if (contrasenia == null && contrasenia.length() == 0) {
            throw new IllegalArgumentException("contraseña no valida " + contrasenia);
        }
    }

    /**
     * metodo para validar isbn, hecho con el pattern tiene que tener entre 10 y 13 caracteres
     * @param isbn a validar
     */
    public static void  validarIsbn (String isbn) {
        // verifica si el isbn es nulo o vacio
        if (isbn == null || isbn.length() == 0) {
            throw new IllegalArgumentException("isbn no valido: " + isbn);
        }
        // el isbn debe estar bien hecho
        if (!isbn_Pattern.matcher(isbn).matches()) {
            throw new IllegalArgumentException("isbn no valido: " + isbn);
        }
    }
    /**
     * metodo para validar el nombre
     */
    public static void validarNombre(String nombre){
        if(nombre == null || nombre.length()==0){
            throw new IllegalArgumentException("nombre no valido: " + nombre);
        }
        if (!nombre_Pattern.matcher(nombre).matches()){
            throw new IllegalArgumentException("nombre no valido: " + nombre);
        }
    }
    /**
     * metodo para validar el apellido
     */
    public static void validarApellido(String apellido){
        if(apellido == null || apellido.length()==0){
            throw new IllegalArgumentException("nombre no valido: " + apellido);
        }
        if (!apellido_Pattern.matcher(apellido).matches()){
            throw new IllegalArgumentException("apellido no valido: " + apellido);
        }

    }

    /**
     * metodo para validar el numero de socio
     */
    public static void validarNumeroSocio(int numeroDeSocio){
        if (!numeroSocio_Pattern.matcher(String.valueOf(numeroDeSocio)).matches()){
            throw new IllegalArgumentException("numero de socio no valido: " + numeroDeSocio);
        }
    }


}

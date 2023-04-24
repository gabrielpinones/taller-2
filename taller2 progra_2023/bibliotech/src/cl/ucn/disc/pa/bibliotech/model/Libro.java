/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

import cl.ucn.disc.pa.bibliotech.services.Utils;

import java.util.Iterator;

/**
 * Clase que representa un Libro.
 *
 * @author Programacion Avanzada.
 */
public final class Libro {

    /**
     * The ISBN.
     */
    private String isbn;

    /**
     * The Titulo.
     */
    private String titulo;

    /**
     * The Author.
     */
    private String autor;

    /**
     * The Categoria
     */
    private String categoria;

    /**
     * la calificacion
     */
    private Double calificacion;

    /**
     * la cantidad de calificaciones dad
     */
    private Integer num_Calificacion;

    /**
     * la cantidad de estrellas totales
     */
    private Double estrellasTotal;

    /**
     * The Constructor.
     *
     * @param isbn      del libro.
     * @param titulo    del libro.
     * @param autor     del libro
     * @param categoria del libro.
     */
    public Libro(final String isbn, final String titulo, final String autor, final String categoria){
        //validacion de isbm
        Utils.validarIsbn(isbn);
        this.isbn = isbn;

        // validacion del titulo
        if (titulo == null || titulo.length() == 0) {
            throw new IllegalArgumentException("Titulo no valido!");
        }
        this.titulo = titulo;

        // validacion del titulo
        if (autor == null || autor.length() == 0 ){
            throw new IllegalArgumentException("autor no valido!");
        }
        this.autor = autor;

        // validacion del titulo
        if (categoria == null || categoria.length() == 0 ){
            throw new IllegalArgumentException("categoria no valida!");
        }
        this.categoria = categoria;

        this.calificacion = null;
        this.num_Calificacion = 0;
        this.estrellasTotal = 0.0;
    }




    /**
     * metodo del iterador
     * @return los elementos de una lista
     */


    /**
     * @return the ISBN.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * @return the titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @return the autor.
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * @return the categoria.
     */
    public String getCategoria() {
        return this.categoria;
    }


    /**
     * la calificacion
     * @return
     */
    public Double getCalificacion() {
        return calificacion;
    }

    /**
     * el set de la calificacion para cambiarla
     * @param calificacion del libro
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * el numero de calificacion
     * @return el numero de califcaciones dada
     */
    public Integer getNum_Calificacion() {
        return num_Calificacion;
    }

    /**
     * el numero de calificaciones dada, se cambia
     * @param num_Calificacion
     */
    public void setNum_Calificacion(Integer num_Calificacion) {
        this.num_Calificacion = num_Calificacion;
    }

    /**
     * las estrellas totales
     * @return
     */
    public Double getEstrellasTotal() {
        return estrellasTotal;
    }

    /**
     * set de las estrellas totales para ir cambiando cuando den una calificacion
     * @param estrellasTotal
     */
    public void setEstrellasTotal(Double estrellasTotal) {
        this.estrellasTotal = estrellasTotal;
    }


}

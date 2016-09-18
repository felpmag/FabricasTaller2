/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Numero.java,v 1.3 2008/08/13 15:40:20 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - 02-Mar-2006
 * Modificado por: Juan Erasmo Gómez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.numeroMvc.mundo;

import java.util.Observable;

/**
 * Representa un número que puede ser observado.
 */
public class Numero extends Observable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Número actual.
     */
    private int numero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el número inicializándolo en 0.
     */
    public Numero( )
    {
        numero = 0;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el valor del número.
     * @param nuevoNumero Nuevo valor de número.
     */
    public void cambiarNumero( int nuevoNumero )
    {
        // Cambia el número
        numero = nuevoNumero;
        // Notifica a los observadores, informando el nuevo número
        setChanged( );
        notifyObservers( new Integer( numero ) );
    }

    /**
     * Devuelve el valor del número.
     * @return Valor actual del número.
     */
    public int darNumero( )
    {
        return numero;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Método para la extensión2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
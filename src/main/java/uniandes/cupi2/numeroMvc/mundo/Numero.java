/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Numero.java,v 1.3 2008/08/13 15:40:20 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - 02-Mar-2006
 * Modificado por: Juan Erasmo G�mez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.numeroMvc.mundo;

import java.util.Observable;

import annotation.Feature;
import uniandes.cupi2.numeroMvc.interfaz.InterfazNumeroMvc;

/**
 * Representa un n�mero que puede ser observado.
 */
@Feature(nombre = "Numero", padre = "InterfazNumeroMvc", or=true, requerido= true)
public class Numero extends Observable
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * N�mero actual.
     */
    private int numero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el n�mero inicializ�ndolo en 0.
     */
    public Numero( )
    {
        numero = 0;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cambia el valor del n�mero.
     * @param nuevoNumero Nuevo valor de n�mero.
     */
    @Feature(padre="Numero", nombre="CambiarNumero", or=true)
    public void cambiarNumero( int nuevoNumero )
    {
        // Cambia el n�mero
        numero = nuevoNumero;
        // Notifica a los observadores, informando el nuevo n�mero
        setChanged( );
        notifyObservers( new Integer( numero ) );
    }

    /**
     * Devuelve el valor del n�mero.
     * @return Valor actual del n�mero.
     */
    @Feature(padre="Numero", nombre="DarNumero", or=true)
    public int darNumero( )
    {
        return numero;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1.
     * @return respuesta1.
     */
    @Feature(padre="Numero", nombre="MetodoUno", or=true)
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2.
     * @return respuesta2.
     */
    @Feature(padre="Numero", nombre="MetodoDos", or=true)
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}
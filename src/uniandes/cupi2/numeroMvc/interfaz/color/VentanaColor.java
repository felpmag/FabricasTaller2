/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VentanaColor.java,v 1.5 2008/08/14 10:53:37 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - Mar 3, 2006
 * Modificado por: Daniel Romero - 22-Sep-2006
 * Modificado por: Juan Erasmo G�mez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.numeroMvc.interfaz.color;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;

import annotation.Feature;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Ventana de visualizaci�n en formato de color.
 */
@Feature(padre="InterfazNumeroMvc", nombre="VentanaColor", xor=true)
public class VentanaColor extends JInternalFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 5107092146528207517L;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la visualizaci�n y el control.
     */
    private PanelColor panelColor;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param numero N�mero a visualizar-modificar.
     */
    @Feature(padre="VentanaColor", nombre="VentanaColorConstructor", requerido=true)
    public VentanaColor( Numero numero )
    {
        setSize( 276, 150 );
        setMaximizable( true );
        setClosable( true );
        setResizable( true );
        setPreferredSize( new java.awt.Dimension( 276, 150 ) );
        setTitle( "Vista-Controlador Color" );

        // panelColor
        panelColor = new PanelColor( numero );

        add( panelColor, BorderLayout.CENTER );
    }

}

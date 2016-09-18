/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VentanaRomano.java,v 1.8 2008/08/14 10:28:30 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - Mar 3, 2006
 * Modificado por: Daniel Romero - 22-Sep-2006
 * Modificado por: Juan Erasmo Gómez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.numeroMvc.interfaz.romano;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JInternalFrame;

import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Ventana de visualización en formato de números romanos.
 */
public class VentanaRomano extends JInternalFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 3325218644134834205L;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la visualización y el control.
     */
    private PanelRomano panelRomano;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param numero Número a visualizar-modificar.
     */
    public VentanaRomano( Numero numero )
    {
        setSize( 276, 150 );
        setMaximizable( true );
        setClosable( true );
        setResizable( true );
        setPreferredSize( new Dimension( 276, 150 ) );
        setTitle( "Vista-Controlador Romano" );

        // panelRomano
        panelRomano = new PanelRomano( numero );
        add( panelRomano, BorderLayout.CENTER );
    }

}

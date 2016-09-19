/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VentanaBinario.java,v 1.6 2008/08/14 11:01:35 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.binario;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JInternalFrame;

import annotation.Feature;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Ventana de visualizaci�n en formato de n�meros binarios.
 */
@Feature(padre="InterfazNumeroMvc", nombre="VentanaBinario", xor=true)
public class VentanaBinario extends JInternalFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = -2975409965024688727L;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la visualizaci�n y el control.
     */
    private PanelBinario panelBinario;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param numero N�mero a visualizar-modificar.
     */
    @Feature(padre="VentanaBinario", nombre="VentanaBinarioConstructor", requerido=true)
    public VentanaBinario( Numero numero )
    {
        // Inicializa ventana
        setSize( 276, 150 );
        setMaximizable( true );
        setClosable( true );
        setResizable( true );
        setPreferredSize( new Dimension( 276, 150 ) );
        setTitle( "Vista-Controlador Binario" );

        // PanelBinario
        panelBinario = new PanelBinario( numero );
        add( panelBinario, BorderLayout.CENTER );
    }

}

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: VentanaNumero.java,v 1.6 2008/08/14 10:34:20 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.numero;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JInternalFrame;

import annotation.Feature;
import annotation.Feature.RestriccionHijos;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Ventana de visualizaci�n en formato de n�meros decimales.
 */
@Feature(padre = "Ventana", nombre = "VentanaNumero", relacion = RestriccionHijos.ALT)
public class VentanaNumero extends JInternalFrame {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = -1041687579265094542L;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la visualizaci�n y el control.
     */
    private PanelNumero panelNumero;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * 
     * @param numero
     *            N�mero a visualizar-modificar.
     */
    @Feature(padre = "VentanaNumero", nombre = "VentanaNumeroConstructor", requerido = true)
    public VentanaNumero(Numero numero) {
        setSize(276, 150);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setPreferredSize(new Dimension(276, 150));
        setTitle("Vista-Controlador Numero");

        // panelNumero
        panelNumero = new PanelNumero(numero);

        add(panelNumero, BorderLayout.CENTER);
    }
}

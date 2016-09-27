/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelColor.java,v 1.7 2008/08/14 11:08:45 jua-gome Exp $
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
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import annotation.Feature;
import annotation.Feature.RestriccionHijos;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualizaci�n Tipo Color.
 */
@Feature(padre = "VentanaColor", nombre = "PanelColor", requerido = true, requiero = "Numero")
public class PanelColor extends JPanel implements Observer, ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 164393480331818959L;

    /**
     * Constante para la acci�n del bot�n de selecci�nar color.
     */
    private final static String SELECCIONAR_COLOR = "Seleccionar Color";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: Numero que se est� visualizando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualizaci�n del Color.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Bot�n de env�o de el cambio de n�mero.
     */
    private JButton botonCambiar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * 
     * @param num
     *            N�mero a visualizar.
     */
    @Feature(padre = "PanelColor", nombre = "ConstructorPanelColor", relacion=RestriccionHijos.OR, requerido = true)
    public PanelColor(Numero num) {
        // Guarda el n�mero
        numero = num;

        // Inicializa el panel
        setLayout(new BorderLayout());
        setSize(261, 106);

        // etiquetaNumero
        etiquetaNumero = new JLabel();
        etiquetaNumero.setText("");
        etiquetaNumero.setHorizontalTextPosition(SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaNumero.setForeground(new Color(128, 130, 159));
        etiquetaNumero.setFont(new Font("Tahoma", Font.BOLD, 24));
        etiquetaNumero.setOpaque(true);

        // botonCambiar
        botonCambiar = new JButton();
        botonCambiar.setText(SELECCIONAR_COLOR);
        botonCambiar.setActionCommand(SELECCIONAR_COLOR);
        botonCambiar.addActionListener(this);

        // panelControl
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new BorderLayout());
        panelControl.add(botonCambiar, BorderLayout.CENTER);

        // Adici�n del panel y la etiqueta
        add(etiquetaNumero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        // Conecta el observador
        numero.addObserver(this);

        // Cambia el color
        etiquetaNumero.setBackground(new Color(numero.darNumero()));
    }

    // -----------------------------------------------------------------
    // Destructor
    // -----------------------------------------------------------------

    /**
     * M�todo llamado por JAVA al salir el panel de la vista del usuario.
     */
    @Feature(padre = "PanelColor", nombre = "RemoveNotifyPanelColor", relacion=RestriccionHijos.OR)
    public void removeNotify() {
        // Elimina el observador
        numero.deleteObserver(this);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Control: Selecciona un color y modifica el n�mero.
     */
    @Feature(padre = "PanelColor", nombre = "CambiarColorPanelColor", relacion=RestriccionHijos.OR)
    public void cambiarColor() {
        Color nuevoColor = JColorChooser.showDialog(this, "Seleccione el color", etiquetaNumero.getBackground());
        if (nuevoColor != null)
            numero.cambiarNumero(16777216 + nuevoColor.getRGB());
    }

    /**
     * Vista: Recibe la notificaci�n de cambio de valor del n�mero.
     */
    @Feature(padre = "PanelColor", nombre = "UpdatePanelColor", relacion=RestriccionHijos.OR)
    public void update(Observable sender, Object num) {
        Integer numero = (Integer) num;

        // Vista: Actualiza la visualizaci�n
        etiquetaNumero.setBackground(new Color(numero.intValue()));
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e
     *            Acci�n que gener� el evento.
     */
    @Feature(padre = "PanelColor", nombre = "ActionPerformedPanelColor", relacion=RestriccionHijos.OR)
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (SELECCIONAR_COLOR.equals(comando))
            // Control: Cambia el color
            cambiarColor();
    }

}

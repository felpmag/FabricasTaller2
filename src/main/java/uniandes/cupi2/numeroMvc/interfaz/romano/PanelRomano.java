/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelRomano.java,v 1.8 2008/08/14 11:08:45 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.romano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import annotation.Feature;
import annotation.Feature.RestriccionHijos;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualizaci�n Tipo Romano.
 */
@Feature(padre = "VentanaRomano", nombre = "PanelRomano", requerido = true, requiero = "Numero")
public class PanelRomano extends JPanel implements Observer, ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 6027780429950087974L;

    /**
     * Constante para la acci�n del bot�n anterior.
     */
    private final static String ANTERIOR = "Anterior";

    /**
     * Constante para la acci�n del bot�n siguiente.
     */
    private final static String SIGUIENTE = "Siguiente";

    /**
     * Valores de las letras.
     */
    private static int[] numeros = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * Letras de los n�meros romanos.
     */
    private static String[] letras = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: N�mero que se est� modificando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualizaci�n del N�mero en Romano.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Bot�n Siguiente.
     */
    private JButton botonSiguiente;

    /**
     * Control: Bot�n de Anterior.
     */
    private JButton botonAnterior;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * 
     * @param num
     *            N�mero a visualizar.
     */
    @Feature(padre = "PanelRomano", nombre = "ConstructorPanelRomano", relacion=RestriccionHijos.OR, requerido = true)
    public PanelRomano(Numero num) {
        // Guarda el n�mero
        numero = num;

        // Inicializa el panel
        setLayout(new BorderLayout());
        setSize(261, 106);

        // etiquetaNumero
        etiquetaNumero = new JLabel();
        etiquetaNumero.setText("###");
        etiquetaNumero.setHorizontalTextPosition(SwingConstants.CENTER);
        etiquetaNumero.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaNumero.setForeground(Color.BLACK);
        etiquetaNumero.setFont(new Font("Times New Roman", Font.BOLD, 24));

        // botonAnterior
        botonAnterior = new JButton();
        botonAnterior.setText(ANTERIOR);
        botonAnterior.setActionCommand(ANTERIOR);
        botonAnterior.addActionListener(this);

        // botonSiguiente
        botonSiguiente = new JButton();
        botonSiguiente.setText(SIGUIENTE);
        botonSiguiente.setActionCommand(SIGUIENTE);
        botonSiguiente.addActionListener(this);

        // panelControl
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new BorderLayout());
        panelControl.add(botonSiguiente, BorderLayout.EAST);
        panelControl.add(botonAnterior, BorderLayout.WEST);

        // Adici�n del panel y la etiqueta
        add(etiquetaNumero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        // Conecta el observador
        numero.addObserver(this);

        // Cambia el n�mero
        etiquetaNumero.setText(darRomano(numero.darNumero()));
    }

    // -----------------------------------------------------------------
    // Destructor
    // -----------------------------------------------------------------

    /**
     * M�todo llamado por JAVA al salir el panel de la vista del usuario.
     */
    @Feature(padre = "PanelRomano", nombre = "RemoveNotifyPanelRomano", relacion=RestriccionHijos.OR)
    public void removeNotify() {
        // Elimina el observador
        numero.deleteObserver(this);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Vista: Retorna el n�mero en representaci�n romana.
     * 
     * @param num
     *            N�mero a convertir.
     * @return Representaci�n en n�meros romanos del n�mero ingresado.
     */
    @Feature(padre = "PanelRomano", nombre = "DarRomanoPanelRomano", relacion=RestriccionHijos.OR)
    public String darRomano(int num) {
        // Valida el numero
        if (num <= 0) {
            return "Numero <= 0!";
        } else if (num > 3999) {
            return "Numero > 3999!";
        }

        // Convierte el numero
        String romano = "";
        int numeroActual = num;
        for (int i = 0; i < numeros.length; i++) {
            while (numeroActual >= numeros[i]) {
                romano += letras[i];
                numeroActual -= numeros[i];
            }
        }
        return romano;
    }

    /**
     * Control: Resta 1 al n�mero visualizado.
     */
    @Feature(padre = "PanelRomano", nombre = "AnteriorPanelRomano", relacion=RestriccionHijos.OR)
    public void anterior() {
        numero.cambiarNumero(numero.darNumero() - 1);
    }

    /**
     * Control: Suma 1 al n�mero visualizado.
     */
    @Feature(padre = "PanelRomano", nombre = "SiguientePanelRomano", relacion=RestriccionHijos.OR)
    public void siguiente() {
        numero.cambiarNumero(numero.darNumero() + 1);
    }

    /**
     * Vista: Recibe la notificaci�n de cambio de valor del n�mero.
     */
    @Feature(padre = "PanelRomano", nombre = "UpdatePanelRomano", relacion=RestriccionHijos.OR)
    public void update(Observable sender, Object num) {
        Integer numero = (Integer) num;

        // Vista: Actualiza la visualizaci�n
        etiquetaNumero.setText(darRomano(numero.intValue()));
    }

    /**
     * Manejo de los eventos de los botones.
     * 
     * @param e
     *            Acci�n que gener� el evento.
     */
    @Feature(padre = "PanelRomano", nombre = "ActionPerformedPanelRomano", relacion=RestriccionHijos.OR)
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (ANTERIOR.equals(comando))
            // Control: Cambia el n�mero por el anterior
            anterior();
        else if (SIGUIENTE.equals(comando))
            // Control: Cambia el n�mero por el siguiente
            siguiente();
    }

}

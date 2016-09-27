/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelNotas.java,v 1.8 2008/08/14 11:08:45 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.notas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import annotation.Feature;
import annotation.Feature.RestriccionHijos;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualizaci�n Tipo Nota Musical.
 */
@Feature(padre = "VentanaNotas", nombre = "PanelNotas", relacion=RestriccionHijos.OR, requerido = true, requiero = "Numero")
public class PanelNotas extends JPanel implements Observer, ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 8169100775679594866L;

    /**
     * Constante para la acci�n del bot�n de seleccionar nota.
     */
    private final static String CAMBIAR = "Seleccionar Nota";

    /**
     * Constante para la acci�n del bot�n de tocar nota.
     */
    private final static String TOCAR = "Tocar";

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
     * Vista: Visualizaci�n de la Nota Musical.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Bot�n de env�o de el cambio nota.
     */
    private JButton botonCambiar;

    /**
     * Bot�n para tocar la nota
     */
    private JButton botonTocar;

    /**
     * Constructor del panel
     * 
     * @param num
     *            N�mero a visualizar
     */
    @Feature(padre = "PanelNotas", nombre = "ConstructorPanelNotas", relacion=RestriccionHijos.OR, requerido = true)
    public PanelNotas(Numero num) {
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
        etiquetaNumero.setForeground(new Color(128, 130, 159));
        etiquetaNumero.setFont(new Font("Tahoma", Font.BOLD, 12));

        // botonCambiar
        botonCambiar = new JButton();
        botonCambiar.setText(CAMBIAR);
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);

        // botonTocar
        botonTocar = new JButton();
        botonTocar.setText(TOCAR);
        botonTocar.setActionCommand(TOCAR);
        botonTocar.addActionListener(this);

        // panelControl
        JPanel panelControl = new JPanel();
        panelControl.setLayout(new BorderLayout());

        panelControl.add(botonCambiar, BorderLayout.CENTER);
        panelControl.add(botonTocar, BorderLayout.EAST);

        // Adici�n del panel y la etiqueta
        add(etiquetaNumero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);

        // Conecta el observador
        numero.addObserver(this);
        //
        // Cambia el n�mero
        etiquetaNumero.setText("Frecuencia Musical: " + darNota(numero.darNumero()) + " (" + numero.darNumero() + ")");
    }

    // -----------------------------------------------------------------
    // Destructor
    // -----------------------------------------------------------------

    /**
     * M�todo llamado por JAVA al salir el panel de la vista del usuario.
     */
    @Feature(padre = "PanelNotas", nombre = "RemoveNotifyPanelNotas", relacion=RestriccionHijos.OR)
    public void removeNotify() {
        // Elimina el observador
        numero.deleteObserver(this);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Control: Reemplaza el n�mero visualizado con el n�mero representado
     * por la nota ingresada por el usuario.
     */
    @Feature(padre = "PanelNotas", nombre = "CambiarNotaPanelNotas", relacion=RestriccionHijos.OR)
    public void cambiarNota() {
        Object[] notas = {"Do", "Re", "Mi", "Fa", "Sol", "La", "Si"};
        String nota = (String) JOptionPane.showInputDialog(this, "Nota", "Nota", JOptionPane.QUESTION_MESSAGE, null,
                notas, "Do");
        if (nota != null) {
            int numeroNota = 0;
            if ("Do".equals(nota))
                numeroNota = 50;
            else if ("Re".equals(nota))
                numeroNota = 52;
            else if ("Mi".equals(nota))
                numeroNota = 54;
            else if ("Fa".equals(nota))
                numeroNota = 55;
            else if ("Sol".equals(nota))
                numeroNota = 57;
            else if ("La".equals(nota))
                numeroNota = 59;
            else if ("Si".equals(nota))
                numeroNota = 61;

            // Cambia el n�mero
            numero.cambiarNumero(numeroNota);
        }
    }

    /**
     * Vista: Toca la nota equivalente al n�mero visualizado.
     */
    @Feature(padre = "PanelNotas", nombre = "TocarNotaPanelNotas", relacion=RestriccionHijos.OR)
    public void tocarNota() {
        ReproductorNota.tocarNota(darNota(numero.darNumero()), 0);
    }

    /**
     * Vista: Retorna la nota que se representa con un n�mero.
     * 
     * @param numero
     *            N�mero a representar.
     * @return Nota representada por el n�mero ingresado.
     */
    @Feature(padre = "PanelNotas", nombre = "DarNotaPanelNotas", relacion=RestriccionHijos.OR)
    public int darNota(int numero) {
        int nota = (numero % 100) + 10;
        return nota;
    }

    /**
     * Vista: Recibe la notificaci�n de cambio de valor del n�mero.
     */
    @Feature(padre = "PanelNotas", nombre = "ActionPerformedPanelNotas", relacion=RestriccionHijos.OR)
    public void update(Observable sender, Object num) {
        Integer numero = (Integer) num;

        // Vista: Actualiza la visualizaci�n
        etiquetaNumero.setText("Frecuencia Musical: " + darNota(numero.intValue()) + " (" + numero.intValue() + ")");
        tocarNota();
    }

    /**
     * Manejo de los eventos de los botones.
     * 
     * @param e
     *            Acci�n que gener� el evento.
     */
    @Feature(padre = "PanelNotas", nombre = "ActionPerformedPanelNotas", relacion=RestriccionHijos.OR)
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (CAMBIAR.equals(comando))
            // Control: Cambia la nota musical
            cambiarNota();
    }

}

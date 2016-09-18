/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBinario.java,v 1.6 2008/08/14 11:08:45 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.binario;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualización Tipo Binario.
 */
public class PanelBinario extends JPanel implements Observer, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = -2486744718378309246L;

    /**
     * Constantes para la acción del botón de cambiar número.
     */
    private final static String CAMBIAR = "Cambiar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: Número que se está visualizando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualización del Número.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Botón de envío del cambio de número.
     */
    private JButton botonCambiar;

    /**
     * Control: Campo de texto para el cambio de número.
     */
    private JTextField textoNumeroBinario;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param num Número a visualizar.
     */
    public PanelBinario( Numero num )
    {
        // Guarda el número
        numero = num;

        // Inicializa el panel
        setLayout( new BorderLayout( ) );
        setSize( 261, 106 );

        // EtiquetaNumero
        etiquetaNumero = new JLabel( );
        etiquetaNumero.setText( "###" );
        etiquetaNumero.setHorizontalTextPosition( SwingConstants.CENTER );
        etiquetaNumero.setHorizontalAlignment( SwingConstants.CENTER );
        etiquetaNumero.setForeground( new java.awt.Color( 0, 94, 169 ) );
        etiquetaNumero.setFont( new java.awt.Font( "Courier New", Font.PLAIN, 18 ) );

        // botonCambiar
        botonCambiar = new JButton( );
        botonCambiar.setText( CAMBIAR );
        botonCambiar.setActionCommand( CAMBIAR );
        botonCambiar.addActionListener( this );

        // textoNumero
        textoNumeroBinario = new JTextField( );

        // panelControl
        JPanel panelControl = new JPanel( );
        panelControl.setLayout( new BorderLayout( ) );
        panelControl.add( botonCambiar, BorderLayout.EAST );
        panelControl.add( textoNumeroBinario, BorderLayout.CENTER );

        // Adición del panel y la etiqueta
        add( etiquetaNumero, BorderLayout.CENTER );
        add( panelControl, BorderLayout.SOUTH );

        // Conecta el observador
        numero.addObserver( this );

        // Cambia el número
        etiquetaNumero.setText( Integer.toBinaryString( numero.darNumero( ) ) );
    }

    // -----------------------------------------------------------------
    // Destructor
    // -----------------------------------------------------------------

    /**
     * Método llamado por JAVA al salir el panel de la vista del usuario.
     */
    public void removeNotify( )
    {
        // Elimina el observador
        numero.deleteObserver( this );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Control: Reemplaza el número visualizado con el número escrito en el campo de texto.
     */
    public void modificarNumero( )
    {
        try
        {
            int nuevoNumero = Integer.parseInt( textoNumeroBinario.getText( ), 2 );
            numero.cambiarNumero( nuevoNumero );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "El número en binario especificado es inválido", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Vista: Recibe la notificación de cambio de valor del número.
     */
    public void update( Observable sender, Object num )
    {
        Integer numero = ( Integer )num;
        textoNumeroBinario.setText( "" );

        // Vista: Actualiza la visualización
        etiquetaNumero.setText( Integer.toBinaryString( numero.intValue( ) ) );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( CAMBIAR.equals( comando ) )
            // Control: Modificar Número
            modificarNumero( );
    }

}

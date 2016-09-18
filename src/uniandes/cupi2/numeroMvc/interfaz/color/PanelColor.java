/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelColor.java,v 1.7 2008/08/14 11:08:45 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.color;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualización Tipo Color.
 */
public class PanelColor extends JPanel implements Observer, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 164393480331818959L;

    /**
     * Constante para la acción del botón de selecciónar color.
     */
    private final static String SELECCIONAR_COLOR = "Seleccionar Color";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: Numero que se está visualizando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualización del Color.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Botón de envío de el cambio de número.
     */
    private JButton botonCambiar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param num Número a visualizar.
     */
    public PanelColor( Numero num )
    {
        // Guarda el número
        numero = num;

        // Inicializa el panel
        setLayout( new BorderLayout( ) );
        setSize( 261, 106 );

        // etiquetaNumero
        etiquetaNumero = new JLabel( );
        etiquetaNumero.setText( "" );
        etiquetaNumero.setHorizontalTextPosition( SwingConstants.CENTER );
        etiquetaNumero.setHorizontalAlignment( SwingConstants.CENTER );
        etiquetaNumero.setForeground( new Color( 128, 130, 159 ) );
        etiquetaNumero.setFont( new Font( "Tahoma", Font.BOLD, 24 ) );
        etiquetaNumero.setOpaque( true );

        // botonCambiar
        botonCambiar = new JButton( );
        botonCambiar.setText( SELECCIONAR_COLOR );
        botonCambiar.setActionCommand( SELECCIONAR_COLOR );
        botonCambiar.addActionListener( this );

        // panelControl
        JPanel panelControl = new JPanel( );
        panelControl.setLayout( new BorderLayout( ) );
        panelControl.add( botonCambiar, BorderLayout.CENTER );

        // Adición del panel y la etiqueta
        add( etiquetaNumero, BorderLayout.CENTER );
        add( panelControl, BorderLayout.SOUTH );

        // Conecta el observador
        numero.addObserver( this );

        // Cambia el color
        etiquetaNumero.setBackground( new Color( numero.darNumero( ) ) );
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
     * Control: Selecciona un color y modifica el número.
     */
    public void cambiarColor( )
    {
        Color nuevoColor = JColorChooser.showDialog( this, "Seleccione el color", etiquetaNumero.getBackground( ) );
        if( nuevoColor != null )
            numero.cambiarNumero( 16777216 + nuevoColor.getRGB( ) );
    }

    /**
     * Vista: Recibe la notificación de cambio de valor del número.
     */
    public void update( Observable sender, Object num )
    {
        Integer numero = ( Integer )num;

        // Vista: Actualiza la visualización
        etiquetaNumero.setBackground( new Color( numero.intValue( ) ) );
    }

    /**
     * Manejo de los eventos de los botones
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( SELECCIONAR_COLOR.equals( comando ) )
            // Control: Cambia el color
            cambiarColor( );
    }

}

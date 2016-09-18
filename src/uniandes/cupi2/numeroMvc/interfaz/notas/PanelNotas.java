/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelNotas.java,v 1.8 2008/08/14 11:08:45 jua-gome Exp $
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

package uniandes.cupi2.numeroMvc.interfaz.notas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualización Tipo Nota Musical.
 */
public class PanelNotas extends JPanel implements Observer, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 8169100775679594866L;

    /**
     * Constante para la acción del botón de seleccionar nota.
     */
    private final static String CAMBIAR = "Seleccionar Nota";

    /**
     * Constante para la acción del botón de tocar nota.
     */
    private final static String TOCAR = "Tocar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: Número que se está modificando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualización de la Nota Musical.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Botón de envío de el cambio nota.
     */
    private JButton botonCambiar;

    /**
     * Botón para tocar la nota
     */
    private JButton botonTocar;

    /**
     * Constructor del panel
     * @param num Número a visualizar
     */
    public PanelNotas( Numero num )
    {
        // Guarda el número
        numero = num;

        // Inicializa el panel
        setLayout( new BorderLayout( ) );
        setSize( 261, 106 );

        // etiquetaNumero
        etiquetaNumero = new JLabel( );
        etiquetaNumero.setText( "###" );
        etiquetaNumero.setHorizontalTextPosition( SwingConstants.CENTER );
        etiquetaNumero.setHorizontalAlignment( SwingConstants.CENTER );
        etiquetaNumero.setForeground( new Color( 128, 130, 159 ) );
        etiquetaNumero.setFont( new Font( "Tahoma", Font.BOLD, 12 ) );

        // botonCambiar
        botonCambiar = new JButton( );
        botonCambiar.setText( CAMBIAR );
        botonCambiar.setActionCommand( CAMBIAR );
        botonCambiar.addActionListener( this );

        // botonTocar
        botonTocar = new JButton( );
        botonTocar.setText( TOCAR );
        botonTocar.setActionCommand( TOCAR );
        botonTocar.addActionListener( this );

        // panelControl
        JPanel panelControl = new JPanel( );
        panelControl.setLayout( new BorderLayout( ) );

        panelControl.add( botonCambiar, BorderLayout.CENTER );
        panelControl.add( botonTocar, BorderLayout.EAST );

        // Adición del panel y la etiqueta
        add( etiquetaNumero, BorderLayout.CENTER );
        add( panelControl, BorderLayout.SOUTH );

        // Conecta el observador
        numero.addObserver( this );
        //
        // Cambia el número
        etiquetaNumero.setText( "Frecuencia Musical: " + darNota( numero.darNumero( ) ) + " (" + numero.darNumero( ) + ")" );
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
     * Control: Reemplaza el número visualizado con el número representado por la nota ingresada por el usuario.
     */
    public void cambiarNota( )
    {
        Object[] notas = { "Do", "Re", "Mi", "Fa", "Sol", "La", "Si" };
        String nota = ( String )JOptionPane.showInputDialog( this, "Nota", "Nota", JOptionPane.QUESTION_MESSAGE, null, notas, "Do" );
        if( nota != null )
        {
            int numeroNota = 0;
            if( "Do".equals( nota ) )
                numeroNota = 50;
            else if( "Re".equals( nota ) )
                numeroNota = 52;
            else if( "Mi".equals( nota ) )
                numeroNota = 54;
            else if( "Fa".equals( nota ) )
                numeroNota = 55;
            else if( "Sol".equals( nota ) )
                numeroNota = 57;
            else if( "La".equals( nota ) )
                numeroNota = 59;
            else if( "Si".equals( nota ) )
                numeroNota = 61;

            // Cambia el número
            numero.cambiarNumero( numeroNota );
        }
    }

    /**
     * Vista: Toca la nota equivalente al número visualizado.
     */
    public void tocarNota( )
    {
        ReproductorNota.tocarNota( darNota( numero.darNumero( ) ), 0 );
    }

    /**
     * Vista: Retorna la nota que se representa con un número.
     * @param numero Número a representar.
     * @return Nota representada por el número ingresado.
     */
    public int darNota( int numero )
    {
        int nota = ( numero % 100 ) + 10;
        return nota;
    }

    /**
     * Vista: Recibe la notificación de cambio de valor del número.
     */
    public void update( Observable sender, Object num )
    {
        Integer numero = ( Integer )num;

        // Vista: Actualiza la visualización
        etiquetaNumero.setText( "Frecuencia Musical: " + darNota( numero.intValue( ) ) + " (" + numero.intValue( ) + ")" );
        tocarNota( );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( CAMBIAR.equals( comando ) )
            // Control: Cambia la nota musical
            cambiarNota( );
    }

}

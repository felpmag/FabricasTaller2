/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelRomano.java,v 1.8 2008/08/14 11:08:45 jua-gome Exp $
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

import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualización Tipo Romano.
 */
public class PanelRomano extends JPanel implements Observer, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 6027780429950087974L;

    /**
     * Constante para la acción del botón anterior.
     */
    private final static String ANTERIOR = "Anterior";

    /**
     * Constante para la acción del botón siguiente.
     */
    private final static String SIGUIENTE = "Siguiente";

    /**
     * Valores de las letras.
     */
    private static int[] numeros = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

    /**
     * Letras de los números romanos.
     */
    private static String[] letras = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

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
     * Vista: Visualización del Número en Romano.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Botón Siguiente.
     */
    private JButton botonSiguiente;

    /**
     * Control: Botón de Anterior.
     */
    private JButton botonAnterior;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param num Número a visualizar.
     */
    public PanelRomano( Numero num )
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
        etiquetaNumero.setForeground( Color.BLACK );
        etiquetaNumero.setFont( new Font( "Times New Roman", Font.BOLD, 24 ) );

        // botonAnterior
        botonAnterior = new JButton( );
        botonAnterior.setText( ANTERIOR );
        botonAnterior.setActionCommand( ANTERIOR );
        botonAnterior.addActionListener( this );

        // botonSiguiente
        botonSiguiente = new JButton( );
        botonSiguiente.setText( SIGUIENTE );
        botonSiguiente.setActionCommand( SIGUIENTE );
        botonSiguiente.addActionListener( this );

        // panelControl
        JPanel panelControl = new JPanel( );
        panelControl.setLayout( new BorderLayout( ) );
        panelControl.add( botonSiguiente, BorderLayout.EAST );
        panelControl.add( botonAnterior, BorderLayout.WEST );

        // Adición del panel y la etiqueta
        add( etiquetaNumero, BorderLayout.CENTER );
        add( panelControl, BorderLayout.SOUTH );

        // Conecta el observador
        numero.addObserver( this );

        // Cambia el número
        etiquetaNumero.setText( darRomano( numero.darNumero( ) ) );
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
     * Vista: Retorna el número en representación romana.
     * @param num Número a convertir.
     * @return Representación en números romanos del número ingresado.
     */
    public String darRomano( int num )
    {
        // Valida el numero
        if( num <= 0 )
        {
            return "Numero <= 0!";
        }
        else if( num > 3999 )
        {
            return "Numero > 3999!";
        }

        // Convierte el numero
        String romano = "";
        int numeroActual = num;
        for( int i = 0; i < numeros.length; i++ )
        {
            while( numeroActual >= numeros[ i ] )
            {
                romano += letras[ i ];
                numeroActual -= numeros[ i ];
            }
        }
        return romano;
    }

    /**
     * Control: Resta 1 al número visualizado.
     */
    public void anterior( )
    {
        numero.cambiarNumero( numero.darNumero( ) - 1 );
    }

    /**
     * Control: Suma 1 al número visualizado.
     */
    public void siguiente( )
    {
        numero.cambiarNumero( numero.darNumero( ) + 1 );
    }

    /**
     * Vista: Recibe la notificación de cambio de valor del número.
     */
    public void update( Observable sender, Object num )
    {
        Integer numero = ( Integer )num;

        // Vista: Actualiza la visualización
        etiquetaNumero.setText( darRomano( numero.intValue( ) ) );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( ANTERIOR.equals( comando ) )
            // Control: Cambia el número por el anterior
            anterior( );
        else if( SIGUIENTE.equals( comando ) )
            // Control: Cambia el número por el siguiente
            siguiente( );
    }

}

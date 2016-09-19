/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: PanelBinario.java,v 1.6 2008/08/14 11:08:45 jua-gome Exp $
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

import annotation.Feature;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Panel Vista-Controlador de la visualizaci�n Tipo Binario.
 */
@Feature(padre="VentanaBinario", nombre="PanelBinario", requerido= true, requiero="Numero")
public class PanelBinario extends JPanel implements Observer, ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = -2486744718378309246L;

    /**
     * Constantes para la acci�n del bot�n de cambiar n�mero.
     */
    private final static String CAMBIAR = "Cambiar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: N�mero que se est� visualizando.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Vista: Visualizaci�n del N�mero.
     */
    private JLabel etiquetaNumero;

    /**
     * Control: Bot�n de env�o del cambio de n�mero.
     */
    private JButton botonCambiar;

    /**
     * Control: Campo de texto para el cambio de n�mero.
     */
    private JTextField textoNumeroBinario;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor del panel.
     * @param num N�mero a visualizar.
     */
    @Feature(padre="PanelBinario", nombre="ConstructorPanelBinario", or=true, requerido=true)
    public PanelBinario( Numero num )
    {
        // Guarda el n�mero
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

        // Adici�n del panel y la etiqueta
        add( etiquetaNumero, BorderLayout.CENTER );
        add( panelControl, BorderLayout.SOUTH );

        // Conecta el observador
        numero.addObserver( this );

        // Cambia el n�mero
        etiquetaNumero.setText( Integer.toBinaryString( numero.darNumero( ) ) );
    }

    // -----------------------------------------------------------------
    // Destructor
    // -----------------------------------------------------------------

    /**
     * M�todo llamado por JAVA al salir el panel de la vista del usuario.
     */
    @Feature(padre="PanelBinario", nombre="RemoveNotifyPanelBinario", or=true)
    public void removeNotify( )
    {
        // Elimina el observador
        numero.deleteObserver( this );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Control: Reemplaza el n�mero visualizado con el n�mero escrito en el campo de texto.
     */
    @Feature(padre="PanelBinario", nombre="ModificarNumeroPanelBinario", or=true)
    public void modificarNumero( )
    {
        try
        {
            int nuevoNumero = Integer.parseInt( textoNumeroBinario.getText( ), 2 );
            numero.cambiarNumero( nuevoNumero );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "El n�mero en binario especificado es inv�lido", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Vista: Recibe la notificaci�n de cambio de valor del n�mero.
     */
    @Feature(padre="PanelBinario", nombre="UpdatePanelBinario", or=true)
    public void update( Observable sender, Object num )
    {
        Integer numero = ( Integer )num;
        textoNumeroBinario.setText( "" );

        // Vista: Actualiza la visualizaci�n
        etiquetaNumero.setText( Integer.toBinaryString( numero.intValue( ) ) );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acci�n que gener� el evento.
     */
    @Feature(padre="PanelBinario", nombre="ActionPerformedPanelBinario", or=true)
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( CAMBIAR.equals( comando ) )
            // Control: Modificar N�mero
            modificarNumero( );
    }

}

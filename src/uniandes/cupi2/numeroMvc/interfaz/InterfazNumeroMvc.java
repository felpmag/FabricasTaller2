/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: InterfazNumeroMvc.java,v 1.6 2008/08/14 11:08:23 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Pablo Barvo - 02-Mar-2006
 * Modificado por: Daniel Romero - 22-Sep-2006
 * Modificado por: Juan Erasmo Gómez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.numeroMvc.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import uniandes.cupi2.numeroMvc.interfaz.binario.VentanaBinario;
import uniandes.cupi2.numeroMvc.interfaz.color.VentanaColor;
import uniandes.cupi2.numeroMvc.interfaz.notas.VentanaNotas;
import uniandes.cupi2.numeroMvc.interfaz.numero.VentanaNumero;
import uniandes.cupi2.numeroMvc.interfaz.romano.VentanaRomano;
import uniandes.cupi2.numeroMvc.mundo.Numero;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazNumeroMvc extends JFrame implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 422461325927300786L;

    /**
     * Constante para la acción del botón de nueva vista.
     */
    private static final String NUEVO = "NUEVO";

    /**
     * Constante para la acción del botón de salir.
     */
    private static final String SALIR = "SALIR";

    /**
     * Constante para la acción del botón de nueva ventana de formato decimal.
     */
    private static final String VC_NUMERO = "Numero";

    /**
     * Constante para la acción del botón de nueva ventana de formato romano.
     */
    private static final String VC_ROMANO = "Romano";

    /**
     * Constante para la acción del botón de nueva ventana de formato color.
     */
    private static final String VC_COLOR = "Color";

    /**
     * Constante para la acción del botón de nueva ventana de formato musical.
     */
    private static final String VC_NOTA = "Nota Musical";

    /**
     * Constante para la acción del botón de nueva ventana de formato binario.
     */
    private static final String VC_BINARIO = "Binario";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Modelo: Número a visualizar - controlar.
     */
    private Numero numero;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Desktop donde se muestran las ventanas adicionales.
     */
    private JDesktopPane desktop;

    /**
     * Panel con las extensiones.
     */
    private PanelExtension panelExtension;

    /**
     * Barra de menú.
     */
    private JMenuBar barraMenu;

    /**
     * El menú VC.
     */
    private JMenu menu;

    /**
     * Ítem nuevo.
     */
    private JMenuItem itemNuevo;

    /**
     * Ítem salir.
     */
    private JMenuItem itemSalir;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la ventana principal de la interfaz.
     */
    public InterfazNumeroMvc( )
    {
        // Crea la clase principal
        numero = new Numero( );

        // Construye la forma
        setTitle( "Numero - MVC" );
        getContentPane( ).setLayout( new BorderLayout( ) );
        setSize( 530, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Menú ventana
        barraMenu = new JMenuBar( );

        menu = new JMenu( "VC" );

        menu.setMnemonic( KeyEvent.VK_D );
        barraMenu.add( menu );

        // Menú ventana- Nueva ventana
        itemNuevo = new JMenuItem( "Nuevo VC" );
        itemNuevo.setMnemonic( KeyEvent.VK_N );
        itemNuevo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.ALT_MASK ) );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.addActionListener( this );
        menu.add( itemNuevo );

        // Menú ventana- Salir
        itemSalir = new JMenuItem( "Salir" );
        itemSalir.setMnemonic( KeyEvent.VK_S );
        itemSalir.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.ALT_MASK ) );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menu.add( itemSalir );
        setJMenuBar( barraMenu );

        // Creación de los paneles aquí
        panelExtension = new PanelExtension( this );
        getContentPane( ).add( panelExtension, BorderLayout.SOUTH );

        // Agrega el Escritorio
        desktop = new JDesktopPane( );
        getContentPane( ).add( desktop, BorderLayout.CENTER );
        desktop.setBackground( Color.LIGHT_GRAY );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Crea una nueva ventana (vista-controlador) del número (modelo).
     */
    public void crearNuevaVentana( )
    {
        JInternalFrame ventana = null;
        //
        // Muestra las opciones al usuario
        String vc = ( String )JOptionPane.showInputDialog( this, "Ventana a crear", "Ventana", JOptionPane.QUESTION_MESSAGE, null, new String[]{ VC_NUMERO, VC_ROMANO, VC_COLOR, VC_NOTA, VC_BINARIO }, null );
        if( vc != null )
        {
            if( VC_NUMERO.equals( vc ) )
                ventana = new VentanaNumero( numero );
            else if( VC_ROMANO.equals( vc ) )
                ventana = new VentanaRomano( numero );
            else if( VC_NOTA.equals( vc ) )
                ventana = new VentanaNotas( numero );
            else if( VC_BINARIO.equals( vc ) )
                ventana = new VentanaBinario( numero );
            else
                ventana = new VentanaColor( numero );

            // Agrega y muestra la ventana
            desktop.add( ventana );
            ventana.setVisible( true );
        }
    }

    /**
     * Manejo de los eventos de los botones.
     * @param e Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( NUEVO.equals( e.getActionCommand( ) ) )
            crearNuevaVentana( );
        else
            System.exit( 0 );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = numero.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = numero.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Argumentos para la ejecución de la aplicación. En este caso no se requieren
     */
    public static void main( String[] args )
    {
        InterfazNumeroMvc interfaz = new InterfazNumeroMvc( );
        interfaz.setVisible( true );
    }

}
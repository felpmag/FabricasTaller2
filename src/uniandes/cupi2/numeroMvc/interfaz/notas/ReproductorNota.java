/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: ReproductorNota.java,v 1.7 2008/08/14 10:48:30 jua-gome Exp $
 * Universidad de los Andes (Bogotá - Colombia) 
 * Departamento de Ingeniería de Sistemas y Computación 
 * Todos los derechos reservados 2006 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_numeroMvc
 * Autor: Diana Puentes - Jul 29, 2005
 * Modificado por: Juan Erasmo Gómez - 7-Ago-2008  
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.numeroMvc.interfaz.notas;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

/**
 * Clase que permite tocar una nota.
 */
public class ReproductorNota
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Guarda la información de inicialización del reproductor.
     */
    private static boolean inicializado;

    /**
     * Es el sintetizador con el que se reproducen las notas.
     */
    private static Synthesizer synth;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Toca la nota indicada, las notas van de 0 a 120 siendo 60 el Do central, 61 Do#, etc
     * @param nota - valor numérico de la nota a reproducir.
     * @param canal - el instrumento con el que se quiere tocar la nota. Van de 0 a 15. 0, es el piano.
     */
    public static void tocarNota( int nota, int canal )
    {
        // Inicializa si es necesario
        if( !inicializado )
        {
            inicializado = true;
            // obtiene un sintetizador del sistema
            try
            {
                synth = MidiSystem.getSynthesizer( );
                // abre el sintetizador
                synth.open( );
            }
            catch( MidiUnavailableException e )
            {
                e.printStackTrace( );
            }
        }

        final int notaT = nota;
        final int canalT = canal;

        Thread t = new Thread( new Runnable( )
        {
            /**
             * Es el canal que se usa para reproducir las notas.
             */
            private MidiChannel channel;

            public void run( )
            {
                synchronized( synth )
                {
                    // define el instrumento a tocar
                    channel = synth.getChannels( )[ canalT ];
                    // prende la nota indicada en el número dado, con un tiempo de decaimiento promedio
                    channel.noteOn( notaT, 100 );

                    // Realiza una espera para darle duración a la nota
                    try
                    {
                        Thread.yield( );
                        Thread.sleep( 300 );
                    }
                    catch( InterruptedException e )
                    {
                        e.printStackTrace( );
                    }

                    // apaga la nota tocada
                    channel.noteOff( notaT, 100 );
                }
            }
        } );

        t.run( );
    }

}

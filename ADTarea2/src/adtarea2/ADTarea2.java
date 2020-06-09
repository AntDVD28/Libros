/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea2;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author AntDVD
 */
public class ADTarea2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Instanciamos un objeto de tipo VentanaPrincipal
        //es importante indicar que sea visible, en caso contrario no lo veremos
        //el usuario no podrá modificar sus dimensiones y establecemos un título
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
        vp.setResizable(false);
        vp.setTitle("Tarea 2: Acceso a Datos");
        
        
        //Obtenemos la resolución de la pantalla dónde estemos
        Toolkit mipantalla = Toolkit.getDefaultToolkit();    
        Dimension tamanoPantalla = mipantalla.getScreenSize();
        
        int altoPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        
        //Establecemos las dimensiones y localización de nuestra pantalla
        //La ventana principal de nuestra aplicación quedará siempre centrada
        vp.setSize(anchoPantalla/2, altoPantalla/2);  
        vp.setLocation(anchoPantalla/4, altoPantalla/4);
        
        //Incluimos además un icono
        Image miIcono = mipantalla.getImage("src/adtarea2/icono.png");
        vp.setIconImage(miIcono);
    }
    
}

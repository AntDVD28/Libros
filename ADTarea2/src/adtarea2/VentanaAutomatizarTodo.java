/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtarea2;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author AntDVD
 */
public class VentanaAutomatizarTodo extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAutomatizarTodo
     */
    public VentanaAutomatizarTodo() {
        initComponents();
        
        /************************************************************/
        /*              PROPIEDADES DE LA VENTANA                   */
        /************************************************************/
        this.setTitle("Ejemplo de automatizar todas las operaciones");
        
        //Obtenemos la resolución de la pantalla dónde estemos
        Toolkit mipantalla = Toolkit.getDefaultToolkit();    
        Dimension tamanoPantalla = mipantalla.getScreenSize();
        
        int altoPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;
        
        //Establecemos las dimensiones y localización de nuestra pantalla
        //La ventana principal de nuestra aplicación quedará siempre centrada
        this.setSize(anchoPantalla/2, altoPantalla/2);  
        this.setLocation(anchoPantalla/4, altoPantalla/4);
        
        //Incluimos además un icono
        Image miIcono = mipantalla.getImage("src/adtarea2/icono.png");
        this.setIconImage(miIcono);
        /**************************************************************/
                
        try{
            
            //Variable que utilizaremos para mostrar las sentencias por pantalla
            String sentencia="";
                
            Connection conn = conexionBD();
            
            
            if(conn != null){
                
                /************************************************************/
                /*              CREACIÓN DE LA TABLA LIBROS                 */
                /************************************************************/
                jTextArea.append("1. CREACIÓN DE LA TABLA LIBROS");
                jTextArea.append(System.getProperty("line.separator")); // Esto para el salto de línea
                jTextArea.append("Creando tabla...");
                jTextArea.append(System.getProperty("line.separator"));
                
                DatabaseMetaData dbm = conn.getMetaData();

                ResultSet rs = dbm.getTables(null, null, "libros", null); 

                if (rs.next()) { 

                    //La tabla existe procedemos a eliminarla en primera instancia
                    String sql = "DROP TABLE libros";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();
                    
                    //A continuación la creamos
                    sql = "CREATE TABLE libros(id Counter Primary Key, isbn TEXT(17), titulo TEXT(100), autor TEXT(100), num_ejemplares INTEGER, editorial TEXT(100), num_paginas INTEGER)";
                    sentencia = sql;
                    pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();
                    
                }else {

                    //La tabla no existe, procedemos a crearla
                    String sql = "CREATE TABLE libros(id Counter Primary Key, isbn TEXT(17), titulo TEXT(100), autor TEXT(100), num_ejemplares INTEGER, editorial TEXT(100), num_paginas INTEGER)";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();

                }
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Tabla libros creada");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*              CREACIÓN DE LA TABLA SOCIOS                 */
                /************************************************************/
                jTextArea.append("2. CREACIÓN DE LA TABLA SOCIOS");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Creando tabla...");
                jTextArea.append(System.getProperty("line.separator"));
                
                dbm = conn.getMetaData();

                rs = dbm.getTables(null, null, "socios", null); 

                if (rs.next()) { 

                    //La tabla existe procedemos a eliminarla en primera instancia
                    String sql = "DROP TABLE socios";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();
                    
                    //A continuación la creamos
                    sql = "CREATE TABLE socios(id Counter Primary Key, dni TEXT(9), nombre TEXT(50), apellidos TEXT(50), edad INTEGER, direccion TEXT(150), telefono TEXT(12))";
                    sentencia = sql;
                    pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();
                    
                }else {

                    //La tabla no existe, procedemos a crearla
                    String sql = "CREATE TABLE socios(id Counter Primary Key, dni TEXT(9), nombre TEXT(50), apellidos TEXT(50), edad INTEGER, direccion TEXT(150), telefono TEXT(12))";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.execute();
                    pstmt.close();

                }
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Tabla socios creada");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*            INSERTAR DATOS EN LA TABLA LIBROS             */
                /************************************************************/
                jTextArea.append("3. INSERTAR DATOS EN LA TABLA LIBROS");
                jTextArea.append(System.getProperty("line.separator")); 
                jTextArea.append("Insertando datos...");
                jTextArea.append(System.getProperty("line.separator"));
                
                dbm = conn.getMetaData();

                rs = dbm.getTables(null, null, "libros", null); 

                if (rs.next()) { 
                    
                    String isbn;
                    String titulo;
                    String autor;
                    int num_ejemplares;
                    String editorial;
                    int num_paginas;
                    
                    String sql;
                    PreparedStatement pstmt;
                    
                    Libro arrayObjetos[] = new Libro[2];
                    arrayObjetos[0] = new Libro("978-84-98-41451-6", "El mundo de Sofia", "JOSTEIN GAARDER", 100, "SIRUELA", 656);
                    arrayObjetos[1] = new Libro("978-84-08-16318-3 ", "El asesinato de Socrates", "Marcos Chicot", 200, "Editorial Planeta", 768);
                    
                    for (int i=0;i<arrayObjetos.length;i++){
                        
                        isbn = arrayObjetos[i].getIsbn();
                        titulo = arrayObjetos[i].getTitulo();
                        autor = arrayObjetos[i].getAutor();
                        num_ejemplares = arrayObjetos[i].getNum_ejemplares();
                        editorial = arrayObjetos[i].getEditorial();
                        num_paginas = arrayObjetos[i].getNum_paginas();
                        
                        sql = "INSERT into libros(isbn, titulo, autor, num_ejemplares, editorial, num_paginas) values('"+isbn+"', '"+titulo+"', '"+autor+"', '"+num_ejemplares+"', '"+editorial+"', '"+num_paginas+"')";
                        sentencia = sql;
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                        jTextArea.append(sentencia);
                        jTextArea.append(System.getProperty("line.separator"));
                    }
                }    
                
                jTextArea.append("Registros insertados");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*            INSERTAR DATOS EN LA TABLA SOCIOS             */
                /************************************************************/
                jTextArea.append("4. INSERTAR DATOS EN LA TABLA SOCIOS");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Insertando datos...");
                jTextArea.append(System.getProperty("line.separator"));
                dbm = conn.getMetaData();

                rs = dbm.getTables(null, null, "socios", null); 

                if (rs.next()) { 
                    
                    String dni;
                    String nombre;
                    String apellidos;
                    int edad;
                    String direccion;
                    String telefono;
                    
                    String sql;
                    PreparedStatement pstmt;
                    
                    Usuario arrayObjetos[] = new Usuario[2];
                    arrayObjetos[0] = new Usuario("48888888T", "David", "Jimenez Riscardo", 42, "C/Batalla de San Quintin num 7", "+34618888888");
                    arrayObjetos[1] = new Usuario("48999999L", "Valme", "Cotan Gomez", 36, "C/Batalla de San Quintin num 7", "+34696555555");
                    
                    for (int i=0;i<arrayObjetos.length;i++){
                        
                        dni = arrayObjetos[i].getDni();
                        nombre = arrayObjetos[i].getNombre();
                        apellidos = arrayObjetos[i].getApellidos();
                        edad = arrayObjetos[i].getEdad();
                        direccion = arrayObjetos[i].getDireccion();
                        telefono = arrayObjetos[i].getTelefono();
                        
                        sql = "INSERT into socios(dni, nombre, apellidos, edad, direccion, telefono) values('"+dni+"', '"+nombre+"', '"+apellidos+"', '"+edad+"', '"+direccion+"', '"+telefono+"')";
                        sentencia = sql;
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        pstmt.close();
                        jTextArea.append(sentencia);
                        jTextArea.append(System.getProperty("line.separator"));
                    }
                }
                jTextArea.append("Registros insertados");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*            RECUPERAR DATOS DE LA TABLA LIBROS            */
                /************************************************************/
                jTextArea.append("5. RECUPERAR DATOS DE LA TABLA LIBROS");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Recuperando datos...");
                jTextArea.append(System.getProperty("line.separator"));
                Statement stmt = conn.createStatement();
                sentencia = "SELECT * from libros";
                rs = stmt.executeQuery(sentencia);
                stmt.close();
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                
                String id;
                String isbn;
                String titulo;
                String autor;
                String num_ejemplares;
                String editorial;
                String num_paginas;
                
                while (rs.next()) {

                    id = String.valueOf(rs.getInt("id"));
                    isbn = String.valueOf(rs.getString("isbn"));
                    titulo = String.valueOf(rs.getString("titulo"));
                    autor = String.valueOf(rs.getString("autor"));
                    num_ejemplares = String.valueOf(rs.getInt("num_ejemplares"));
                    editorial = String.valueOf(rs.getString("editorial"));
                    num_paginas = String.valueOf(rs.getInt("num_paginas"));
                    
                    jTextArea.append(id+" "+isbn+" "+titulo+" "+autor+" "+num_ejemplares+" "+editorial+" "+num_paginas);    
                    jTextArea.append(System.getProperty("line.separator"));
                }
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*            RECUPERAR DATOS DE LA TABLA SOCIOS            */
                /************************************************************/ 
                jTextArea.append("6. RECUPERAR DATOS DE LA TABLA SOCIOS");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Recuperando datos...");
                jTextArea.append(System.getProperty("line.separator"));
                stmt = conn.createStatement();
                sentencia = "SELECT * from socios";
                rs = stmt.executeQuery(sentencia);
                stmt.close();
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                
                String id_socio;
                String dni;
                String nombre;
                String apellidos;
                String edad;
                String direccion;
                String telefono;
                
                while (rs.next()) {

                    id_socio = String.valueOf(rs.getInt("id"));
                    dni = String.valueOf(rs.getString("dni"));
                    nombre = String.valueOf(rs.getString("nombre"));
                    apellidos = String.valueOf(rs.getString("apellidos"));
                    edad = String.valueOf(rs.getInt("edad"));
                    direccion = String.valueOf(rs.getString("direccion"));
                    telefono = String.valueOf(rs.getString("telefono"));
                    
                    jTextArea.append(id_socio+" "+dni+" "+nombre+" "+apellidos+" "+edad+" "+direccion+" "+telefono);    
                    jTextArea.append(System.getProperty("line.separator"));
                }
                jTextArea.append(System.getProperty("line.separator"));
                
                /************************************************************/
                /*        ACTUALIZAR 1 REGISTRO EN LA TABLA LIBROS          */
                /************************************************************/ 
                jTextArea.append("7. ACTUALIZAR UN REGISTRO EN LA TABLA LIBROS");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Pondré el nombre del autor y editorial del libro 'El mundo de Sofía' en minúsculas con sólo la primera letra en mayúsculas.");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Actualizando...");
                jTextArea.append(System.getProperty("line.separator"));
                stmt = conn.createStatement();
                sentencia = "UPDATE libros SET autor='Jostein Gaarder', editorial='Siruela' WHERE id=1";
                stmt.executeUpdate(sentencia);
                stmt.close();
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Recuperamos el registro de la Base de datos para comprobarlo");
                jTextArea.append(System.getProperty("line.separator"));
                stmt = conn.createStatement();
                sentencia = "SELECT * FROM libros WHERE id=1";
                rs = stmt.executeQuery(sentencia);
                rs.next();
                stmt.close();
                id = String.valueOf(rs.getInt("id"));
                isbn = String.valueOf(rs.getString("isbn"));
                titulo = String.valueOf(rs.getString("titulo"));
                autor = String.valueOf(rs.getString("autor"));
                num_ejemplares = String.valueOf(rs.getInt("num_ejemplares"));
                editorial = String.valueOf(rs.getString("editorial"));
                num_paginas = String.valueOf(rs.getInt("num_paginas"));
                jTextArea.append(id+" "+isbn+" "+titulo+" "+autor+" "+num_ejemplares+" "+editorial+" "+num_paginas);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /***************************************************************/
                /*ACTUALIZAR 1 REGISTRO EN LA TABLA SOCIOS(SENTENCIA PREPARADA)*/
                /***************************************************************/ 
                jTextArea.append("8. ACTUALIZAR UN REGISTRO EN LA TABLA SOCIOS(SENTENCIA PREPARADA)");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Cambiaré los datos de mi mujer por los de mi madre.");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Actualizando...");
                jTextArea.append(System.getProperty("line.separator"));
                String sql = "UPDATE socios SET dni=?, nombre=?, apellidos=?, edad=?, direccion=?, telefono=? WHERE id=?";
                sentencia = sql;
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "28506587L"); 
                pstmt.setString(2, "Manuela");
                pstmt.setString(3, "Riscardo Castro");
                pstmt.setString(4, "65");
                pstmt.setString(5, "C/Rellenadora 37, Pta E");
                pstmt.setString(6, "618882178");
                pstmt.setString(7, "2");
                pstmt.executeUpdate();
                pstmt.close();
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Recuperamos el registro de la Base de datos para comprobarlo");
                jTextArea.append(System.getProperty("line.separator"));
                stmt = conn.createStatement();
                sentencia = "SELECT * FROM socios WHERE id=2";
                rs = stmt.executeQuery(sentencia);
                rs.next();
                stmt.close();
                id = String.valueOf(rs.getInt("id"));
                dni = String.valueOf(rs.getString("dni"));
                nombre = String.valueOf(rs.getString("nombre"));
                apellidos = String.valueOf(rs.getString("apellidos"));
                edad = String.valueOf(rs.getInt("edad"));
                direccion = String.valueOf(rs.getString("direccion"));
                telefono = String.valueOf(rs.getString("telefono"));
                jTextArea.append(id+" "+dni+" "+nombre+" "+apellidos+" "+edad+" "+direccion+" "+telefono);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
                /***************************************************************/
                /*ACTUALIZAR 1 REGISTRO EN LA TABLA LIBROS(SENTENCIA PREPARADA)*/
                /***************************************************************/ 
                jTextArea.append("9. ACTUALIZAR UN REGISTRO EN LA TABLA LIBROS(SENTENCIA PREPARADA)");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Cambiaré los datos del libro 'El mundo de Sofia' por los datos del libro 'El principito'");
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Actualizando...");
                jTextArea.append(System.getProperty("line.separator"));
                sql = "UPDATE libros SET isbn=?, titulo=?, autor=?, num_ejemplares=?, editorial=?, num_paginas=? WHERE id=?";
                sentencia = sql;
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "978-84-78-88719-4"); 
                pstmt.setString(2, "El principito");
                pstmt.setString(3, "ANTOINE DE SAINT-EXUPERY");
                pstmt.setString(4, "150");
                pstmt.setString(5, "Salamandra");
                pstmt.setString(6, "96");
                pstmt.setString(7, "1");
                pstmt.executeUpdate();
                pstmt.close();
                jTextArea.append(sentencia);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append("Recuperamos el registro de la Base de datos para comprobarlo");
                jTextArea.append(System.getProperty("line.separator"));
                stmt = conn.createStatement();
                sentencia = "SELECT * FROM libros WHERE id=1";
                rs = stmt.executeQuery(sentencia);
                rs.next();
                stmt.close();
                id = String.valueOf(rs.getInt("id"));
                isbn = String.valueOf(rs.getString("isbn"));
                titulo = String.valueOf(rs.getString("titulo"));
                autor = String.valueOf(rs.getString("autor"));
                num_ejemplares = String.valueOf(rs.getInt("num_ejemplares"));
                editorial = String.valueOf(rs.getString("editorial"));
                num_paginas = String.valueOf(rs.getInt("num_paginas"));
                jTextArea.append(id+" "+isbn+" "+titulo+" "+autor+" "+num_ejemplares+" "+editorial+" "+num_paginas);
                jTextArea.append(System.getProperty("line.separator"));
                jTextArea.append(System.getProperty("line.separator"));
                
            }//fin del if
            
            conn.close();
            
        }catch(SQLException e){
            
            System.out.println("SQL Exception: "+ e.toString());
        }
   
    }
    
    public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
         } catch (Exception e) {
            System.out.println(e);
         }
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButtonVolver = new javax.swing.JButton();
        jLabelConexion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVolver)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVolver))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        this.setVisible(false);
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
       
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private Connection conexionBD(){
        
        try {
            // Obtener la conexión
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://Bibliooteca.accdb");
            
            if(conn != null){
                
                jLabelConexion.setText("Conexión ESTABLECIDA con la Base de Datos");  
                
                return conn;
                
            }        
            
        } catch (SQLException e) {
             System.out.println("SQL Exception: "+ e.toString());
             jLabelConexion.setText("Conexión NO ESTABLECIDA con la Base de Datos");        
        } 
         return null;              
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAutomatizarTodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAutomatizarTodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAutomatizarTodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAutomatizarTodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAutomatizarTodo().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelConexion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables
}

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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AntDVD
 */
public class VentanaLibros extends javax.swing.JFrame {

   //Objeto que sirve de puente para agregar datos al objeto jTable
    DefaultTableModel dtm;
    //Array de tipo Objeto que utilizaremos para ayudarnos a rescatar la información introducida en los textFields y pasarla al DefaultTableModel
    Object[] o = new Object[7];
    //Variable auxiliar para saber si hemos seleccionado una fila en el jTable
    int filaSeleccionada = -1;
    
    /**
     * Creates new form VentanaSocios
     */
    public VentanaLibros() {
        initComponents();
        this.setTitle("Tabla Libros");
        
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
        
        mostrarTabla();
    }
    
    //Método para limpiar todos los textfields y devolver el foco al primero de ellos
    public void limpiar() {
        jTextFieldIsbn.setText("");
        jTextFieldTitulo.setText("");
        jTextFieldAutor.setText("");
        jTextFieldNumEjemplares.setText("");
        jTextFieldEditorial.setText("");
        jTextFieldNumPaginas.setText("");
        jTextFieldIdLibro.setText("");
        jTextFieldIsbn.grabFocus();
    }
    
    private void mostrarTabla(){
        
        try {
            
            Connection conn = conexionBD();
           
            if(conn != null){
                
                // La clase Statement contiene los métodos executeQuery y
                //executeUpdate para realizar consultas y actualizaciones
                Statement stmt = conn.createStatement();

                //El método executeQuery devuelve un objeto ResultSet para poder
                // recorrer el resultado de la consulta utilizando un cursor.
                ResultSet rs = stmt.executeQuery("SELECT * from libros");
                
                stmt.close();
                conn.close();

                //Instanciamos un objeto de tipo DefaultTableModel y lo seteamos en el jTable     
                dtm = new DefaultTableModel();
                jTableLibros.setModel(dtm);

                 //Creamos array de Strings el cual inicializamos con el título de las columnas
                String arrayColumnas[] = {"Id","ISBN", "Título", "Autor", "Num.Ejemplares", "Editorial", "Num.Páginas"};     
                dtm.setColumnIdentifiers(arrayColumnas);

                 while (rs.next()) {

                    //Por cada línea creamos un array de objetos, dicho array lo agregaremos al objeto de tipo DefaultTableModel             
                    Object arrayLinea[] = {rs.getInt("id"), rs.getString("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("num_ejemplares"), rs.getString("editorial"), rs.getInt("num_paginas")};

                    dtm.addRow(arrayLinea);
                }

                 //Creamos un objeto para darle estilo a la tabla, sólo hemos centrado el contenido de varias celdas
                DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                jTableLibros.getColumnModel().getColumn(0).setCellRenderer(dtcr);
                jTableLibros.getColumnModel().getColumn(1).setCellRenderer(dtcr);
                jTableLibros.getColumnModel().getColumn(4).setCellRenderer(dtcr);
                jTableLibros.getColumnModel().getColumn(6).setCellRenderer(dtcr);
            }
        
        } catch (SQLException e) {
            
            System.out.println("SQL Exception: "+ e.toString());
            
        }     
    }
    
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLibros = new javax.swing.JPanel();
        jScrollPaneLibros = new javax.swing.JScrollPane();
        jTableLibros = new javax.swing.JTable();
        jLabelIsbn = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelAutor = new javax.swing.JLabel();
        jLabelNumEjemplares = new javax.swing.JLabel();
        jLabelEditorial = new javax.swing.JLabel();
        jLabelNumPaginas = new javax.swing.JLabel();
        jTextFieldIsbn = new javax.swing.JTextField();
        jTextFieldTitulo = new javax.swing.JTextField();
        jTextFieldAutor = new javax.swing.JTextField();
        jTextFieldNumEjemplares = new javax.swing.JTextField();
        jTextFieldEditorial = new javax.swing.JTextField();
        jTextFieldNumPaginas = new javax.swing.JTextField();
        jButtonAniadirLibro = new javax.swing.JButton();
        jButtonEliminarLibro = new javax.swing.JButton();
        jButtonModificarLibro = new javax.swing.JButton();
        jButtonSeleccionarLibro = new javax.swing.JButton();
        jButtonLimpiarLibro = new javax.swing.JButton();
        jButtonVolverLibro = new javax.swing.JButton();
        jTextFieldIdLibro = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();
        jPanelPie = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelConexion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "ISBN", "Titulo", "Autor ", "Num.ejemplares", "Editorial", "Num.Paginas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneLibros.setViewportView(jTableLibros);

        jLabelIsbn.setText("ISBN:");

        jLabelTitulo.setText("Titulo:");

        jLabelAutor.setText("Autor:");

        jLabelNumEjemplares.setText("Num.ejemplares:");

        jLabelEditorial.setText("Editorial:");

        jLabelNumPaginas.setText("Num.paginas:");

        jButtonAniadirLibro.setText("Añadir");
        jButtonAniadirLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAniadirLibroActionPerformed(evt);
            }
        });

        jButtonEliminarLibro.setText("Eliminar");
        jButtonEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarLibroActionPerformed(evt);
            }
        });

        jButtonModificarLibro.setText("Modificar");
        jButtonModificarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarLibroActionPerformed(evt);
            }
        });

        jButtonSeleccionarLibro.setText("Seleccionar");
        jButtonSeleccionarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarLibroActionPerformed(evt);
            }
        });

        jButtonLimpiarLibro.setText("Limpiar");
        jButtonLimpiarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarLibroActionPerformed(evt);
            }
        });

        jButtonVolverLibro.setText("Volver");
        jButtonVolverLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverLibroActionPerformed(evt);
            }
        });

        jTextFieldIdLibro.setEditable(false);
        jTextFieldIdLibro.setFocusable(false);

        jLabelId.setText("Id:");

        javax.swing.GroupLayout jPanelLibrosLayout = new javax.swing.GroupLayout(jPanelLibros);
        jPanelLibros.setLayout(jPanelLibrosLayout);
        jPanelLibrosLayout.setHorizontalGroup(
            jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLibrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLibros)
                .addContainerGap())
            .addGroup(jPanelLibrosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLibrosLayout.createSequentialGroup()
                        .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIsbn)
                            .addComponent(jLabelTitulo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelLibrosLayout.createSequentialGroup()
                        .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAutor)
                            .addComponent(jLabelId))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumEjemplares)
                    .addComponent(jLabelEditorial)
                    .addComponent(jLabelNumPaginas))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldNumEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAniadirLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificarLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jButtonLimpiarLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSeleccionarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVolverLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62))
        );
        jPanelLibrosLayout.setVerticalGroup(
            jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLibrosLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPaneLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIsbn)
                    .addComponent(jLabelNumEjemplares)
                    .addComponent(jTextFieldNumEjemplares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAniadirLibro)
                    .addComponent(jButtonEliminarLibro))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSeleccionarLibro)
                        .addComponent(jButtonModificarLibro))
                    .addComponent(jTextFieldEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEditorial)
                        .addComponent(jLabelTitulo)
                        .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolverLibro)
                    .addComponent(jButtonLimpiarLibro)
                    .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAutor)
                        .addComponent(jTextFieldAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelNumPaginas)
                        .addComponent(jTextFieldNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIdLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelId))
                .addGap(0, 64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelLibros, java.awt.BorderLayout.PAGE_START);

        jPanelPie.setPreferredSize(new java.awt.Dimension(580, 44));

        jLabel1.setText("David Jiménez Riscardo - 2019");

        javax.swing.GroupLayout jPanelPieLayout = new javax.swing.GroupLayout(jPanelPie);
        jPanelPie.setLayout(jPanelPieLayout);
        jPanelPieLayout.setHorizontalGroup(
            jPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPieLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 511, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );
        jPanelPieLayout.setVerticalGroup(
            jPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPieLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(jLabelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanelPie, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverLibroActionPerformed
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
        
    }//GEN-LAST:event_jButtonVolverLibroActionPerformed

    private void jButtonAniadirLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAniadirLibroActionPerformed
        if( !jTextFieldIsbn.getText().equals("") && 
            !jTextFieldTitulo.getText().equals("") &&
            !jTextFieldAutor.getText().equals("") &&    
            !jTextFieldNumEjemplares.getText().equals("") &&    
            !jTextFieldEditorial.getText().equals("") && 
            !jTextFieldNumPaginas.getText().equals("")){
            
            //Debemos de conocer el número de registros que contiene la tabla, pues deberemos de numerar en el jTable el nuevo registro
            int num_registros = 0;
            
            try {
                Connection conn = conexionBD();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from libros");
                while (rs.next()){
                   num_registros++;
                }
                stmt.close();
                conn.close();     
            }catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());

            } 
            
            //Instancio un objeto para poderlo agregar al JTable 
            //Para asignar un id en el JTable, debo de conocer el número de registros que tiene la Base de Datos y sumarle una unidad
            o[0] = num_registros + 1;
            o[1] = jTextFieldIsbn.getText();
            o[2] = jTextFieldTitulo.getText();
            o[3] = jTextFieldAutor.getText();
            o[4] = jTextFieldNumEjemplares.getText();
            o[5] = jTextFieldEditorial.getText();
            o[6] = jTextFieldNumPaginas.getText();
            dtm.addRow(o);

            //Limpiamos el contenido de los textfield
            limpiar();
            
            //Insertamos los datos en la BD
            try {
                Connection conn = conexionBD();
                String sql = "INSERT into libros(isbn, titulo, autor, num_ejemplares, editorial, num_paginas) values (?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                //Debemos de hacer un cast del objeto pues debemos de pasar un String
                pstmt.setString(1, (String) o[1]);
                pstmt.setString(2, (String) o[2]);
                pstmt.setString(3, (String) o[3]);
                pstmt.setString(4, (String) o[4]);
                pstmt.setString(5, (String) o[5]);
                pstmt.setString(6, (String) o[6]);
                pstmt.executeUpdate();

                pstmt.close();
                conn.close();

            }catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());

            } 

            //Vaciamos el objeto
            for (int i = 0; i < o.length; i++) {
                o[i] = "";
            }

            //Mostramos un alert indicando que la operación se realizó correctamente
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente.");
            
        }else {
            
            JOptionPane.showMessageDialog(null, "Debe de rellenar todos los campos de texto.");
        }
    }//GEN-LAST:event_jButtonAniadirLibroActionPerformed

    private void jButtonSeleccionarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarLibroActionPerformed
        filaSeleccionada = jTableLibros.getSelectedRow();
              
        if (filaSeleccionada != -1) {
            limpiar();
            jTextFieldIdLibro.setText(jTableLibros.getValueAt(filaSeleccionada, 0).toString());
            jTextFieldIsbn.setText(jTableLibros.getValueAt(filaSeleccionada, 1).toString());
            jTextFieldTitulo.setText(jTableLibros.getValueAt(filaSeleccionada, 2).toString());
            jTextFieldAutor.setText(jTableLibros.getValueAt(filaSeleccionada, 3).toString());
            jTextFieldNumEjemplares.setText(jTableLibros.getValueAt(filaSeleccionada, 4).toString());
            jTextFieldEditorial.setText(jTableLibros.getValueAt(filaSeleccionada, 5).toString());
            jTextFieldNumPaginas.setText(jTableLibros.getValueAt(filaSeleccionada, 6).toString());
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccinado una fila");
        }
    }//GEN-LAST:event_jButtonSeleccionarLibroActionPerformed

    private void jButtonLimpiarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarLibroActionPerformed
        limpiar();
    }//GEN-LAST:event_jButtonLimpiarLibroActionPerformed

    private void jButtonModificarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarLibroActionPerformed
        
        if (filaSeleccionada != -1) {
             
            //Actualizo el jTable con la nueva información 
            jTableLibros.setValueAt(jTextFieldIdLibro.getText(), filaSeleccionada, 0);
            jTableLibros.setValueAt(jTextFieldIsbn.getText(), filaSeleccionada, 1);
            jTableLibros.setValueAt(jTextFieldTitulo.getText(), filaSeleccionada, 2);
            jTableLibros.setValueAt(jTextFieldAutor.getText(), filaSeleccionada, 3);
            jTableLibros.setValueAt(jTextFieldNumEjemplares.getText(), filaSeleccionada, 4);
            jTableLibros.setValueAt(jTextFieldEditorial.getText(), filaSeleccionada, 5);
            jTableLibros.setValueAt(jTextFieldNumPaginas.getText(), filaSeleccionada, 6);
            
            filaSeleccionada = -1;
            
            
            //Actualizamos el registro en la BD
            try {
                
                Connection conn = conexionBD();
                
                if(conn != null){
                    
                    String sql = "UPDATE libros SET isbn=?, titulo=?, autor=?, num_ejemplares=?, editorial=?, num_paginas=? WHERE id=?";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, (String) jTextFieldIsbn.getText()); 
                    pstmt.setString(2, (String) jTextFieldTitulo.getText());
                    pstmt.setString(3, (String) jTextFieldAutor.getText());
                    pstmt.setString(4, (String) jTextFieldNumEjemplares.getText());
                    pstmt.setString(5, (String) jTextFieldEditorial.getText());
                    pstmt.setString(6, (String) jTextFieldNumPaginas.getText());
                    pstmt.setString(7, (String) jTextFieldIdLibro.getText());
                    
                    pstmt.executeUpdate();

                    pstmt.close();
                    conn.close();          
                }
                   
            }catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            
            }
            limpiar();
            //Mostramos un alert indicando que la operación se realizó correctamente
            JOptionPane.showMessageDialog(null, "Registro modificado correctamente.");
            
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionado una fila");
        }
    }//GEN-LAST:event_jButtonModificarLibroActionPerformed

    private void jButtonEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarLibroActionPerformed
        
        if (filaSeleccionada != -1) {
            
            dtm.removeRow(jTableLibros.getSelectedRow());
            filaSeleccionada = -1;
            
            try {
                
                Connection conn = conexionBD();
                
                if(conn != null) {
                    
                    String sql = "DELETE FROM libros WHERE id=?";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, (String) jTextFieldIdLibro.getText());

                    pstmt.executeUpdate();

                    pstmt.close();
                    conn.close();
                    
                }
                
            }catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            
            }
            limpiar();
            //Mostramos un alert indicando que la operación se realizó correctamente
            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
            
         } else {
             
            JOptionPane.showMessageDialog(null, "No has seleccionado una fila");
         }
        
    }//GEN-LAST:event_jButtonEliminarLibroActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaLibros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaLibros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAniadirLibro;
    private javax.swing.JButton jButtonEliminarLibro;
    private javax.swing.JButton jButtonLimpiarLibro;
    private javax.swing.JButton jButtonModificarLibro;
    private javax.swing.JButton jButtonSeleccionarLibro;
    private javax.swing.JButton jButtonVolverLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelConexion;
    private javax.swing.JLabel jLabelEditorial;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelIsbn;
    private javax.swing.JLabel jLabelNumEjemplares;
    private javax.swing.JLabel jLabelNumPaginas;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelLibros;
    private javax.swing.JPanel jPanelPie;
    private javax.swing.JScrollPane jScrollPaneLibros;
    private javax.swing.JTable jTableLibros;
    private javax.swing.JTextField jTextFieldAutor;
    private javax.swing.JTextField jTextFieldEditorial;
    private javax.swing.JTextField jTextFieldIdLibro;
    private javax.swing.JTextField jTextFieldIsbn;
    private javax.swing.JTextField jTextFieldNumEjemplares;
    private javax.swing.JTextField jTextFieldNumPaginas;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}

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
public class VentanaSocios extends javax.swing.JFrame {

   //Objeto que sirve de puente para agregar datos al objeto jTable
    DefaultTableModel dtm;
    //Array de tipo Objeto que utilizaremos para ayudarnos a rescatar la información introducida en los textFields y pasarla al DefaultTableModel
    Object[] o = new Object[7];
    //Variable auxiliar para saber si hemos seleccionado una fila en el jTable
    int filaSeleccionada = -1;
    
    /**
     * Creates new form VentanaSocios
     */
    public VentanaSocios() {
        initComponents();
        this.setTitle("Tabla Socios");
        
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
        jTextFieldDni.setText("");
        jTextFieldNombre.setText("");
        jTextFieldApellidos.setText("");
        jTextFieldEdad.setText("");
        jTextFieldDireccion.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldIdSocio.setText("");
        jTextFieldDni.grabFocus();
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
                ResultSet rs = stmt.executeQuery("SELECT * from socios");
                
                stmt.close();
                conn.close();

                //Instanciamos un objeto de tipo DefaultTableModel y lo seteamos en el jTable     
                dtm = new DefaultTableModel();
                jTableSocios.setModel(dtm);

                 //Creamos array de Strings el cual inicializamos con el título de las columnas
                String arrayColumnas[] = {"Id", "DNI", "Nombre", "Apellidos", "Edad", "Dirección", "Teléfono"};     
                dtm.setColumnIdentifiers(arrayColumnas);

                 while (rs.next()) {

                    //Por cada línea creamos un array de objetos, dicho array lo agregaremos al objeto de tipo DefaultTableModel             
                    Object arrayLinea[] = {rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("edad"), rs.getString("direccion"), rs.getString("telefono")};

                    dtm.addRow(arrayLinea);
                }

                 //Creamos un objeto para darle estilo a la tabla, sólo hemos centrado el contenido de varias celdas
                DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                jTableSocios.getColumnModel().getColumn(0).setCellRenderer(dtcr);
                jTableSocios.getColumnModel().getColumn(1).setCellRenderer(dtcr);
                jTableSocios.getColumnModel().getColumn(4).setCellRenderer(dtcr);
                jTableSocios.getColumnModel().getColumn(6).setCellRenderer(dtcr);
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

        jPanelSocios = new javax.swing.JPanel();
        jScrollPaneLibros = new javax.swing.JScrollPane();
        jTableSocios = new javax.swing.JTable();
        jLabelDni = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelAutor = new javax.swing.JLabel();
        jLabelEdad = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldDni = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldApellidos = new javax.swing.JTextField();
        jTextFieldEdad = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jButtonAniadirSocio = new javax.swing.JButton();
        jButtonEliminarSocio = new javax.swing.JButton();
        jButtonModificarSocio = new javax.swing.JButton();
        jButtonSeleccionarSocio = new javax.swing.JButton();
        jButtonLimpiarSocio = new javax.swing.JButton();
        jButtonVolverSocio = new javax.swing.JButton();
        jLabelId = new javax.swing.JLabel();
        jTextFieldIdSocio = new javax.swing.JTextField();
        jPanelPie = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelConexion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Column 1", "Column 2", "Column 3", "Column 4", "Column 5", "Column 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPaneLibros.setViewportView(jTableSocios);

        jLabelDni.setText("DNI:");

        jLabelTitulo.setText("Nombre:");

        jLabelAutor.setText("Apellidos:");

        jLabelEdad.setText("Edad:");

        jLabelDireccion.setText("Direccion:");

        jLabelTelefono.setText("Teléfono:");

        jButtonAniadirSocio.setText("Añadir");
        jButtonAniadirSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAniadirSocioActionPerformed(evt);
            }
        });

        jButtonEliminarSocio.setText("Eliminar");
        jButtonEliminarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarSocioActionPerformed(evt);
            }
        });

        jButtonModificarSocio.setText("Modificar");
        jButtonModificarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarSocioActionPerformed(evt);
            }
        });

        jButtonSeleccionarSocio.setText("Seleccionar");
        jButtonSeleccionarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarSocioActionPerformed(evt);
            }
        });

        jButtonLimpiarSocio.setText("Limpiar");
        jButtonLimpiarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarSocioActionPerformed(evt);
            }
        });

        jButtonVolverSocio.setText("Volver");
        jButtonVolverSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverSocioActionPerformed(evt);
            }
        });

        jLabelId.setText("Id:");

        jTextFieldIdSocio.setEditable(false);
        jTextFieldIdSocio.setFocusable(false);

        javax.swing.GroupLayout jPanelSociosLayout = new javax.swing.GroupLayout(jPanelSocios);
        jPanelSocios.setLayout(jPanelSociosLayout);
        jPanelSociosLayout.setHorizontalGroup(
            jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSociosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLibros)
                .addContainerGap())
            .addGroup(jPanelSociosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSociosLayout.createSequentialGroup()
                        .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDni)
                            .addComponent(jLabelTitulo))
                        .addGap(23, 23, 23)
                        .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelSociosLayout.createSequentialGroup()
                        .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAutor)
                            .addComponent(jLabelId))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(41, 41, 41)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEdad)
                    .addComponent(jLabelDireccion)
                    .addComponent(jLabelTelefono))
                .addGap(18, 18, 18)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 303, Short.MAX_VALUE)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAniadirSocio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModificarSocio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jButtonLimpiarSocio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSeleccionarSocio, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jButtonEliminarSocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVolverSocio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62))
        );
        jPanelSociosLayout.setVerticalGroup(
            jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSociosLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPaneLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDni)
                    .addComponent(jLabelEdad)
                    .addComponent(jTextFieldEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAniadirSocio)
                    .addComponent(jButtonEliminarSocio))
                .addGap(18, 18, 18)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSeleccionarSocio)
                        .addComponent(jButtonModificarSocio))
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelDireccion)
                        .addComponent(jLabelTitulo)
                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVolverSocio)
                    .addComponent(jButtonLimpiarSocio)
                    .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAutor)
                        .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTelefono)
                        .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanelSociosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextFieldIdSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 67, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelSocios, java.awt.BorderLayout.PAGE_START);

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
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jLabelConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanelPie, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverSocioActionPerformed
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
    }//GEN-LAST:event_jButtonVolverSocioActionPerformed

    private void jButtonAniadirSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAniadirSocioActionPerformed
        if( !jTextFieldDni.getText().equals("") && 
            !jTextFieldNombre.getText().equals("") &&
            !jTextFieldApellidos.getText().equals("") &&    
            !jTextFieldEdad.getText().equals("") &&    
            !jTextFieldDireccion.getText().equals("") && 
            !jTextFieldTelefono.getText().equals("")){
            
            //Debemos de conocer el número de registros que contiene la tabla, pues deberemos de numerar en el jTable el nuevo registro
            int num_registros = 0;
            
            try {
                Connection conn = conexionBD();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from socios");
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
            o[1] = jTextFieldDni.getText();
            o[2] = jTextFieldNombre.getText();
            o[3] = jTextFieldApellidos.getText();
            o[4] = jTextFieldEdad.getText();
            o[5] = jTextFieldDireccion.getText();
            o[6] = jTextFieldTelefono.getText();
            dtm.addRow(o);

            //Limpiamos el contenido de los textfield
            limpiar();
            
            //Insertamos los datos en la BD
            try {
                Connection conn = conexionBD();
                String sql = "INSERT into socios(dni, nombre, apellidos, edad, direccion, telefono) values (?, ?, ?, ?, ?, ?)";

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
    }//GEN-LAST:event_jButtonAniadirSocioActionPerformed

    private void jButtonSeleccionarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarSocioActionPerformed
        filaSeleccionada = jTableSocios.getSelectedRow();
              
        if (filaSeleccionada != -1) {
            limpiar();
            jTextFieldIdSocio.setText(jTableSocios.getValueAt(filaSeleccionada, 0).toString());
            jTextFieldDni.setText(jTableSocios.getValueAt(filaSeleccionada, 1).toString());
            jTextFieldNombre.setText(jTableSocios.getValueAt(filaSeleccionada, 2).toString());
            jTextFieldApellidos.setText(jTableSocios.getValueAt(filaSeleccionada, 3).toString());
            jTextFieldEdad.setText(jTableSocios.getValueAt(filaSeleccionada, 4).toString());
            jTextFieldDireccion.setText(jTableSocios.getValueAt(filaSeleccionada, 5).toString());
            jTextFieldTelefono.setText(jTableSocios.getValueAt(filaSeleccionada, 6).toString());
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccinado una fila");
        }
    }//GEN-LAST:event_jButtonSeleccionarSocioActionPerformed

    private void jButtonLimpiarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarSocioActionPerformed
        limpiar();
    }//GEN-LAST:event_jButtonLimpiarSocioActionPerformed

    private void jButtonModificarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarSocioActionPerformed
        if (filaSeleccionada != -1) {
             
            //Actualizo el jTable con la nueva información 
            jTableSocios.setValueAt(jTextFieldIdSocio.getText(), filaSeleccionada, 0);
            jTableSocios.setValueAt(jTextFieldDni.getText(), filaSeleccionada, 1);
            jTableSocios.setValueAt(jTextFieldNombre.getText(), filaSeleccionada, 2);
            jTableSocios.setValueAt(jTextFieldApellidos.getText(), filaSeleccionada, 3);
            jTableSocios.setValueAt(jTextFieldEdad.getText(), filaSeleccionada, 4);
            jTableSocios.setValueAt(jTextFieldDireccion.getText(), filaSeleccionada, 5);
            jTableSocios.setValueAt(jTextFieldTelefono.getText(), filaSeleccionada, 6);
            
            filaSeleccionada = -1;
            
            
            //Actualizamos el registro en la BD
            try {
                
                Connection conn = conexionBD();
                
                if(conn != null){
                    
                    String sql = "UPDATE socios SET dni=?, nombre=?, apellidos=?, edad=?, direccion=?, telefono=? WHERE id=?";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, (String) jTextFieldDni.getText()); 
                    pstmt.setString(2, (String) jTextFieldNombre.getText());
                    pstmt.setString(3, (String) jTextFieldApellidos.getText());
                    pstmt.setString(4, (String) jTextFieldEdad.getText());
                    pstmt.setString(5, (String) jTextFieldDireccion.getText());
                    pstmt.setString(6, (String) jTextFieldTelefono.getText());
                    pstmt.setString(7, (String) jTextFieldIdSocio.getText());
                    
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
    }//GEN-LAST:event_jButtonModificarSocioActionPerformed

    private void jButtonEliminarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarSocioActionPerformed
        
        if (filaSeleccionada != -1) {
            
            dtm.removeRow(jTableSocios.getSelectedRow());
            filaSeleccionada = -1;
            
            try {
                
                Connection conn = conexionBD();
                
                if(conn != null) {
                    
                    String sql = "DELETE FROM socios WHERE id=?";

                    PreparedStatement pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, (String) jTextFieldIdSocio.getText());

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
        
    }//GEN-LAST:event_jButtonEliminarSocioActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSocios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSocios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAniadirSocio;
    private javax.swing.JButton jButtonEliminarSocio;
    private javax.swing.JButton jButtonLimpiarSocio;
    private javax.swing.JButton jButtonModificarSocio;
    private javax.swing.JButton jButtonSeleccionarSocio;
    private javax.swing.JButton jButtonVolverSocio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAutor;
    private javax.swing.JLabel jLabelConexion;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDni;
    private javax.swing.JLabel jLabelEdad;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelPie;
    private javax.swing.JPanel jPanelSocios;
    private javax.swing.JScrollPane jScrollPaneLibros;
    private javax.swing.JTable jTableSocios;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldDni;
    private javax.swing.JTextField jTextFieldEdad;
    private javax.swing.JTextField jTextFieldIdSocio;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}

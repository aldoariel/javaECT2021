package jdbc.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import jdbc.controlador.ClienteDAOPostgreSQL;
import jdbc.modelo.Cliente;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaCliente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfcodigo;
	private JTextField tfnombre;
	
	private String abrir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaCliente frame = new ConsultaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultaCliente() {
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		scrollPane.setBounds(10, 11, 328, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionarRegistro(abrir);
			}
		});
		scrollPane.setViewportView(table);
		
		
		cargarDatos();
//		table.setModel(model);	
		
		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		btnNewButton.setBounds(366, 96, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		btnBorrar.setBounds(366, 228, 89, 23);
		contentPane.add(btnBorrar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnGuardar.setBounds(493, 403, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
				cargarDatos();
			}
		});
		btnCancelar.setBounds(584, 403, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnSalir.setBounds(675, 403, 89, 23);
		contentPane.add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBounds(468, 11, 420, 384);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(10, 92, 46, 14);
		panel.add(lblCodigo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 159, 46, 14);
		panel.add(lblNombre);
		
		tfcodigo = new JTextField();
		tfcodigo.setEnabled(false);
		tfcodigo.setBounds(81, 89, 86, 20);
		panel.add(tfcodigo);
		tfcodigo.setColumns(10);
		
		tfnombre = new JTextField();
		tfnombre.setBounds(81, 156, 308, 20);
		panel.add(tfnombre);
		tfnombre.setColumns(10);
	}
	
	private void cargarDatos(){
		TableJTable model = new TableJTable();
		
		List<Cliente> lc = new ClienteDAOPostgreSQL().buscarTodos();
		//List<Cliente> lc = new ClienteDAOMySQL().buscarTodos();
		//columnas
		String[] s = {"Id","Nombre"};
		model.setNombreColumna( s );
		//registros
		List<String[]> r = new ArrayList<String[]>();	
		for (Cliente cliente : lc) {
			String[] registros = {cliente.getCodigo().toString(),cliente.getNombre()};
			//String[] registros = {cliente.getNombre()};
			r.add(registros);
		}
		//cargar
		model.setRegistros(r);	
		
		table.setModel(model);
	}
	
	private void seleccionarRegistro(String abrir) {
		// TODO Auto-generated method stub
		Integer fila = this.table.getSelectedRow();
		String xcodigo = String.valueOf(this.table.getValueAt( fila, 0 ));
		String xnombre = String.valueOf(this.table.getValueAt( fila, 1 ));

		this.tfcodigo.setText( xcodigo );  //cargamos el valor de dato en el campo codigo
		tfnombre.setText( xnombre );  //cargamos el valor de dato en el campo codigo

		
	}
	
	private void nuevo() {
		tfcodigo.setText( "" );  //cargamos el valor de dato en el campo codigo
		tfnombre.setText( "" );  //cargamos el valor de dato en el campo codigo
	}
	
	private void borrar(){
		Cliente cliente = new Cliente();
		//si el codigo es celro
		if( this.tfcodigo.getText().length() == 0 ){
			cliente.setCodigo(0L);
		}else{
			cliente.setCodigo((long) Integer.parseInt(this.tfcodigo.getText()) );
		}	
		
		//validar
		if(this.tfcodigo.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "Seleccione un registro", "Aviso", 1);
		}else{
		//borrar
			new ClienteDAOPostgreSQL().borrar(cliente.getCodigo());
			JOptionPane.showMessageDialog(null, "Registro ha sido borrado", "Exito", 1);
		}

		//cargar la tabla
		this.cargarDatos();
		
		nuevo();
		
	}



	private void guardar(){
		Cliente cliente = new Cliente();
		//si el codigo es celro
		if( this.tfcodigo.getText().length() == 0 ){
			cliente.setCodigo(0L);
		}else{
			cliente.setCodigo((long) Integer.parseInt(this.tfcodigo.getText()) );
		}
		cliente.setNombre(this.tfnombre.getText());
		
		//validar
		if(this.tfnombre.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "Nombre es obligatorio", "Aviso", 1);
			this.tfnombre.requestFocus();
		}else{
			//guardar
			boolean esNuevo = cliente.getCodigo()==0;
			if(esNuevo){
				//insertar
				new ClienteDAOPostgreSQL().insertar(cliente);	
				JOptionPane.showMessageDialog(null, "Registro ha sido guardado", "Exito", 1);
			}else{
				//modificar
				new ClienteDAOPostgreSQL().modificar(cliente);
				JOptionPane.showMessageDialog(null, "Registro ha sido guardado", "Exito", 1);
			}
			
			//cargar la tabla
			this.cargarDatos();
		}
		
	}
}

package jdbc.test;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import jdbc.controlador.ClienteDAOPostgreSQL;
import jdbc.controlador.ClienteDAO;
import jdbc.modelo.Cliente;

public class VerClientes {

	public static void main(String[] args) {
		
		// elegir opcion

		 String[] opciones = {
		            "Buscar por codigo",
		            "Insertar",
		            "Modificar",
		            "Borrar",
		            "Listar todos",
		            "Listar por filtros"
		        };

		        String resp = (String) JOptionPane.showInputDialog(null, "Seleccione una opcion", "Opciones", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
		        
		        
		//buscar    	
		if(resp=="Buscar por codigo"){
			
			
			String codigo = JOptionPane.showInputDialog(null, "Codigo del registro"
					, "Buscar", JOptionPane.QUESTION_MESSAGE);
			
						
			//forma 1 de conectar
			Cliente cliente1 = new Cliente();
			cliente1.setCodigo(Long.parseLong(codigo));
			
			ClienteDAO controladorCliente1 = new ClienteDAOPostgreSQL();
			cliente1 = controladorCliente1.buscarPorCodigo(cliente1.getCodigo());
			
			if(cliente1 != null){
				JOptionPane.showMessageDialog(null, cliente1, "Registro", JOptionPane.DEFAULT_OPTION, null);		
				System.out.println(cliente1);
			}

			
			
			//forma 2 de conectar
			Cliente cliente2 = new Cliente();
			cliente2 = new ClienteDAOPostgreSQL().buscarPorCodigo(Long.parseLong(codigo));
			
			if(cliente2 != null){
				JOptionPane.showMessageDialog(null, cliente2, "Registro", JOptionPane.INFORMATION_MESSAGE, null);	
				System.out.println(cliente2);
			}
						
			
		}
				        
						        
	
		// Insertar
		if(resp=="Insertar"){
			
			String nombre = JOptionPane.showInputDialog(null, "Nombre del registro"
					, "Nuevo", JOptionPane.DEFAULT_OPTION);
			
			if (nombre != null) {
				//insertar
				Cliente cliente3 = new Cliente(0L, nombre);
				new ClienteDAOPostgreSQL().insertar(cliente3);
			}
		}
		        
		 
		
		
		//buscar y modificarç
		if(resp=="Modificar"){
					
			String codigo = JOptionPane.showInputDialog(null, "Codigo del registro"
					, "Buscar", JOptionPane.QUESTION_MESSAGE);
					
			//buscar y modificar
			Cliente cliente4 = new Cliente(Long.parseLong(codigo),"");
			cliente4 = new ClienteDAOPostgreSQL().buscarPorCodigo(cliente4.getCodigo());
			System.out.println(cliente4);
			
			if(cliente4!=null){
				String descripcion = JOptionPane.showInputDialog("Nombre: ", cliente4.getNombre());
				cliente4.setNombre(descripcion);
				new ClienteDAOPostgreSQL().modificar(cliente4);
			}
				
		}
		        
		     
		
		
		
		//borrar
		if(resp=="Borrar"){
			
			String codigo = JOptionPane.showInputDialog(null, "Codigo del registro"
			, "Buscar", JOptionPane.QUESTION_MESSAGE);
			

			//borrar
			new ClienteDAOPostgreSQL().borrar(Long.parseLong(codigo));
						
			
		}
		   
		
		
		//listar todos
		if(resp=="Listar todos"){
			//listar todos
			
			System.out.println("\n** Lista **");
			List<Cliente> listacliente1 = new ClienteDAOPostgreSQL().buscarTodos();
			for (Cliente clientevarible : listacliente1) {
				System.out.println(clientevarible);
			}
			
			Object[] listaClientesArray = listacliente1.toArray();
	        Object resultado =  JOptionPane.showInputDialog(null, "Seleccione una opcion", "Opciones", JOptionPane.DEFAULT_OPTION, null
	        		, listaClientesArray, listaClientesArray[0]);
	    					
			
		}
		
		
		
		//lista por filtros
		if(resp=="Listar por filtros"){

			//lista por filtros
			System.out.println("\n** Lista filtros **");
			String nombre = JOptionPane.showInputDialog(null, "Nombre del registro"
			, "Buscar", JOptionPane.QUESTION_MESSAGE);
			
			List<Cliente> listacliente2 = new ClienteDAOPostgreSQL().buscarPorCampo("nombre", nombre);
			for (Cliente cliente : listacliente2) {
				System.out.println(cliente);
			}
				
			
			 Object[] listaClientesArray = listacliente2.toArray();
			 Object resultado =  JOptionPane.showInputDialog(null, "Seleccione una opcion", "Opciones", JOptionPane.DEFAULT_OPTION, null
	        		, listaClientesArray, listaClientesArray[0]);
	      
		}
		
			
		
	}
}

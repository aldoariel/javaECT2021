package jdbc.conexion;




public abstract class DAOFactory {

	public DAOFactory getDAOFactory() {
		return new JdbcDAOFactory();
	}
	//se esta fabricanndo JdbcDAOFactory
	//public abstract ClienteDAO getClienteDAO();
	
	///instanciar el dao de cada objeto del modelo
	
}

package lt.com.alura.gerenciador.dao;

import java.util.List;
import java.util.stream.Collectors;

import lt.com.alura.gerenciador.Empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EmpresaDaoImpl {
	
		// SQLServer
//		private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//		private static final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=ca";
//		private static final String ID = "sa";
//		private static final String PASS = "sa";
		
		// Oracle
//		private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
//		private static final String DB_URL = "jdbc:oracle:thin:@dbserver:1521:xe";
//		private static final String ID = "sys";
//		private static final String PASS = "orcl";
		
		// MySQL
		private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
		private static final String DB_URL = "jdbc:mysql://localhost:3306/empresas";
		private static final String ID = "root";
		private static final String PASS = "2812";
		
		// PostgreSQLs
//		private static final String DRIVER_NAME = "org.postgresql.Driver";
//		private static final String DB_URL = "jdbc:postgresql:ca";
//		private static final String ID = "postgres";
//		private static final String PASS = "postgres";
		
		private static final String DELETE = "DELETE FROM empresa WHERE id=?";
		private static final String FIND_ALL = "SELECT * FROM empresa ORDER BY id";
		private static final String FIND_BY_ID = "SELECT * FROM empresa WHERE id=?";
		private static final String FIND_BY_NAME = "SELECT * FROM empresa WHERE nombre=?";
		private static final String INSERT = "INSERT INTO empresa(nombre, fecha) VALUES(?, ?)";
		private static final String UPDATE = "UPDATE empresa SET nombre=?, fecha=? WHERE id=?";
		
		
		public int delete(int id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(DELETE);
				stmt.setInt(1, id);
				
				return stmt.executeUpdate();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
		}
		
		public List<Empresa> findAll() {
			Connection conn = null;
			PreparedStatement stmt = null;
			List<Empresa> list = new ArrayList<Empresa>();
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(FIND_ALL);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt("id"));
					empresa.setNombre(rs.getString("nombre"));
					
					empresa.setFecha(rs.getString("fecha"));
					
					
					list.add(empresa);
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
			
			return list;
		}
		
		public List<Empresa> busquedaPorSimilaridad(String filtro){
			Connection conn = null;
			PreparedStatement stmt = null;
			List<Empresa> list = new ArrayList<Empresa>();
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(FIND_ALL);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt("id"));
					empresa.setNombre(rs.getString("nombre"));
					
					empresa.setFecha(rs.getString("fecha"));
					
					
					list.add(empresa);
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
			
			//list.stream()                        
            //.filter(c -> "blue".equalsIgnoreCase( filtro )).collect(Collectors.toList());
			
			List<Empresa> filterList = list.stream()
			.filter(c ->  c.getNombre().toLowerCase().contains(filtro.toLowerCase() )).collect(Collectors.toList());
			
			return filterList;
		}
		
		public Empresa findById(int id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(FIND_BY_ID);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt("id"));
					empresa.setNombre(rs.getString("nombre"));
					empresa.setFecha(rs.getString("fecha"));
					
					
					return empresa;
				} else {
					return null;
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
		}
		
		public Empresa findByName(String nombre) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(FIND_BY_NAME);
				stmt.setString(1, nombre);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setId(rs.getInt("id"));
					empresa.setNombre(rs.getString("nombre"));
					empresa.setFecha(  (rs.getString("fecha")) );
					
					
					return empresa;
				} else {
					return null;
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
		}
		
		public int insert(Empresa empresa) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, empresa.getNombre());
				stmt.setString(2, empresa.getFecha());
				
				
				int result = stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				
				if (rs.next()) {
					empresa.setId(rs.getInt(1));
				}
				
				return result;
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
		}
		
		public int update(Empresa empresa) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = getConnection();
				stmt = conn.prepareStatement(UPDATE);
				stmt.setString(1, empresa.getNombre());
				stmt.setString(2, String.valueOf( (empresa.getFecha())) );
				
				stmt.setInt(3, empresa.getId());
				
				return stmt.executeUpdate();
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				close(stmt);
				close(conn);
			}
		}
		
	
		private Connection getConnection() {
			try {
				Class.forName(DRIVER_NAME);
				return DriverManager.getConnection(DB_URL, ID, PASS);
			} catch (Exception e) {
				// e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		
		private static void close(Connection con) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		
		
		private static void close(Statement stmt) {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}

}

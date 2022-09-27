package lt.com.alura.gerenciador.dao;

import java.util.List;

import lt.com.alura.gerenciador.Empresa;

public interface EmpresaDao {
	
	public int delete(int id);

	
	public List<Empresa> findAll();

	
	public Empresa findById(int id);
	
	
	public Empresa findByName(String name);

	public int insert(Empresa empresa);

	
	public int update(Empresa empresa);

}

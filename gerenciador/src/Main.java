import java.util.List;

import lt.com.alura.gerenciador.Empresa;
import lt.com.alura.gerenciador.dao.EmpresaDaoImpl;

public class Main {
	public static void main(String[] args) {
		List<Empresa> list = new EmpresaDaoImpl().findAll();
		
		
		
		for(Empresa ev:list) {
			System.out.println(ev.getNombre());
		}
	}
}

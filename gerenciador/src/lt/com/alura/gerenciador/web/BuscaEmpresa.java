package lt.com.alura.gerenciador.web;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.com.alura.gerenciador.Empresa;
import lt.com.alura.gerenciador.dao.EmpresaDaoImpl;


@WebServlet(urlPatterns="/busca")
public class BuscaEmpresa extends HttpServlet{
	
	
	private static final long serialVersionUID = -3423709712951690330L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html><body>");
		writer.println("Resultado de la busqueda:<br/>");
		String filtro = req.getParameter("filtro");
		
		//Empresa empresas = new EmpresaDaoImpl().findByName(filtro);
		
		System.out.println(filtro);
		List<Empresa> empresas = new EmpresaDaoImpl().busquedaPorSimilaridad(filtro);
		
		writer.println("<ul>");
		for(Empresa empresa:empresas) {
			writer.println("<li>"+empresa.getId()+": "+empresa.getNombre()+".");
		}
		writer.println("</ul>");
		writer.println("</body></html>");
		
	}
	
	public static void main(String[] args) {
List<Empresa> list = new EmpresaDaoImpl().findAll();
		
		
		
		for(Empresa ev:list) {
			System.out.println(ev.getNombre());
		}
	}
	

}

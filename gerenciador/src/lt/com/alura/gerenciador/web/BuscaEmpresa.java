package lt.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lt.com.alura.gerenciador.Empresa;
import lt.com.alura.gerenciador.dao.EmpresaDaoImpl;


public class BuscaEmpresa extends HttpServlet{
	
	private static final long serialVersionUID = -3423709712951690330L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html><body>");
		writer.println("Resultado de la busqueda:<br/>");
		String filtro = req.getParameter("filtro");
		Empresa empresas = new EmpresaDaoImpl().findByName(filtro);
		writer.println("<ul>");
		//for(Empresa empresa:empresas) {
			writer.println("<li>"+empresas.getId()+": "+empresas.getNombre()+".");
		//}
		writer.println("</ul>");
		writer.println("</body></html>");
		
	}

}

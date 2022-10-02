package lt.com.alura.gerenciador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

public class PseudoRepository {
	
	List<Empresa> empresas = new ArrayList<>();
	
	public List<Empresa> ejecuta() throws NumberFormatException, IOException {		
		System.out.println("hola");
        //try (BufferedReader reader = new BufferedReader(new FileReader("empresas.txt"))){
        	
		BufferedReader  document = new BufferedReader (new FileReader( "empresas.txt"));
		
		String word=null;
            while((word = document.readLine())  != null) {
                //System.out.println("->"+document);
                String[] line = word.split(" ");
                empresas.add(new Empresa(Integer.parseInt(line[0]),line[1]));
                
            }
       /* }catch(IOException e) {
            System.err.println("no consiguio leer el archivo. Error: " + e.getMessage());
        } */      
    
        return empresas;
    }
	
	
	public Collection<Empresa> busquedaPorSimilitud(String filtro) {
		//List<Empresa> empresas = new PseudoRepository().ejecuta();
		
		Collection<Empresa> indSearch = new ArrayList<>();
		
		System.out.println("ev"+filtro+"hola"+empresas.get(0).getNombre());

		for(Empresa ev:empresas) {
			System.out.println(filtro +"method");
			if(ev.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
				indSearch.add(ev);
			}
		}

/*for(Empresa ev:indSearch) {
	System.out.println(ev +"ev");
}*/
	
	
	return indSearch;
	}


	
}

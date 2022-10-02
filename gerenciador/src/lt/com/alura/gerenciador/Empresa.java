package lt.com.alura.gerenciador;



public class Empresa {
	
	private Integer id;
	private String nombre;
	private String fecha;
	
	
	
	public Empresa() {
		super();
	}
	public Empresa(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getFecha() {
		return fecha;
	}
	@Override
	public String toString() {
		return "Empresa [id = " + id + ", nombre = " + nombre + "]";
	}
	
	
}

package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
//Arreglos


static String[] usuarios = new String[10];
static String[] contraseñas = new String[10];

static int contadorUsuarios = 0;
static String usuarioActual = "";

static Scanner s = new Scanner(System.in);


	public static void main(String[] args) throws FileNotFoundException{
		
		
		
		File arch = new File("Usuarios.txt");
		Scanner lector = new Scanner(arch);
		
		//Lectura archivo
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			
			usuarios[contadorUsuarios] = partes[0];
			contraseñas[contadorUsuarios] = partes[1];
			contadorUsuarios++;
			
		}
			lector.close();
			
		int opcion = 0;
		
		while (opcion != 3){
			
			System.out.println("1) Menu de usuarios");
			System.out.println("2) Menu de analisis");
			System.out.println("3) Salir");
			System.out.print("> ");
			opcion = s.nextInt();
			
			switch (opcion) {
				case 1: 
					menuUsuarios();
					break;
				case 2:
					menuAnalisis();
					break;
				case 3: 
					System.out.println("Hasta luego");
					break;
				default: System.out.println("Opcion invalida. Intentelo nuevamente");
			
				
				
				
				
			}
			
		} 
		
			
	}

	

	
	
	private static void menuAnalisis() {
		
	}




// MENU USUARIOS
	private static void menuUsuarios() {
		
		//Validacion
		boolean encontrado = false;
				do {
					
					System.out.print("Usuario: ");
					String usuario = s.next();
					System.out.print("Contraseña: ");
					String contraseña = s.next();
					
					for (int i = 0; i < contadorUsuarios; i++) {
						if (usuarios[i].equals(usuario) && contraseñas[i].equals(contraseña)) {
							usuarioActual = usuarios[i];
							encontrado = true;
							break;
						}
					}
					
					if (encontrado) {
						System.out.println("Acceso correcto!");
						System.out.println();
						
						
					} else {
						System.out.println("Credenciales incorrectas.");
					}
				}while(encontrado == false);
				
				//Opciones usuario
				int opcion = 0;
				while (opcion !=5) {
					System.out.println();
					System.out.printf("Bienvenido %s!",usuarioActual);
					System.out.println();
					System.out.println("Que deseas realizar?");
					System.out.println("1) Registrar actividad.");
					System.out.println("2) Modificar actividad.");
					System.out.println("3) Eliminar actividad.");
					System.out.println("4) Cambiar contraseña.");
					System.out.println("5) Salir.");
					System.out.print("> ");
					opcion = s.nextInt();
					
					switch (opcion) {
					case 1:
						registrarActividad();
						break;
					case 2:
						modificarActividad();
						break;
					case 3:
						eliminarActividad();
						break;
					case 4: 
						cambiarContraseña();
						break;
					case 5:
						System.out.println("Volviendo al menu principal.");
						break;
					default:
						System.out.println("Opcion invalida. Intentelo Nuevamente");
						
					}
				}
				
		
				
				
				
	}





private static void cambiarContraseña() {
		// TODO Auto-generated method stub
		
	}





private static void eliminarActividad() {
		// TODO Auto-generated method stub
		
	}





private static void modificarActividad() {
		// TODO Auto-generated method stub
		
	}





private static void registrarActividad() {
	// TODO Auto-generated method stub
	
}

}
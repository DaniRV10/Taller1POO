package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
//Arreglos
static String[] usuarios = new String[10];
static String[] contraseñas = new String[10];

static int contadorUsuarios = 0;
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
		// TODO Auto-generated method stub
		
	}





	private static void menuUsuarios() {
		boolean encontrado = false;
				do {
					
					System.out.print("Usuario: ");
					String usuario = s.next();
					System.out.print("Contraseña: ");
					String contraseña = s.next();
					
					for (int i = 0; i < contadorUsuarios; i++) {
						if (usuarios[i].equals(usuario) && contraseñas[i].equals(contraseña)) {
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
		
				
	}

}
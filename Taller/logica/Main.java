package logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
//Carlos Alberto Montenegro Pérez 22.154.893-0 ICCI
// Daniel Alexanders Robles Valdenegro 20.738.244-2 ICCI
	
//Arreglos usuarios
static String[] usuarios = new String[10];
static String[] contraseñas = new String[10];
static int contadorUsuarios = 0;
static String usuarioActual = "";

//Arreglos registros
static String[] regUsuario = new String[300];
static String[] regFecha = new String[300];
static int[] regHoras = new int[300];
static String[] regActividad = new String[300];
static int contadorReg =0;



static Scanner s = new Scanner(System.in);


	public static void main(String[] args) throws FileNotFoundException{
		
		
		//Lectura archivo usuarios
		
		File arch = new File("Usuarios.txt");
		Scanner lector = new Scanner(arch);
		
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			
			usuarios[contadorUsuarios] = partes[0];
			contraseñas[contadorUsuarios] = partes[1];
			contadorUsuarios++;
			
		}
			lector.close();
			
		//Lectura archivo registros
			
		File archReg = new File("Registros.txt");
		Scanner lectorReg = new Scanner(archReg);
		
		while (lectorReg.hasNextLine()) {
			String linea = lectorReg.nextLine();
			String[] partes = linea.split(";"); 
			
			regUsuario[contadorReg]   = partes[0];
            regFecha[contadorReg]     = partes[1];
            regHoras[contadorReg]     = Integer.parseInt(partes[2].trim());
            regActividad[contadorReg] = partes[3];
            contadorReg++;
			
		}	
		lectorReg.close();
		
		//Print Menu inicial y control de errores
		
		int opcion = 0;
		
		while (opcion != 3){
			
			System.out.println("1) Menu de usuarios");
			System.out.println("2) Menu de analisis");
			System.out.println("3) Salir");
			System.out.print("> ");
			try {
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
			}catch (Exception e) {
				System.out.println("Porfavor ingrese un numero");
				s.nextLine();
			}
				
			}
			
		 
		
			
	}

	

	//MENU ANALISIS
	
	private static void menuAnalisis() {
		
		int opcion = 0;
		while (opcion != 5) {
			System.out.println("Bienvenido al menu de analisis:");
			System.out.println();
			System.out.println("Que deseas realizar?");
			System.out.println("1) Actividad mas realizada");
			System.out.println("2) Actividad mas realizada por cada ususario");
			System.out.println("3) Usuario con mayor procastinacion");
			System.out.println("4) Ver todas las actividades");
			System.out.println("5) Salir");
			
			try {
				opcion = s.nextInt();
				
				switch (opcion) {
				
				case 1:
					actMasRealizada();
					break;
				case 2:
					actMasRealizadaxUsuario();
					break;
				case 3:
					usuarioMayorProcastinacion();
					break;
				case 4: 
					verActividades();
					break;
				case 5:
					System.out.println("Volviendo al menu principal.");
					break;
				default:
					System.out.println("Opcion invalida. Intentelo Nuevamente");
				
				}
				 
			} catch (Exception e) {
				System.out.println("Porfavor ingrese un numero");
				s.nextLine();
			}
		}	
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
				System.out.println();
				System.out.printf("Bienvenido %s!",usuarioActual);
				System.out.println();
				while (opcion !=5) {
					System.out.println("Que deseas realizar?");
					System.out.println("1) Registrar actividad.");
					System.out.println("2) Modificar actividad.");
					System.out.println("3) Eliminar actividad.");
					System.out.println("4) Cambiar contraseña.");
					System.out.println("5) Salir.");
					System.out.print("> ");
					
					try {
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
							cambiarContraseña(s,usuarioActual);
							break;
						case 5:
							System.out.println("Volviendo al menu principal.");
							break;
						default:
							System.out.println("Opcion invalida. Intentelo Nuevamente");
							
						}
					}catch (Exception e) {
						System.out.println("Porfavor ingrese un numero");
						s.nextLine();
					}
				}
				
			
	}



private static void cambiarContraseña(Scanner s, String usuario) {
	
	
	s.nextLine(); // <-- LIMPIEZA: Se come el "\n" de la opción 4
	// Ingreso por consola la nueva contraseña
	System.out.print("Ingrese su nueva contraseña: ");
	String newpass = s.nextLine();
	
	
	// recorre los usuarios hasta encontrar al que se le quiere cambiar la contraseña y lo realiza
	for (int i=0; i < contadorUsuarios; i++) {
		if (usuarios[i].equals(usuario)) {
			contraseñas[i] = newpass;
			System.out.println("Contraseña actualizada en el sistema");
            break;
		}
	}
	
	
	guardarUsuarios();
		
		
	}




// Funcion para guardar los usuarios actualizados
private static void guardarUsuarios() {
	try {
		
		FileWriter fw = new FileWriter("Usuarios.txt");
        BufferedWriter bw = new BufferedWriter(fw);
		
        for (int i = 0; i < contadorUsuarios; i++) {
            
        	// Escribir en el archivo ID;contraseña
            String linea = usuarios[i] + ";" + contraseñas[i];
            bw.write(linea);
            
            // Solo agregamos salto de línea si no es el último registro
            if (i < contadorUsuarios - 1) {
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
		
		
		
	}catch (Exception e) {
		System.out.println("Error al escribir en el archivo");
	}
	
	
}




private static void registrarActividad() {

	if (contadorReg >= 300) {
		System.out.println("Se alcanzo el limite de registros");
		
	}
	
	s.nextLine(); //limpieza nuevamente
	
	System.out.print("Ingrese la fecha (DD/MM/AAAA)");
	String fecha = s.nextLine();
			
	//Validar horas 
	
	int horas = 0;
	boolean horasValidas = false;
	
	while (!horasValidas) {	
		System.out.print("Ingrese horas: ");
		try {
			horas = Integer.valueOf(s.nextLine());
			if (horas > 0) {
				horasValidas = true;
			} else {
				System.out.println("Las horas deben ser positivas. Intentelo nuevamente.");
			}
			
		}catch (Exception e) {
			System.out.println("Porfavor ingrese un numero. Intentelo Nuevamente.");
		}
	}
	
	System.out.print("Ingrese tipo de actividad: ");
	String actividad = s.nextLine();
	
	regUsuario[contadorReg] = usuarioActual;
	regFecha[contadorReg] = fecha;
	regHoras[contadorReg] = horas;
	regActividad[contadorReg] = actividad;
	contadorReg++;
	
	guardarRegistros();
	System.out.println("Actividad registrada!");
	
}



private static void guardarRegistros() {
try {
		
		FileWriter fw = new FileWriter("Registros.txt");
        BufferedWriter bw = new BufferedWriter(fw);
		
        for (int i = 0; i < contadorReg; i++) {
            
            String linea = regUsuario[i] + ";" + regFecha[i] + ";" + regHoras[i] + ";" + regActividad[i];
            bw.write(linea);
            
            if (i < contadorReg - 1) {
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
		
		
		
	}catch (Exception e) {
		System.out.println("Error al escribir en el archivo");
	}	
}



private static void verActividades() {
	// TODO Auto-generated method stub
	
}



private static void usuarioMayorProcastinacion() {
	// TODO Auto-generated method stub
	
}



private static void actMasRealizadaxUsuario() {
	// TODO Auto-generated method stub
	
}



private static void actMasRealizada() {
	// TODO Auto-generated method stub
	
}



private static void eliminarActividad() {
		// TODO Auto-generated method stub
		
	}





private static void modificarActividad() {

		System.out.println("Tus actividades");
		int contador = 1; // contador para mostrarles todas las actividades 
		int[] lugarActividades = new int[300];
		
		System.out.println("Cual actividad deseas modificar?");
		System.out.println("0) Regresar.");
		for (int i = 0; i < contadorReg; i++) {
			if (regUsuario[i].equals(usuarioActual)) {
				System.out.println(contador + ") "+ regUsuario[i] + ";"+ regFecha[i] + ";"+ regHoras[i] + ";"+ regActividad[i]);
				lugarActividades[contador] = i;
				contador++;
			}
		}
		boolean flag = true;
		while(flag) {
			try {
				System.out.print(">");
				int opcion = s.nextInt();
				s.nextLine(); // Limpiar buffer
				System.out.println();
				if (opcion == 0) {
					return;
				}else if (opcion < contador) {
					flag= false;
					int iReal = lugarActividades[opcion]; // Aqui tenemos la posicio  donde se encuentra realmente en registros la actividad
					System.out.println("0) Regresar.");
					System.out.println("1) Fecha");
					System.out.println("2) Duracion");
					System.out.println("3) Tipo de actividad");
					System.out.print(">");
					int modificar = s.nextInt();
					s.nextLine(); // Limpiar buffer
					switch(modificar) {
						case 0:
							return;
						case 1:
							System.out.print("Ingrese nueva fecha (DD/MM/AAAA): ");
							regFecha[iReal] = s.nextLine();
							break;
						case 2:
							System.out.print("Ingrese nueva duración (horas): ");
							regHoras[iReal] = s.nextInt();
	                        s.nextLine();
	                        break;
	                    case 3:
	                        System.out.print("Ingrese nuevo tipo de actividad: ");
	                        regActividad[iReal] = s.nextLine();
	                        break;
	                    default:
	                        System.out.println("Opción no válida.");
	                        continue;
					}
					
				}else {
					System.out.println("Porfavor ingrese una accion valida");
				}
				
			}catch (Exception e) {
				
				System.out.println("Porfavor ingrese un numero");
				System.out.println("Cual actividad deseas modificar?");
				s.nextLine();//Limpiar el buffer
			}
		}
		
		System.out.println("Modificacion realizada con exito");
		guardarRegistros();
		
		
		
	}






}
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
		int intentos = 3;
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
						intentos--;
						if(intentos == 0) {
							System.out.println("Se acabaron los intentos para ingresar al menu Usuarios");
							System.out.println("Saliendo del menu Usuarios");
							return;
							
						}else {System.out.println("Quedan "+intentos+" intentos");}
						
						
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
	
	
	s.nextLine(); // LIMPIEZA: Se come el "\n" de la opción 4 al ingresar a cambiar contraseña en el menu
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
	
	System.out.print("Ingrese la fecha (DD/MM/AAAA): ");
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


//Funcion para guardar las actualizacion de los registros 
private static void guardarRegistros() {
	
	
	//En donde se va guardando nuevamente todos los registros en el arhcivo Registros.txt
	try {
	
		FileWriter fw = new FileWriter("Registros.txt");
        BufferedWriter bw = new BufferedWriter(fw);
		
        for (int i = 0; i < contadorReg; i++) {
        	if (regUsuario[i] != null) {
        		
        		String linea = regUsuario[i] + ";" + regFecha[i] + ";" + regHoras[i] + ";" + regActividad[i];
        		bw.write(linea);
        		
        		if (i < contadorReg - 1) {
        			bw.newLine();
        	}
            
            }
        }
        bw.close();
        fw.close();
		
		
		
	}catch (Exception e) {
		System.out.println("Error al escribir en el archivo");
	}	
}



private static void verActividades() {
	
	//Funcion para mostrar todas las actividades de Registros.txt
	System.out.println("----------Mostrando todas las actividades--------------");
	
	//Recorre todos los registros guardados
	for(int i = 0; i<contadorReg ; i++) {
		if(regUsuario[i] != null) {  //En caso de que un registro haya sido eliminado y haya quedado en null que no se imprima
			System.out.println("El usuario "+ regUsuario[i] + " realizo "+ regActividad[i] + " durante " + regHoras[i] +" horas el dia " + regFecha[i]);
		}
	}
	System.out.println("-------------------------------------------------------");
	
}



private static void usuarioMayorProcastinacion() {
	
	// Variable para guardar las horas por usuario
	int[] horasxUsuario = new int[10];
	
	for(int i=0; i< contadorUsuarios; i++) {
		String usuario = usuarios[i];
		
		for(int j = 0;j<contadorReg;j++) {
			if(regUsuario[j].equals(usuario)) {
				
				horasxUsuario[i] += regHoras[j];
			}
		}
	
	}
	
	//Variable para ver quien es el usuario mayor procastinador
	String usuarioProcastinacion = "";
	int maxHoras = 0;
	 // Se verifica por usuario quien es el que consiguio tener mas horas y se guardan en las variables
	for(int j=0;j<contadorUsuarios;j++) {  
		if(horasxUsuario[j]>maxHoras) {
			usuarioProcastinacion = usuarios[j];
			maxHoras = horasxUsuario[j];
		}
	}
	
	System.out.println("El usuario "+usuarioProcastinacion+ " es el mayor procastinador/a con "+maxHoras+" horas invertidas");
	
	
}



private static void actMasRealizadaxUsuario() {
	
	System.out.println("Actividades mas realizadas por cada usuario: ");
	
	for(int i = 0;i<contadorUsuarios;i++) {
		
		//Variables utilizadas para saber que usuario se esta comparando y para guardar sus actividades y sus respectivas horas
		String usuario = usuarios[i];
		String[] nombresActividades = new String[300];
		int[] horasActividades = new int[300];
		int contadorActividades = 0; //Contador para ir iterando despues por las actividades ya vistas
		
		for(int j= 0;j<contadorReg;j++) {
			if (regUsuario[j].equals(usuario)) {
				
				//Variables para trabajar con las actividades y sus respectivas horas
				String actividadActual = regActividad[j].toLowerCase().trim();
				int horaActual = regHoras[j];
				
				boolean encontrada = false;
				//Recorre las actividades ya encontradas para sumarle las horas si ya esta encontrada
				for (int k = 0; k<contadorActividades ;k++) {
					if (nombresActividades[k].equals(actividadActual)) {
						horasActividades[k] += horaActual;
						encontrada = true;
						break;
					}
				}
				//si es la primera vez que aparece la actividad se agrega a los nombres actividades
				if(!encontrada) {
					nombresActividades[contadorActividades] = actividadActual;
					horasActividades[contadorActividades] = horaActual;
					contadorActividades++;
				}
				
			}
		}
		
		
		//Luego de revisar todos los registros se comparan entre las actividades del usuario por su actividad mas realizada
		if(contadorActividades > 0) {
			//auxiliares para saber que actividad es la de mayor horas
			int maxHoras = 0; 
			String actividadGanadora = ""; 
			
			for(int m=0; m<contadorActividades;m++) {
				if(horasActividades[m]> maxHoras) {
					maxHoras = horasActividades[m];
					actividadGanadora = nombresActividades[m];
				}
			}
			
			System.out.println("* "+ usuario + " -> "+ actividadGanadora + " -> con "+ maxHoras + " horas registradas");
			
		}else {System.out.println("El usuario "+ usuario +" no registra actividades");}
		
		
	}
	
	
	
}



private static void actMasRealizada() {
	System.out.println();
	
	// Variables para obtener todas las actividades y sus horas
	String[] nombresActividades = new String[300];
	int[] horasActividades = new int[300];
	int contadorActividades = 0;
	
	for(int i=0;i<contadorReg;i++) {
		
		//Variables para trabajar con las actividades y sus respectivas horas
		String actividadActual = regActividad[i].toLowerCase().trim();
		int horaActual = regHoras[i];
		
		boolean encontrada = false;
		
		//Recorre las actividades ya encontradas para sumarle las horas si ya esta encontrada
		for(int j=0;j<contadorActividades;j++) {
			if (nombresActividades[j].equals(actividadActual)) {
				horasActividades[j] += horaActual;
				encontrada = true;
				break;
			}
		}
		//si es la primera vez que aparece la actividad se agrega a los nombres actividades
		if(!encontrada) {
			nombresActividades[contadorActividades] = actividadActual;
			horasActividades[contadorActividades] = horaActual;
			contadorActividades++;
		}
		
	}
	
	if(contadorActividades > 0) {
		//auxiliares para saber que actividad es la de mayor horas
		int maxHoras = 0; 
		String actividadGanadora = ""; 
		
		for(int k=0; k<contadorActividades; k++) {
			if(horasActividades[k]> maxHoras) {
				maxHoras = horasActividades[k];
				actividadGanadora = nombresActividades[k];
			}
		}
		
		System.out.println("La actividad mayor realizada es "+ actividadGanadora + " -> con "+ maxHoras + " horas registradas");
		
	}else {System.out.println("No se encontraron Actividades en los registros");}
	
	
	
}



private static void eliminarActividad() {

	//arreglo de indices
	int[] indices = new int[300]; //max 300 actividades
	int actividadesPropias = 0; // Actividades del usuario actual

	for (int i = 0; i < contadorReg; i++){
		if (regUsuario != null && regUsuario[i].equals(usuarioActual)) {
			indices[actividadesPropias] = i;
			actividadesPropias++;
			
		}
	}
	if (actividadesPropias == 0){
		System.out.println("No tienes actividades.");
		return;	
	}
	
	System.out.println("Que actividad quieres eliminar?");
	System.out.println("0) Regresar");
	
	for (int i = 0; i< actividadesPropias; i++) {
		int aux = indices[i]; 
		System.out.println((i+1)+ ") " + regUsuario[aux] + ";"+ regFecha[aux] + ";" + regHoras[aux] + ";" + regActividad[aux]);
	}
	System.out.print("> ");
	
	int opcion = 0;
	try {
		opcion = s.nextInt();
	} catch (Exception e) {
		System.out.println("Porfavor ingrese un numero");
		s.nextLine();
		return;
	}
	
	if (opcion == 0) 
		return;
	
	if (opcion < 1 || opcion > actividadesPropias) {
		System.out.println("Opcion invalida");
		return;
	}
	
	int indiceReaal = indices[opcion -1];
	
	regUsuario[indiceReaal] =null;
	regFecha[indiceReaal] =null;
	regHoras[indiceReaal] = 0;
	regActividad[indiceReaal] =null;

	guardarRegistros();
	System.out.println("Actividad eliminada!");
	}




private static void modificarActividad() {

		System.out.println("Tus actividades");
		
		int[] lugarActividades = new int[300];
		
		System.out.println("Cual actividad deseas modificar?");
		
		boolean flag = true;
		while(flag) {
			try {
				System.out.println("0) Regresar.");
				int contador = 1; // contador para mostrarles todas las actividades 
				for (int i = 0; i < contadorReg; i++) {
					if (regUsuario[i].equals(usuarioActual)) {
						System.out.println(contador + ") "+ regUsuario[i] + ";"+ regFecha[i] + ";"+ regHoras[i] + ";"+ regActividad[i]);
						lugarActividades[contador] = i;
						contador++;
					}
				}
				System.out.print(">");
				int opcion = s.nextInt();
				s.nextLine(); // Limpiar buffer
				System.out.println();
				if (opcion == 0) {
					return;
				}else if (opcion < contador && opcion >= 1) { //Asegurar que la opcion no sea negativo
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
							flag= false;
							break;
						case 2:
							
							//regHoras[iReal] = s.nextInt();
							boolean horaValida = false;
							while(!horaValida) {
								System.out.print("Ingrese nueva duración (horas): ");
								int nuevaHora = s.nextInt();
								s.nextLine(); // Limpiar buffer

					            if (nuevaHora >= 0) { // Validación de no negativo
					                regHoras[iReal] = nuevaHora;
					                horaValida = true;
					            } else {
					                System.out.println("Error: Las horas no pueden ser negativas.");
					            }
								
							}
	                        flag= false;
	                        break;
	                    case 3:
	                        System.out.print("Ingrese nuevo tipo de actividad: ");
	                        regActividad[iReal] = s.nextLine();
	                        flag= false;
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
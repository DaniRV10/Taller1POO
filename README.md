# Taller 1: Programación Orientada a Objetos

## Descripcion del Proyecto
Este software permite a los usuarios analizar y modificar un archivo de texto llamado Registros.txt para un maximo de 10 usuarios y 300 registros, donde podran monitorear sus actividades de ocio.
Donde este sistema permite:
- Registrar, modificar o eliminar actividades que se encuentren en los registros de un usuario.
- Podra cambiar su contraseña.
- Podra analizar sin entrar como un usuario a analizar metricas de comparacion de los tiempos de ocio que se encuentran en los registros.

## Integrantes
- Carlos Alberto Montenegro Pérez - 22.154.893-0 - Akr0yy
- Daniel Alexanders Robles Valdenegro - 20.738.244-2 - DaniRV10

## Estructura del proyecto
El software esta desarrollado con un puro archivo llamado Main.java pero a su vez necesita que se encuentren en una misma carpeta los archivos de datos que deben llamarse 'Usuarios.txt' y 'Registros.txt'. En donde:
- El Main.java la clase principal que contiene las funciones para hacer funcionar nuestro software, la cual contiene:
    - El flujo de los menus solicitados como el menu de analisis y menu de usuario
    - Logicas de escritura y lectura de archivos.
    - Funciones que permiten revisar metricas para comparar niveles de procastinacion.
    - Y funciones que permiten añadir, modificar o eliminar actividades del propio usuario que ingrese ademas de poder cambiar su contraseña.

- Los archivos de datos:
    - 'Usuarios.txt': Almacena las credenciales de los usuarios de la forma (ID;Contraseña).
    - 'Registros.txt': Almacena el historial de actividades de la forma (ID;Fecha;Horas;Actividad).


## Instrucciones de ejecución.

Para ejecutar el programa se debe tener instalado un entorno como **Eclipse** o **Java JDK 21**.

En caso de hacerlo en un entorno que no sea el de eclipse como powershell primero se compila.

```bash
javac -d bin Taller/logica/Main.java
```
Luego se ejecuta con el siguiente codigo.

```bash
java -cp bin logica.Main
```
Tambien para que el software funcione correctamente, asegurar que en la carpeta Taller1POO se encuentren los archivos 'Usuarios.txt' y 'Registros.txt'



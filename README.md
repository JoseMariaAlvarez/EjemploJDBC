# EjemploJDBC
Ejemplo de uso de JDBC con Java.

Está estructurado siguiendo la arquitectura MVC. Por cada una de las dos funciones principales, añadir un nuevo jugador y adscribir un jugador a un equipo o darlo de baja de un equipo, hay una clase que implementa el interfaz usando Swing y una clase controladora que atiende las ocurrencias de eventos. Las clases controladoras se encargan de modificar los objetos del modelo y actualizar coherentemente el contenido de la base de datos a través de una clase dedicada.

Las clases del modelo son dos arraylists, uno de equipos, con sus jugadores, y otro de jugadores que no están adscritos a ningún equipo.

La clase que se encarga de comunicarse con la base de datos está implementada siguiendo el patrón singular. Usa JDBC para abrir una conexión. Ofrece un método por cada operación que puedan necesitar los requisitos funcionales. En esos métodos se usan Statements y PreparedStatements para realizar dichas operaciones.

El objeto singular de esta clase de interfaz con la base de datos se está usando como variable global, lo que es una mala práctica de diseño. Se debería pasar como parámetro a las clases que lo usen.

No se ha usado ni el patrón Observador ni el Publicador/Suscriptor, que pueden mejorar la comunicación entre las clases de la vista y las de modelo.

Dependencias: Para conectarse a la base de datos, JDBC usa el driver de MySQL. Ese driver está en el archivo mysql-connector-java-5.1.49.jar. El archivo se puede descargar desde la página https://dev.mysql.com/downloads/connector/j/5.1.html. Para instalarlo, se puede bajar el archivo en formato zip, extraer de él el archivo .jar, copiarlo a una carpeta conocida y añadirlo al proyecto a través de la opción Configurar Build Path como nuevo External JAR.

# Academia de formación — Java + JDBC + H2
Abrir esta carpeta en IntelliJ IDEA 2026.2.3 y cargar el proyecto Maven. Requiere JDK 17+ como minimo
Ejecutar `com.academia.Main`. La base H2 se crea en `./data/academia.mv.db`.

Modelo de tres tablas: PROFESORES (PK ID), ASIGNATURAS (PK ID, FK PROFESOR_ID) y ALUMNOS (PK ID, FK ASIGNATURA_ID). Para cumplir el requisito de tres tablas, el ejemplo asume un profesor por asignatura y una asignatura por alumno; en un sistema real, la matrícula normalmente sería una cuarta tabla.

Incluye tres registros iniciales por tabla, consulta simple, JOIN de las tres tablas, agregación COUNT/AVG y alta/eliminación de alumnos. Los valores introducidos se pasan mediante PreparedStatement para evitar inyección SQL.
1. Resumen ejecutivo

El proyecto Academia de Formación consiste en el desarrollo de una aplicación de software destinada a gestionar información académica básica de un centro de formación, incluyendo alumnos, profesores y asignaturas.

La aplicación se desarrolla en Java y utiliza JDBC como mecanismo de conexión y acceso a una base de datos relacional H2. La arquitectura se organiza mediante paquetes especializados, separando el modelo de datos, la conexión, las operaciones de acceso a datos y la interfaz de usuario por consola.

La solución incorpora la creación automática del esquema de base de datos, la carga inicial de datos de ejemplo, consultas SQL simples, consultas compuestas mediante JOIN y consultas de agregación mediante COUNT() y AVG().

Asimismo, permite realizar operaciones de alta y eliminación de alumnos y aplica mecanismos básicos de seguridad e integridad, entre los que destacan el uso de PreparedStatement, las claves primarias y foráneas y las restricciones de validación definidas en la base de datos.

El propósito de esta primera versión es disponer de un prototipo funcional que permita evaluar la arquitectura propuesta, comprobar las relaciones entre entidades y validar las operaciones fundamentales antes de considerar una posible ampliación del sistema.

2. Objetivos del proyecto

2.1. Objetivo general

Desarrollar una aplicación Java de gestión académica que permita almacenar, consultar y relacionar información de alumnos, profesores y asignaturas mediante una base de datos relacional.

2.2. Objetivos específicos

Crear las tablas de la base de datos directamente desde código Java.

Definir correctamente las claves primarias (PK) y las claves foráneas (FK).

Incorporar tres registros iniciales por cada tabla.

Implementar consultas SQL simples sobre las entidades.

Desarrollar una consulta compuesta que relacione las tres tablas mediante JOIN.

Implementar consultas de agregación con COUNT() y AVG().

Permitir el alta y la eliminación de alumnos.

Separar las responsabilidades del programa mediante paquetes y clases.

Aplicar consultas parametrizadas para reducir el riesgo de inyección SQL.

Facilitar el mantenimiento y la futura ampliación del software.

3. Alcance funcional

La versión inicial contempla las siguientes funcionalidades:

Funcionalidad

Descripción

Inicialización de la base de datos

Creación de las tablas desde Java si todavía no existen.

Datos de ejemplo

Inserción de tres registros iniciales por tabla cuando la tabla está vacía.

Consulta de alumnos

Listado de los alumnos registrados.

Consulta de profesores

Listado de profesores y especialidades.

Consulta de asignaturas

Listado de asignaturas, horas y profesor asociado.

Consulta compuesta

Resumen académico de alumnos, asignaturas y profesores mediante JOIN.

Consulta de agregación

Número de alumnos por asignatura y edad media.

Alta de alumnos

Registro de nuevos alumnos.

Eliminación de alumnos

Eliminación de un alumno mediante su identificador.

La versión actual no implementa todavía el CRUD completo de las tres entidades. Las operaciones de modificación, así como las altas y eliminaciones de profesores y asignaturas, quedan previstas para futuras versiones.

4. Tecnologías utilizadas

Tecnología

Aplicación en el proyecto

Java 17 o superior

Lenguaje de programación y lógica de aplicación.

IntelliJ IDEA 2026.2.3

Entorno de desarrollo integrado (IDE).

Maven

Gestión del proyecto y sus dependencias.

JDBC

API de Java para el acceso a datos.

H2 Database

Motor relacional embebido y almacenamiento local.

SQL

Definición de tablas, consultas y operaciones de datos.

La dependencia principal de base de datos se declara en el archivo pom.xml:

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.3.232</version>
</dependency>

Maven permite descargar y gestionar automáticamente esta dependencia.

5. Arquitectura del software

El proyecto utiliza una organización por capas, con el objetivo de separar la interfaz de usuario, el modelo de dominio, la conexión a la base de datos y las operaciones de persistencia.

5.1. Estructura de paquetes

com.academia
├── conexion
│   └── ConexionBD.java
├── modelo
│   ├── Alumno.java
│   ├── Profesor.java
│   └── Asignatura.java
├── dao
│   ├── AlumnoDAO.java
│   ├── ProfesorDAO.java
│   ├── AsignaturaDAO.java
│   └── ConsultasDAO.java
└── Main.java

5.2. Responsabilidad de cada componente

Main.java — Interfaz y coordinación

Presenta el menú de consola, recibe las opciones del usuario y coordina la ejecución de las operaciones disponibles.

ConexionBD.java — Acceso y conexión

Centraliza la configuración de la conexión JDBC, proporciona una instancia mediante el patrón Singleton e inicializa las tablas de la base de datos.

Paquete modelo — Representación de entidades

Contiene las clases Java que representan los datos de cada entidad:

Alumno.java: información del alumno.

Profesor.java: información del profesor.

Asignatura.java: información de la asignatura.

Paquete dao — Acceso a datos

DAO significa Data Access Object (objeto de acceso a datos). Estas clases encapsulan las consultas SQL y las operaciones realizadas sobre la base de datos:

AlumnoDAO.java: consulta, alta y eliminación de alumnos.

ProfesorDAO.java: consulta de profesores.

AsignaturaDAO.java: consulta de asignaturas.

ConsultasDAO.java: consultas compuestas y estadísticas.

5.3. Diagrama lógico de arquitectura

            USUARIO
               |
               v
       +----------------+
       |    Main.java  |
       | Menú de consola|
       +----------------+
               |
               v
       +----------------+
       |  Capa DAO      |
       |----------------|
       | AlumnoDAO      |
       | ProfesorDAO    |
       | AsignaturaDAO  |
       | ConsultasDAO   |
       +----------------+
               |
               v
       +----------------+
       | ConexionBD.java|
       | /JDBC          |
       +----------------+
               |
               v
       +----------------+
       | H2 Database    |
       |----------------|
       | ALUMNOS        |
       | ASIGNATURAS    |
       | PROFESORES     |
       +----------------+

El paquete modelo define las clases que representan las entidades y permite transferir los datos entre las capas de la aplicación.

Esta separación facilita la comprensión del código, reduce el acoplamiento y permite ampliar la solución sin concentrar toda la lógica en una única clase.

6. Diseño de la base de datos

La base de datos contiene tres tablas relacionadas: PROFESORES, ASIGNATURAS y ALUMNOS.

6.1. Tabla PROFESORES

Campo

Tipo

Restricción

Descripción

id

INT

PK, autogenerado

Identificador único.

nombre

VARCHAR(60)

NOT NULL

Nombre del profesor.

apellidos

VARCHAR(100)

NOT NULL

Apellidos del profesor.

especialidad

VARCHAR(100)

NOT NULL

Área de especialización.

6.2. Tabla ASIGNATURAS

Campo

Tipo

Restricción

Descripción

id

INT

PK, autogenerado

Identificador único.

nombre

VARCHAR(100)

NOT NULL

Nombre de la asignatura.

horas

INT

NOT NULL, CHECK

Duración en horas, superior a cero.

profesor_id

INT

FK, NOT NULL

Referencia al profesor responsable.

La clave foránea profesor_id referencia PROFESORES.id.

6.3. Tabla ALUMNOS

Campo

Tipo

Restricción

Descripción

id

INT

PK, autogenerado

Identificador único.

nombre

VARCHAR(60)

NOT NULL

Nombre del alumno.

apellidos

VARCHAR(100)

NOT NULL

Apellidos del alumno.

email

VARCHAR(120)

NOT NULL, UNIQUE

Correo electrónico único.

edad

INT

NOT NULL, CHECK

Edad mínima de 16 años en el prototipo.

asignatura_id

INT

FK, NOT NULL

Referencia a la asignatura asociada.

La clave foránea asignatura_id referencia ASIGNATURAS.id.

6.4. Diagrama entidad-relación

PROFESORES
|
| 1
|
| N
ASIGNATURAS
|
| 1
|
| N
ALUMNOS

Las relaciones establecidas son las siguientes:

Un profesor puede estar asociado a varias asignaturas.

Cada asignatura tiene un profesor responsable.

Una asignatura puede estar asociada a varios alumnos.

Cada alumno está asociado a una asignatura.

Las claves foráneas garantizan que las relaciones apunten a registros existentes.

6.5. Consideración sobre el modelo académico

El diseño de tres tablas responde al alcance simplificado de esta versión. En una academia real, un alumno puede matricularse en varias asignaturas y cada asignatura puede tener muchos alumnos.

Para representar correctamente esa relación muchos-a-muchos se recomienda incorporar una cuarta tabla, MATRICULAS, con referencias a alumnos y asignaturas y, opcionalmente, fecha de matrícula, curso académico y estado.

La restricción de edad mínima es una regla didáctica del prototipo y debe adaptarse a las condiciones reales del centro.

7. Consultas SQL implementadas

7.1. Consulta simple

Obtiene el listado de alumnos:

SELECT *
FROM alumnos
ORDER BY id;

Esta consulta permite comprobar los registros almacenados en la tabla de alumnos.

7.2. Consulta compuesta mediante JOIN

La consulta relaciona la información de alumnos, asignaturas y profesores:

SELECT
al.nombre AS alumno,
al.apellidos,
asi.nombre AS asignatura,
pr.nombre AS profesor,
pr.apellidos AS apellidos_profesor
FROM alumnos al
JOIN asignaturas asi
ON al.asignatura_id = asi.id
JOIN profesores pr
ON asi.profesor_id = pr.id
ORDER BY al.id;

El resultado proporciona una vista conjunta de la información académica y demuestra la utilización de relaciones entre tablas mediante claves foráneas.

7.3. Consulta de agregación

La consulta calcula el número de alumnos y la edad media por asignatura:

SELECT
asi.nombre,
COUNT(al.id) AS total_alumnos,
AVG(al.edad) AS edad_media
FROM asignaturas asi
LEFT JOIN alumnos al
ON al.asignatura_id = asi.id
GROUP BY asi.id, asi.nombre
ORDER BY asi.nombre;

COUNT() cuenta los alumnos asociados a cada asignatura.

AVG() calcula la edad media de los alumnos.

GROUP BY agrupa los resultados por asignatura.

LEFT JOIN permite incluir asignaturas que todavía no tienen alumnos.

8. Seguridad e integridad de la información

La aplicación incorpora medidas básicas destinadas a reducir errores y mejorar la seguridad del acceso a datos.

8.1. Prevención de inyección SQL

Las operaciones que reciben datos del usuario emplean PreparedStatement y parámetros SQL.

Ejemplo:

String sql = "DELETE FROM alumnos WHERE id = ?";

try (Connection con =
ConexionBD.getInstancia().getConexion();
PreparedStatement ps =
con.prepareStatement(sql)) {

    ps.setInt(1, id);
    ps.executeUpdate();
}

El valor del identificador se transmite como parámetro, en lugar de concatenarse directamente en la sentencia SQL.

Esta práctica reduce el riesgo de inyección SQL. Los nombres de tablas y columnas deben mantenerse definidos por el programa y no recibirse como entradas libres.

8.2. Integridad referencial

Se utilizan los siguientes mecanismos:

PRIMARY KEY: identifica de forma única cada registro.

FOREIGN KEY: impide referencias a profesores o asignaturas inexistentes.

UNIQUE: evita la duplicidad de correos electrónicos.

NOT NULL: obliga a introducir los campos requeridos.

CHECK: valida determinadas reglas, como las horas de una asignatura y la edad mínima.

8.3. Gestión de recursos

Las conexiones, sentencias y resultados JDBC se gestionan mediante try-with-resources, que permite cerrarlos automáticamente al finalizar cada operación.

8.4. Límites de seguridad

La aplicación es un prototipo local de consola. No incorpora autenticación de usuarios, perfiles de acceso ni una interfaz remota.

Para un entorno de producción será necesario evaluar permisos, protección de credenciales, copias de seguridad, validaciones adicionales y pruebas específicas de seguridad.

9. Instalación y ejecución

9.1. Requisitos previos

Java JDK 17 o superior.

IntelliJ IDEA 2026.2.3.

Maven, integrado o configurado en IntelliJ IDEA.

Acceso a la dependencia H2 mediante Maven durante la configuración inicial.

9.2. Procedimiento

Descargar y descomprimir el proyecto.

Abrir IntelliJ IDEA.

Seleccionar Open y elegir la carpeta raíz que contiene pom.xml.

Esperar a que IntelliJ importe el proyecto Maven y descargue las dependencias.

Configurar el JDK del proyecto en la versión 17 o superior.

Abrir src/main/java/com/academia/Main.java.

Ejecutar el método main().

Utilizar las opciones que aparecen en la consola.

9.3. Almacenamiento de la base de datos

La conexión está configurada con la siguiente URL:

jdbc:h2:./data/academia;DB_CLOSE_ON_EXIT=FALSE

La base de datos se almacena en un archivo local, normalmente:

data/academia.mv.db

La ubicación efectiva depende del directorio de trabajo desde el que se ejecute la aplicación.

La inicialización crea las tablas que falten y carga los datos de ejemplo cuando cada tabla está vacía. No se trata de una operación de borrado y recreación de la base de datos.

10. Menú de operaciones

La aplicación ofrece las siguientes opciones:

Opción

Operación

1

Consultar alumnos.

2

Consultar profesores.

3

Consultar asignaturas.

4

Mostrar resumen académico mediante JOIN.

5

Mostrar estadísticas mediante COUNT y AVG.

6

Dar de alta un alumno.

7

Eliminar un alumno por ID.

0

Salir de la aplicación.

11. Plan de validación funcional

Antes de aprobar formalmente el proyecto, se recomienda ejecutar las siguientes pruebas y documentar sus resultados.

Prueba

Procedimiento

Resultado esperado

Inicialización

Ejecutar por primera vez

Se crean las tres tablas y se cargan tres registros por tabla.

Persistencia

Cerrar y volver a ejecutar

Los registros existentes se conservan y no se duplican los datos iniciales.

Consulta simple

Seleccionar opción 1

Se muestra el listado de alumnos.

Consulta JOIN

Seleccionar opción 4

Se muestran los datos relacionados de alumno, asignatura y profesor.

Agregación

Seleccionar opción 5

Se muestran recuentos y edades medias por asignatura.

Alta válida

Introducir un email nuevo y datos válidos

El alumno se inserta correctamente.

Email duplicado

Reutilizar un email existente

La restricción UNIQUE rechaza la inserción.

Clave foránea

Introducir un ID de asignatura inexistente

La base de datos rechaza la inserción.

Entrada no numérica

Introducir texto en un campo numérico

El programa solicita un entero válido.

Eliminación

Introducir un ID existente

El alumno se elimina y se informa del resultado.

Importante: esta tabla representa un plan de pruebas. Las pruebas deben ejecutarse y registrarse en el entorno de desarrollo antes de considerar los resultados validados.

12. Limitaciones conocidas

La versión actual presenta las siguientes limitaciones:

Interfaz de consola, sin interfaz gráfica ni web.

CRUD completo pendiente para profesores y asignaturas.

Operación de modificación de registros todavía no implementada.

Relación simplificada que permite una sola asignatura por alumno.

Ausencia de una tabla específica de matrículas.

Validación básica del formato de correo electrónico.

Ausencia de pruebas automatizadas y de un proceso formal de despliegue.

Ausencia de un procedimiento integrado de copias de seguridad.

Estas limitaciones son coherentes con el alcance de un prototipo inicial y deben considerarse antes de su utilización operativa.

13. Evolución propuesta

Para avanzar hacia una aplicación de gestión académica más completa se proponen las siguientes fases:

Completar las operaciones CRUD de alumnos, profesores y asignaturas.

Incorporar la tabla MATRICULAS para representar relaciones muchos-a-muchos.

Implementar pruebas automatizadas para consultas, restricciones e integridad referencial.

Mejorar las validaciones de entrada y los mensajes de error.

Añadir transacciones para operaciones que involucren varias tablas.

Incorporar informes y exportación de listados.

Evaluar una interfaz gráfica o web según las necesidades del centro.

Definir autenticación, permisos, copias de seguridad y procedimientos de despliegue si el sistema pasa a producción.

14. Conclusión

La aplicación Academia de Formación constituye una primera base técnica para demostrar la integración de Java, JDBC y una base de datos relacional H2.

La separación por capas, el uso de claves primarias y foráneas, las consultas SQL simples y compuestas, las funciones de agregación y la parametrización de consultas proporcionan una base adecuada para validar los conceptos fundamentales del proyecto.
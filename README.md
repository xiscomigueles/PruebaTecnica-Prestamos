# PruebaTecnica-Prestamos
Prueba tecnica de microservicio sobre Prestamo

## 1. Instrucciones para ejecutar el proyecto

El proyecto está desarrollado con **Spring Boot 4**, usando **Java 21**, y cuenta con documentación de API disponible vía **Swagger** en [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

### Ejecución desde IntelliJ IDEA

1. Abre el proyecto en IntelliJ IDEA.
2. Asegúrate de que tengas configurada la **JDK 21** en tu proyecto.
3. Navega hasta la clase principal `PrestamosApplication` (o equivalente) que contiene el método `main`.
4. Haz clic derecho sobre la clase y selecciona **Run 'PrestamosApplication'**.
5. Una vez iniciado, abre tu navegador y accede a:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
   Aquí podrás interactuar con todas las APIs expuestas.

### Ejecución desde la consola (sin IDE)

1. Abre una terminal y navega hasta la carpeta raíz del proyecto (donde se encuentra el `pom.xml`).
2. Ejecuta el siguiente comando para compilar y empaquetar la aplicación:

   ```bash
   mvn clean package
   
3. Una vez compilado, ejecuta el JAR generado en la carpeta target:

    ```bash
   java -jar target/prestamos-0.0.1-SNAPSHOT.jar
   
4. La aplicación se iniciará en http://localhost:8080. Para acceder a la documentación de Swagger, abre: http://localhost:8080/swagger-ui/index.html

### Notas sobre Swagger y autenticación

- Swagger solo estará disponible mientras la aplicación esté corriendo.
- **Autenticación**: Para poder usar las APIs desde Swagger, es necesario incluir un token en la cabecera **Authorization**.  
  Se soportan dos tipos de token:
    - `cliente-token` → Acceso a APIs de cliente.
    - `gestor-token` → Acceso a APIs de gestor/admin.
- El token debe enviarse como **Bearer Token**.

Sin este token, los endpoints no responderán.

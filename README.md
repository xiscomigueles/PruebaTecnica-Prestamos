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
   mvnw clean package 
   ```
    Importante: tener la jdk 21 en la variable de entorno de tu SO.
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

## 2. Arquitectura y decisiones técnicas

Este proyecto implementa un **microservicio para la gestión de solicitudes de préstamos personales**, basado en el enunciado proporcionado. La API REST permite:

- Crear solicitudes de préstamo.
- Modificar el estado de una solicitud: `Pendiente → Aprobada/Rechazada → Cancelada`.
- Consultar el historial de solicitudes o el detalle de una solicitud específica.

### Arquitectura

Se ha optado por una **arquitectura DDD (Domain-Driven Design) con enfoque hexagonal**, aplicando principios de **SOLID** y separación de responsabilidades. Aunque el proyecto es un **módulo único** por motivos de demostración, la arquitectura está diseñada para escalar fácilmente a **multi-módulos** en proyectos más grandes.

La estructura de carpetas refleja esta organización:

```bash
prestamos
│
├── application 
├── boot 
├── domain 
│ ├── enumerations
│ ├── repository
│ ├── usecase
│ └── vo
├── infrastructure 
│ ├── repository
│ └── security
└── rest 
├── beans
├── controller
└── mapper
```

- **boot:** clase principal de Spring Boot para iniciar la aplicación.
- **domain:** contiene la lógica de negocio y modelos del dominio, incluyendo enums y VO.
- **application:** implementación de los casos de uso, actuando como la capa de servicio.
- **infrastructure:** repositorios (simulados en memoria) y configuración de seguridad.
- **rest:** controladores, mappers y beans para exponer los endpoints de la API.

Esta organización permite que **el dominio sea independiente**, mientras que los demás módulos (REST, infraestructura, application) puedan sustituirse o modificarse sin afectar la lógica de negocio.

---

### Decisiones técnicas

- **Lenguaje y framework:** Java 21 y Spring Boot 4.
- **Dependencias utilizadas:**
    - `spring-boot-starter-webmvc` → para exponer la API REST.
    - `spring-boot-devtools` → recarga en caliente durante el desarrollo.
    - `lombok` → reducción de código repetitivo para getters, setters y constructores.
    - `hibernate-validator` → validación de datos de entrada.
    - `spring-boot-starter-test` → pruebas unitarias y de integración.
    - `jakarta.jakartaee-core-api` → anotaciones estándar de Java EE.
    - `spring-boot-starter-security` → control de acceso basado en roles.
    - `springdoc-openapi-starter-webmvc-ui` → documentación y testing de la API vía Swagger.

- **Seguridad:** se han definido **dos roles** tras el enunciado proporcionado:
    - `cliente-token`: permite crear solicitudes de préstamo.
    - `gestor-token`: permite consultar, aprobar, rechazar y cancelar solicitudes.  
      La autenticación se realiza mediante **Bearer Token** en los endpoints de la API.

- **Persistencia:** almacenamiento en memoria mediante mapas (`Map`) similar a H2 console para simular base de datos. Se ha añadido un **delete físico** que solo se permite si la solicitud está `Cancelada`.

- **Testing:** por limitaciones de tiempo se han implementado pruebas únicamente sobre la capa de `application` para la consulta de solicitudes. La misma estructura de tests puede aplicarse a los demás módulos (REST, repository, domain).

- **Documentación en el código:** en el dominio se han añadido comentarios descriptivos sobre clases, métodos y variables. Aunque no se replicaron en todos los módulos por tiempo, es una buena práctica que facilita la comprensión del código a nuevos desarrolladores.

---

Esta arquitectura asegura que la **lógica de negocio se mantenga centralizada**, mientras que la infraestructura, seguridad y exposición REST son fácilmente intercambiables, haciendo que el sistema sea **mantenible y escalable a largo plazo**.

## 3. Mejoras y extensiones futuras

Con más tiempo y recursos, se podrían implementar diversas mejoras y extensiones tanto en el **ámbito técnico** como en el **funcional**, para hacer la aplicación más robusta, mantenible y cercana a un sistema bancario real.

---

### Mejoras técnicas

- **Pruebas completas:**
    - Implementar tests faltantes en todas las capas (`application`, `rest`, `repository`).
    - Añadir **tests de integración** para validar la interacción entre módulos.
    - Incorporar **tests de carga y estrés** para evaluar el comportamiento de la aplicación con un gran volumen de solicitudes de préstamo y garantizar la sostenibilidad del sistema.

- **Validaciones del dominio:**
    - Mejorar las validaciones en las entidades del dominio para asegurar la integridad de los datos y respuestas correctas desde la API.
    - Esto incluye validaciones más estrictas sobre documentos identificativos, importes y fechas.

- **Documentación:**
    - Completar la documentación de **OpenAPI/Swagger** para que sea completamente descriptiva.
    - Añadir comentarios claros en todas las capas del proyecto para mejorar la legibilidad y facilitar la incorporación de nuevos desarrolladores.

- **Principios y patrones:**
    - Continuar aplicando **SOLID** y considerar el uso de **patrones de diseño** adicionales (Factory, Strategy, Observer, etc.) según las necesidades del negocio o la complejidad futura.

---

### Mejoras funcionales

- **Validación de solicitudes:**
    - Validar que el DNI/NIE proporcionado exista en la base de datos o en sistemas externos de verificación antes de crear una solicitud.

- **Gestión del historial:**
    - Actualmente se permite un **borrado físico** de préstamos cancelados. Para conservar el historial, se podría implementar un **borrado lógico** mediante un estado “Eliminado” y ejecutar periódicamente un job para borrar físicamente registros antiguos si fuera necesario.

- **Refinamiento de procesos bancarios:**
    - Incorporar reglas más sofisticadas para la aprobación de préstamos: análisis de situación laboral, saldo bancario, estadísticas históricas, scoring crediticio, etc.
    - Esto permitiría un flujo de negocio más realista, similar a sistemas de bancos actuales.

---

### Mejoras arquitecturales

- **Persistencia:**
    - Migrar de almacenamiento en memoria a una **base de datos relacional** como PostgreSQL u Oracle, garantizando soporte ACID, seguridad y consistencia para uso bancario.
    - No se recomienda usar bases NoSQL para este tipo de aplicación financiera debido a la falta de transacciones ACID por diseño.

- **Escalabilidad y modularidad:**
    - Mantener la arquitectura DDD + hexagonal, que es muy adaptable.
    - Separar el proyecto en **múltiples módulos Maven**, permitiendo una mayor independencia entre dominio, infraestructura y exposición REST, especialmente útil en proyectos de mayor envergadura.

Estas mejoras permitirían que el microservicio no solo cumpla con los requisitos funcionales actuales, sino que también sea **robusto, escalable, mantenible y alineado con prácticas bancarias profesionales**.

# MindConnect

MindConnect es una API REST académica construida con Java 21, Spring Boot, Maven, PostgreSQL, Flyway, Spring Security, JWT, DDD y arquitectura hexagonal.

El proyecto implementa 52 tablas. Por requisito académico, cada tabla tiene su propio bounded context repetido explícitamente en las capas `domain`, `application` e `infrastructure`. No son microservicios: todos los contextos forman una sola aplicación Spring Boot y una sola base de datos.

## Estado verificado

- Reactor Maven con cuatro proyectos visibles: `springboot` (padre), `domain`, `application` e `infrastructure`.
- 52 bounded contexts en cada capa.
- 52 migraciones Flyway de negocio y 4 migraciones técnicas de seguridad (V53-V56).
- 165 pruebas ejecutadas correctamente: 59 en `domain` y 106 en `application`.
- JAR ejecutable de Spring Boot generado desde `infrastructure`.
- PostgreSQL JDBC y soporte Flyway para PostgreSQL, sin dependencias de MySQL.
- Autenticación stateless con access token JWT, refresh tokens revocables y roles.
- Hibernate configurado con `ddl-auto: validate`; Hibernate no crea ni modifica el esquema.

## Tecnologías

| Componente | Versión verificada |
| --- | --- |
| Java | 21 |
| Maven Wrapper | Maven 3.9.16 |
| Spring Boot | 4.0.2 |
| Hibernate ORM | 7.2.1.Final |
| Flyway | 11.14.1 |
| PostgreSQL JDBC | 42.7.9 |
| Base de datos | PostgreSQL 16+ |
| Spring Security | Administrada por Spring Boot 4.0.2 |
| JJWT | 0.12.6 |

Las versiones transitivas se administran mediante el BOM de Spring Boot declarado en el POM raíz.

## Organización del proyecto

```text
springboot/
├── pom.xml                 # Padre Maven; packaging=pom
├── application/            # Casos de uso, commands, responses y excepciones de aplicación
├── domain/                 # Agregados, value objects, eventos y puertos
├── infrastructure/         # REST, JPA, adaptadores, configuración y aplicación ejecutable
├── .mvn/wrapper/           # Maven Wrapper reproducible
├── mvnw
├── mvnw.cmd
├── .env.example            # Plantilla de configuración sin secretos
└── .vscode/                # Importación y arranque desde VS Code
```

Los módulos son carpetas hermanas. No hay módulos Maven dentro de `src/main/java`.

### Dirección de dependencias

```text
infrastructure ──> application ──> domain
```

- `domain` no depende de Spring ni de JPA.
- `application` solo depende de `domain` y tampoco usa Spring/JPA.
- `infrastructure` conecta los puertos con Spring Web, Spring Data JPA, Flyway, PostgreSQL y Spring Security.

Una petición REST sigue este recorrido:

```text
Controller -> UseCase -> Repository (puerto) -> RepositoryAdapter -> JpaRepository -> PostgreSQL
```

Los beans se crean explícitamente en las clases `*BeansConfig`; los casos de uso no llevan `@Service`.

## Patrón de cada bounded context

Cada entidad reproduce el patrón de `Country`:

- `application`: dos commands, un response, una excepción y cinco casos de uso CRUD.
- `domain`: tres eventos, agregado, identificador UUID y puerto de repositorio.
- `infrastructure`: controller, dos requests REST, entidad JPA, mapper, repositorio JPA, adapter y configuración de beans.
- `domain/common/exception`: una excepción de dominio específica por entidad.
- Pruebas del agregado y del caso de uso de eliminación.

Las capas usan UUID en Java y conservan `CHAR(36)` en PostgreSQL para mantener compatibles los 52 mapeos JPA existentes. Los nombres SQL se conservan mediante mapeos explícitos.

## Las 52 tablas

Las migraciones están en:

```text
infrastructure/src/main/resources/db/migration/
```

Están organizadas por dependencias desde `V1` hasta `V52`:

- Geografía y catálogos: `countries`, `state_regions`, `city_municipalities`, `document_types`, `genders`, `relationship_types`, `professional_types`, `studies`.
- Personas y contactos: `professionals`, `patients`, `contacts`, `phone_contacts`, `email_contacts`, `patient_contacts`, `patient_allergies`, `professional_studies`.
- Información clínica: `clinical_record_statuses`, `clinical_records`, `encounter_types`, `encounter_modalities`, `encounter_statuses`, `encounters`, `risk_levels`, `risk_assessments`, `clinical_notes`, `mental_status_exams`, `treatment_statuses`, `treatment_plans`, `treatment_goal_statuses`, `treatment_goals`, `medication_routes`, `assessment_types`, `consent_types`, `diagnostic_systems`.
- Chat: `conversations_statuses`, `priorities`, `sender_types`, `message_types`, `chat_conversations`, `chat_participants`, `chat_messages`.
- IA: `provider_models_ai`, `ai_models`, `chat_conversation_ai_settings`, `ai_runs_statuses`, `chat_ai_runs`, `chat_ai_run_metrics`, `chat_ai_run_errors`.
- Escalaciones: `escalations_statuses`, `chat_escalations`, `chat_escalation_assignments`, `chat_escalation_status_history`.

La seguridad agrega cuatro tablas técnicas mediante V53-V56: `auth_roles`, `auth_users`, `auth_user_roles` y `auth_refresh_tokens`. Las migraciones cargan los roles `ROLE_USER`, `ROLE_ADMIN` y `ROLE_MODERATOR`.

No se deben editar migraciones que ya hayan sido aplicadas. Tampoco se debe ejecutar `flyway clean` sobre una base con datos.

Esta rama convierte V1-V52 del dialecto MySQL a PostgreSQL, por lo que debe desplegarse sobre una base PostgreSQL vacía. El historial y los datos de una base MySQL previa no se migran automáticamente.

# Tutorial de instalación y arranque

Los siguientes pasos parten de una computadora nueva y no requieren una instalación global de Maven.

## 1. Instalar los requisitos

Instala:

1. Git.
2. Un JDK 21 completo, por ejemplo Eclipse Temurin 21. Debe incluir `java` y `javac`.
3. PostgreSQL 16 o una versión compatible.
4. Opcionalmente, VS Code con `Extension Pack for Java` y `Spring Boot Extension Pack`.

No basta con instalar un JRE: Maven necesita el compilador `javac`.

Comprueba Java desde una terminal nueva:

```powershell
java -version
javac -version
```

Ambos deben mostrar la versión 21. Si hay varias versiones instaladas, configura `JAVA_HOME` con la ruta real de tu JDK 21.

Ejemplo temporal en PowerShell:

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

La carpeta exacta puede variar. Confírmala antes de copiar el ejemplo.

En Linux o macOS:

```bash
export JAVA_HOME=/ruta/real/al/jdk-21
export PATH="$JAVA_HOME/bin:$PATH"
```

## 2. Obtener y abrir el proyecto

Clona el repositorio o descomprime la entrega. Después entra en la carpeta que contiene el `pom.xml` raíz:

```powershell
cd C:\ruta\al\proyecto\springboot
```

Todos los comandos de esta guía deben ejecutarse desde esa raíz. Esto también permite que Spring encuentre el archivo `.env`.

Comprueba el wrapper:

```powershell
.\mvnw.cmd -version
```

En Linux/macOS:

```bash
chmod +x mvnw
./mvnw -version
```

La primera ejecución puede descargar Maven y dependencias desde Maven Central, por lo que necesita acceso a Internet.

## 3. Crear una base PostgreSQL vacía

Entra a PostgreSQL con una cuenta administradora y crea un usuario y una base separados:

```sql
CREATE ROLE mindconnect_user WITH LOGIN PASSWORD 'UNA_CLAVE_SEGURA';
CREATE DATABASE mindconnect OWNER mindconnect_user;
```

`CREATE DATABASE` debe ejecutarse fuera de una transacción. Si la base o el usuario ya existen, revisa el entorno antes de repetir estas sentencias.

También se puede usar PostgreSQL local en Docker:

```powershell
docker run --name mindconnect-postgres `
  -e POSTGRES_DB=mindconnect `
  -e POSTGRES_USER=mindconnect_user `
  -e POSTGRES_PASSWORD=UNA_CLAVE_SEGURA `
  -p 5432:5432 `
  -d postgres:16
```

Comprueba el arranque con `docker logs -f mindconnect-postgres` y detén la espera cuando PostgreSQL acepte conexiones.

## 4. Crear el archivo local `.env`

Desde la raíz del proyecto:

```powershell
Copy-Item .env.example .env
```

En Linux/macOS:

```bash
cp .env.example .env
```

Edita `.env` y coloca los datos reales de tu instalación local:

```properties
DB_URL=jdbc:postgresql://localhost:5432/mindconnect
DB_USERNAME=mindconnect_user
DB_PASSWORD=UNA_CLAVE_SEGURA
SPRING_PROFILES_ACTIVE=dev
SERVER_PORT=8081
CORS_ALLOWED_ORIGINS=http://localhost:5173,http://localhost:3000
QUEUE_BLOCK_USERS_FIXED_DELAY_MS=5000
JWT_SECRET=una_clave_aleatoria_larga_de_32_caracteres_o_mas
JWT_ACCESS_TOKEN_EXPIRATION=900000
JWT_REFRESH_TOKEN_EXPIRATION=604800000
```

Reglas importantes:

- No confirmes `.env` en Git. Ya está incluido en `.gitignore`.
- `.env.example` solo debe contener valores ficticios.
- No uses por defecto el superusuario `postgres` para la aplicación.
- Usa un `JWT_SECRET` aleatorio de al menos 32 caracteres y nunca lo publiques.
- Si PostgreSQL está en otro host o puerto, cambia `DB_URL`.
- Para producción usa `SPRING_PROFILES_ACTIVE=prod`; ese perfil mantiene `show-sql` desactivado.

Spring carga `.env` mediante `optional:file:.env[.properties]`. Por eso el directorio de trabajo debe ser la raíz del repositorio.

## 5. Compilar y ejecutar todas las pruebas

En Windows:

```powershell
.\mvnw.cmd clean verify
```

En Linux/macOS:

```bash
./mvnw clean verify
```

El comando compila el reactor completo en este orden:

1. `springboot`.
2. `domain`.
3. `application`.
4. `infrastructure`.

No necesita conectarse a PostgreSQL para las pruebas unitarias actuales. Al terminar debe aparecer `BUILD SUCCESS` y se genera:

```text
infrastructure/target/infrastructure-1.0-SNAPSHOT.jar
```

## 6. Arrancar la aplicación

Asegúrate de que PostgreSQL esté iniciado y ejecuta desde la raíz:

```powershell
java -jar .\infrastructure\target\infrastructure-1.0-SNAPSHOT.jar
```

En Linux/macOS:

```bash
java -jar ./infrastructure/target/infrastructure-1.0-SNAPSHOT.jar
```

Durante el arranque:

1. Spring lee `.env`.
2. Se crea el pool de conexiones hacia PostgreSQL.
3. Flyway aplica en orden las 56 migraciones pendientes (52 de negocio y 4 de seguridad).
4. Flyway registra su historial en `flyway_schema_history`.
5. Hibernate valida que las entidades coincidan con las tablas; no genera el esquema.
6. El servidor queda disponible, por defecto, en `http://localhost:8081`.

El primer arranque en una base vacía puede tardar un poco más por las migraciones. Busca un mensaje de Spring Boot indicando que `DemoApplication` inició correctamente.

Detén la aplicación con `Ctrl+C`.

## 7. Registrar un usuario e iniciar sesión

Los endpoints públicos son `POST /api/auth/register`, `POST /api/auth/login` y `POST /api/auth/refresh`. Todos los endpoints restantes requieren un access token válido.

```powershell
$registerBody = @{
    email = "user@example.com"
    password = "password123"
} | ConvertTo-Json

Invoke-RestMethod `
    -Method Post `
    -Uri "http://localhost:8081/api/auth/register" `
    -ContentType "application/json" `
    -Body $registerBody

$session = Invoke-RestMethod `
    -Method Post `
    -Uri "http://localhost:8081/api/auth/login" `
    -ContentType "application/json" `
    -Body $registerBody

$headers = @{ Authorization = "Bearer $($session.accessToken)" }
```

`$session.refreshToken` permite obtener un access token nuevo y se revoca mediante `POST /api/auth/logout`. También están disponibles `GET /api/auth/me` y `PUT /api/auth/change-password`.

La asignación de roles está protegida con `ROLE_ADMIN` en `POST /api/users/{userId}/roles`; el cuerpo es `{"role":"ADMIN"}` o `{"role":"ROLE_ADMIN"}`. Para el primer administrador, registra la cuenta, consulta su `id` y realiza una única asignación controlada directamente en PostgreSQL:

```sql
INSERT INTO auth_user_roles (user_id, role_id)
SELECT 'UUID_DEL_USUARIO', id
FROM auth_roles
WHERE authority = 'ROLE_ADMIN'
ON CONFLICT DO NOTHING;
```

Después vuelve a iniciar sesión para que el nuevo rol se incluya en el JWT.

## 8. Probar el CRUD de Country

Country no requiere crear previamente otra entidad, por lo que sirve como prueba inicial.

### Crear

En PowerShell:

```powershell
$body = @{
    nameCountry = "Colombia"
    codeCountry = "CO"
    description = "Colombia"
    active = $true
    telephonePrefix = "+57"
} | ConvertTo-Json

$country = Invoke-RestMethod `
    -Method Post `
    -Uri "http://localhost:8081/api/countries" `
    -Headers $headers `
    -ContentType "application/json" `
    -Body $body

$country
```

La respuesta tiene estado HTTP `201 Created` y contiene un UUID. Guárdalo para las demás operaciones:

```powershell
$countryId = $country.id
```

### Listar y consultar

```powershell
Invoke-RestMethod -Method Get -Uri "http://localhost:8081/api/countries" -Headers $headers
Invoke-RestMethod -Method Get -Uri "http://localhost:8081/api/countries/$countryId" -Headers $headers
```

### Actualizar

```powershell
$updatedBody = @{
    nameCountry = "Colombia"
    codeCountry = "COL"
    description = "República de Colombia"
    active = $true
    telephonePrefix = "+57"
} | ConvertTo-Json

Invoke-RestMethod `
    -Method Put `
    -Uri "http://localhost:8081/api/countries/$countryId" `
    -Headers $headers `
    -ContentType "application/json" `
    -Body $updatedBody
```

### Eliminar

```powershell
Invoke-RestMethod -Method Delete -Uri "http://localhost:8081/api/countries/$countryId" -Headers $headers
```

La eliminación correcta devuelve `204 No Content`.

Los demás controllers siguen el mismo patrón general:

```text
POST   /api/recurso
GET    /api/recurso
GET    /api/recurso/{uuid}
PUT    /api/recurso/{uuid}
DELETE /api/recurso/{uuid}
```

Los nombres exactos de las rutas y los cuerpos requeridos están en los `*Controller.java` y los records `Create*Request`/`Update*Request` de cada contexto. En entidades con claves foráneas primero deben existir los registros referenciados.

## 9. Arrancar desde VS Code

1. Abre en VS Code la carpeta que contiene el `pom.xml` raíz, no un módulo aislado.
2. Comprueba que `.env` exista en esa raíz.
3. Abre la paleta con `Ctrl+Shift+P`.
4. Ejecuta `Java: Clean Java Language Server Workspace` y confirma la recarga si los proyectos no aparecen.
5. Ejecuta `Maven: Reload Projects`.
6. En **Java Projects** deben aparecer `springboot`, `domain`, `application` e `infrastructure`.
7. Abre **Run and Debug** y selecciona `Spring Boot-DemoApplication<infrastructure>`.
8. Inicia con `F5`.

La configuración usa:

- Clase principal: `springboot.infrastructure.DemoApplication`.
- Proyecto: `infrastructure`.
- Directorio de trabajo: `${workspaceFolder}`.
- Variables: `${workspaceFolder}/.env`.

## Configuración relevante

La configuración común está en `infrastructure/src/main/resources/application.yml`:

- Driver `org.postgresql.Driver` y dialecto `PostgreSQLDialect`.
- Flyway habilitado en `classpath:db/migration`.
- Validación estricta de nombres de migración.
- `spring.jpa.hibernate.ddl-auto=validate`.
- `PhysicalNamingStrategyStandardImpl`, para respetar los nombres explícitos de tablas y columnas.
- `open-in-view=false`.
- Seguridad stateless: BCrypt para contraseñas, JWT de 15 minutos y refresh tokens de 7 días por defecto.

Los perfiles solo controlan actualmente la impresión del SQL:

- `application-dev.yml`: `show-sql=true`.
- `application-prod.yml`: `show-sql=false`.

## Comandos útiles

Compilar y probar todo:

```powershell
.\mvnw.cmd clean verify
```

Compilar sin limpiar los resultados anteriores:

```powershell
.\mvnw.cmd verify
```

Ver el árbol real de dependencias:

```powershell
.\mvnw.cmd -pl infrastructure -am dependency:tree
```

Comprobar las migraciones aplicadas desde PostgreSQL:

```sql
SELECT installed_rank, version, description, success
FROM flyway_schema_history
ORDER BY installed_rank;
```

## Solución de problemas

### `JAVA_HOME` apunta a una ruta incorrecta

Comprueba:

```powershell
$env:JAVA_HOME
java -version
javac -version
.\mvnw.cmd -version
```

Los tres últimos comandos deben usar Java 21.

### `Could not resolve placeholder`

Falta `.env`, está incompleto o la aplicación se inició desde otro directorio. Son obligatorias las variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` y `JWT_SECRET`.

### No se puede conectar a PostgreSQL

Comprueba que PostgreSQL esté iniciado, que `.env` exista y que la aplicación haya sido iniciada desde la raíz del proyecto.

### `database "mindconnect" does not exist`

Crea la base vacía descrita en el paso 3. Flyway crea las tablas, pero la base debe existir antes de iniciar.

### `password authentication failed`

Comprueba `DB_USERNAME`, `DB_PASSWORD`, `pg_hba.conf` y que el usuario sea propietario o tenga permisos sobre la base. No coloques la contraseña en el código ni en el repositorio.

### El puerto 5432 ya está ocupado

Si usas Docker y ya tienes PostgreSQL local, detén uno de los servicios o publica el contenedor en otro puerto, por ejemplo `-p 5433:5432`, y usa:

```properties
DB_URL=jdbc:postgresql://localhost:5433/mindconnect
```

### El puerto 8081 ya está ocupado

Cambia en `.env`:

```properties
SERVER_PORT=8082
```

### Flyway informa un checksum diferente

Una migración aplicada fue modificada o la base proviene de otra versión. No ejecutes `clean` ni `repair` automáticamente y no edites el SQL para ocultar el problema. Compara las migraciones con la versión que creó esa base o utiliza una base nueva de desarrollo identificada como desechable.

### Hibernate indica que falta una tabla o columna

Hibernate está en modo `validate`, así que el mensaje suele señalar que Flyway no terminó, se utilizó otra base o el esquema fue alterado manualmente. Revisa primero el log de Flyway, `DB_URL` y `flyway_schema_history`.

### VS Code no muestra cuatro proyectos

Abre la raíz correcta y ejecuta, en este orden:

1. `Java: Clean Java Language Server Workspace`.
2. `Maven: Reload Projects`.
3. `Developer: Reload Window`, si los dos pasos anteriores no bastan.

## Alcance funcional

La entrega implementa persistencia y CRUD explícito para los 52 contextos, además de autenticación JWT y autorización por roles. Los nombres clínicos, de chat o IA no implican lógica médica ni conexión con modelos de IA.

## Seguridad y mantenimiento

- Nunca publiques `.env` ni credenciales reales.
- Cambia siempre el `JWT_SECRET` de ejemplo antes de desplegar.
- Usa HTTPS en producción para proteger tokens y credenciales en tránsito.
- Utiliza una base de pruebas separada para ensayos que escriban datos.
- No uses una base compartida sin autorización.
- No ejecutes `flyway clean` sobre información que necesites conservar.
- No cambies migraciones ya aplicadas; añade una migración nueva para evoluciones futuras.
- Ejecuta `clean verify` antes de entregar cambios.

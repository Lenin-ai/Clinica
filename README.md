yecto Clínica Backend

## 📌 Descripción
Este proyecto corresponde al **backend del sistema de gestión clínica**, desarrollado con **Spring Boot** y **MySQL**.  
El sistema permite la gestión de pacientes, médicos, citas médicas y autenticación mediante **JWT**.

Incluye configuración para ejecutarse fácilmente mediante **Docker Compose**, sin necesidad de instalar MySQL localmente.

---

## ⚙️ Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalados:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Git

---

## 📁 Estructura del proyecto

```
.
├── Dockerfile
├── docker-compose.yml
├── .env
├── src/
│   ├── main/
│   └── test/
└── README.md
```

---

## 🧩 Archivo `.env`

Crea un archivo `.env` en la raíz del proyecto con el siguiente contenido:

```env
MYSQL_USER=root
MYSQL_PASSWORD=12345678
SPRING_DATASOURCE_URL=jdbc:mysql://mysql-clinica:3306?createDatabaseIfNotExist=true&serverTimezone=America/Lima&allowPublicKeyRetrieval=true&useSSL=false
JWT_SECRET=TDSZydxbpLTzBiSyAdfK6qzd8nBt9WeOFBO-Pi7NO5X1IWgLA594XmYEj99lEK_ZEyKKs2dkmIe8g1dFBYuQJg
```

---

## 🐳 Ejecución con Docker Compose

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/Lenin-ai/Clinica.git
   cd Clinica
   ```

2. **Construye y levanta los contenedores:**
   ```bash
   docker-compose up --build -d
   ```

3. **Verifica que los servicios estén corriendo:**
   ```bash
   docker ps
   ```

   Deberías ver:
   ```
   CONTAINER ID   IMAGE               PORTS
   xxxxxxx        mysql:8.0           3307->3306/tcp
   yyyyyyy        clinica-backend     8080->8080/tcp
   ```

4. **Accede al backend:**
   ```
   http://localhost:8080
   ```
5. **Accede al Swagger:**
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 🗂️ Base de datos

- Host interno: `mysql-clinica`
- Puerto: `3306`
- Usuario: `root`
- Contraseña: `12345678`
- Base de datos: `clinica_db`

Para acceder manualmente:
```bash
docker exec -it mysql-clinica mysql -uroot -p12345678
```

---

## 🧹 Comandos útiles

- **Ver logs:**
  ```bash
  docker-compose logs -f clinica
  ```
- **Reiniciar contenedores:**
  ```bash
  docker-compose restart
  ```
- **Detener y eliminar contenedores:**
  ```bash
  docker-compose down
  ```

---

## 📖 Notas finales
- El proyecto originalmente se conectaba a una base **RDS en AWS**, pero para esta versión pública se incluye un contenedor MySQL interno.
- JWT está configurado mediante la variable `JWT_SECRET` en el archivo `.env`.
- Si deseas desplegar en AWS EC2, basta con clonar el repositorio y ejecutar los mismos comandos.

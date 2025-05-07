
# Sistema de Gestión Integral de Ventas, Compras y Almacén

Este proyecto es una aplicación web monolítica desarrollada en **Spring Boot con Thymeleaf** como motor de plantillas. Está orientada a la gestión de ventas, compras y operaciones de almacén, y está diseñada para ofrecer una solución completa en la administración de inventario y procesos comerciales.
## Características Principales

- **Gestión de Categorías:** Crear, editar y eliminar categorías de productos.
- **Entradas y Salidas de Almacén:** Control y registro de movimientos de inventario (entradas y salidas).
- **Gestión de Productos:** Registro y manejo de productos con sus respectivos detalles y cantidades en stock.
- **Gestión de Proveedores:** Administración de la información de proveedores para compras.
- **Reportes y Exportación:** Generación de reportes en distintos formatos para análisis y auditoría.
- **Notificaciones:** Sistema de alertas para mantener informado al usuario sobre productos con stok crítico.
- **Gestión de Roles y Usuarios:** Módulo de control de acceso con distintos roles (administrador, vendedor, etc.).
- **Vista de Inventario:** Consulta del estado actual del inventario para facilitar la toma de decisiones.

## Instalación y Configuración

### Requisitos Previos

- **Java 17**
- **Maven** (la instalación de dependencias se gestiona a través de Maven y se define en el archivo `pom.xml`)

### Pasos para la Instalación

1. **Clonar el Repositorio**

   Clona el repositorio en tu máquina local:

   ```bash
   git clone https://github.com/tu_usuario/nombre_del_repositorio.git
   cd nombre_del_repositorio
   ```

2. **Instalar las Dependencias**

   Las dependencias están definidas en el archivo `pom.xml`.
   Para instalarlas, ejecuta:

   ```bash
   mvn clean install
   ```

3. **Ejecutar la Aplicación**

   Una vez instaladas las dependencias, puedes ejecutar el proyecto usando:

   ```bash
   mvn spring-boot:run
   ```

   La aplicación se iniciará en el puerto configurado (por defecto, 8080).

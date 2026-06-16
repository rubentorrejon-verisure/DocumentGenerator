# Random Document Generator

Generador de documentos de prueba en Java con soporte inicial para DNI español.
El proyecto está diseñado para ser fácilmente escalable a otros países y tipos de documentos como IBAN, tarjetas de crédito o números de cuenta.

## Estructura

- `src/main/java/` — código fuente Java
  - `com.example.docgenerator` — paquete principal
  - `com.example.docgenerator.generators.dni` — generador de DNI español
- `bin/` — salida de compilación

## Comenzar

```bash
cd random-doc-generator
build.bat
run.bat
```

## Uso básico

```bash
build.bat
run.bat
```

## Extender

Agregar nuevos generadores en `src/main/java/com/example/docgenerator/generators/` y nuevos países como subpaquetes es la forma recomendada para escalar.

# Random Document Generator

Generador de documentos de prueba con soporte inicial para DNI español.
El proyecto está diseñado para ser fácilmente extensible a otros países y tipos de documentos como IBAN, tarjetas de crédito o números de cuenta.

## Estructura

- `src/`
  - `generators/` — lógica de generación por tipo y país
  - `types/` — definiciones de tipos compartidas
  - `utils/` — utilidades comunes
- `tests/` — pruebas unitarias

## Comenzar

```bash
cd random-doc-generator
npm install
npm test
```

## Uso básico

```ts
import { generateDocument } from "./src";
const dni = generateDocument("ES", "dni");
```

## Extender

Agregar nuevos documentos en `src/generators/` y nuevos países bajo carpetas por tipo es la forma recomendada para escalar.

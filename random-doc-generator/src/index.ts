import { generateDni } from "./generators/dni";
import { CountryCode, DocumentGeneratorOptions, DocumentType } from "./types/document";

export function generateDocument(
  country: CountryCode,
  documentType: DocumentType,
  _options: DocumentGeneratorOptions = {}
): string {
  switch (documentType) {
    case "dni":
      return generateDni(country);
    default:
      throw new Error(`Tipo de documento no soportado: ${documentType}`);
  }
}

export * from "./types/document";

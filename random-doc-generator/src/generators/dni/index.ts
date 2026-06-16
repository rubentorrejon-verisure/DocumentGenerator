import { generateDniEs } from "./es";
import { CountryCode } from "../../types/document";

type DocumentGenerator = () => string;

export const dniGenerators: Record<CountryCode, DocumentGenerator> = {
  ES: generateDniEs,
};

export function generateDni(country: CountryCode): string {
  const generator = dniGenerators[country];
  if (!generator) {
    throw new Error(`No existe generador de DNI para el país: ${country}`);
  }
  return generator();
}

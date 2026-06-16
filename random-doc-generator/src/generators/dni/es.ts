import { randomDigits } from "../../utils/random";

const dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE";

export function generateDniEs(): string {
  const number = randomDigits(8);
  const letter = dniLetters[parseInt(number, 10) % dniLetters.length];
  return `${number}${letter}`;
}

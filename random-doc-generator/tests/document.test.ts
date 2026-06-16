import { describe, expect, it } from "vitest";
import { generateDocument } from "../src";

describe("generateDocument", () => {
  it("genera un DNI español válido", () => {
    const dni = generateDocument("ES", "dni");
    expect(dni).toMatch(/^[0-9]{8}[A-Z]$/);
  });
});

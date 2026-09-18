// ============================================================
// PATRÓN BUILDER (Creacional)
// ============================================================
// ¿CUÁNDO USARLO EN EL EXAMEN?
// Cuando el caso de estudio tenga un objeto COMPLEJO, con
// MUCHOS atributos opcionales, que se arma "paso a paso".
// Ej: armar una pizza personalizada, armar una hoja de vida,
// armar un computador a la medida, armar un contrato,
// construir un reporte con muchas secciones opcionales.
// Pistas en el enunciado: "personalizar", "paso a paso",
// "con distintas configuraciones opcionales", "armar/construir".
//
// CÓMO ADAPTARLO:
// 1. Producto = la clase final compleja (ej: Computador, Pizza).
// 2. Builder = interfaz con un método por cada "parte" a construir.
// 3. ConcreteBuilder = va guardando las partes y arma el producto.
// 4. Director (opcional) = define "recetas" fijas de construcción.
// ============================================================

// ---------- 1. Producto complejo ----------
class Producto {
    private String parteA;
    private String parteB;
    private String parteC;

    public void setParteA(String parteA) { this.parteA = parteA; }
    public void setParteB(String parteB) { this.parteB = parteB; }
    public void setParteC(String parteC) { this.parteC = parteC; }

    @Override
    public String toString() {
        return "Producto [A=" + parteA + ", B=" + parteB + ", C=" + parteC + "]";
    }
}

// ---------- 2. Builder (interfaz) ----------
interface Builder {
    Builder construirParteA(String valor);
    Builder construirParteB(String valor);
    Builder construirParteC(String valor);
    Producto build();
}

// ---------- 3. Builder concreto ----------
class ConcreteBuilder implements Builder {
    private final Producto producto = new Producto();

    @Override
    public Builder construirParteA(String valor) {
        producto.setParteA(valor);
        return this; // permite encadenar llamadas (fluent API)
    }

    @Override
    public Builder construirParteB(String valor) {
        producto.setParteB(valor);
        return this;
    }

    @Override
    public Builder construirParteC(String valor) {
        producto.setParteC(valor);
        return this;
    }

    @Override
    public Producto build() {
        return producto;
    }
}

// ---------- 4. Director (opcional: "recetas" predefinidas) ----------
class Director {
    public Producto construirProductoBasico(Builder builder) {
        return builder.construirParteA("Básico A")
                       .construirParteB("Básico B")
                       .build(); // sin parte C
    }

    public Producto construirProductoCompleto(Builder builder) {
        return builder.construirParteA("Full A")
                       .construirParteB("Full B")
                       .construirParteC("Full C")
                       .build();
    }
}

// ------------------- DEMO / MAIN -------------------
public class Builder_Demo {
    public static void main(String[] args) {
        // Uso directo del builder (personalizado por el cliente)
        Producto p1 = new ConcreteBuilder()
                .construirParteA("Custom A")
                .construirParteC("Custom C") // se puede omitir B
                .build();
        System.out.println(p1);

        // Uso con Director (recetas fijas)
        Director director = new Director();
        Producto p2 = director.construirProductoCompleto(new ConcreteBuilder());
        System.out.println(p2);
    }
}

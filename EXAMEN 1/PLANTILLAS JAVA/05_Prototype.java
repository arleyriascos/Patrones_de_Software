// ============================================================
// PATRÓN PROTOTYPE (Creacional)
// ============================================================
// ¿CUÁNDO USARLO EN EL EXAMEN?
// Cuando el caso de estudio necesite crear objetos NUEVOS
// COPIANDO uno ya existente (en vez de construirlo desde cero),
// porque crearlo desde cero es costoso o porque se quieren
// varias copias parecidas con pequeñas variaciones.
// Ej: clonar una plantilla de documento, duplicar un producto
// de catálogo para crear una variante, clonar un enemigo/objeto
// en un videojuego, copiar la configuración de un pedido anterior.
// Pistas en el enunciado: "duplicar", "clonar", "copiar",
// "crear una variante a partir de uno existente".
//
// CÓMO ADAPTARLO:
// 1. Haz que la clase implemente Cloneable y sobreescriba clone().
// 2. Si la clase tiene atributos que son OBJETOS (no primitivos),
//    hay que clonarlos también manualmente (clonación profunda),
//    si no, ambos objetos compartirán la misma referencia.
// ============================================================

class Prototipo implements Cloneable {
    private String nombre;
    private int valor;
    // Ejemplo de atributo de referencia (para explicar clon profundo)
    private java.util.List<String> etiquetas;

    public Prototipo(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.etiquetas = new java.util.ArrayList<>();
    }

    public void agregarEtiqueta(String etiqueta) {
        etiquetas.add(etiqueta);
    }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setValor(int valor) { this.valor = valor; }

    // Clonación PROFUNDA: copiamos también la lista interna,
    // no solo la referencia
    @Override
    public Prototipo clone() {
        Prototipo copia = new Prototipo(this.nombre, this.valor);
        copia.etiquetas = new java.util.ArrayList<>(this.etiquetas); // copia real
        return copia;
    }

    @Override
    public String toString() {
        return "Prototipo [nombre=" + nombre + ", valor=" + valor
                + ", etiquetas=" + etiquetas + "]";
    }
}

// ------------------- DEMO / MAIN -------------------
public class Prototype_Demo {
    public static void main(String[] args) {
        Prototipo original = new Prototipo("Original", 100);
        original.agregarEtiqueta("base");

        // Creamos una copia y la modificamos SIN afectar al original
        Prototipo copia = original.clone();
        copia.setNombre("Copia modificada");
        copia.setValor(200);
        copia.agregarEtiqueta("nueva");

        System.out.println(original);
        System.out.println(copia);
    }
}

// ============================================================
// PATRÓN FACTORY METHOD (Creacional)
// ============================================================
// ¿CUÁNDO USARLO EN EL EXAMEN?
// Cuando el caso de estudio tenga VARIOS TIPOS de un mismo
// concepto y el sistema deba crear uno u otro según una
// condición, SIN que el código cliente sepa la clase exacta.
// Ej: tipos de vehículos, tipos de pago, tipos de documentos,
// tipos de usuarios, tipos de notificaciones.
// Pistas en el enunciado: "diferentes tipos de...", "según el
// tipo se debe generar...", "puede haber varias variantes de...".
//
// CÓMO ADAPTARLO:
// 1. Producto = la interfaz común (ej: Vehiculo, MetodoPago).
// 2. ProductoConcretoA/B = las variantes reales (ej: Carro, Moto).
// 3. Creador = clase abstracta con el "factoryMethod()".
// 4. CreadorConcretoA/B = deciden qué producto concreto crear.
// ============================================================

// ---------- 1. Producto (interfaz) ----------
interface Producto {
    void operacion();
}

// ---------- 2. Productos concretos ----------
class ProductoConcretoA implements Producto {
    @Override
    public void operacion() {
        System.out.println("Operación del Producto A");
    }
}

class ProductoConcretoB implements Producto {
    @Override
    public void operacion() {
        System.out.println("Operación del Producto B");
    }
}

// ---------- 3. Creador (clase abstracta) ----------
abstract class Creador {

    // Este es el "Factory Method": lo redefine cada subclase
    public abstract Producto crearProducto();

    // Lógica de negocio común que usa el producto creado,
    // sin saber qué clase concreta es
    public void operacionDeNegocio() {
        Producto producto = crearProducto();
        producto.operacion();
    }
}

// ---------- 4. Creadores concretos ----------
class CreadorConcretoA extends Creador {
    @Override
    public Producto crearProducto() {
        return new ProductoConcretoA();
    }
}

class CreadorConcretoB extends Creador {
    @Override
    public Producto crearProducto() {
        return new ProductoConcretoB();
    }
}

// ------------------- DEMO / MAIN -------------------
public class FactoryMethod {
    public static void main(String[] args) {
        Creador creador1 = new CreadorConcretoA();
        creador1.operacionDeNegocio(); // Operación del Producto A

        Creador creador2 = new CreadorConcretoB();
        creador2.operacionDeNegocio(); // Operación del Producto B
    }
}

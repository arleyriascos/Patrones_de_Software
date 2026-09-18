// ============================================================
// PATRÓN ABSTRACT FACTORY (Creacional)
// ============================================================
// ¿CUÁNDO USARLO EN EL EXAMEN?
// Cuando el caso de estudio tenga FAMILIAS de productos
// relacionados entre sí, y debas garantizar que los productos
// creados sean compatibles entre ellos.
// Ej: muebles (Silla+Mesa) estilo Moderno vs Clásico,
// interfaces gráficas (Boton+Checkbox) para Windows vs Mac,
// componentes de un pedido (Bebida+Comida) según el tipo de menú.
// Pistas en el enunciado: "familias de productos", "conjuntos
// relacionados", "variantes que deben ser compatibles entre sí",
// "por región/plataforma/estilo se debe generar un conjunto de...".
//
// DIFERENCIA CLAVE CON FACTORY METHOD:
// Factory Method crea UN producto. Abstract Factory crea
// VARIOS productos relacionados (una familia completa) a la vez.
//
// CÓMO ADAPTARLO:
// 1. Define cuántos productos tiene la familia (aquí 2: A y B).
// 2. Crea las variantes concretas de cada producto (Producto1, Producto2...).
// 3. AbstractFactory declara un método de creación por producto.
// 4. Cada fábrica concreta crea la familia completa coherente.
// ============================================================

// ---------- 1. Productos abstractos de la familia ----------
interface ProductoA {
    void usar();
}

interface ProductoB {
    void usar();
}

// ---------- 2. Familia de productos "Variante 1" ----------
class ProductoA1 implements ProductoA {
    @Override
    public void usar() {
        System.out.println("Usando Producto A - Variante 1");
    }
}

class ProductoB1 implements ProductoB {
    @Override
    public void usar() {
        System.out.println("Usando Producto B - Variante 1");
    }
}

// ---------- 2. Familia de productos "Variante 2" ----------
class ProductoA2 implements ProductoA {
    @Override
    public void usar() {
        System.out.println("Usando Producto A - Variante 2");
    }
}

class ProductoB2 implements ProductoB {
    @Override
    public void usar() {
        System.out.println("Usando Producto B - Variante 2");
    }
}

// ---------- 3. Fábrica abstracta ----------
interface AbstractFactory {
    ProductoA crearProductoA();
    ProductoB crearProductoB();
}

// ---------- 4. Fábricas concretas (una por cada familia) ----------
class Factory1 implements AbstractFactory {
    @Override
    public ProductoA crearProductoA() {
        return new ProductoA1();
    }

    @Override
    public ProductoB crearProductoB() {
        return new ProductoB1();
    }
}

class Factory2 implements AbstractFactory {
    @Override
    public ProductoA crearProductoA() {
        return new ProductoA2();
    }

    @Override
    public ProductoB crearProductoB() {
        return new ProductoB2();
    }
}

// ------------------- DEMO / MAIN -------------------
public class AbstractFactory_Demo {
    public static void main(String[] args) {
        // El cliente solo conoce AbstractFactory, no las clases concretas
        AbstractFactory fabrica = new Factory1(); // podría venir de config/parámetro

        ProductoA a = fabrica.crearProductoA();
        ProductoB b = fabrica.crearProductoB();

        a.usar();
        b.usar();

        System.out.println("--- Cambiando de familia ---");
        fabrica = new Factory2();
        fabrica.crearProductoA().usar();
        fabrica.crearProductoB().usar();
    }
}

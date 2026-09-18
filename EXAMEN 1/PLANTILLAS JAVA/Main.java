import java.util.ArrayList;
import java.util.List;

// ============================================================
// PLANTILLA EXAMEN - 5 PATRONES CREACIONALES EN UN SOLO ARCHIVO
// ============================================================
// Compilar:  javac Main.java        (o:  javac -d out Main.java)
// Ejecutar:  java Main              (o:  java -cp out Main)
//
// REGLAS DE ESTE ARCHIVO:
// - Solo UNA clase es "public": Main (el archivo se llama Main.java).
// - Cada patrón vive dentro de su propia clase contenedora
//   (PatronXxx) con clases anidadas "static". Así NO se chocan
//   los nombres (Producto, Builder, etc.) entre patrones.
// - Buscar "CAMBIAR" para saber qué renombrar según el caso.
//
// QUÉ PATRÓN USAR SEGÚN EL ENUNCIADO:
//   Singleton        -> "único", "global", "una sola instancia"
//   Factory Method   -> "diferentes tipos de...", crea UN objeto según tipo
//   Abstract Factory -> "familias", "conjuntos compatibles" (varios objetos)
//   Builder          -> objeto complejo, "paso a paso", opcionales
//   Prototype        -> "clonar", "duplicar", "copiar uno existente"
//
// EN EL EXAMEN: borra los patrones que no uses (la clase PatronXxx
// completa y su línea en main) y renombra lo que marca CAMBIAR.
// ============================================================

public class Main {
    public static void main(String[] args) {
        // Deja solo los patrones que necesites
        PatronSingleton.demo();
        PatronFactoryMethod.demo();
        PatronAbstractFactory.demo();
        PatronBuilder.demo();
        PatronPrototype.demo();
    }
}

// ============================================================
// 1. SINGLETON
// ============================================================
// CAMBIAR: nombre "Singleton" (ej: ConexionBD, Logger, GestorCaja),
//          atributos y métodos de negocio.
// NO CAMBIAR: constructor privado + getInstance().
class PatronSingleton {

    static class Singleton {
        private static Singleton instancia;   // instancia única

        private String estado;                // CAMBIAR: atributos propios

        private Singleton() {                 // constructor PRIVADO
            this.estado = "Inicializado";
            System.out.println("Instancia creada (solo pasa una vez)");
        }

        public static synchronized Singleton getInstance() {
            if (instancia == null) {
                instancia = new Singleton();
            }
            return instancia;
        }

        // CAMBIAR: métodos de negocio
        public void mostrarEstado() {
            System.out.println("Estado actual: " + estado);
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }

    static void demo() {
        System.out.println("\n===== SINGLETON =====");
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.setEstado("Modificado por s1");
        s2.mostrarEstado();
        System.out.println("¿Son la misma instancia? " + (s1 == s2));
    }
}

// ============================================================
// 2. FACTORY METHOD
// ============================================================
// CAMBIAR: Producto -> concepto común (Vehiculo, MetodoPago...)
//          ProductoConcretoA/B -> variantes (Carro, Moto...)
//          Creador -> (VehiculoCreador...) y sus subclases.
// Para agregar otra variante: 1 producto concreto + 1 creador concreto.
class PatronFactoryMethod {

    // 1. Producto (interfaz)
    interface Producto {
        void operacion();
    }

    // 2. Productos concretos
    static class ProductoConcretoA implements Producto {
        @Override
        public void operacion() {
            System.out.println("Operación del Producto A");
        }
    }

    static class ProductoConcretoB implements Producto {
        @Override
        public void operacion() {
            System.out.println("Operación del Producto B");
        }
    }

    // 3. Creador (abstracto) con el factory method
    static abstract class Creador {
        public abstract Producto crearProducto();   // <- Factory Method

        public void operacionDeNegocio() {
            Producto producto = crearProducto();
            producto.operacion();
        }
    }

    // 4. Creadores concretos
    static class CreadorConcretoA extends Creador {
        @Override
        public Producto crearProducto() {
            return new ProductoConcretoA();
        }
    }

    static class CreadorConcretoB extends Creador {
        @Override
        public Producto crearProducto() {
            return new ProductoConcretoB();
        }
    }

    static void demo() {
        System.out.println("\n===== FACTORY METHOD =====");
        Creador creador1 = new CreadorConcretoA();
        creador1.operacionDeNegocio();

        Creador creador2 = new CreadorConcretoB();
        creador2.operacionDeNegocio();
    }
}

// ============================================================
// 3. ABSTRACT FACTORY
// ============================================================
// CAMBIAR: ProductoA/ProductoB -> productos de la familia
//          (Silla, Mesa...), Variante 1/2 -> estilos (Moderno, Clásico...)
//          Factory1/2 -> fábricas por familia.
// Diferencia con Factory Method: aquí se crea una FAMILIA completa.
class PatronAbstractFactory {

    // 1. Productos abstractos
    interface ProductoA {
        void usar();
    }

    interface ProductoB {
        void usar();
    }

    // 2. Familia "Variante 1"
    static class ProductoA1 implements ProductoA {
        @Override
        public void usar() {
            System.out.println("Usando Producto A - Variante 1");
        }
    }

    static class ProductoB1 implements ProductoB {
        @Override
        public void usar() {
            System.out.println("Usando Producto B - Variante 1");
        }
    }

    // 2. Familia "Variante 2"
    static class ProductoA2 implements ProductoA {
        @Override
        public void usar() {
            System.out.println("Usando Producto A - Variante 2");
        }
    }

    static class ProductoB2 implements ProductoB {
        @Override
        public void usar() {
            System.out.println("Usando Producto B - Variante 2");
        }
    }

    // 3. Fábrica abstracta
    interface AbstractFactory {
        ProductoA crearProductoA();
        ProductoB crearProductoB();
    }

    // 4. Fábricas concretas (una por familia)
    static class Factory1 implements AbstractFactory {
        @Override
        public ProductoA crearProductoA() {
            return new ProductoA1();
        }

        @Override
        public ProductoB crearProductoB() {
            return new ProductoB1();
        }
    }

    static class Factory2 implements AbstractFactory {
        @Override
        public ProductoA crearProductoA() {
            return new ProductoA2();
        }

        @Override
        public ProductoB crearProductoB() {
            return new ProductoB2();
        }
    }

    static void demo() {
        System.out.println("\n===== ABSTRACT FACTORY =====");
        AbstractFactory fabrica = new Factory1();

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

// ============================================================
// 4. BUILDER
// ============================================================
// CAMBIAR: Producto -> objeto complejo (Pizza, Computador...)
//          parteA/B/C -> sus atributos (tamaño, masa, RAM...)
//          construirParteX -> nombres por atributo.
// El Director es opcional: son "recetas" predefinidas.
class PatronBuilder {

    // 1. Producto complejo
    static class Producto {
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

    // 2. Builder (interfaz)
    interface Builder {
        Builder construirParteA(String valor);
        Builder construirParteB(String valor);
        Builder construirParteC(String valor);
        Producto build();
    }

    // 3. Builder concreto
    static class ConcreteBuilder implements Builder {
        private final Producto producto = new Producto();

        @Override
        public Builder construirParteA(String valor) {
            producto.setParteA(valor);
            return this;
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

    // 4. Director (opcional)
    static class Director {
        public Producto construirProductoBasico(Builder builder) {
            return builder.construirParteA("Básico A")
                          .construirParteB("Básico B")
                          .build();
        }

        public Producto construirProductoCompleto(Builder builder) {
            return builder.construirParteA("Full A")
                          .construirParteB("Full B")
                          .construirParteC("Full C")
                          .build();
        }
    }

    static void demo() {
        System.out.println("\n===== BUILDER =====");
        Producto p1 = new ConcreteBuilder()
                .construirParteA("Custom A")
                .construirParteC("Custom C")
                .build();
        System.out.println(p1);

        Director director = new Director();
        Producto p2 = director.construirProductoCompleto(new ConcreteBuilder());
        System.out.println(p2);
    }
}

// ============================================================
// 5. PROTOTYPE
// ============================================================
// CAMBIAR: Prototipo -> objeto a clonar (Documento, Enemigo...)
//          atributos. Si hay atributos que son objetos/listas,
//          clonarlos también dentro de clone() (clon profundo).
class PatronPrototype {

    static class Prototipo implements Cloneable {
        private String nombre;
        private int valor;
        private List<String> etiquetas;

        public Prototipo(String nombre, int valor) {
            this.nombre = nombre;
            this.valor = valor;
            this.etiquetas = new ArrayList<>();
        }

        public void agregarEtiqueta(String etiqueta) {
            etiquetas.add(etiqueta);
        }

        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setValor(int valor) { this.valor = valor; }

        // Clonación PROFUNDA
        @Override
        public Prototipo clone() {
            Prototipo copia = new Prototipo(this.nombre, this.valor);
            copia.etiquetas = new ArrayList<>(this.etiquetas);
            return copia;
        }

        @Override
        public String toString() {
            return "Prototipo [nombre=" + nombre + ", valor=" + valor
                    + ", etiquetas=" + etiquetas + "]";
        }
    }

    static void demo() {
        System.out.println("\n===== PROTOTYPE =====");
        Prototipo original = new Prototipo("Original", 100);
        original.agregarEtiqueta("base");

        Prototipo copia = original.clone();
        copia.setNombre("Copia modificada");
        copia.setValor(200);
        copia.agregarEtiqueta("nueva");

        System.out.println(original);
        System.out.println(copia);
    }
}

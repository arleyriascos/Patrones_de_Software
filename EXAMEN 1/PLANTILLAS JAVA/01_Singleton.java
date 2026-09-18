// ============================================================
// PATRÓN SINGLETON (Creacional)
// ============================================================
// ¿CUÁNDO USARLO EN EL EXAMEN?
// Cuando el caso de estudio hable de algo que debe existir UNA
// SOLA VEZ en todo el sistema: una conexión a base de datos,
// un logger, una configuración global, un gestor de caja/turnos,
// un administrador de sesión, etc.
// Pistas en el enunciado: "único", "global", "centralizado",
// "una sola instancia", "compartido por todo el sistema".
//
// CÓMO ADAPTARLO:
// 1. Cambia el nombre de la clase (ej: ConexionBD, Logger,
//    ConfiguracionSistema, GestorInventario...).
// 2. Agrega los atributos propios del caso de estudio.
// 3. Agrega los métodos de negocio propios del caso de estudio.
// 4. Deja el constructor privado y el getInstance() tal cual.
// ============================================================

public class Singleton {

    // 1. Instancia única, estática y privada
    private static Singleton instancia;

    // Atributos propios del caso de estudio (ejemplo)
    private String estado;

    // 2. Constructor PRIVADO: nadie puede hacer "new Singleton()"
    //    desde afuera de la clase
    private Singleton() {
        this.estado = "Inicializado";
        System.out.println("Instancia creada (solo pasa una vez)");
    }

    // 3. Método estático de acceso global (thread-safe con
    //    "synchronized"; en el examen puedes usar la versión
    //    simple si no piden concurrencia)
    public static synchronized Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

    // 4. Métodos de negocio propios del caso de estudio
    public void mostrarEstado() {
        System.out.println("Estado actual: " + estado);
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ------------------- DEMO / MAIN -------------------
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.setEstado("Modificado por s1");
        s2.mostrarEstado(); // Debe mostrar "Modificado por s1"

        // Prueba de que es la misma instancia
        System.out.println("¿Son la misma instancia? " + (s1 == s2));
    }
}

# Guía rápida — Patrones de diseño creacionales en Java

Plantillas listas para copiar/pegar y adaptar durante el examen con caso de estudio.
Todos son **patrones creacionales** (crean objetos), que es la familia que has visto en clase.

## Tabla de decisión rápida

| Si el caso de estudio dice...                                              | Usa...           |
|------------------------------------------------------------------------------|------------------|
| "debe existir una única instancia / global / compartida"                    | **Singleton**        |
| "según el tipo se crea un objeto distinto" (un solo objeto, varias clases)  | **Factory Method**   |
| "familias de objetos relacionados que deben ser compatibles entre sí"       | **Abstract Factory** |
| "objeto con muchos atributos opcionales, se arma paso a paso / personalizado"| **Builder**          |
| "duplicar / clonar / copiar un objeto existente para crear uno nuevo"       | **Prototype**        |

## Archivos incluidos

- `01_Singleton.java`
- `02_FactoryMethod.java`
- `03_AbstractFactory.java`
- `04_Builder.java`
- `05_Prototype.java`

Cada archivo compila solo (tiene su propio `main`) para que puedas probarlo
directo en [online-java.com](https://www.online-java.com/) (el recurso que
usa tu profe) antes de adaptarlo al caso de estudio.

## Cómo usarlos en el examen (paso a paso)

1. **Lee el caso de estudio completo primero.** Identifica los sustantivos
   (posibles clases) y detecta si hay: algo único, varios tipos de algo,
   familias de cosas relacionadas, un objeto armado con partes, o algo que
   se duplica/clona.
2. **Elige el patrón con la tabla de arriba.** A veces un caso de estudio
   combina dos patrones (ej: un Singleton que internamente usa un Factory
   Method) — está bien si tu solución los combina, siempre que tenga sentido.
3. **Copia la plantilla correspondiente** y renombra:
   - Los nombres de clase genéricos (`ProductoA`, `CreadorConcretoA`, etc.)
     por los del caso de estudio real (ej: `Vehiculo`, `FabricaCarros`).
   - Agrega los atributos y métodos de negocio propios del enunciado.
4. **Conserva la estructura del patrón** (constructor privado en Singleton,
   `factoryMethod()` abstracto en Factory Method, interfaz `AbstractFactory`
   con un método por producto, `build()` fluido en Builder, `clone()` en
   Prototype). Esa estructura es lo que el profe va a estar buscando.
5. **Prueba con un `main`** que demuestre que el patrón funciona (como los
   que ya trae cada plantilla).
6. Si el examen pide diagrama UML además del código, dibuja las mismas
   clases/relaciones que ves en la plantilla (herencia con flecha hueca,
   implementación de interfaz con flecha punteada, composición con rombo).

## Notas por patrón (para no confundirte en el examen)

- **Singleton**: el punto clave es el **constructor privado** + **método
  estático `getInstance()`**. Si el profe pregunta por qué, es porque así se
  impide crear más de una instancia desde fuera de la clase.
- **Factory Method vs Abstract Factory**: si dudas cuál usar, pregúntate
  "¿estoy creando UN producto o una FAMILIA de productos relacionados?".
  Uno → Factory Method. Varios relacionados y compatibles entre sí →
  Abstract Factory.
- **Builder**: útil cuando un constructor tendría demasiados parámetros
  (muchos `null` opcionales). El método `build()` siempre debe ir al final.
- **Prototype**: cuidado con la **clonación profunda**: si el objeto tiene
  atributos que son listas/objetos, hay que copiarlos explícitamente en
  `clone()`, si no, el clon y el original van a compartir esa referencia
  (bug clásico que preguntan en el examen).

## Otros lenguajes

Todo el material del curso que trabajaste está en Java, así que estas
plantillas están en Java. Si en el examen el profe pide otro lenguaje,
la estructura de cada patrón (qué clases/interfaces se necesitan y cómo se
relacionan) es la misma; solo cambia la sintaxis. Avísame si necesitas la
misma plantilla traducida a otro lenguaje y te la genero.

## Cómo compilar en Sublime (o en terminal)

javac -d out Main.java
java -cp out Main
<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 1
### Nouveautés dans les interfaces


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Méthodes statiques et par défaut dans les interfaces

Définition

```java
interface MonInterface {
 
	String CONSTANTE = "Ma constante"; // public static final
	
	void prototype(); // public abstract
	
	static void staticMethod() { ... } // Nouveau : méthode statique
	
	default void defaultMethod() { ... } // Nouveau : méthode par défaut
}
```

Utilisation

```java
class MaClasse implements MonInterface { }

// Méthode statique
MonInterface.staticMethod();

// Méthode par défaut
MaClasse instance = new MaClasse();
instance.defaultMethod();
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Pourquoi les méthodes par défaut ?

Permet d’ajouter de nouvelles méthodes aux interfaces existantes
 - sans casser la compatibilité ascendante

Exemple dans l’interface `Collection<E>`
```java
default Spliterator<E> spliterator() {
	return Spliterators.spliterator(this, 0);
}

default Stream<E> stream() {
	return StreamSupport.stream(spliterator(), false);
}
```

<!-- .element: class="icon warn" -->Problèmes de l’héritage multiple
 - même prototype défini par défaut dans deux interfaces
  - &rarr; surcharge obligatoire de la méthode dans la classe qui implémente les deux interfaces
 - prototypes incompatibles définis par défaut dans deux interfaces
  - &rarr; une classe ne peut pas implémenter les deux interfaces simultanément


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Pourquoi les méthodes statiques dans les interfaces ?

Parce qu’il peut désormais y avoir du code dans les interfaces

Cela évite d’avoir à créer des classes utilitaires
 - par exemple dans l’interface `Comparator<T>` :

```java
public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
	return Collections.reverseOrder();
}

public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor) {
	Objects.requireNonNull(keyExtractor);
	return (Comparator<T>) (a, b) -> Integer.compare(keyExtractor.applyAsInt(a), keyExtractor.applyAsInt(b));
}    
```

 - utilisation :

```java
Arrays.asList("a", "b", "c").sort(Comparator.reverseOrder()); // ["c", "b", "a"]

Arrays.asList("a", "b", "c").sort(reverseOrder()); // import static java.util.Comparator.* 
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Interfaces fonctionnelles

**Interface fonctionnelle** : interface qui ne comporte qu’**une seule méthode abstraite**
 - interface « SAM » : *single abstract method*
 - les méthodes statiques, par défaut ou définies dans `Object` ne comptent pas

Utiliser l’annotation `@FunctionalInterface`
 - le compilateur génère une erreur si l’interface annotée ne vérifie pas les conditions

Exemple d’interfaces fonctionnelles :

```java
@FunctionalInterface
public interface Runnable {
	public abstract void run(); // Unique méthode abstraite
}

@FunctionalInterface
public interface Comparator<T> {
	// Méthodes par défaut, statiques ou de la class Object
	int compare(T a, T b); // Unique méthode abstraite
}
```


<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 1
### Méthodes implémentées dans les interfaces


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Méthodes statiques et par défaut dans les interfaces

Définition

```java
interface MonInterface {
 
	String CONSTANTE = "Ma constante"; // static final
	
	void prototype(); // abstract
	
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
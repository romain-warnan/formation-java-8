<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 1
### Méthodes implémentées dans les interfaces


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Deux nouveaux types de méthodes

Les méthodes statiques
 - comme dans une classe abstraite

```java
public interface MonInterface {
 
	// Prototypes de méthodes
	
	default void defaultMethod() {
		// default method implementation
	}
}
```
 
Les implémentations par défaut
```java
public interface MonInterface {
 
	// Prototypes de méthodes
	
	default void defaultMethod() {
		// default method implementation
	}
}
```
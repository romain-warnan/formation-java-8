<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 3
### Références de méthodes


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Une alternative aux expressions lambda

Référence de méthode : autre manière d’implémenter les interfaces fonctionnelles
 - on n’écrit pas le code de l’unique méthode à implémenter,
  - on donne simplement le nom d’une méthode dont la signature est compatible
 - utilisation du nouvel opérateur « `::` »

Exemple :

```java
Function<String, Integer> a = t -> Integer.valueOf(t); 	// Expression lambda
Function<String, Integer> b = Integer::valueOf;			// Référence de méthode
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Les 4 types de références de méthodes

Méthode statique
 - `s -> Integer.valueOf(s)`
 - `Integer::valueOf`

Constructeur
 - `s -> new Integer(s)`
 - `Integer::new`

Méthode d’une autre instance
 - `s -> "abc".compareTo(s)`
 - `"abc"::compareTo`

Méthode de l’objet courant
 - `s -> s.length()`
 - `String::length`
 
<!-- .element: class="icon idea" -->Ces 4 références de méthode implémentent toutes :
 - `Function<String, Integer>` : `public Integer apply(String s);`


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Exemples avec des bi-fonctions

Méthode statique
 - `(x, y) -> Math.pow(x, y)`
 - `Math::pow`

Constructeur
 - `(initialCapacity, loadFactor) -> new HashSet(initialCapacity, loadFactor)`
 - `HashSet::new`

Méthode d’une autre instance
 - `(regex, replacement) -> "abc".replaceAll(regex, replacement)`
 - `"abc"::replaceAll`

Méthode du premier paramètre
 - `(a, b) -> a.concat(b)`
 - `String::concat`

<!-- .element: class="icon warn" -->Dans ce dernier cas, la fonction est appelé sur le 1<sup>er</sup> paramètre et les suivants sont passés en arguments
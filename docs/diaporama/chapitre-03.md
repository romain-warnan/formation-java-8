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

 - Expression lambda
 
```java
Function<String, Integer> function = t -> Integer.valueOf(t);
```

 - Référence de méthode

```java
Function<String, Integer> function = Integer::valueOf;
```

<!-- .element: class="icon info" -->Il existe 4 types de références de méthodes


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### 1/ Référence à une méthode statique

Méthode statique

 - de n’importe quelle classe
 - dont la signature identique à celle de la *SAM*

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
```

```java
class UneClasse {
	static <T, R> R uneMethodeStatique(T t) {
		//...
	}
}
```

```java
Function<T, R> function = UneClasse::uneMethodeStatique;
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### 2/ Référence à une méthode sur une instance

Méthode non statique
 - de n’importe quelle classe
 - dont la signature identique à celle de la *SAM*

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
```

```java
class UneClasse {
	R uneMethode(T t) {
		// ...
	}
}
```

```java
UneClasse uneInstance = ...
Function<T, R> function = uneInstance::uneMethode;
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### 3/ Référence à un constructeur

Constructeur
 - de la classe correspondant au type de retour de la *SAM*
 - acceptant en paramètre le même type que la *SAM*

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
```

```java
class R {
	R(T t) {
		// ...
	}
}
```

```java
Function<T, R> function = R::new;
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### 4/ Référence à une méthode d’un objet arbitraire d’un type donné

Méthode non statique
 - de la classe du type du premier paramètre de la *SAM*
 - retournant le même type que la *SAM*

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
```

```java
class T {
	R uneMethode() {
		// ...
	}
}
```

```java
Function<T, R> function = T::uneMethode;
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### *SAM* avec plusieurs paramètres

```java
@FunctionalInterface public interface BiFunction<T, U, R> { R apply(T t, U u); }
```

1/ Méthode statique

```java
class UneClasse {
	static <T, U, R> R uneMethodeStatique(T t, U u) { ... } // BiFunction<R, T, U> b = UneClasse::uneMethodeStatique
}
```

2/ Méthode d’une instance particulière

```java
class UneClasse {
	R uneMethode(T t, U u) { ... } // BiFunction<R, T, U> b = UneClasse::uneMethode
}
```
3/ Constructeur

```java
class R {
	R(T t, U u) { ... } // BiFunction<R, T, U> b = R::new
}
```

4/ Méthode d’un objet arbitraire d’un type donné

```java
class T {
	R uneMethode(U u) { ... } // BiFunction<R, T, U> b = T::uneMethode
}
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Exemples avec des fonctions

1/ Méthode statique

```java
s -> Integer.valueOf(s)
Integer::valueOf
```

2/ Méthode d’une instance particulière
```java
s -> "abc".compareTo(s)
"abc"::compareTo
```

3/ Constructeur

```java
s -> new Integer(s)
Integer::new
```

4/ Méthode d’un objet arbitraire d’un type donné

```java
s -> s.length()
String::length
``` 
<!-- .element: class="icon info" -->Ces 4 références de méthode implémentent toutes :
 - `Function<String, Integer>` : `public Integer apply(String s);`


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Exemples avec des bi-fonctions

1/ Méthode statique
```java
 (x, y) -> Math.pow(x, y)
 Math::pow
```

2/ Méthode d’une instance particulière
```java
 (regex, replacement) -> "abc".replaceAll(regex, replacement)
 "abc"::replaceAll
 ```
 
3/ Constructeur
```java
 (initialCapacity, loadFactor) -> new HashSet(initialCapacity, loadFactor)
 HashSet::new
```

4/ Méthode d’un objet arbitraire d’un type donné
```java
(a, b) -> a.concat(b)
String::concat
```

 - <!-- .element: class="icon warn" -->Dans ce dernier cas, la fonction est appelé sur le 1<sup>er</sup> paramètre et les suivants sont passés en arguments
 

%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Une syntaxe plus expressive

Avec les expressions lambda on a pu écrire :
```java
Comparator<Personne> comparator = (a, b) -> Integer.compare(a.getAge(), b.getAge());
```

Simplification avec les références de méthode et les méthodes statiques :
```java
Comparator<Personne> comparator = Comparator.comparingInt(Personne::getAge);
```

Ou encore en utilisant les imports statiques :
```java
// import static java.util.Comparator.*;
Comparator<Personne> comparator = comparingInt(Personne::getAge);
```
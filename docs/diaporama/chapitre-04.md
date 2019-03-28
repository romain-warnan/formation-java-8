<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 4
### Les optionnels


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Objectif des optionnels

Nouvelle classe `Optional<T>`
 - permet de mieux gérer les valeurs `null`
 - utilisation de valeurs optionnelles plutôt que nulles

Une instance de `Optional<T>` :
 - soit contient une valeur non nulle de type `T`
 - soit est non nulle mais vide

<!-- .element: class="icon idea" -->Les optionels ont été introduits pour gérer proprement le cas des *streams* vides


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Création d’un optionel

Optionel non vide
```java
String value = ...
Optional<String> optional = Optional.of(value);
```
 - <!-- .element: class="icon warn" --> `NullPointerException` si `value` est `null`

Optionel vide
```java
Optional<String> optional = Optional.empty();
var optional = Optional.<String>empty(); // en Java 10
```

Optionel vide ou contenant une valeur
```java
String value = ...
Optional<String> optional = Optional.ofNullable(value);
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Vérifier la présence d’une valeur

Méthode `isPresent()`
```java
boolean a = Optional.of("Chaine").isPresent(); // a = true
boolean b = Optional.ofNullable(null).isPresent(); // b = false
```
 - <!-- .element: class="icon warn" --> Depuis Java 11, il existe aussi `isEmpty()`
 
Exécuter une action si l’optionnel n’est pas vide :

```java
Optional<String> optional = ...
optional.ifPresent(s -> System.out.println(s.length()));
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Valeur par défaut et exception

La méthode `orElse(T t)` retourne
 - la valeur de l’optionnel si il est non vide
 - la valeur passée en paramètre sinon

```java
Optional<String> optional = ...
String value = optional.orElse("Valeur par défaut");
```

La méthode `orElseGet(T t)` fonctionne pareil mais de manière *lazy*

```java
Optional<String> optional = ...
String value = optional.orElseGet(() -> service.findDefaultValue());
```

On peut aussi lever une exception si l’optionnel est vide :
```java
String value = ...
String name = Optional.ofNullable(value).orElseThrow(IllegalArgumentException::new);
```

<!-- .element: class="icon warn" -->Ne pas utiliser la méthode `get()`
```java
Optional<String> optional = ...
String value = optional.get(); // Risque de lever une NoSuchElementException
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Évaluation de conditions

Les optionnels permettent aussi d’écrire différemment les conditions :
```java
boolean isPasswordValid(String password) {
	return password != null
		&& isLongEnough(password)
		&& isNotToEasy(password)
		&& isNotWellKnown(password);
}
```

Avec la fonction `filter()` :

```java
boolean isPasswordValid(String password) {
	return Optional.ofNullable(password)
		.filter(this::isLongEnough)
		.filter(this::isNotToEasy)
		.filter(this::isNotWellKnown)
		.isPresent();
}
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Transformation d’une valeur non nulle


```java
boolean isPasswordValid(String password) {
	if(password == null) return false;
	String trimmedPassword = password.trim();
	return isLongEnough(trimmedPassword) && isNotToEasy(trimmedPassword);
}
```

Combinée avec la fonction `map()`, cela devient encore plus intéressant :
```java
boolean isPasswordValid(String password) {
	return Optional.ofNullable(password)
		.map(String::trim)
		.filter(this::isLongEnough)
		.filter(this::isNotToEasy)
		.isPresent();
}
```
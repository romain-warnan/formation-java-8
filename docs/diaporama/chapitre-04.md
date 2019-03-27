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

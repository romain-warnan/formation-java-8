<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 2
### Expressions lambda


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Une évolution majeure dans le langage

Expressions lambda
 - ou closures, ou fonctions anonymes, ou &lambda;
 - permet de passer des traitements en paramètres d’une méthode

**Avant**

 - utilisation de classe anonymes

```java
personnes.sort(new Comparator<Personne>() {
	@Override
	public int compare(Personne a, Personne b) {
		return Integer.compare(a.age, b.age);
	}
});
```

**Après**

 - utilisation d’expressions lambda

```java
personnes.sort((a, b) -> Integer.compare(a.age, b.age));
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelle syntaxe

 - utilisation d’une classe anonyme

```java
Comparator<Personne> comparator = new Comparator<Personne>() {
	@Override
	public int compare(Personne a, Personne b) {
		return Integer.compare(a.age, b.age);
	}
};
```
 - nouvelle notation avec la flèche : `->`

```java
Comparator<Personne> comparator = (Personne a, Personne b) -> {
	return Integer.compare(a.age, b.age);
};
```

 - suppression des types en paramètre, implicites

```java
Comparator<Personne> comparator = (a, b) -> {
	return Integer.compare(a.age, b.age);
};
```

 - suppression des accolades « `{}` », du « `return` » et du point virgule « `;` »
  - car l’expression &lambda; ne comporte qu’une seule instruction

```java
Comparator<Personne> comparator = (a, b) -> Integer.compare(a.age, b.age);
```

%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### D’une classe anonyme à une expression lambda

<div class="center">
	<img src="images/anonymous-class-to-lambda.gif" />
</div>


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles dans Java 8

Java 8 introduit de nombreuses interfaces fonctionnelles
 - interfaces les plus utiles, les plus génériques
 - il est rare d’avoir à définir une nouvelle interface fonctionnelle
 
Combinées aux méthodes par défaut, elles enrichissent considérablement le JDK

Exemple dans l’interface `Map` :

```java
public interface Map<K, V> {
	// ...
	default V computeIfAbsent(K key, Function<K, V> mappingFunction) {...}
}
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`Function<T, R>`**
 - ensemble des expressions lambda qui **acceptent une valeur et en retournent une autre** 

```java
@FunctionalInterface
public interface Function<T, R> {
	R apply(T t);
}
```

Exemple
 - `Map<K, V>`

```java
public V computeIfAbsent(K key, Function<K, V> mappingFunction)
```

```java
Map<Personne, Integer> map = ...
Personne jean = ...
Integer ageDeJean = map.computeIfAbsent(jean, p -> p.getAge());
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`Supplier<T>`**
 - ensemble des expressions lambda qui **retournent une valeur sans prendre de paramètre** 

```java
@FunctionalInterface
public interface Supplier<T> {
	T get();
}
```

Exemple
 - `Optional<T>`

```java
public T orElseGet(Supplier<T> other)
```

```java
Optional<Personne> optional = ...
Personne personne = optional.orElseGet(() -> personneService.findDefault()); // public Personne findDefault();
```

<!-- .element: class="icon question" -->Pourquoi est-ce mieux que d’écrire :

```java
Personne personne = optional.orElse(personneService.findDefault());
```

<ul class="fragment">
 <li>
 	car avec le `Supplier`, l’expression n’est évaluée que si c’est nécessaire
 	<ul><li>*i.e.* quand l’`Optional` est vide</li></ul>
 </li>
</ul>


%%%

<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`Consumer<T>`**
 - ensemble des expressions lambda qui **acceptent une valeur mais ne retournent rien** 

```java
@FunctionalInterface
public interface Consumer<T> {
	void accept(T t);
}
```

Exemple
 - `Iterable<T>`

```java
public void forEach(Consumer<T> action)
```

```java
List<String> noms = Arrays.asList("Pierre", "Marie", "Jean", "Paul");
noms.forEach(nom -> System.out.println("Bonjour, " + nom));
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`Predicate<T>`**
 - ensemble des expressions lambda qui **acceptent une valeur et retournent un booléen** 

```java
@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}
```

Exemple
 - `Collection<E>`

```java
boolean removeIf(Predicate<E> filter)
```

```java
List<String> noms = Arrays.asList("Pierre", "Marie", "Jean", "Paul");
noms.removeIf(nom -> nom.startsWith("P")); // noms = ["Marie", "Jean"]
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`UnaryOperator<T>`**
 - ensemble des expressions lambda qui **acceptent une valeur et retournent une valeur du même type** 

```java
@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {}
```

Exemple
 - `List<E>`

```java
void replaceAll(UnaryOperator<E> operator)
```

```java
List<String> noms = Arrays.asList("Pierre", "Marie", "Jean", "Paul");
noms.replaceAll(nom -> nom.toUpperCase()); // noms = ["PIERRE", "MARIE", "JEAN", "PAUL"]
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`BiFunction<T, U, R>`**
 - ensemble des expressions lambda qui **acceptent deux valeurs et retournent une autre valeur** 

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
	R apply(T t, U u);
}
```

Exemple
 - `Map<K, V>`

```java
void replaceAll(BiFunction<K, V, V> function)
```

```java
Map<String, Integer> salaires = new HashMap<>();
salaires.put("Marie", 40_000);
salaires.put("Pierre", 30_000);
salaires.put("Jean", 50_000);
 
salaires.replaceAll((nom, salaire) -> nom.equals("Jean") ? salaire : salaire + 10_000);
// Tout le monde est augmenté, sauf Jean
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`BiConsumer<T, U>`**
 - ensemble des expressions lambda qui **acceptent deux valeurs et ne retournent rien** 

```java
@FunctionalInterface
public interface BiConsumer<T, U> {
	void accept(T t, U u);
}
```

Exemple
 - `Map<K, V>`

```java
void forEach(BiConsumer<K, V> action)
```

```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Marie", 31);
ages.put("Pierre", 35);
ages.put("Jean", 58);
 
ages.forEach((nom, age) -> System.out.println(nom + " a " + age + " ans"));
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

**`BinaryOperator<T>`**
 - ensemble des expressions lambda qui **acceptent deux valeurs du même type et retournent une valeur du même type** 

<!-- .element: class="icon idea" -->Permet de faire des opérations de réduction (somme, moyenne, *etc.*)
 
```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {}
```

Exemple
 - `Stream<T>`

```java
public T reduce(T identity, BinaryOperator<T> accumulator);
```

```java
List<Integer> nombres = Arrays.asList(3, 5, 8, 9, 12);
int somme = nombres.stream().reduce(0, (a, b) -> a + b);
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles

Spécialisations pour les types primitifs

```java
ToIntFunction<T>			int applyAsInt(T value)
IntFunction<R>				R apply(int value)

IntToLongFunction			long applyAsLong(int value)
// IntToShort, IntToByte, ...
	
ToIntBiFunction<T, U>		int applyAsInt(T t, U u)

IntSupplier<T>				int getAsInt()

IntConsumer<T>				void accept(int value)

IntPredicate				boolean test(int value)

IntUnaryOperator			int applyAsInt(int operand)

IntBinaryOperator			int applyAsInt(int left, int right)
```
<!-- .slide: data-background-image="images/java-cup.svg" data-background-size="400px" class="chapter" -->
## 2
### Expressions lambda


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
### Nouvelles interfaces fonctionnelles dans Java 8

Java 8 introduit différentes interfaces fonctionnelles :

 - **`Function`** : acceptent une valeur et en retournent une autre 

```java
Function<T, R>			R apply(T t)
```

- **`Supplier`** : fonctions sans paramètre 

```java
Supplier<T>				T get()
```

- **`Consumer`** : fonctions qui ne retournent rien

```java
Consumer<T>				void accept(T t)
```

 - **`Predicate`** : fonctions qui retourne un booléen

```java
Predicate<T>			boolean test(T t)
```


%%%


<!-- .slide: class="slide" data-background-image="images/java-cup.svg" data-background-size="400px" -->
 - **`BiFunction`** : fonctions à deux paramètres

```java
BiFunction<T, U, R>		R apply(T t, U u)
```

 - **`UnaryOperator`** : fonctions qui retourne le même type que celui passé en entrée

```java
UnaryOperator<T>			T apply(T t)
```

 - **`BinaryOperator`** : fonctions qui retourne le même type que ceux passés en entrée

```java
BinaryOperator<T>			T apply(T a, T b)
```

```java
Function<T, R>				R apply(T t) 			// Accepte une valeur et en retourne une autre
ToIntFunction<T>			int applyAsInt(T value)	// Spécialisation pour les types primitifs
IntFunction<R>				R apply(int value)
	
BiFunction<T, U, R>			R apply(T t, U u)			// Fonctions à deux paramètres
ToIntBiFunction<T, U>		int applyAsInt(T t, U u)

Supplier<T>					T get()			// Une fonction qui ne prend pas de paramètres
IntSupplier<T>				int getAsInt()

Consumer<T>					void accept(T t) // Fonction qui ne retourne rien
IntConsumer<T>				void accept(int value)
```

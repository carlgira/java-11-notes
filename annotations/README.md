# Annotations
Form of metadata

- Can be retained at different levels
  - RetentionPolicy.SOURCE – visible by neither the compiler nor the runtime
  - RetentionPolicy.CLASS – visible by the compiler (default)
  - RetentionPolicy.RUNTIME – visible by the compiler and the runtime

- Applicable to different type of targets
  - ANNOTATION_TYPE
  - FIELD
  - LOCAL_VARIABLE
  - METHOD
  - PACKAGE
  - PARAMETER
  - TYPE: Class, interface or enum
  - TYPE_PARAMETER:
  - TYPE_USE: Use of type (with constructors)
  - If none used, all are possible.

- Also create a custom annotation

- Annotation can have attributes:
  - primitive
  - String
  - Class
  - Enum
  - Annotation
  - An array of above
  - No wrapper classes (Boolean, Integer, Double etc)
  - Object not supported
  - void not supported as output  
  - An annotation element can include a default value if it is a non‐null constant expression.
    - No "new Object()"
    - No methods calls
    - No null permitted

- Attribute can have default value.
- Attribute can be of array values.
- Repeatable annotation is allowed to be used more than once within a container.

- Can have fields (with value).
- No methods with body. (default or private)
- Can be extended. (not good idea)
- public methods, or explicitly public methods. (no private, or protected)
- Arrays permitted but no List.

- Marker annotation are those without attributes.

```java
import java.lang.annotation.*;
import java.util.stream.Stream;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BussinesPolicies {
    BusinessPolicy[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(BussinesPolicies.class)
public @interface BusinessPolicy {
    String name() default "default policy";

    String[] countries();

    String value();
}

@BussinesPolicies({
        @BusinessPolicy(name = "Returns Policy", countries = "GB", value = "4"),
        @BusinessPolicy(countries = {"GB", "FR"}, value = "ship")
})
public class Shop {}


    public static void main(String[] args) {
        Stream.of(Shop.class.getAnnotations()).forEach(t -> t);
        Class annotationType = Shop.class.getAnnotation()[0].getAAnotationType();
        BusinessPolicy[] policies = Shop.class.getAnnotationsByType(BusinessPolicy.class);
        for(BusinessPolicy policy : policies){
            System.out.println(policy.name());
        }
    }

```

### Apply Annotations
Apply annotations to the appropriate type of target
- Attributes are set as list of name-value pairs.
- When no attributes are used () can be omitted
- Attribute "value" does not need to have specified name if it's the only attribute needed
- If array has only one value {} can be omitted
- Attributes with default values can be omitted.

```java
@BussinesPolicies({
        @BusinessPolicy(name = "Returns Policy", countries = "GB", value = "4"),
        @BusinessPolicy(countries = {"GB", "FR"}, value = "ship")
})
public class Shop {}


```

Annotations applied to a class is not inherited by its subclasses, unless it is marked as @Inherited @Serializable

### Discover annotations

    - [] getAnnotations()
    - [] getAnnotationsByType(Class)

```java
    Stream.of(Shop.class.getAnnotations()).forEach(t -> t);
    Class annotationType = Shop.class.getAnnotation()[0].getAAnotationType();
    BusinessPolicy[] policies = Shop.class.getAnnotationsByType(BusinessPolicy.class);
    for(BusinessPolicy policy : policies){
    System.out.println(policy.name());
}
```

### @Repeteable

```java
@Forecast({@Weather("Storm"), @Weather("Cloudy")})
@Weather("Storm") @Weather("Cloudy")
```

### Documented annotation @Documented

By default an annotation is not added to the javadocs, but if its added the annotation @Documented it is.

### Annotation that validate Design

- **@FunctionalInterface:** Annotation that prevents compilation if the rule is broken.
- **@Override:** Prevents subclass compiling if the signature of a method is not same as parent.
  - Always optional, can cause compilation errors if

### Code Marked annotations
- Attribute since indicates after which this code should no longer be used.
- Attribute for forRemoval

```java
@Deprecated(since="11", forRemoval=true)
```

### Suppress Compiler Warning
Unchecked warnings are caused by assignment of raw-type object to generic-type.

```java
@SuppressWarnings({"unchecked", "deprecation"})
```

### Var-args and Heap Pollution
- Heap Pollution: Put on heap something that is not the type of the object expected, (raw list type).
- Var-args when using the "...".
- If using the "..." with some List type it can cause heap-pollution.
- To suppress warnings of hea-pollution when using var-args use @SafeArgs

To use Safeargs method must be final or private. (public final is also ok).

```java
@SafeVarargs
private void safeargs(List<String>... values){}

@SafeVarargs
public final void safeargs(List<String>... values){}
```

## Links

- https://www.baeldung.com/java-custom-annotation
- https://www.baeldung.com/java-default-annotations

Question Notes:
- If annotation only on SOURCE can not be found by reflection.
- Annotation are good to save metadata.
- Wrapper classes are not permitted as annotation element types.
- SuppressWarnings must have one param.
- @Retention and @Target are optional.
- Default values for elements can not be null.
- To put directly the value on the annotation, the element must be named "value" and the other elements must be optional.
- By default annotations are not visible at runtime.
- default values can not be null.
- Annotations are optional metadata, and removing all of them from a class does not cause a compiler error
- Interface can declares an abstract method overriding one of the public method from java.lang.Object, that still can be considered as functional interface.


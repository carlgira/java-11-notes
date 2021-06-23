# Exceptions

Read first **Exception Handling in Java** https://www.baeldung.com/java-exceptions

Type of exceptions

```
              ---> Throwable <--- 
              |    (checked)     |
              |                  |
              |                  |
      ---> Exception           Error
      |    (checked)        (unchecked)
      |
RuntimeException
  (unchecked)
```

- **checked:** Exceptions that java requires us to handle.
  -  IOException
  -  ServletException
  -  SQLException
- **UnChecked:** Exceptions that java requires us to handle and they associate to Exceptions at Runtime.
  - NullPointerException
  - SecurityException
  - ArrayIndexOutOfBoundsException
  - IllegalArgumentException
  - ClassCastException
- **Errors:** Serious not recoverable error. 
  - StackOverFlowError 
  - OutOFMemoryError
-  "throws" keyword for method declaration.
-  "throw" keyword to declarative throw an Exception

## try

- **structures:** try-catch, try-finally, try-catch-finally, try-(catch-catch-catch....)-finally
- List of catchs (Exceptions in ascending order, otherwise will not compile)
- List of Exceptions in one catch (must be simblings Exceptions or of different types)
      
## try-with-resources
  
- **structures:** try, try-finally, try-catch , try-catch-finally
- The try-with-resources defined variables only lives on the try block.
- Variables in a try-with-resources must be final or effectively final
- Objects on try-with-resources must implement AutoCloseable
- AutoCloseable => void close throws Exception.
- (extends AutoCloseable) Closeable => void close throws IOException;
- The catch blocks are run after the declared resources have been closed.
- When more than one resource is used in a try‐with‐resources statement, they are closed in the reverse order in which they are declared.
- The close() method should be idempotent, which means it is able to be run multiple times without triggering any side effects.
- When for some reason the close() method of a Autoclosable class fails, its possible to capture the Exception catching "Exception" and looking for supressed Exceptions array. If the array is empty there is no supresed exception.
```
catch(Excepcion e){
  Throwable[] supresed = e.getSupresed();
  for(Throwable s : supresed){
    s.printStackTrace();
  }
}

```
    
  ## Notes of question "Not compile"
- throw instead of throws
- throws instead of throw
- throw Exception() => It must be => throw new Exception();
- new Exception() => It must be => throw new Exception();
- Remember method signature of Closeable and AutoCloseable
- Declare throws of a Checked exception and define a throw of a Unchecked
- RunTimeException do not need to be catched.
- If there is an checked Exception that is not been thrown in a try block, fails
- Not recomended to catch Throwable and Error


## Logging
  - java.util.logging.*;
  - log(), severe(), warning(), info(), config(), fine(), finer(), finest()
  - Logger logger = Logger.getLogger("");
  - logging.properties
  - Set Handlers to write
## References

Exception Handling in Java https://www.baeldung.com/java-exceptions
# Security

- DoS,
- Sensitive data leaks (encryption),
- Code corruption, (lack of encapsulation or immutability)
- Code injections (input validation and sanitation)

DoS -> Denial of service
- Legitimate user are unable to access resources
- Excessive resources consumption
- Caused by a service overwhelmed by many bogus request or by some resource that got to big.
- It can be prevented:
    - Validating inputs
    - Release all resources
    - Monitor excessive resource consumption
    - Use permissions to restrict access to sensitive code
- Sockets:
    - Unprotected sockets can be overwhelmed with lots of request
    - Restrict connection following privileges or
    - Validate server addresses origin

Other DoS:
- Zip bombs => Zip that create a big files after decompression. Limit the size of unziped files, or the number of them.
- Billions laughs:
    - XML bomb using XML entity expansion of DTDs
      lol1 = lol
      lol2 = lo1;lo1;lo1;lo1;lo1;lo1;lo1;lo1;lo1;
      lol3 = lol2;lol2;lol2;lol2;lol2;lol2;lol2;
      lol4 = lol3;lol3;lol3;lol3;lol3;lol3;lol3;
      <v>lol4</v>
      It can be avoid with XmlConstan.FEATURE_SECURE_PROCESING to set a limit
- Avoid recursive reference between entities
- Unsustainable Growth: Collections or buffer can’t grow to big sizes:
- Protect against DoS
    - Define timeouts
    - Monitor processes
    - Terminate processes that take to many resources.

Protect Confidential information:
- Credentials, user data,
- Encapsulate information, restrictive as possible, limit scope of access, do not reveal information to untrusted code and libraries, stored information in a immutable way. Do not include data in log files.
- Purge sensitive information from exceptions.
- Do not log highly sensitive information

Integrity of inputs (Validate Inputs)
- Input validation, casual typos, formatting, special characters
- System must be resilient and recover from issues.
- Don’t use untested api or libraries
- **Integers:** add 1 to infinite, divisions by zeros or 1/MIN_VALUE. Use fu (Double.isInfinite(), Double.isNan() Double.addExcect)
- **Files:** Avoid use relative paths and normalize them. getCannonicalPath(), getCanonicalFile(), Path.toRealPath(), Path.normalize()

SQL Injection:
- Use prepared statement to only allow the substitutions of the value you want.

JavaScript Injection
- Html variables can be used to inject information in a variable in javascript.
- Sanitize (validate) the code.

XML Inclusion:
- DoS and billion of laughs

XML Entities
- XML external entities (XXE): A DTD can have references to other DTDs, they can be used to create a binding to create a Dos billion laugh
- Protection: limit resources, validating inputs, disabling external entities.

File Inclusion attacks
Reference to outside files, with scripts or big files.

Directory transversals attacks
- Attempts to guess directories using relatives paths
- Remove redundant elements form paths using
- normalize
- toRealPath
- Protect against DoS attacks

Isolate Unrelated Code:
- Encapsulate information, fields as final or static.
- Separate and create modules between unrelated code.

Module
- Protect access reflection using modules.

Limit Extensibility
- Good encapsulation
- Private methods
- Declare classes as final when needed.
- Do not call overridable methods from constructors.
- Builder methods, validate inputs. (https://dzone.com/articles/design-patterns-the-builder-pattern)

Beware of SuperClass Changes

Problems with passed objects into constructors or functions
-  When there are references an outside object can change a private one. SOlution, create copies of object before assigment, (or in get method return a clone() )

File Security bug reports

Java Policy Files
- Define permissions to resources.
- System policy file location:
- java.home/lib/security/java.policy  (Solaris/Linux)
- java.home\lib\security\java.policy  (Windows)
- user policy file location:
- user.home/.java.policy  (Solaris/Linux)
- user.home\.java.policy  (Windows)

      Grant Entries define access to resources

      Define policies to access or limit resources to jar or classes 
         - Access of read and write to files
         - Access to specific sockets
         - There is class permission, and they can be declared in java also.
         - Some subclases are:
           - FilePermission 
             - resource: Files
             - Actions: read, write, delete, and execute
           - SocketPermission 
             - resources: url, port range, url with wildcards
             - actions: accept, connect, listen, and resolve
           - PropertyPermission:
             -  resource: properties as java.home or os.name
             -  actions: read (System.getProperty()), write (System.serPtoperty())
          - URLPermission
          - ReflectPermission: To avoid/allow reflection on classes

      https://docs.oracle.com/en/java/javase/11/security/java-se-platform-security-architecture.html#GUID-1C00ACB3-88F6-4A8E-85D8-9AF7CB46D812

      grant codeBase "some.jar" signedBy Carlos {
         permmision java.net.SocketPermission "localhost:7777" "listen";
         permission java.io.FilePermission "someFIle" "read, write";
      }

      grant {
         permission java.io.FilePermission "/dessert/icecream/rockyroad.yum", "read,write";
         permission java.io.FilePermission "/dessert/icecream/mintchip.yum", "read";
      };

AccessController is a utility class for check if the app has permission to do something or to execute some code that needs a priviliged action.
AccessController.doPrivileged(new PrivilgedAction ) => To a outside module ask for permission to use some "local" permited charecteristic.
https://stackoverflow.com/questions/8703234/accesscontroller-usage/8705410

Best practices for protecting your code
- Encapsulation
- Make objects immutable
- Do not break subclasses with overridden methods
- Be careful that users override certain methods
- Design classes using private or final methods and fields
- Verify Bytecode => do not disable, because bytecode can be created or modified.
- Use builder pattern to perform validation of inputs.

Erroneous Value Guards:
- Using exact methods of Math to avoid Arithmetic exceptions
- Protect against division by 0 with Math.isNan
- Protect against Math.isInfinte
- Protect against null with Optional class.

Protect Sensitive data:
- Scrambling data
- Clean data out of memory
- Do not log sensitive information
- Encrypt and decrypt value

Test vulnerabilities
- Penetration testing, break software

Interface Cloneable, has one single method "clone" to add the logic to create a copy of an object.

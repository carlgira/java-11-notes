package com.carlgira.javaio;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public void copyFileBinary(){
        try (InputStream inputStream = new FileInputStream("file.txt");
             OutputStream outputStream = new FileOutputStream("file-copy.txt")
            ){

            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) != -1){
                outputStream.write(buff, 0, length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyFileChar(){

        try (
                Reader reader = new FileReader("file.txt", Charset.defaultCharset());
                Writer writer = new FileWriter("file-copy.txt", Charset.defaultCharset());
                ){
            char[] buff = new char[1024];
            int length = 0;
            while ((length = reader.read(buff)) != -1){
                writer.write(buff, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectingStreams(){
        try ( BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
              BufferedWriter writer = new BufferedWriter(new FileWriter("file-copy.txt"));
                ){
            String line = null;
            while( (line = reader.readLine()) != null){
                writer.write(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanner() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        scanner.nextDouble();
        scanner.nextFloat();

        System.out.println("Name :");
        scanner.nextLine();

    }

    public void console() throws IOException {
        Console c = System.console();
        if(c == null){ // Console can be null, is necessary to check
            return;
        }

        c.writer().println(""); // System.out
        c.reader().read();  // System.in

        c.readLine(); // System.in , scanner.nextLine()
        c.readPassword();
        c.readLine("Name: ");
        c.readPassword("Enter password: ");
        char[] password = c.readPassword("Enter password: ");
    }

}

class FilesU {

    public static void main(String[] args) throws IOException {

        try (var d = System.out;)
        {
            System.out.println("this prints ");
        }
        finally {
            System.out.println("this does not print because System.out is closed");
        }
    }

    public static void files() throws IOException {
        Path path = Path.of("c:\\dev\\licenses\\windows\\readme.txt");
        Path path1 = Paths.get("c:\\dev\\licenses\\windows\\readme.txt");
        boolean exists = Files.exists(path);

        Files.createTempFile("somePrefixOrNull", ".jpg");

        Path newDirectory = Files.createDirectories(path.getParent().resolve("some/new/dir"));
        System.out.println("newDirectory = " + newDirectory);

        Path newFile = Files.createFile(newDirectory.resolve("emptyFile.txt"));
        System.out.println("newFile = " + newFile);

        Path utfFile = Files.createTempFile("some", ".txt");

        Files.createSymbolicLink(path, utfFile);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(utfFile)) {
            // handle reader
        }

        try (OutputStream os = Files.newOutputStream(utfFile)) {
            // handle outputstream
        }

        String s = Files.readString(utfFile);// UTF 8 // Al lines of files on single string
        System.out.println("s = " + s);

        List<String> lines = Files.readAllLines(Path.of("file.txt"));

        // File attributes

        // Get single attribute ( [view-name:] by default is basic)
        Files.getAttribute(path, "size");
        Files.getAttribute(path, "posix:permision");

        // Get set attributes
        Map<String,Object> attrrs1 =  Files.readAttributes(path, "posix:*");
        Map<String,Object> attrrs2 =  Files.readAttributes(path, "*");
        Map<String,Object> attrrs3 =  Files.readAttributes(path, "posix:permissions,owner,size");
        Map<String,Object> attrrs4=  Files.readAttributes(path, "size,lastModifiedTime,lastAccessTime");

        // Get set of attributes by view-name
        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(path, AclFileAttributeView.class);
        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);

        // List Files

        Files.list(utfFile).forEach(System.out::println); // List files on same directory

        Files.walk(utfFile).forEach(System.out::println); // List files recursively
        Files.walk(utfFile, 3).forEach(System.out::println);  // List files recursively with MAxDepth
        Files.find(utfFile, 3, (p, b) ->  p.startsWith("*.asd")); // Same as walk with BiPredicate

        // Move, delete

        Files.move(utfFile, Path.of("c:\\dev").resolve(utfFile.getFileName().toString()));
        Files.delete(utfFile); // Directories only if they are empty

        // Delete every thing of a folder
        try (Stream<Path> walk = Files.walk(utfFile)) {
            walk.sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        Path p = Paths.get("./src/main/java/../resources/some.properties");
        System.out.println("p.isAbsolute() = " + p.isAbsolute()); // False

        Path p2 = p.toAbsolutePath();
        System.out.println("p2 = " + p2);
        System.out.println("p2.isAbsolute() = " + p2.isAbsolute());

        /**
         * p2 = C:\dev\java-file-article\.\src\main\java\..\resources\some.properties
         * p2.isAbsolute() = true
         */

        Path p3 = p.normalize().toAbsolutePath();
        System.out.println("p3 = " + p3);
        System.out.println("p3.isAbsolute() = " + p3.isAbsolute());

        /**
         * p3 = C:\dev\java-file-article\src\main\resources\some.properties
         * p3.isAbsolute() = true
         */
    }
}

package com.carlgira.streams;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.*;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        mapExamples();
        filterExamples();
        aggregatingOperationsExamples();
        moreFunnyExamples();
    }

    private static void mapExamples() {
        String s = "1 2 3 4 5 6 7 8 9 10";

        System.out.println("1. Mapping with a one-argument function:");
        List<Integer> ints = Arrays.stream(s.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(ints);

        System.out.println("2. Mapping with a simple lambda expression:");
        List<Double> pow2 = ints.stream().map(x -> Math.pow(2.0, x)).collect(Collectors.toList());
        System.out.println(pow2);
        List<String> doubled = ints.stream().map(i -> i + "_" + i).collect(Collectors.toList());
        System.out.println(doubled);

        System.out.println("3. Mapping with complex function:");
        // Though this one can be simplified to the form in (2). Try it.
        List<Integer> pows = pow2.stream().map(d -> {
            String str = Double.toString(d);
            return str.length();
        }).collect(Collectors.toList());
        System.out.println(pows);

        // Everywhere else where you have to pass a function or a predicate, you can use one of the three forms:
        // 1. Function reference, e.g. `Integer::parseInt`
        // 2. One-expression lambda, e.g `x -> Integer.parseInt(x)`
        // 3. Lambda with body, e.g. `x -> { return Integer.parseInt(x); }`
    }

    private static void filterExamples() {
        List<String> words = Arrays.asList("tst", "one", "two", "three", "four", "five", "rotator", "deified");

        System.out.println("1. Simple filtering");
        List<String> longWords = words.stream().filter(s -> s.length() > 3).collect(Collectors.toList());
        System.out.println(longWords);

        System.out.println("2. A little more complex filtering");
        List<String> palindromes = words.stream()
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .collect(Collectors.toList());
        System.out.println(palindromes);
    }

    private static void aggregatingOperationsExamples() {
        List<String> words = Arrays.stream("Once upon a midnight dreary while I pondered weak and weary".split("\\s+"))
                .collect(Collectors.toList());

        System.out.println("1. Check if all items satisfy a criterion:");
        boolean noWordsLongerThan9 = words.stream().allMatch(w -> w.length() <= 9);
        boolean theSame = words.stream().noneMatch(w -> w.length() > 9);
        System.out.println(noWordsLongerThan9);

        System.out.println("2. Check if any item satisfies a criterion:");
        boolean hasShortWords = words.stream().anyMatch(w -> w.length() < 3);
        System.out.println(hasShortWords);

        System.out.println("3. Average over the items");
        //Note the last call. If stream contains no items, it will return 0.0;
        double average = words.stream().mapToInt(String::length).average().orElse(0.0);
        System.out.println(average);

        System.out.println("4. Count items that satisfy a criterion:");
        long wordsWithE = words.stream().filter(w -> w.contains("e")).count();
        System.out.println(wordsWithE);

        System.out.println("5. Max and sum");
        int maxWordLength = words.stream().mapToInt(String::length).max().orElse(0); //min is the same
        int sumWordLength = words.stream().mapToInt(String::length).sum();
        System.out.println("max: " + maxWordLength + ", sum: " + sumWordLength);
    }

    private static void moreFunnyExamples() {
        List<String> words = Arrays.stream("These examples cover only a small part of Java 8 Streams.".split("\\s+"))
                .collect(Collectors.toList());

        System.out.println("1. Join to string:");
        String noDelimiter = words.stream().collect(Collectors.joining());
        System.out.println(noDelimiter);
        String withDelimiter = words.stream().map(it -> Integer.toString(it.length())).collect(Collectors.joining(" + "));
        System.out.println(withDelimiter);

        System.out.println("2. Concat two streams:");
        List<String> concat = Stream.concat(words.stream(),
                words.stream().map(s -> new StringBuilder(s).reverse().toString()))
                .collect(Collectors.toList());
        System.out.println(concat);

        System.out.println("3. Create a Map from stream");
        //toMap takes two arguments: the first is the function to get keys (we pass identity there -- it is a function
        //that returns exactly its argument, like `x -> x`, and the second one to get values.
        Map<String, Integer> map = words.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

        System.out.println("4. Distinct items of a stream:");
        //Here's also a way to create a stream directly from values
        Set<Integer> unique = Stream.of(5, 1, 2, 3, 4, 5, 4, 3, 2, 1).collect(Collectors.toSet());
        System.out.println(unique);

        System.out.println("5. Sorted stream");
        List<String> sorted = Stream.of("a", "z", "b", "c").sorted().collect(Collectors.toList());
        System.out.println(sorted);

        System.out.println("6. Limit and skip");
        List<Integer> part = Stream.of(1, 2, 3, 4, 5, 7, 8, 9).skip(2).limit(4).collect(Collectors.toList());
        System.out.println(part);

        System.out.println("7. Production of ints in the stream");
        // This example is a bit complex, but it can be useful to learn the concept of `reducing`.
        // `reduce` is an operation with an accumulator: we provide an initial value for the accumulator and a function
        // with two arguments. As one of the arguments the current accumulator value will always be passed.
        // The function is applied to every item in the stream and its result is the next value of the accumulator.
        //
        // Now let's try to implement ints production which, unfortunately, is not there along with `sum` and `average`.
        int production = Stream.of(1, 2, 3, 5, 7, 11).reduce(1, (acc, i) -> acc * i);
        System.out.println(production);

        System.out.println("8. Int range");
        // Yeah, `mapToObj(i -> i)` looks weird, but in Java IntStream and Stream are not the same, and this is the
        // way to convert one to another.
        List<Integer> range = IntStream.range(1, 10).mapToObj(i -> i).collect(Collectors.toList());
        System.out.println(range);
        // Uses the production
        int factorial = range.stream().reduce(1, (acc, i) -> acc * i);
        System.out.println(factorial);

        System.out.println("9. Flat map");
        // Flat map is another concept which is not that simple, but might be useful.
        // Flat map is similar to map: it iterates over every item in the stream and calls the function which you
        // pass as the argument. But your function should return not a single value but a stream. And flat map then
        // concatenates all the streams returned.
        List<Character> chars = words.stream()
                .flatMap(w -> w.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());
        System.out.println(chars);

        System.out.println("10. Group by");
        // The result is a map where for each value of the function passed to groupingBy there is a list of the stream
        // items which had this value.
        Map<Integer, List<String>> wordsByLength = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(wordsByLength);
    }

    public void other(){

        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        String result1 = givenList.stream()
                .collect(Collectors.joining());

        Long result2 = givenList.stream()
                .collect(Collectors.counting());

        DoubleSummaryStatistics result3 = givenList.stream()
                .collect(Collectors.summarizingDouble(String::length));
        result3.getAverage();
        result3.getMax();
        result3.getMin();
        result3.getSum();
        result3.getCount();

        Double result4 = givenList.stream()
                .collect(Collectors.averagingDouble(String::length));

        Double result5 = givenList.stream()
                .collect(Collectors.summingDouble(String::length));

        Optional<String> result6 = givenList.stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()));

        Optional<String> result7 = givenList.stream()
                .collect(Collectors.minBy(Comparator.reverseOrder()));

        Map<Integer, Set<String>> result8 = givenList.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));


        int s = givenList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> l.size()));

    }

    enum BlogPostType {
        NEWS,
        REVIEW,
        GUIDE
    }

    public void groupBy(){

        class BlogPost {
            String title;
            String author;
            BlogPostType type;
            int likes;

            public BlogPost(String title, String author, BlogPostType type, int likes) {
                this.title = title;
                this.author = author;
                this.type = type;
                this.likes = likes;
            }

            public BlogPostType getType() {
                return type;
            }

            public String getTitle() {
                return title;
            }

            public String getAuthor() {
                return author;
            }

            public int getLikes() {
                return likes;
            }
        }

        class Tuple {
            BlogPostType type;
            String author;

            public Tuple(BlogPostType type, String author) {
                this.type = type;
                this.author = author;
            }
        }

        List<BlogPost> posts = Arrays.asList(
                new BlogPost("one", "five", BlogPostType.NEWS, 1),
                new BlogPost("three", "six", BlogPostType.REVIEW, 2),
                new BlogPost("one", "six", BlogPostType.NEWS, 3),
                new BlogPost("two", "seven", BlogPostType.REVIEW, 4),
                new BlogPost("two", "five", BlogPostType.GUIDE, 1),
                new BlogPost("three", "five", BlogPostType.GUIDE, 1)
        );

        // Simple Grouping by a Single Column
        Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType));

        // groupingBy with a Complex Map Key Type
        Map<Tuple, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
                .collect(Collectors.groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));

        // Modifying the Returned Map Value Type
        Map<BlogPostType, Set<BlogPost>> postsPerType1 = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType, Collectors.toSet()));

        // Grouping by Multiple Fields
        Map<String, Map<BlogPostType, List<BlogPost>>> map1 = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getAuthor, Collectors.groupingBy(BlogPost::getType)));
        /**
         * {six={NEWS=[BlogPost@a1c25081], REVIEW=[BlogPost@15a3d005]},
         * seven={REVIEW=[BlogPost@19b4f786]},
         * five={GUIDE=[BlogPost@2c686bb, BlogPost@8e774689], NEWS=[BlogPost@6e966319]}}
         */

        Map<String, Map<BlogPostType, Map<String, List<BlogPost>>>> map2 = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getAuthor, Collectors.groupingBy(BlogPost::getType,
                        Collectors.groupingBy(BlogPost::getTitle))));
        /**
         * {six={NEWS={one=[BlogPost@a1c25081]}, REVIEW={three=[BlogPost@15a3d005]}},
         * seven={REVIEW={two=[BlogPost@19b4f786]}},
         * five={GUIDE={three=[BlogPost@8e774689], two=[BlogPost@2c686bb]}, NEWS={one=[BlogPost@6e966319]}}}
         */

        // Getting the Average from Grouped Results
        Map<BlogPostType, Double> averageLikesPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType, Collectors.averagingInt(BlogPost::getLikes)));
        /**
         * {GUIDE=1.0, REVIEW=3.0, NEWS=2.0}
         */

        // Getting the Sum from Grouped Results
        Map<BlogPostType, Integer> likesPerType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType, Collectors.summingInt(BlogPost::getLikes)));
        /**
         * {GUIDE=2, REVIEW=6, NEWS=4}
         */

        // Getting the Maximum or Minimum from Grouped Results
        Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType,
                        Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));
        /**
         * {GUIDE=Optional[BlogPost@2c686bb], REVIEW=Optional[BlogPost@19b4f786], NEWS=Optional[BlogPost@a1c25081]}
         */

        // Mapping Grouped Results to a Different Type
        Map<BlogPostType, List<String>> postsPerType3 = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType,
                        Collectors.mapping(BlogPost::getTitle, Collectors.toList())));

        Map<BlogPostType, String> postsPerType2 = posts.stream()
                .collect(Collectors.groupingBy(BlogPost::getType,
                        Collectors.mapping(BlogPost::getTitle, Collectors.joining(", ", "Post titles: [", "]"))));

        /**
        * {GUIDE=Post titles: [two, three], REVIEW=Post titles: [three, two], NEWS=Post titles: [one, one]}
        */

        // Concurrent groupingBy Collector
        ConcurrentMap<BlogPostType, List<BlogPost>> postsPerType4 = posts.parallelStream()
                .collect(Collectors.groupingByConcurrent(BlogPost::getType));

        // filtering
        List<Integer> numbers = List.of(1, 2, 3, 5, 5);
        Map<Integer, Long> result = numbers.stream()
                .collect(Collectors.groupingBy(i -> i,
                        Collectors.filtering(val -> val > 3, Collectors.counting())));
        /**
         * {1=0, 2=0, 3=0, 5=2}
         */
    }

    public void collectorsFlatMap() {

        class Blog {
            private String authorName;
            private List<String> comments;

            Blog(String authorName, String... comments){
                this.authorName = authorName;
                this.comments = Arrays.asList(comments);
            }

            public String getAuthorName() {
                return authorName;
            }

            public List<String> getComments() {
                return comments;
            }
        }

        Blog blog1 = new Blog("1", "Nice", "Very Nice");
        Blog blog2 = new Blog("2", "Disappointing", "Ok", "Could be better");
        List<Blog> blogs = List.of(blog1, blog2);

        Map<String,  List<List<String>>> authorComments1 = blogs.stream()
                .collect(Collectors.groupingBy(Blog::getAuthorName,
                        Collectors.mapping(Blog::getComments, Collectors.toList())));
        /**
         * {1=[[Nice, Very Nice]], 2=[[Disappointing, Ok, Could be better]]}
         */

        Map<String, List<String>> authorComments2 = blogs.stream()
                .collect(Collectors.groupingBy(Blog::getAuthorName,
                        Collectors.flatMapping(blog -> blog.getComments().stream(),
                                Collectors.toList())));

        /**
         * {1=[Nice, Very Nice], 2=[Disappointing, Ok, Could be better]}
         */

    }

    public void assers(){
        assert false : "Bad vaue";
        assert false;
    }

}

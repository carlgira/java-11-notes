# Collections

- List:
    - Operations => add, remove, sort, indexof, set, clear
- Set:
    - List of unique objects
    - Operations => add, remove, sort, indexof, set, clear
- Stack =>
    - LIFO last in first out.
    - Operations => add, remove, push, pop, peek.
- Queue
- FIFO first in first out,
-  Operations => add, offer, remove, poll, peek.
- Deque
    - FIFO, LIFO.
    - Operations => adds, addFirst, addLast, offer, offerFirst, offerLast, remove, removeFirst, removeLast, pop, push, poll, pollFirst, pollLast (ArrayDeque LinkedList)

**Map**
- HashMap => Map that does not care about order.
- LinkedHashMap => Map that store keys in the same order they are inserted.
- TreeMap => Map that sorts the entries when inserted.

**Set** (repeated data is not added) (Sets do not have indexes)
- HashSet => Data does not has an order.
- LinkedHasSet => data is stored in same order is inserted.
- TreeSet => data is ordered according with keys

For the TreeSet and TreeMap, the objects must implement Comparable or use Comparator or else it fails.

**Comparator, Comparable**
- Used as utility classes (Collections or in lambdas) to sort arrays.
- Class or lambda to compare values.
- CompareTo method with 3 outputs (-1, 0, +1)
- Having compare (x, y) (when x<y => -1) (when x==y => 0)  (when x>y => +1)
- Comparable => interface with single method to compare values, implemented by objects inside a collection.
- Comparator:
    - public int compare(T o1, T o2) {
- Comparable:
    - public int compareTo(T o)

**Arrays**

- Arrays.binarySearch(new int[]{5,2, 1}, 3) => Search a value on the array an returns the position where is it, else -1.
- Arrays.mismatch(new int[]{5,2, 1}, new int[]{5,1, 4}) => Receives two arrays as input and returns the first position where there is a mismatch, else -1.
- Arrays.sort(new int[]{5,2, 1}) => Sorts the array.
- Arrays.asList() => Returns a List from the input array.
- Arrays.stream() => Create a stream of array.

**Collections**
- Collections functions, sort(), reverse(), binarySearch(), shuffle()


## Concurrent access to collections

- Prevent Collection corruption
    - Unmodifiable: (immutable) (fast, but-read-only)
      Set<Product> readOnlySet = Collections.unmodifiableSet(set);
    - Synchronized: (slow and no scalable)
      Map<Product, Integer> syncMap = Collections.synchronizedMap(map);
    - Copy-on-write: (fast, but consumes memory) Every thread creates a copy for each thread and later joins changes of all threads.
      List<Product> copyOnWrite = new CopyOnWriteArrayList<>(list);
      
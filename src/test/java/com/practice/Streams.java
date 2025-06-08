package com.practice;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    /**
     * 1. find the word having highest length in a sentence
     * reurns the first longest word occurence
     */
    @Test
    public void ques1() {
        String line = "Quick brown fox jumps over the lazy hare";
       String result =
        Arrays.stream(line.split(" "))
                .sorted(Comparator.comparing(String::length)) //DNSO is ascending
                .findFirst()
                .orElse(null);
        System.out.println("longest word: "+result);
    }


    /**
     * 2. remove duplicates from stream & return in same order
     *      convert string into int stream => each int means a character
     *      reduce is a terminal operation
     */
    @Test
    public void ques2() {
        String words = "characterizationcharacterizationcharacterizationcharacterizationcharacterization"; //"dabcadefg";
        //M-1
        String result1 =
        Arrays.stream(words.split(""))
                .collect(
                        Collectors.toCollection(LinkedHashSet::new)
                ).stream()
                .peek(System.out::print)
                .reduce("", (a,b) -> a + b);
        System.out.println(result1);

        //M-2 //int can be cast to char not Character
        String result2 =
        words.chars().mapToObj(x -> (char) x) //need to box them to insert
//                .peek(System.out::print)
//                .map(LinkedHashMap::new) //wrong creates ne set everytime, use collectors
                .collect(Collectors.toCollection(LinkedHashSet::new))
                .stream()
                .map(String::valueOf)
                .reduce("", (x,y) -> x+y);
        System.out.println("\n"+result2);
    }

    /**3. given a sentence, find word having the nth highest length & then the length of that word too
     * N= 2nd
     */
    @Test
    public void ques3() {
        String line = "I am learning stream in java 8";
        String res = Arrays.stream(line.split(" "))
                .sorted(Comparator.comparing(String::length).reversed()) //returns ascending order
                .skip(1).findFirst().get(); //skip 1st to get 2nd largest
        System.out.println("2nd Longest word "+res);

        //finding length of 2nd longest word
        int res2 = Arrays.stream(line.split(" "))
                .map( w -> w.length())
                .sorted(Comparator.reverseOrder()) //to get descending order
                .skip(1).findFirst().get();
        System.out.println("length : "+ res2);
    }

    /**4. given a sentence, find the #occurences of each word
     *
     */
    @Test
    public void ques4() {
        String line = "I am learning stream in java 8";
        Map<String, Long> occurences = Arrays.stream(line.split(" ")) //give list where u can't add more values
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                //forms the key, this function returns the arg itself (t) -> return t
                                Collectors.counting()//forms the value
                        )
                ); //returns a map of string , long
        System.out.println(occurences);
    }


    /**5. given a sentence, find the word with specified num of vowels
     * find word having exactly 2 vowels
     */
    @Test
    public void ques5() {
        String line = "This is a beautiful stream based solution";
        List<String> result =
        Arrays.stream(line.split(""))
//                .map(x -> x.replaceAll("[^aeiouAEIOU]",""))
                // mapping transforms, you need original word intact, use filter
                .filter(x -> x.replaceAll("[^aeiouAEIOU]","").length()==2)
                .collect(Collectors.toList());
        System.out.println(result);
    }

    /**6. given a int array divide into 2 lists one having even nos & other having odd
     *
     */
    @Test
    public void ques6() {
        int[] arr = {23,33,44,55,66,77,88,99,11,22,34,67};
        //boxed() -> a Stream consistent of the elements of this stream, each boxed to an Integer
        Map<Boolean, List<Integer>> result =
       Arrays.stream(arr)
               .mapToObj(x -> (Integer) x)
               .collect(Collectors.partitioningBy(
                       x -> x%2==0,                 //function result forms keys
                       Collectors.toList()          //values
               ));
        System.out.println(result);

        Map<Boolean, List<Integer>> result2 =
        Arrays.stream(arr).filter(x -> x%2==0)
                .boxed()
                .collect(Collectors.groupingBy(
                        x -> x%2 ==0, //will collect only matching values
                        Collectors.toList()
                ));
        System.out.println(result2);
    }

    /**
     * find occurrence of each character in  String words = "dabcadefg";
     * solution: convert to stream of characters
     * chars() gives an IntStream which you can mapToObj() of character
     * use the Collectors.groupingBy(identity, counting) inside collect
     */
    @Test
    public void ques7() {
        String words = "dabcadefgjkjjsdklnsdjdkldjfkadckjnsdjbjeaiguwrhsdn";
        Map<Character, Long> result =
        words.chars().mapToObj(ch -> (char) ch)
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
                );
        System.out.println(result);
    }

    /**
     * find the number of occurrence of each word in a line
     */
    @Test
    public void ques8() {
        String line = "Hello rocky wonderful wonderful wonderful is a wonderful & marvelous marvelous moviewonderful";
        Map<String, Long> result = Arrays.asList(line.split(" ")).stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(result);
    }

    /**
     * find longest word in a scentence
     */
    @Test
    public void ques9() {
        String line = "Hello rocky wonderful is a wonderful & marvelous marvelous moviewonderful";
        String result = Arrays.asList(line.split(" "))
                .stream()
                .max(
                        Comparator
                                .comparing(String::length)
                ).get();
        System.out.println(result);

        //if you want word with nth highest length then below solution
        //also remember comparator does natural sorting order of ascending
        //Here you have to use comparator comparing based on length but sort it too
        Arrays.stream(line.split(" "))
                .sorted(
                        Comparator
                                .comparing(String::length)
                                .reversed()
                )
                .skip(1);

        //you can also use map to return a stream of words & their lengths
//        Arrays.stream(line.split(" "))
//                .map( w -> w.length())
//                .collect(Collectors.toList());
        int answer =  Arrays.stream(line.split(" "))
                .map( w -> w.length())
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();
        //find length of 2nd longest word
        System.out.println(answer);
    }

    /**
     * find occurrence of each character in word & find the one appearing max times
     */
    @Test
    public void ques10() {
        String word = "dabcadefgmoviewonderfulHello";
        Map<Character, Long> charsList = word.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                ));
        Map.Entry<Character, Long> result = charsList.entrySet().stream().max(
                Map.Entry.comparingByValue()
        ).orElse(null); //returns an entry
        System.out.println(charsList);
        System.out.println(result);

    }

    /**
     * find the word which have N number of vowels in them, like print all words having 2 vowels each
     */
    @Test
    public void ques11() {
        String line = "Hello fight club is a wonderful & marvelous movie";

        //below is wrong as it won't result in anything
//        List<String> list = Arrays.stream(line.split(" "))
//                .map(
//                        word -> word.replaceAll(
//                                "[^aeiouAEIOU]]", //regex pattern allow only vowels
//                                "" //replace them with empty chars
//                        )
//                ).sorted(
//                        Comparator.comparing(String::length) //sorts in natural order
//                ).collect(
//                        Collectors.toList() //returns a stream of word containing only vowels
//                );
//                //.findFirst().get()
//         System.out.println(list);

        //you the replqceAll returns the number of items it has replaced, you can add a check for that
        //map will return the stream itself after modification,
        // filter will return the original objects only which match your condition
        Arrays.stream(line.split(" "))
                .filter(
                        word -> word.replaceAll("[^aeiouAEIOU]","")
                                .length() == 3 //as after replacing all chars that do not match
                        // the regex only vowels will be left
                ).forEach(
                        System.out::println
                );

    }

    /**
     * Given a word give me how many times each characters appears in a word/sentence
     */
    @Test
    public void ques12() {
        //HINT : whenever you are asked to give count , most questions could be solved by grouping by
        //grouping by return two things most times so use it
        String line = "Given a word give me how!! many times each cha@@racters appears in a word/sentence?";

//        line.replaceAll(" ", "").chars().mapToObj( x -> (char) x);

        Map<String, Long> result = Arrays.stream(
                line.replaceAll(" ", "").split(""))
                .collect(Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
             );
        System.out.println(result);
        //what if you want only characters not special characters

        Map<String, Long> result2 = Arrays.stream(
                        line.replaceAll(" ", "").split(""))
                //as regexp works only on String convert to string, split already returns String[]
                .filter( w -> w.matches("[a-zA-Z]"))
                .collect(Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(result2);
    }

    /**
     * find integers of highest / lowest possible value
     */
    @Test
    public void ques13() {
        int[] arr = {172, 2,3,4,5,7};
        String result = Arrays.stream(arr)
                .sorted() //can leave as is for natural order .sorted() => smallest number
                .mapToObj(String::valueOf) //1st sort & then convert to string
                .reduce("", (a, b) -> a + b);

        String result2 = Arrays.stream(arr)
                .mapToObj(x -> x)
                .sorted(Comparator.reverseOrder()) //can leave as is for natural order .sorted() => smallest number
                //Comparator.reverseOrder will throw error for integer, use collections
                //java.util.stream.IntStream cannot be applied to given types as they are primitives;
//                .mapToObj(String::valueOf) //1st sort & then convert to string
                .map(x -> String.valueOf(x))
                .reduce("", (a, b) -> a + b);

        System.out.println(result);
        System.out.println(result2);

    }

    /**
     * given an int array find the sum of unique elements
     */
    @Test
    public void ques14() {
        int[] arrnum = {1,3,4,6,4,4,6,5,5,9,0,5,4,2,5,4,7,6,2,4,3,3,5,4,5,5,9,6,6,6,4};
        //M-1 : using reduce
        int result = Arrays.stream(arrnum).distinct().reduce(0,(x,y) -> x+y);
        System.out.println(result);

        //M-2 converting to a SET
        Set<Integer> result2 = Arrays.stream(arrnum)
                .mapToObj(x->x)
                .collect(Collectors.toSet());
        int result3 = result2.stream().reduce(0,(a,b)->a+b);
        System.out.println(result3);
    }

    /**
     * find the 2nd most repeated character in a scentence
     */
    @Test
    public void ques15() {
       String line = "Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=61616:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.st";
       LinkedHashMap<String, Long> charOccurenceMap =
       Arrays.stream(
                line.replaceAll(" ","" )
                .split("")
                ) //split because chars() returns an IntStream and we want string match regex filter too
                .filter( str  -> str.matches("[a-zA-Z]"))
               .map(String::toLowerCase) //otherwise all chars will repeat based on case
                .collect( //you are asked to find 2nd most occuring
                        //there can be many chars which occur similar num of times
                        //so preserve insertion order & collectors.groupingBy can have 3 arguments too
                       Collectors.groupingBy(
                               Function.identity(),
                               LinkedHashMap::new,
                               Collectors.counting()
                       )
                );
        System.out.println(charOccurenceMap);

        Map.Entry<String, Long> result = charOccurenceMap
                                                .entrySet().stream()
                                                .skip(1)
                                                .findFirst()
                                                .orElse(null);
        System.out.println(result);
    }

    /**
     * given an array of numbs, group them based on tens, twenties, thirties, forties
     */
    @Test
    public void ques16() {
        int[] nums = {37, 92, 15, 60, 3, 84, 18, 71, 9, 45, 28, 67, 50, 33, 88, 5, 73, 21, 96, 11,
                82, 31, 77, 40, 1, 64, 90, 13, 7, 52, 26, 98, 58, 24, 86, 70, 17, 35, 93, 19,
                10, 56, 43, 20, 6, 49, 0, 39, 12, 75, 80, 46, 29, 69, 32, 95, 38, 47, 59, 30,
                14, 4, 22, 66, 99, 42, 63, 36, 34, 2, 78, 68, 87, 48, 85, 8, 94, 76, 16, 25,
                81, 74, 44, 55, 41, 97, 62, 53, 23, 57, 51, 27, 89, 91, 65, 83, 61, 79, 72, 54};
        //M-1
        Map<Integer,List<Integer>> result = new HashMap<>();
        Long numsList =
        Arrays.stream(nums)
                .map(x -> {
                    if(x>9) {
                        int tensPlace = (x/10)%10;
                        result.computeIfAbsent(tensPlace * 10, k -> new ArrayList<>()).add(x);
                    } else {
                        result.computeIfAbsent(0, k -> new ArrayList<>()).add(x);
                    }
                    return x;
                }) //map is not a terminal operator, so execute it need to add terminal
                .boxed()
                .collect(Collectors.counting());

        System.out.println(result);

       // M-2
       Map<Integer, List<Integer>> result2 = Arrays.stream(nums)
                .boxed()
                .collect(
                        Collectors.groupingBy(
                                //1st key you want the tens place signifier
                                //in 2nd values you want a list
                                num -> (num / 10) * 10,
                                LinkedHashMap::new, //occurrence order preserved
                                Collectors.toList()
                        )
                );
        System.out.println(result2);
    }

    /**
     * give a list of strings take out only integers
     */
    @Test
    public void ques17() {
        List<Integer> numbers =
        Arrays.asList("abs","122","323","fdaf","123aaf","12asd23","4213","24","@##@","1")
                .stream().filter(
                        x -> x.matches("\\d+") //[0-9]+ ,[0-9]{3}, \\d => single digit
                ).map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(numbers);
    }

    /**
     * Find product of first n elements in a list after skipping k elements
     */
    @Test
    public void ques18() {
        int k = 6;
        int n = 15;
        Integer reduce =
        Arrays.asList(37, 92, 15, 60, 3, 84, 18, 71, 9, 45, 28, 67, 50, 33, 88, 5, 73, 21, 96, 11,
                82, 31, 77, 40, 1, 64, 90, 13, 7, 52, 26, 98, 58, 24, 86, 70, 17, 35, 93, 19,
                10, 56, 43, 20, 6, 49, 0, 39, 12, 75, 80, 46, 29, 69, 32, 95, 38, 47, 59, 30,
                14, 4, 22, 66, 99, 42, 63, 36, 34, 2, 78, 68, 87, 48, 85, 8, 94, 76, 16, 25,
                81, 74, 44, 55, 41, 97, 62, 53, 23, 57, 51, 27, 89, 91, 65, 83, 61, 79, 72, 54)
                .stream()
                .skip(k)
                .limit(n)
                .reduce(1,(a,b)-> a * b);
        System.out.println(reduce);
    }

    /**
     * group or pair anagrams from a list of strings
     */
    @Test
    public void ques19() {
        String[] words = {"pat","tap","pan","nap","team","meat","tree","silent","listen"};
        //M-2
        Map<String, List<String>> result =
        Arrays.stream(words).collect(
                Collectors.groupingBy(
                        x ->
                            Arrays.stream(x.toLowerCase().split("")).sorted().collect(Collectors.joining())
                        ,LinkedHashMap::new
                        ,Collectors.toList()
                )
        );
//       result.entrySet().stream().forEach(System.out::println);
        System.out.println(result);
    }

    /**
     * WAP to return product of alternative numbers in a number array
     */
    @Test
    public void ques20() {
        int[] nums = {3, 2, 5, 6, 3, 8, 8, 1, 9, 4, 8, 7, 5, 3, 8, 5, 7};
       // IntStream has a range function where range is the indexes like in angular
        Integer result =
        IntStream.range(0, nums.length) //gives a stream of indexes
                .filter( idx -> idx%2==0)
                .peek(x -> System.out.println("index = " + x + " & number is " + nums[x]))
                .map(idx -> nums[idx]) //now you have indexes only right, take numbers out
                .reduce(1, (a,b) -> a * b);
        System.out.println(result);
    }

    /**
     * find product of numbers 1st & last, 2nd & 2nd last
     */
    @Test
    public void ques21() {
        //start =0 & len-1-start
        //start = 1 & len-1-start
        //you have to move till half of the array
        //what if the array is odd?
        int[] nums = {7, 92, 15, 60, 3, 84, 18, 71, 9, 45, 28, 67, 50, 33, 88, 5, 73, 21, 96, 11,
                82, 31, 77, 40, 1, 64, 90, 13, 7, 52, 26, 98, 58, 24, 86, 70, 17, 35, 93, 19,
                10, 56, 43, 20, 6, 49, 0, 39, 12, 75, 80, 46, 29, 69, 32, 95, 38, 47, 59, 30,
                14, 4, 22, 66, 99, 42, 63, 36, 34, 2, 78, 68, 87, 48, 85, 8, 94, 76, 16, 25,
                81, 74, 44, 55, 41, 97, 62, 53, 23, 57, 51, 27, 89, 91, 65, 83, 61, 79, 72, 54};

        IntStream.range(0, nums.length/2)
                .map(x -> nums[x] * nums[ (nums.length-1) - x])
                .forEach(System.out::println);
    }

    /**
     * Move all zeros to begining of an int array
     */
    @Test
    public void ques22() {
        int[] num = {7, 92, 15, 60, 3, 84, 18, 71, 9, 45, 28, 67, 0, 33, 88, 5, 73, 21, 96, 11,
                82, 31, 77, 0, 1, 64, 90, 13, 7, 52, 26, 98, 58, 24, 86, 0, 17, 35, 93, 19,
                10, 56, 43, 0, 6, 49, 0, 39, 12, 75, 0, 46, 29, 69, 32, 95, 38, 47, 59, 0,
                14, 4, 22, 66, 99, 42, 63, 36, 34, 2, 78, 68, 87, 48, 85, 8, 94, 76, 16, 25,
                81, 74, 44, 55, 41, 97, 62, 53, 23, 57, 51, 27, 89, 91, 65, 83, 61, 79, 72, 54};
        //M-1 : make a list of zeros & non-zeros, concat both lists, concat two lists using flatmap
        Stream<Integer> zeros =
        Arrays.stream(num)
                .boxed()
                .filter(x -> x==0);

        Stream<Integer> nonzeros =
                Arrays.stream(num)
                        .boxed()
                        .filter(x -> x>0);

        List<Integer> result = Stream.concat(zeros, nonzeros).collect(Collectors.toList());
        System.out.println(result);

        //M-2 : Collectors.partitionBy will divide stream into a partition condition
        //mentioned as lambda in predicate & with a map of the lambda result & the object which pass that
        // (!! not in-place, returns new array)
        Map<Boolean, List<Integer>> result2 =
        Arrays.stream(num).boxed()
                .collect(Collectors
                        .partitioningBy(
                        x -> x != 0 //this results in boolean so map is of key boolean & values are list of int boxed
                ));

        List<Integer> result3  = result2.entrySet().stream()
                .flatMap( x -> x.getValue().stream())
                .collect(Collectors.toList());

        System.out.println(result2);
        System.out.println(result3);

    }

    /**
     * Count vowels and consonants in a string
     */
    @Test
    public void ques23() {
        String line = "Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java -ea -Didea.test.cyclic.buffer.size=1048576 -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=61616:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.st";
        Map<Boolean, List<String>> wrongRes =
        Arrays.stream(line.split(""))
                .filter(x -> x.matches("[a-zA-Z]"))
                .collect(
                        Collectors.partitioningBy(
                                x -> x.matches("[^aeiouAEIOU]")
                        )
                );
        //you do not need list of chars, just count is needed, grouping by would work
        System.out.println(wrongRes);
        Map<Boolean, Long> resutlt =
        Arrays.stream(line.split(""))
                .filter(x -> x.matches("[a-zA-Z]"))
                .collect(
                        Collectors.groupingBy(
                                x ->  x.matches("[^aeiouAEIOU]"),
                                Collectors.counting()
                        )
                );
        System.out.println(resutlt);

    }

    /**
     * reverse a string using string & ReverseInPlace(hint use a temp variable & swap)
     */
    @Test
    public void ques24() {
        String line = "Library/Java/JavaVirtualMachines/jdk-21";
        System.out.println(line);
        String reversed = IntStream.range(0,line.length())
//                .map(i -> line.charAt( (line.length()-1)-i ))
// .map(...) in an IntStream expects an int to int transformation.
                //converting int of IntStream to character
                .mapToObj(i -> String.valueOf(line.charAt( (line.length()-1)-i )))
                .collect(Collectors.joining(""));
//        System.out.println(reversed);

        //M-2 In-place reversal using temp variable
        String[] letters = line.split("");
//        You're looping i from 0 to letters.length, but swapping i and j on every iteration.
//        This causes double swaps, then reverses them again.
//        It should only go halfway (i < j) to avoid undoing the swap.

//        i < j ensures we only go up to the middle.
//                Swap letters[i] with letters[j].
//        When i == j, you're at the middle (no need to swap).
//        Once i > j, you’ve already reversed everything.
        //for odd length Middle c stays in place

//        for(int i = 0, j = letters.length-1 ; i < letters.length ; i++, j--) {
//            String temp = letters[j];
//            letters[j] = letters[i];
//            letters[i] = temp;
//        }
        for(int i = 0, j = letters.length-1 ; i < j ; i++, j--) {
            String temp = letters[j];
            letters[j] = letters[i];
            letters[i] = temp;
        }
        //for reversing if you traverse half the array it would work, so either i = j or i < j or i < line.length/2
        String reversedResult = Arrays.stream(letters).collect(Collectors.joining());
        System.out.println(reversedResult);

    }

    /**
     * Convert string to title case
     */
    @Test
    public void ques25() {
        String para = "Title case, also known as headline case, is a capitalization style where the first letter of most words in a title or heading is capitalized, while the rest of the letters in those words are lowercase. Minor words like articles (\"a,\" \"an,\" \"the\"), conjunctions, and short prepositions are usually not capitalized, unless they are the first or last word in the title";
        String result =
        Arrays.stream(para.split(" "))
                .map(word ->
                        { if(word.length()>1){
                                String title =Character.toUpperCase(word.charAt(0))
                                        + word.substring(1).toLowerCase();
                                return title;
                            } else {
                                return word;
                            }
                        }
                ).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    /**
     *
     */
    @Test
    public void ques26() {}

    @Test
    public void ques27() {}

    @Test
    public void ques28() {}

    @Test
    public void ques29() {}

    @Test
    public void ques30() {}

    @Test
    public void ques31() {}

    @Test
    public void ques32() {}

    @Test
    public void ques33() {}

    @Test
    public void ques34() {}

    @Test
    public void ques35() {}

    @Test
    public void ques36() {}

    @Test
    public void ques37() {}

    @Test
    public void ques38() {}

    @Test
    public void ques39() {}

    @Test
    public void ques40() {}

    @Test
    public void ques41() {}

    @Test
    public void ques42() {}

    @Test
    public void ques43() {}

    @Test
    public void ques44() {}

    @Test
    public void ques45() {}

    @Test
    public void ques46() {}

    @Test
    public void ques47() {}

    @Test
    public void ques48() {}

    @Test
    public void ques49() {}

    @Test
    public void ques50() {}

}
package edu.cmu.andrew.dmolenho;

// Dan Molenhouse dmolenho
// Project 5 Part 2
// Tempest Analyzer

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import org.apache.log4j.Level;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import java.util.Arrays;
import org.apache.log4j.Logger;
import java.util.Scanner;

public class TempestAnalytics {

    //tempestAnalytics method handles all operations
    private static void tempestAnalytics(String fileName) {
        //turn off the loggers
        Logger.getLogger("org").setLevel(Level.OFF);
        Logger.getLogger("akka").setLevel(Level.OFF);

        //Spark initializations
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("TempestAnalytics");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        // task 0, count # of total lines
        JavaRDD<String> input = sparkContext.textFile(fileName);

        // task 0, print
        System.out.println("Task 0, Number of Lines: " + input.count());

        // task 1, number of words
        JavaRDD<String> words = input.flatMap(content -> Arrays.asList(content.split("[^a-zA-Z]+")));

        // task 1, print
        System.out.println("Task 1, Number of Words: " + words.count());

        // task 2, print the # of distinct words
        System.out.println("Task 2, Number of Distinct Words: " + words.distinct().count());

        // task 3
        JavaRDD<String> symbols = input.flatMap(content -> Arrays.asList(content.split("")));

        // task 3 print
        System.out.println("Task 3, number of symbols: " + symbols.count());

        // task 4 print distinct symbols
        System.out.println("Task 4, number of distinct symbols: " + symbols.distinct().count());

        // task 5 number of distinct letters
        JavaRDD<String> letters = input.flatMap(content -> Arrays.asList(content.split("")));
        letters = letters.filter(k -> k.matches("[a-zA-Z]+"));
        System.out.println("Task 5, number of distinct letters: " + letters.distinct().count());

        // task 6 search
        System.out.println("Task 6, Search for: ");
        Scanner in = new Scanner(System.in);
        String search = in.nextLine().trim();
        System.out.println("Lines with " + search + ": ");

        // task 6 search
        input.foreach((string) -> {
            if (string.contains(search))
                System.out.println(string);
        }
        );
    }


    //Main method borrowed from WordCount.java in Lab9
    public static void main (String[] args) {
        //If no arg detected
        if (args.length == 0) {
            System.out.println("No files provided.");
            System.exit(0);
        }

        //run tempestAnalytics method
        tempestAnalytics(args[0]);
    }
}
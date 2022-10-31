# Distributed-Systems-Project5-Hadoop-and-Spark
**Project 1 - Distributed Systems**

**Project Objective:**
1. Use Hadoop servers and mapper and reducer java classes to perform data processing operations.
2. Use Spark to perform data processing operations.

**Tasks:**
1. There was a series of 7 different tasks to complete in a Hadoop server that required the Reducer and Mapper to be changed, compiled, made into a JAR, and executed on Hadoop. An example of one of these tasks will be shown below.
2. Use a Apache Spark cluster to perform a series of analytics on a large text file.

**Topics/Skills covered:**
- Distributed computing
- Cloud computing
- MapReduce
- Hadoop
- Spark
- JARs

**Demonstration of completed tasks:**

**Hadoop tasks:**

All 7 tasks in Hadoop followed this format. It involved making some changes to the Java file to correctly compute the desired metric, then the file was compiled and turned into a JAR and run on the cluster. This is just one example:

>Setup
>
>mkdir Task2
>
>cd Task0
>
>copy WordCount.java:
>
>cp -R /home/student134/Project5/Part_1/Task0/WordCount.java /home/student134/Project5/Part_1/Task2
>
>Changes to java code:

        public static class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable>
        {
                private final static IntWritable one = new IntWritable(1);

                private Text word = new Text();	

                @Override
                //map method modified from original WordCount.java file provided by instructors
                //if statement testing for "fact" added

                public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
                {
                        //tokenize string
                        String line = value.toString();
                        StringTokenizer tokenizer = new StringTokenizer(line);

                        //parse through words
                        while(tokenizer.hasMoreTokens())
                        {
                                word.set(tokenizer.nextToken());

                                //Only major change of WordCount original code
                                //This tests if the word contains the "fact" string
                                if(word.toString().toLowerCase().contains("fact")){
                                        context.write(word, one);
                                }
                        }
                }
        }

>More setup
>
>mkdir wordCount_classes
>
>Compile Java
>
>javac -classpath /usr/local/hadoop/hadoop-core-1.2.1.jar:./wordCount_classes -d wordCount_classes 
>
>WordCount.java
>
>Create Jar
>
>jar -cvf wordcount.jar -C wordCount_classes/ .
>
>Execute JAR
>
>hadoop jar /home/student134/Project5/Part_1/Task2/wordcount.jar org.myorg.WordCount  /user/student134/input/words.txt /user/student134/output/Task2
>
>View Output:
>
>hadoop dfs -cat /user/student134/output/Task2/part-r-00000
>
>Merge output to local files:
>hadoop dfs -getmerge /user/student134/output/Task2 Task2Output


**Spark Demonstration:**

Similarly, the Spark section of this project involved a large text file that would be analyzed in numerous different ways on a Spark cluster. The images below demonstrate that the Spark cluster was running correctly and performing the requested analysis.

The text file is a .txt file of The Tempest:

<img width="468" alt="image" src="https://user-images.githubusercontent.com/114946651/199124083-b120bdc3-c8ee-4cc9-8353-173004bad92e.png">

Spark library added:

<img width="468" alt="image" src="https://user-images.githubusercontent.com/114946651/199124142-0cb2fed0-e783-4b2b-9c80-d038653948e8.png">

Screenshot of the Tempest Analytics file performing analytics on the Spark cluster:
![image](https://user-images.githubusercontent.com/114946651/199124234-39cab9d2-3857-458a-b1bf-56cc6b199e7f.png)






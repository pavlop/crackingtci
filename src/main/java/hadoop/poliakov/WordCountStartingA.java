package hadoop.poliakov;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class WordCountStartingA {
    private static final Logger log = Logger.getLogger(WordCountStartingA.class);

    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(WordCountStartingA.class);
//        conf.setJobName("wordcount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(MapWordsCounter.class);
        conf.setCombinerClass(CombineLocal.class);
        conf.setReducerClass(ReduceWordsCounter.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path("hadoopinput"));
        // true stands for recursively deleting the folder you gave
        FileSystem.get(conf).delete(new Path("target/hadoop"), true);
        FileOutputFormat.setOutputPath(conf, new Path("target/hadoop"));

        JobClient.runJob(conf);




        JobConf sorterJobConf = new JobConf(WordCountStartingA.class);
        sorterJobConf.setOutputKeyClass(LongWritable.class);
        sorterJobConf.setOutputValueClass(Text.class);
        sorterJobConf.setMapperClass(MapSorter.class);
        sorterJobConf.setCombinerClass(ReduceSorter.class);

        FileInputFormat.setInputPaths(sorterJobConf, new Path("target/hadoop"));
        // true stands for recursively deleting the folder you gave
        FileSystem.get(conf).delete(new Path("target/hadoopSorted"), true);
        FileOutputFormat.setOutputPath(sorterJobConf, new Path("target/hadoopSorted"));
        JobClient.runJob(sorterJobConf);
    }

    public static class MapWordsCounter extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                if (word.toString().toLowerCase().contains("zz")) output.collect(word, one);
            }
        }
    }

    public static class CombineLocal extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
            int sum = 0;
            while (values.hasNext()) sum += values.next().get();
            output.collect(key, new IntWritable(sum));
        }
    }

    public static class ReduceWordsCounter extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            output.collect(key, new IntWritable(sum));
        }
    }

    public static class MapSorter extends MapReduceBase implements Mapper<LongWritable, Text, LongWritable, Text> {
        Text word = new Text();
        LongWritable counter = new LongWritable();

        public void map(LongWritable key, Text value,  OutputCollector<LongWritable, Text> output,
                Reporter reporter) throws IOException {
            String line = value.toString();
            StringTokenizer tokenizer = new StringTokenizer(line, "\t");
            log.error("PAVLOP line="+line);
            word.set(tokenizer.nextToken());
            counter.set(Long.parseLong(tokenizer.nextToken()));

//            log.error("PAVLOP word="+word);
//            log.error("PAVLOP counter="+counter);
            output.colelect(counter, word);
        }
    }

    public static class ReduceSorter extends MapReduceBase implements Reducer<LongWritable, Text,
            LongWritable, Text> {
        public void reduce(LongWritable key, Iterator<Text> values, OutputCollector<LongWritable,
                Text> output, Reporter reporter) throws IOException {
            while (values.hasNext()) {
                output.collect(key, values.next());
            }
        }
    }
}
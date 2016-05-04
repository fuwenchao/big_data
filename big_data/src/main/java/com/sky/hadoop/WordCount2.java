package com.sky.hadoop;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/** 测试类(自己实现的代码统计类)
 * @author Sky
 *
 */
public class WordCount2 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if(otherArgs.length != 2){
			System.out.println("Usage：wordcount <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "WordCount");
		job.setJarByClass(WordCount2.class);
		job.setMapperClass(MapperClass.class);// 设置Mapper类
		job.setReducerClass(ReduceClass.class);// 设置Reducer类
		job.setOutputKeyClass(Text.class);// 设置输出Key类型
		job.setOutputValueClass(IntWritable.class);// 设置输出Value类型
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));// 设置输入路径
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));// 设置输出路径
		System.out.println(job.waitForCompletion(true) ? 0 : 1);// 提交任务，并执行。
	}
}

/** Map处理类
 * @author Sky
 *
 */
class MapperClass extends Mapper<Object, Text, Text, IntWritable> {
	
	@Override
	protected void map(Object key, Text value, Context context)throws IOException, InterruptedException {
		// 第1步：获得某行值("My name is tom")
		String str = key.toString();
		// 第2步：拆分当前行值
		StringTokenizer stringToKenizer = new StringTokenizer(str);// 默认是空格
		
		Text keyText = new Text("key");
		while(stringToKenizer.hasMoreTokens()){
			keyText.set(stringToKenizer.nextToken());
			// 第3步：设置Map键值对(Key：My；value：1)
			context.write(keyText, new IntWritable(1));
		}
	}
}

/** Reduce处理类
 * @author Sky
 *
 */
class ReduceClass extends Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value, Context context)throws IOException, InterruptedException {
		Iterator<IntWritable> it = value.iterator();
		int sum = 0;// 汇总
		while(it.hasNext()){
			sum += it.next().get();
		}
		context.write(key, new IntWritable(sum));
	}
}
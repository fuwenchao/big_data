package com.sky.spark;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class RDD {

	public static void main(String[] args) {
		parallelize();
		textFile();
		filter();
	}
	
	/**
	 * 读取"集合数据集"(开发和测试时使用)
	 */
	@SuppressWarnings("resource")
	private static void parallelize(){
		SparkConf sparkConf = new SparkConf().setAppName("parallelize").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.parallelize(Arrays.asList("Hello World", " ", "Spark Demo!"));
		System.out.println("统计集合数据集个数：------->"+inputRDD.count());
		context.stop();
	}
	
	/**
	 * 读取"外部数据集"
	 */
	@SuppressWarnings("resource")
	private static void textFile(){
		SparkConf sparkConf = new SparkConf().setAppName("textFile").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile("D:\\ftp\\1\\newAllBaseData.txt", 1);
		System.out.println("统计外部数据集个数：------->"+inputRDD.count());
		context.stop();
	}
	
	@SuppressWarnings({ "resource", "serial" })
	private static void filter(){
		SparkConf sparkConf = new SparkConf().setAppName("filter").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile("D:\\ftp\\1\\newAllBaseData.txt", 1);
		JavaRDD<String> errorsRDD = inputRDD.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String v1) throws Exception {
				return v1.contains("newInfo");
			}
		});
		System.out.println("输出错误日志个数：--------->"+errorsRDD.count());
		context.stop();
	}
}
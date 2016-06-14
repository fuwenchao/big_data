package com.sky.spark.example;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

/** 单词统计应用
 * @ClassName: WordCount
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午2:31:25
 * @version V1.0
 */
public class WordCount {
	
	private static final Pattern SPACE = Pattern.compile(" ");
	
	/** 测试方法
	 * @MethodName: main
	 * @Description: 
	 * @param args
	 */
	@SuppressWarnings({ "serial", "resource" })
	public static void main(String[] args) {
		// 创建SparkConf对象来配置你的应用(设置本地开发：setMaster("local"))
		SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount").setMaster("local");
		// 基于SparkConf创建一个SparkContext对象
		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		// 读取外部数据集(本地文件)
		JavaRDD<String> lines = ctx.textFile("D:\\ftp\\1\\*", 1);
		
		// 切分单词
		JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>(){
			@Override
			public Iterable<String> call(String s) {
				return Arrays.asList(SPACE.split(s));
			}
		});

		// 转换为键值对
		JavaPairRDD<String, Integer> ones = words.mapToPair(new PairFunction<String, String, Integer>() {
			@Override
			public Tuple2<String, Integer> call(String s){
				return new Tuple2<String, Integer>(s, 1);
			}
		});

		// 计数
		JavaPairRDD<String, Integer> counts = ones.reduceByKey(new Function2<Integer, Integer, Integer>() {
			@Override
			public Integer call(Integer i1, Integer i2){
				return i1 + i2;
			}
		});

		// 收集数据
		List<Tuple2<String, Integer>> output = counts.collect();
		for(Tuple2<?, ?> tuple : output) {// 循环输出
			System.out.println(tuple._1() + ": " + tuple._2());
		}
		ctx.stop();// 退出应用
	}
}
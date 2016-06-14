package com.sky.spark;

import java.io.File;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/** Spark操作类
 * @ClassName: SparkClient
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午3:42:59
 * @version V1.0
 */
public class SparkClient {
	
	/** 
	 * 文件路径
	 */
	private static String FILE_PATH = System.getProperty("user.dir")+File.separator+"dataFile"+File.separator+"BaseData.txt";
	
	/** 测试方法
	 * @MethodName: main
	 * @Description: 
	 * @param args
	 */
	public static void main(String[] args) {
		SparkClient client = new SparkClient();
		client.parallelize();
		client.textFile();
		client.first();
		client.map();
		client.filter1();
		client.filter2();
	}
	
	/** 读取"集合数据集"(开发和测试时使用)
	 * @MethodName: parallelize
	 * @Description: 
	 */
	@SuppressWarnings("resource")
	private void parallelize(){
		SparkConf sparkConf = new SparkConf().setAppName("parallelize").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.parallelize(Arrays.asList("Hello World", " ", "Spark Demo!"));
		System.out.println("统计集合数据集个数：------->"+inputRDD.count());
		context.stop();
	}
	
	/** 读取"外部数据集"
	 * @MethodName: textFile
	 * @Description: 
	 */
	@SuppressWarnings("resource")
	private void textFile(){
		SparkConf sparkConf = new SparkConf().setAppName("textFile").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile(FILE_PATH, 1);
		System.out.println("统计外部数据集个数：------->"+inputRDD.count());
		context.stop();
	}
	
	/** 输出第一行数据
	 * @MethodName: first
	 * @Description: 
	 */
	@SuppressWarnings({ "resource" })
	private void first(){
		SparkConf sparkConf = new SparkConf().setAppName("filter").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile(FILE_PATH, 1);
		System.out.println("输出第一行字符串：--------->"+inputRDD.first());
		context.stop();
	}
	
	/** Map函数求所有数的平方值
	 * @MethodName: map
	 * @Description: 
	 */
	@SuppressWarnings({ "serial", "resource" })
	private void map(){
		SparkConf sparkConf = new SparkConf().setAppName("filter").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<Integer> inputRDD = context.parallelize(Arrays.asList(1, 2, 3, 4));
		JavaRDD<Integer> result = inputRDD.map(new Function<Integer, Integer>() {
			@Override
			public Integer call(Integer x) throws Exception {
				return x*x;
			}
		});
		System.out.println("输出所有数的平方值：-------->"+StringUtils.join(result.collect(), ","));
		context.stop();
	}
	
	/** 过滤字符串
	 * @MethodName: filter1
	 * @Description: 
	 */
	@SuppressWarnings({ "resource", "serial" })
	private void filter1(){
		SparkConf sparkConf = new SparkConf().setAppName("filter").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile(FILE_PATH, 1);
		JavaRDD<String> errorsRDD = inputRDD.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String x) throws Exception {
				return x.contains("newInfo");
			}
		});
		System.out.println("输出错误日志个数：--------->"+errorsRDD.count());
		context.stop();
	}
	
	/** 过滤字符串
	 * @MethodName: filter2
	 * @Description: 
	 */
	@SuppressWarnings({ "resource" })
	private void filter2(){
		SparkConf sparkConf = new SparkConf().setAppName("filter").setMaster("local");
		JavaSparkContext context = new JavaSparkContext(sparkConf);
		JavaRDD<String> inputRDD = context.textFile(FILE_PATH, 1);
		JavaRDD<String> errorsRDD = inputRDD.filter(new FilterFunction("newInfo"));
		System.out.println("输出错误日志个数：--------->"+errorsRDD.count());
		context.stop();
	}
}

/** 自定义过虑类
 * @ClassName: FilterFunction
 * @Description: 
 * @author Sky
 * @date 2016年6月7日 下午3:48:53
 * @version V1.0
 */
class FilterFunction implements Function<String, Boolean>{
	
	private static final long serialVersionUID = 1L;
	private String query;
	
	public FilterFunction(String query){
		this.query = query;
	}
	
	@Override
	public Boolean call(String x) throws Exception {
		return x.contains(query);
	}
}
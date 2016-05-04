package com.sky.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

public class OperateHDFS {

	public static void main(String[] args) {
		readHDFS(args[0]);
		String filePath = "/home/hadoop/hdfs_mkdirs";
		mkdirs(filePath);
		copyFromLocalFile("/home/hello.txt", filePath);
		FindFileOnHDFS(filePath+"/hello.txt");
	}
	
	/** 读取Dnfs数据
	 * @param uri
	 */
	private static void readHDFS(String uri) {
		Configuration conf = new Configuration();
		InputStream in = null;
		try {
			FileSystem fs = FileSystem.get(URI.create(uri), conf);
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(null != in){
				IOUtils.closeStream(in);
			}
		}
	}

	/** 创建文件夹
	 * @param path
	 */
	private static void mkdirs(String path){
		// 第1步：获取Configuration对象
		Configuration conf = new Configuration();
		try {
			// 第2步：获取FileSystem对象
			FileSystem fs = FileSystem.get(conf);
			// 第3步：创建文件夹
			fs.mkdirs(new Path(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 复制本地文件到Hadoop服务器
	 * @param source 源地址
	 * @param dest 目标地址
	 */
	private static void copyFromLocalFile(String src, String dst){
		// 第1步：获取Configuration对象
		Configuration conf = new Configuration();
		try {
			// 第2步：获取FileSystem对象
			FileSystem fs = FileSystem.get(conf);
			// 第3步：从本地复制文件到Hadoop服务器
			fs.copyFromLocalFile(new Path(src), new Path(dst));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 查找某个文件在HDFS集群的位置
	 * @param filePath 文件
	 */
	private static void FindFileOnHDFS(String filePath){
		Configuration conf = new Configuration();
		try {
			FileSystem fs = FileSystem.get(conf);
			// 获取分布式文件系统
			DistributedFileSystem hdfs = (DistributedFileSystem) fs;
			// 获取所有的节点数
			DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
			// 循环打印
			for(int i=0; i<dataNodeStats.length; i++){
				System.out.println("DataNode_"+i+"_Name:"+dataNodeStats[i].getHostName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Configuration conf1 = new Configuration();
		try {
			FileSystem hdfs = FileSystem.get(conf1);
			// 获取文件系统里面的文件信息
			FileStatus fileStatus = hdfs.getFileLinkStatus(new Path(filePath));
			// 获取文件的块信息
			BlockLocation[] blkLocations = hdfs.getFileBlockLocations(fileStatus, 0, 100);
			// 循环打印
			for(int j=0; j<blkLocations.length; j++){
				String[] hosts = blkLocations[j].getHosts();
				System.out.println("block_"+j+"_location:"+hosts[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
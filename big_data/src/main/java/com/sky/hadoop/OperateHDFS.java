package com.sky.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class OperateHDFS {

	public static void main(String[] args) {
		readHDFS(args[0]);
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
}
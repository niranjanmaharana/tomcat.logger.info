package com.legato.tomcat.logger.info.util;

import java.io.Serializable;
import java.util.Date;

public class FileObj implements Serializable, Comparable<FileObj>{
	private static final long serialVersionUID = 8792468649964518731L;
	public String filePath;
	public String fileName;
	public Date modifiedDate;
	public int fileSize;
	public int compareTo(FileObj f){
		return  this.fileSize-f.fileSize;
	}
}
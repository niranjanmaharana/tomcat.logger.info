package com.legato.tomcat.logger.info.util;

import java.util.Comparator;

public class FileObjByDateAesc implements Comparator<FileObj>{
	@Override
	public int compare(FileObj f1, FileObj f2) {
		return f1.modifiedDate.compareTo(f2.modifiedDate);
	}
}
package com.legato.tomcat.logger.info.util;

import java.util.Comparator;

public class FileObjByNameDesc implements Comparator<FileObj>{
	@Override
	public int compare(FileObj f1, FileObj f2) {
		return -(f1.fileName.compareTo(f2.fileName));
	}
}
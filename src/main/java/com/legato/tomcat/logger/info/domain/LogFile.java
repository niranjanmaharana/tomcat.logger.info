package com.legato.tomcat.logger.info.domain;

import java.util.Comparator;
import java.util.Date;

public class LogFile implements Comparable<LogFile>{
	private String fileName;
	private int size;
	private Date lastModifiedDate;
	
	public LogFile() {
		super();
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		LogFile other = (LogFile) obj;
		if (fileName == null) {
			if (other.fileName != null) return false;
		} else if (!fileName.equals(other.fileName)) return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null) return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate)) return false;
		if (size != other.size)
			return false;
		return true;
	}
	@Override
	public int compareTo(LogFile logFile) {
		if(logFile == null) return -1;
		return this.fileName.compareTo(logFile.fileName);
	}
	
	public static Comparator<LogFile> sortByName(final boolean ascending){
		return new Comparator<LogFile>() {
			@Override
			public int compare(LogFile logFile1, LogFile logFile2) {
				if(ascending) return logFile1.getFileName().compareTo(logFile2.getFileName());
				return logFile2.getFileName().compareTo(logFile1.getFileName());
			}
        };
	}
	
	public static Comparator<LogFile> sortByDate(final boolean ascending){
		return new Comparator<LogFile>() {
			@Override
			public int compare(LogFile logFile1, LogFile logFile2) {
				if(ascending) return logFile1.getLastModifiedDate().compareTo(logFile2.getLastModifiedDate());
				return logFile2.getLastModifiedDate().compareTo(logFile1.getLastModifiedDate());
			}
        };
	}
	public static Comparator<LogFile> sortBySize(final boolean ascending){
		return new Comparator<LogFile>() {
			@Override
			public int compare(LogFile logFile1, LogFile logFile2) {
				if(logFile1.getSize() == logFile2.getSize()) return 0;
				if(ascending) return logFile1.getSize() > logFile2.getSize() ? 1 : -1;
				return logFile1.getSize() < logFile2.getSize() ? 1 : -1;
			}
        };
	}
}
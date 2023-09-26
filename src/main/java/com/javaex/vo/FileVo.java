package com.javaex.vo;

public class FileVo {

	//필드
	private int no;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;

	//생성자
	public FileVo() {
		
	}
	
	public FileVo(String orgName, String saveName, String filePath, long fileSize) {
		super();
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	
	public FileVo(int no, String orgName, String saveName, String filePath, long fileSize) {
		super();
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}


	//메서드gs
	
	
	//메서드일반
	@Override
	public String toString() {
		return "FileVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + "]";
	}
	
	
}







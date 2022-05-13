package project.Ex.service;

import lombok.Data;

@Data
public class ExVO {

	private int num;
	private String title;
	private String writer;
	private String content;
	
	@Override
	public String toString() {
		return "[메모번호 : " + num + ", 메모제목 : " + title + ", 메모작성자 : " + writer + ", 메모 내용 : " + content + "]";
	}
	
	
}

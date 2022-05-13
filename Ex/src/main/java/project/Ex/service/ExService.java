package project.Ex.service;

import java.util.List;


public interface ExService {

	void insertEx(ExVO vo); //추가
	
	int updateEx(ExVO vo); // 수정
	
	void deleteEx(ExVO vo); // 삭제
	
	List<ExVO> selectExList(String keyword); // 메모조회
	
	List<ExVO> selectAllList(); // 전체조회
}

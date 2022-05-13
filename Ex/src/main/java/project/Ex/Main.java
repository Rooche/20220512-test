package project.Ex;

import java.util.List;
import java.util.Scanner;

import project.Ex.service.ExService;
import project.Ex.service.ExServiceImpl;
import project.Ex.service.ExVO;


public class Main {
	
	private Scanner scanner = new Scanner(System.in);
	private ExService dao = new ExServiceImpl();

	public Main() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				insertEx();
			} else if (menuNo == 2) {
				updateEx();
			} else if (menuNo == 3) {
				deleteEx();
			} else if (menuNo == 4) {
				selectExList();
			} else if (menuNo == 5) {
				selectAllList();
			} else if (menuNo == 9) {
				end();
				break;
			}

		}
	}

	private void menuPrint() {
		System.out.println("============================================================================");
		System.out.println("1.등록 | 2.수정 | 3.삭제 | 4.메모조회 | 5.전체조회 | 9.종료");
		System.out.println("============================================================================");
		System.out.print("메뉴선택>");
	}

	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("없는 메뉴입니다.");
		}
		return menuNo;
	}

	//등록
	private void insertEx() {
		ExVO vo = inputExInfo();
		dao.insertEx(vo);
	}
	
	private ExVO inputExInfo() {
		ExVO vo = new ExVO();
		System.out.print("메모번호>");
		vo.setNum(scanner.nextInt());
		scanner.nextLine();
		System.out.print("메모제목>");
		vo.setTitle(scanner.nextLine());
		System.out.print("메모작성자>");
		vo.setWriter(scanner.nextLine());
		System.out.print("메모내용>");
		vo.setContent(scanner.nextLine());
		System.out.println("1건이 등록되었습니다");
		return vo;
	}
	
	private void updateEx() {
		ExVO vo = inputExInoff();
		dao.updateEx(vo);
	}
	
	private ExVO inputExInoff() {
		ExVO vo = new ExVO();
		System.out.print("메모번호>");
		vo.setNum(scanner.nextInt());
		scanner.nextLine();
		System.out.print("메모내용>");
		vo.setContent(scanner.nextLine());
		System.out.println("1건이 수정되었습니다.");
		return vo;
	}
	
	private void deleteEx() {
		ExVO vo = inputExIde();
		dao.deleteEx(vo);
	}
	
	private ExVO inputExIde() {
		ExVO vo = new ExVO();
		System.out.print("메모번호>");
		vo.setNum(scanner.nextInt());
		scanner.nextLine();
		return vo;
	}
	
	private void selectExList() {
		String keyword = inputContent();
		List<ExVO> list = dao.selectExList(keyword);
		
		for(ExVO book : list) {
			System.out.println(book);
		}
	}
	
	private String inputContent() {
		String keyword = null;
		System.out.print("메모내용>");
		keyword = scanner.nextLine();
		return keyword;
	}
	
	private void selectAllList() {
		List<ExVO> vo = dao.selectAllList();
		for (ExVO voo : vo) {
			System.out.println(voo);
		}
	}
	
	
	
	private void end() {
		System.out.println("프로그램이 종료되었습니다.");
	}

	
}

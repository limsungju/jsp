package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.dao.BoardDAO;
import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import common.Constants;
import page.Pager;

//url mapping 
@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

//get방식 호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청한 주소
		String url = request.getRequestURL().toString();
		BoardDAO dao = new BoardDAO();
		// 한글깨짐방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
// 스트링.indexOf("키워드") 키워드가 포함된 위치값, 없으면 -1		
			if (url.indexOf("list.do") != -1) {

			int count = dao.count(); // 전체 레코드 갯수
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			System.out.println("결과1 : " + count);
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println("결과2 : " + start);
			System.out.println("결과3 : " + end);
			System.out.println(pager);
			List<BoardDTO> list = dao.list(start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 출력 페이지로 이동
			String page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("travleList.do") != -1) {

			int count = dao.count(); // 전체 레코드 갯수
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			System.out.println("결과1 : " + count);
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println("결과2 : " + start);
			System.out.println("결과4 : " + end);
			System.out.println(pager);
			List<BoardDTO> list = dao.travleList(start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 출력 페이지로 이동
			String page = "/board/travleList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("qnaList.do") != -1) {

			int count = dao.count(); // 전체 레코드 갯수
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			System.out.println("결과1 : " + count);
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println("결과2 : " + start);
			System.out.println("결과5 : " + end);
			System.out.println(pager);
			List<BoardDTO> list = dao.qnaList(start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 출력 페이지로 이동
			String page = "/board/qnaList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("search.do") != -1) {
			// 검색옵션과 검색키워드
			String search_option = request.getParameter("search_option");
			String keyword = request.getParameter("keyword");

			int count = dao.count(search_option, keyword);
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println(pager);
			List<BoardDTO> list = dao.searchList(search_option, keyword, start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 검색옵션,키워드도 저장
			request.setAttribute("search_option", search_option);
			request.setAttribute("keyword", keyword);
			// 출력 페이지로 이동
			String page = "/board/search.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("travleSearch.do") != -1) {
			// 검색옵션과 검색키워드
			String search_option = request.getParameter("search_option");
			String keyword = request.getParameter("keyword");

			int count = dao.count(search_option, keyword);
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println(pager);
			List<BoardDTO> list = dao.travleSearchList(search_option, keyword, start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 검색옵션,키워드도 저장
			request.setAttribute("search_option", search_option);
			request.setAttribute("keyword", keyword);
			// 출력 페이지로 이동
			String page = "/board/travleSearch.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("qnaSearch.do") != -1) {
			// 검색옵션과 검색키워드
			String search_option = request.getParameter("search_option");
			String keyword = request.getParameter("keyword");

			int count = dao.count(search_option, keyword);
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println(pager);
			List<BoardDTO> list = dao.qnaSearchList(search_option, keyword, start, end); // 게시물 목록이 넘어옴
			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 검색옵션,키워드도 저장
			request.setAttribute("search_option", search_option);
			request.setAttribute("keyword", keyword);
			// 출력 페이지로 이동
			String page = "/board/qnaSearch.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("insert.do") != -1) {
			BoardDTO dto = new BoardDTO();
			File uploadDir = new File(Constants.UPLOAD_PATH);
			if (!uploadDir.exists()) { // 디렉토리가 없으면
				uploadDir.mkdir(); // 디렉토리를 생성함
			}
//request 객체를 확장한 MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest(request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			String filename = "";
			int filesize = 0;
			try {
				Enumeration files = multi.getFileNames(); // 업로드 파일 집합
				while (files.hasMoreElements()) { // 다음 파일이 있으면
					String file1 = (String) files.nextElement(); // 다음 파일
					filename = multi.getFilesystemName(file1); // 파일 이름
					File f1 = multi.getFile(file1);
					if (f1 != null) {
						filesize = (int) f1.length(); // 파일 크기
						if (filename != null) {
							// 파일의 확장자 검사
							int start = filename.lastIndexOf(".") + 1;
							if (start != -1) {
								String ext = filename.substring(start, filename.length());
								System.out.println(ext);
								if (ext.equals("jsp") || ext.equals("exe")) {
									try {
										System.out.println("금지된 파일입니다...");
										f1.delete(); // 업로드된 파일을 삭제
									} catch (Exception e) {
										e.printStackTrace();
									}
									System.out.println("삭제됨...");
									response.sendRedirect(request.getContextPath() + "/board/write.jsp?message=error");
									return;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String passwd = multi.getParameter("passwd");
			String id = (String) request.getSession().getAttribute("userId");
			int gubun = Integer.parseInt(multi.getParameter("gubun"));

			String ip = request.getRemoteAddr(); // 클라이언트의 ip 주소
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			dto.setIp(ip);
			dto.setId(id);
			dto.setGubun(gubun);
			// 업로드한 파일이 없을 경우의 처리
			if (filename == null || filename.equals("")) {
				filename = "-";
			}
			dto.setFilename(filename);
			dto.setFilesize(filesize);
			System.out.println(dto);
			dao.insert(dto); // 레코드가 추가됨
			// 게시물 목록 갱신
			String page = request.getContextPath() + "/board_servlet/list.do";
			response.sendRedirect(page);
		} else if (url.indexOf("download.do") != -1) {
			// 클릭한 첨부파일의 글번
			int num = Integer.parseInt(request.getParameter("num"));
			String filename = dao.getFileName(num);
			System.out.println("첨부파일 이름:" + filename);
			// 파일 다운로드 처리리
			String path = Constants.UPLOAD_PATH + filename;
			byte b[] = new byte[4096]; // 바이트배열
			// 파일을 읽기 위한 입력스트림
			FileInputStream fis = new FileInputStream(path);
			// 파일의 종류(마임타입)
			String mimeType = getServletContext().getMimeType(path);
			if (mimeType == null) { // 다운로드할 파일의 형식
				mimeType = "application/octet-stream;charset=utf-8";
			}
			// 한글 파일 이름이 깨지지 않도록 처리
			filename = new String(filename.getBytes("utf-8"), "8859_1");

			response.setHeader("Content-Disposition", "attachment;filename=" + filename); // 헤더 전

			// body 전송
			// 출력 스트림 생성
			ServletOutputStream out = response.getOutputStream();
			int numRead;
			while (true) {
				numRead = fis.read(b, 0, b.length); // 파일을 읽음
				if (numRead == -1)
					break; // 내용이 있으면
				out.write(b, 0, numRead); // 파일 저장
			}
			out.flush(); // 스트림을 비우기
			out.close(); // 출력스트림 닫기
			fis.close(); // 입력스트림 닫기
			// 다운로드 횟수 증가 처리
			dao.plusDown(num);

//			String page=
//					request.getContextPath()+"/board_servlet/list.do";
//			response.sendRedirect(page);
		} else if (url.indexOf("view.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			System.out.println("게시물번호:" + num);
			dao.plusReadCount(num, request.getSession()); // 조회수 증가 처리
			BoardDTO dto = dao.view(num, true);
			System.out.println("상세화면용 dto:" + dto);
			// 출력을 위해 request 영역에 저장
			request.setAttribute("dto", dto);
			// 출력 페이지로 이동
			String page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("pass_check.do") != -1) {
			int num = Integer.parseInt(request.getParameter("num"));
			String passwd = request.getParameter("passwd");
			System.out.println("글번호:" + num + ",비번:" + passwd);
			// 올바른 비밀번호인지 확인
			String result = dao.passwdCheck(num, passwd);
			String page = "";
			// 비번이 맞으면 수정 화면으로 이동
			if (result != null) {
				page = "/board/edit.jsp";
				request.setAttribute("dto", dao.view(num, false));
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			} else { // 비번이 틀리면 되돌아감
				page = request.getContextPath() + "/board_servlet/view.do?num=" + num + "&message=error";
				response.sendRedirect(page);
			}
		} else if (url.indexOf("update.do") != -1) {
			// 폼에서 입력한 값을 dto에 저장
			BoardDTO dto = new BoardDTO();
			// 첨부파일처리를 위한 MultipartRequest 선언
			MultipartRequest multi = new MultipartRequest(request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());

			String filename = " ";
			int filesize = 0;
			try {
				Enumeration files = multi.getFileNames();// 첨부파일 집합
				while (files.hasMoreElements()) {
					String file1 = (String) files.nextElement();
					// 첨부파일의 이름
					filename = multi.getFilesystemName(file1);
					File f1 = multi.getFile(file1);
					// 첨부파일의 size
					if (f1 != null) {
						filesize = (int) f1.length();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// request=>multi로 변경
			String writer = multi.getParameter("writer");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String passwd = multi.getParameter("passwd");
			String ip = request.getRemoteAddr(); // ip주소
			int num = Integer.parseInt(multi.getParameter("num"));

			// 첨부파일 삭제 처리
			String fileDel = multi.getParameter("fileDel");
			// 체크가 되지 않으면 null, 체크되면 on
			if (fileDel != null && fileDel.equals("on")) {
				// 테이블에 저장된 파일 이름
				String fileName = dao.getFileName(num);
				File f = new File(Constants.UPLOAD_PATH + fileName);
				f.delete(); // 파일 삭제
				// 첨부파일 관련 레코드 정보 수정
				dto.setNum(num);
				dto.setWriter(writer);
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setPasswd(passwd);
				dto.setIp(ip);
				dto.setFilename("-");
				dto.setFilesize(0);
				dto.setDown(0);
				dao.update(dto);
			}

			dto.setNum(num);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			dto.setIp(ip);

			if (filename == null || filename.trim().equals("")) {
				// 새로운 첨부파일이 없을 때(테이블의 정보를 가져옴)
				BoardDTO dto2 = dao.view(num, false);
				String fName = dto2.getFilename();
				int fSize = dto2.getFilesize();
				int fDown = dto2.getDown();
				dto.setFilename(fName);
				dto.setFilesize(fSize);
				dto.setDown(fDown);
			} else {
				// 새로운 첨부파일이 있을 때
				dto.setFilename(filename);
				dto.setFilesize(filesize);
			}

			String result = dao.passwdCheck(num, passwd);
			if (result != null) { // 비밀번호가 맞을 때
				// dao에 update 요청
				dao.update(dto);
				// list.do로 이동
				String page = request.getContextPath() + "/board_servlet/list.do";
				response.sendRedirect(page);
			} else { // 비밀번호가 틀렸을 때
				request.setAttribute("dto", dto);
				String page = "/board/edit.jsp?pwd_error=y";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if (url.indexOf("delete.do") != -1) {
// enctype="multipart/form-data"로 넘어온 값은 
// request 객체로 받을 수 없음 			
			// int num=Integer.parseInt(request.getParameter("num"));
			MultipartRequest multi = new MultipartRequest(request, Constants.UPLOAD_PATH, Constants.MAX_UPLOAD, "utf-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("num"));
			System.out.println("삭제할 게시물번호:" + num);
			// 레코드 삭제(숨김) 처리
			dao.delete(num);
			// 목록으로 이동
			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);
		} else if (url.indexOf("commentList.do") != -1) {
			// 게시물 번호
			int num = Integer.parseInt(request.getParameter("num"));
			// 댓글 목록이 넘어옴
			List<BoardCommentDTO> list = dao.commentList(num);
			// 출력페이지에서 읽을 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 화면 전환
			String page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("commentAdd.do") != -1) {
//view.jsp에서 넘어온 값들을 dto에 저장 			
			BoardCommentDTO dto = new BoardCommentDTO();
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			dto.setBoard_num(board_num);
			dto.setWriter(writer);
			dto.setContent(content);
			System.out.println("댓글쓰기 dto:" + dto);
			// 레코드가 추가됨
			dao.commentAdd(dto);
			// 실행이 끝나면 view.jsp의 콜백함수(success)로 넘어감
		} else if (url.indexOf("reply.do") != -1) {
			// 게시물 번호 조회
			int num = Integer.parseInt(request.getParameter("num"));
			// 게시물 내용을 dto로 받음
			BoardDTO dto = dao.view(num, true);
			// 답변 작성의 편의를 위해 reply.jsp 페이지에 dto를 전달
			dto.setContent("====게시물의 내용====<br>" + dto.getContent());
			request.setAttribute("dto", dto);
			String page = "/board/reply.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("insertReply.do") != -1) {
			// 원글의 게시물번호(답글의 대상)
			int num = Integer.parseInt(request.getParameter("num"));
			// 원글 내용
			BoardDTO dto = dao.view(num, false);
			int ref = dto.getRef(); // 답변 그룹 번호
			int re_step = dto.getRe_step() + 1;// 출력순번
			int re_level = dto.getRe_level() + 1;// 답변 단계
			// 답변 내용
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String passwd = request.getParameter("passwd");
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			dto.setRef(ref);
			dto.setRe_level(re_level);
			dto.setRe_step(re_step);
			// 첨부파일 관련 정보
			dto.setFilename("-");
			dto.setFilesize(0);
			dto.setDown(0);
			// 답글 순서 조정
			dao.updateStep(ref, re_step);
			// 답글 쓰기
			dao.reply(dto);
			// 목록으로 이동
			String page = "/board_servlet/list.do";
			response.sendRedirect(request.getContextPath() + page);

		} else if (url.indexOf("myWriteList.do") != -1) {
			String id = (String) request.getSession().getAttribute("userId");

			int count = dao.myWriteListcount(id); // 전체 레코드 갯수
			int curPage = 1; // 현재 페이지 번호
			if (request.getParameter("curPage") != null) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			System.out.println("결과1 : " + count);
			Pager pager = new Pager(count, curPage);
			int start = pager.getPageBegin(); // #{start}에 입력될 값
			int end = pager.getPageEnd(); // #{end}에 입력될 값
			System.out.println("결과2 : " + start);
			System.out.println("결과3 : " + end);
			System.out.println(pager);

			List<BoardDTO> list = dao.myWriteList(start, end, id); // 게시물 목록이 넘어옴

			// 출력 페이지에서 사용할 수 있도록 request 영역에 저장
			request.setAttribute("list", list);
			// 페이지 네비게이션 정보를 저장
			request.setAttribute("page", pager);
			// 출력 페이지로 이동
			String page = "/board/myWriteList.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

//post 방식 호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
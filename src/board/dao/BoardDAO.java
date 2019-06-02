package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSession;

import board.dto.BoardCommentDTO;
import board.dto.BoardDTO;
import sqlmap.MybatisManager;

public class BoardDAO {

	public List<BoardDTO> searchList(String search_option, String keyword, int start, int end) {
		List<BoardDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("keyword", "%" + keyword + "%");
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("board.searchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BoardDTO> travleSearchList(String search_option, String keyword, int start, int end) {
		List<BoardDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("keyword", "%" + keyword + "%");
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("board.travleSearchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<BoardDTO> qnaSearchList(String search_option, String keyword, int start, int end) {
		List<BoardDTO> list = null;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("keyword", "%" + keyword + "%");
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("board.qnaSearchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int count(String search_option, String keyword) {
		int result = 0;
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, String> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("keyword", keyword);
			result = session.selectOne("board.search_count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int count() {
		int result = 0;
		// try~with java7부터 사용 가능 try(객체) 자동 소멸
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("board.count");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

//		int result=0;
//		SqlSession session=null;
//		try {
//			session=MybatisManager.getInstance().openSession();
////레코드 1개 selectOne(), 2개 이상 selectList() 			
//			result=session.selectOne("board.count"); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if(session!=null) session.close();
//		}
//		return result;
	}

	// 답변글 등록
	public void reply(BoardDTO dto) {
		SqlSession session = null;
		try {
			// mybatis 실행 객체 생성
			session = MybatisManager.getInstance().openSession();
			session.insert("board.reply", dto); // 레코드 추가
			session.commit(); // 수동 커밋
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // 리소스 정리
		}
	}

	// 답글의 순서 조정(ref-게시물 그룹 번호, re_step 게시물 그룹 순번)
	public void updateStep(int ref, int re_step) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
// 쿼리에 전달할 값이 2개 이상인 경우-dto 또는 map으로 전달 			
//			BoardDTO dto=new BoardDTO();
//			dto.setRef(ref);
//			dto.setRe_step(re_step);
//			session.update("board.updateStep", dto);
			Map<String, Object> map = new HashMap<>();
			map.put("ref", ref);
			map.put("re_step", re_step);
			session.update("board.updateStep", map);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	// 댓글 추가
	public void commentAdd(BoardCommentDTO dto) {
		SqlSession session = null;
		try {
			// mybatis 실행 객체 생성
			session = MybatisManager.getInstance().openSession();
			session.insert("board.commentAdd", dto); // 레코드가 추가됨
			session.commit(); // 변경사항 확정(수동 commit)
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // mybatis 리소스 정리
		}
	}

	// 삭제
	public void delete(int num) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.delete", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	public List<BoardDTO> deleteid(String id) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.deleteid", id);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

//게시물 저장 	
	public void insert(BoardDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.insert("board.insert", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

//게시물 목록 리턴 	
	public List<BoardDTO> list(int start, int end) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("board.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // SqlSession 종료
		}
		return list;
	}

	// 게시물 목록 리턴
	public List<BoardDTO> travleList(int start, int end) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			System.out.println("결과 : " + start);
			System.out.println("결과 : " + end);

			list = session.selectList("board.travleList", map);

			String sql = session.getConfiguration().getMappedStatement("board.travleList").getBoundSql(map).getSql();
			List<ParameterMapping> parameterMappings = session.getConfiguration().getMappedStatement("board.travleList")
					.getBoundSql(map).getParameterMappings();

			for (ParameterMapping parameterMapping : parameterMappings) {
				int param = Integer.parseInt(map.get(parameterMapping.getProperty()).toString());
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			}

			System.out.println("sql : " + sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // SqlSession 종료
		}

		System.out.println("결과 : " + list.size());
		return list;
	}

	// 게시물 목록 리턴
	public List<BoardDTO> qnaList(int start, int end) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			list = session.selectList("board.qnaList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // SqlSession 종료
		}
		return list;
	}
	
	
	

	// 내가 쓴 게시물 목록 리턴
	public List<BoardDTO> myWriteList(int start, int end, String id) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("id", id);
			
			System.out.println("결과5 : "+id);
			
			String sql = session.getConfiguration().getMappedStatement("board.myWriteList").getBoundSql(map).getSql();
			List<ParameterMapping> parameterMappings = session.getConfiguration().getMappedStatement("board.myWriteList")
					.getBoundSql(map).getParameterMappings();

			for (ParameterMapping parameterMapping : parameterMappings) {
				int param = Integer.parseInt(map.get(parameterMapping.getProperty()).toString());
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			}

			System.out.println("sql : " + sql);
			
			list = session.selectList("board.myWriteList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // SqlSession 종료
		}
		return list;
	}

	// 내가 쓴 게시물 목록 리턴
	public List<BoardDTO> myCommentList(int start, int end, String id) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", start);
			map.put("end", end);
			map.put("id", id);
			
			System.out.println("결과5 : "+id);
			
			String sql = session.getConfiguration().getMappedStatement("board.myCommentList").getBoundSql(map).getSql();
			List<ParameterMapping> parameterMappings = session.getConfiguration().getMappedStatement("board.myCommentList")
					.getBoundSql(map).getParameterMappings();

			for (ParameterMapping parameterMapping : parameterMappings) {
				int param = Integer.parseInt(map.get(parameterMapping.getProperty()).toString());
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			}

			System.out.println("sql : " + sql);
			
			list = session.selectList("board.myWriteList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // SqlSession 종료
		}
		return list;
	}
	
	
	

	public String getFileName(int num) {
		String result = "";
		SqlSession session = null;
		try {
			// mybatis 실행 객체 생성
			session = MybatisManager.getInstance().openSession();
			// 게시물 번호에 해당하는 첨부파일 이름이 리턴됨
			result = session.selectOne("board.getFileName", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // 리소스 정 리
		}
		return result;
	}

//다운로드 횟수 증가 처리	
	public void plusDown(int num) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.plusDown", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

//조회수 증가 처리
	public void plusReadCount(int num, HttpSession count_session) {
		SqlSession session = null;
		try {
			long read_time = 0; // 게시물을 읽은 시간
			// 세션이 있으면 읽은 시간을 가져옴
			if (count_session.getAttribute("read_time_" + num) != null) {
				read_time = (long) count_session.getAttribute("read_time_" + num);
			}
			long current_time = System.currentTimeMillis();// 현재 시각
			session = MybatisManager.getInstance().openSession();
			// 일정시간이 경과하면 조회수를 올림
			if (current_time - read_time > 5 * 1000) {
				session.update("board.plusReadCount", num);
				session.commit();
				count_session.setAttribute("read_time_" + num, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

//상세화면용 dto 리턴 코 드	
	public BoardDTO view(int num, boolean newline) {
		SqlSession session = null;
		BoardDTO dto = null;
		try {
			session = MybatisManager.getInstance().openSession();
			dto = session.selectOne("board.view", num);
			// 줄바꿈 처리
			if (newline == true) {
				String content = dto.getContent();
				content = content.replace("\n", "<br>");
				dto.setContent(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return dto;
	}

	public String passwdCheck(int num, String passwd) {
		String result = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("num", num); // 맵.put("변수명", 값)
			map.put("passwd", passwd);
			result = session.selectOne("board.pass_check", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return result;
	}

//게시물 수정	
	public void update(BoardDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.update", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	// 댓글 목록 리턴
	public List<BoardCommentDTO> commentList(int num) {
		List<BoardCommentDTO> list = null;
		SqlSession session = null;
		try {
			// mybatis 실행 객체 생성
			session = MybatisManager.getInstance().openSession();
			// board 네임스페이스의 commentList 쿼리 실행
			list = session.selectList("board.commentList", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close(); // mybatis 실행 객체 닫기
		}
		return list;
	}

	public int myWriteListcount(String id) {
		int result = 0;
		// try~with java7부터 사용 가능 try(객체) 자동 소멸
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("board.myWriteListcount",id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void deleteBoardData(String writer) {
		// try~with java7부터 사용 가능 try(객체) 자동 소멸
		try (SqlSession session = MybatisManager.getInstance().openSession()) {
			session.selectOne("board.deleteBoardData",writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
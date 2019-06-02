package page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class EmpDAO {
	public List<EmpDTO> empList(int start, int end){
//		List<EmpDTO> items=new ArrayList<>();
		//mybatis 실행 객체 생성
		SqlSession session=MybatisManager.getInstance().openSession();
		// page 네임스페이스의 id가 empList 인 쿼리 실행
		Map<String,Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		List<EmpDTO> items=session.selectList("page.empList",map);
		// mybatis 리소스 정리
		session.close();
		return items; //List 리턴
	}
//레코드 갯수 계산 	
	public int empCount() {
		SqlSession session=MybatisManager.getInstance().openSession();
//실행 결과 레코드가 1개일 때 selectOne() 2개 이상이면 selectList() 		
		int count=session.selectOne("page.empCount");
		session.close();
		return count;
	}
}














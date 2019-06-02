package page;

public class EmpDTO {
	private int empno;
	private String ename;
	//getter,setter,toString()
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Override
	public String toString() {
		return "EmpDTO [empno=" + empno + ", ename=" + ename + "]";
	}
	
}

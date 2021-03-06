package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {

	@Autowired
	//@Qualifier("exam2")
	private Exam exam;
	
	public InlineExamConsole() {			// 기본 생성자
		System.out.println("constructor");
	}
	
	public InlineExamConsole(Exam exam) {	// 오버로드 생성자
		System.out.println("overloaded constructor");
		this.exam = exam;
	}

	@Override								// print 메소드
	public void print() {
		if(exam == null)
			System.out.printf("total is %d, avg is %f\n",0,0.0);
		else
			System.out.printf("total is %d, avg is %f\n",exam.total(),exam.avg());
	}

	
	@Override
	public void setExam(Exam exam) {		// setter
		System.out.println("setter");
		this.exam = exam;
	}

}

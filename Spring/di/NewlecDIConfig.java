package spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;

@ComponentScan("spring.di.ui")
@Configuration
public class NewlecDIConfig {
	@Bean
	public Exam exam() {			//exam()이란 함수를 키원드로 생각해야함.
		return new NewlecExam(); 
	}
}

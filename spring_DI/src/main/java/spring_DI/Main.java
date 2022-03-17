package spring_DI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:com/spring/context/root-context.xml");
		
		MemberService memberService = ctx.getBean("memberService", MemberService.class);
		
		try {
			MemberVO member = memberService.getMember("woo");
			
			System.out.println(member.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}

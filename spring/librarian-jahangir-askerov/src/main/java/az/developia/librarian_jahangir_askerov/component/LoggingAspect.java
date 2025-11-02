package az.developia.librarian_jahangir_askerov.component;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//	execution - pointCut operator
//	before, after - advice
	@Before(value = "execution(public * az.developia.librarian_jahangir_askerov.service.LendingService.*(..))")
	public void logBefore() {
		System.out.println("Method called!");
	}
	
}

package be.vdab.aop;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Logging {
	private final static Logger logger = Logger.getLogger(Logging.class
			.getName());

	@AfterThrowing(pointcut = "be.vdab.aop.PointcutExpressions.servicesEnTransacties()", throwing = "ex")
	void schrijfException(JoinPoint joinPoint, Throwable ex) {
		StringBuilder builder = new StringBuilder("\nTijdstip\t")
				.append(new Date());
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication != null
				&& !"anonymousUser".equals(authentication.getName())) {
			builder.append("\nGebruiker\t").append(authentication.getName());
		}
		builder.append("\nMethod\t\t").append(
				joinPoint.getSignature().toLongString());
		for (Object object : joinPoint.getArgs()) {
			builder.append("\nParameter\t").append(object);
		}
		logger.log(Level.SEVERE, builder.toString(), ex);
	}
}

package be.vdab.aop;

import java.util.Date;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Aspect
@Component
class Auditing {
	private final static Logger logger = Logger.getLogger(Auditing.class
			.getName());

	@Before("execution(* be.vdab.services.*.*(..))")
	void schrijfAudit(JoinPoint joinPoint) {
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
			builder.append("\nParameter\t").append(object.toString());
		}
		logger.info(builder.toString());
	}
}

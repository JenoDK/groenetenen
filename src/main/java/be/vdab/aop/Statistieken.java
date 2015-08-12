package be.vdab.aop;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Statistieken {
	private final static Logger logger = Logger.getLogger(Statistieken.class
			.getName());
	private final ConcurrentHashMap<String, AtomicInteger> statistieken = new ConcurrentHashMap<>();

	@After("be.vdab.aop.PointcutExpressions.services()")
	void statistiekBijwerken(JoinPoint joinPoint) {
		String joinPointSignatuur = joinPoint.getSignature().toLongString();
		AtomicInteger vorigGevondenAantal = statistieken.putIfAbsent(
				joinPointSignatuur, new AtomicInteger(1));
		int aantalOproepen = vorigGevondenAantal == null ? 1
				: vorigGevondenAantal.incrementAndGet();
		logger.info(joinPointSignatuur + " werd " + aantalOproepen
				+ " keer opgeroepen");
	}
}

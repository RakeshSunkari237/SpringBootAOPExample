package com.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * Aspect is a class, that provides additional services to project.
	Transaction Management, Logging, Security, Encode and Decode..etc.
*/
@Aspect
@Component
public class GloballyEnableTxAndCommit {

	/*
	 * Pointcut : It is expression, it will select b.method which needs advices. It
	 * can never specify what advices.
	 * 
	 * expression : AS RT PACK.CLASS.METHOD(PARAM)
	 */
	@Pointcut("execution(public String com.app.service.BankService.depositAmount(long) )")
	public void p1() {
	}

	/*
	 * 
	 * advice :
	 * 
	 * It is a method inside Aspect class. [It is actual implementation of Aspect]
	 * 
	 * @Before("p1()") // joinPoint : it is combination of advices+pointcut In
	 * simple connecting b.methods with required advices. public void beginTx() {
	 * System.out.println("before business logic--transacton begined"); }
	 * 
	 * 
	 * @After("p1()") public void txCommit() {
	 * System.out.println("at the end of business logic--transacton committed"); }
	 * 
	 * @AfterReturning(value = "p1()", returning = "ob") public void
	 * sendReport(Object ob) { System.out.
	 * println("on successful business logic execution tx commited sending reports"
	 * +ob); }
	 * 
	 * @AfterThrowing(value = "p1()",throwing = "th") public void
	 * rollbackTx(Throwable th) {
	 * System.out.println("if business logic throws any exception adive is executed"
	 * +th); }
	 */
	@Around("p1()")
	public void testAroundAdvice(ProceedingJoinPoint jp) {
		// before business logic
		System.out.println("begin tx ");
		try {
			jp.proceed();
			// on success like commit
			System.out.println("tx commited");
		} catch (Throwable e) {
			// on failure like rollback
			System.out.println("Failure: tx rollbacked");
			e.printStackTrace();
		}
		// after business logic
		System.out.println("closing resouces");
	}
	
	
	@Pointcut("@annotation(com.app.annotation.MyAnnotation)")
	public void p2() {
	}
	
	
	/*
	 * @Around("p2()") public void
	 * testAroundAdviceWithAnnotation(ProceedingJoinPoint jp) { // before business
	 * logic System.out.println("begin tx "); try { jp.proceed(); // on success like
	 * commit System.out.println("tx commited"); } catch (Throwable e) { // on
	 * failure like rollback System.out.println("Failure: tx rollbacked");
	 * e.printStackTrace(); } // after business logic
	 * System.out.println("closing resouces"); }
	 */
	@Before("p2()")
	public void beginTxWithAnnotation() {
		System.out.println("Tx beginned...");
	}

}

package com.bpkim.springbootmvc.common.userlog;

import com.bpkim.springbootmvc.common.accounts.Account;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LoggerAspect {

    public static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);


    @Autowired
    UserMoveLogService userMoveLogService;


    /*@Around("@annotation(com.bpkim.springbootmvc.common.userlog.MoveLogging)")
    public Object logUserPage(ProceedingJoinPoint joinPoint) throws Throwable {

    }*/
    @AfterReturning("@annotation(com.bpkim.springbootmvc.common.userlog.MoveLogging)")
    public void logUserPage() throws Throwable {


        Object principal = (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String httpMethod = request.getMethod();


        logger.info("http method "+ httpMethod);

        logger.info("logUserPage start");
        long begin = System.currentTimeMillis();

        if(SecurityContextHolder.getContext()== null) {
            logger.info("인증 받은것 없음 ");

            return ;
        }

            // 사용자 정보
        if(principal == null){
            logger.info("사용자 정보 없음 ");

            return ;
        }
        // 로그인 안한 사용자
        if(principal.toString().equals("anonymousUser")){
            logger.info("로그인한 사용자 없음  ");

            return ;
        }
        Account account = (Account)principal;

        logger.info("userId : " + account.getId());
        logger.info("email  : " + account.getEmail());
        logger.info("userNm : " + account.getEmail());

        // 사용자 ip
//        String ipAddress = RequestUtils.getRemoteIP(request);
//        RequestUtil.getRequestURL(request).toString();

        // 접근 경로(메뉴 패턴)
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String pattern = requestURI.replaceAll("(^" + contextPath + ")|((\\.[^\\.]*)$)|((/[^/]+){1}/*$)", "");

        logger.info("requestURI "+ requestURI);
        logger.info("contextPath "+ contextPath);
        logger.info("pattern "+ pattern);


        System.out.println(System.currentTimeMillis() - begin);

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();


        String oldPrePage = (String)session.getAttribute("prePage");

        String oldNowPage = (String)session.getAttribute("nowPage");

        logger.info("prePage"+ oldPrePage);
        logger.info("nowPage"+ oldNowPage);

        session.setAttribute("prePage", oldNowPage);
        session.setAttribute("nowPage", requestURI);

        logger.info("prePage"+ (String)session.getAttribute("prePage"));
        logger.info("nowPage"+ (String)session.getAttribute("nowPage"));


        UserMoveLog userMoveLog = UserMoveLog.builder()
                    .userId(account.getId())
                    .userNm(account.getUsername())
                    .prePage(oldNowPage)
                    .nowPage(requestURI)
                    .build();

        userMoveLogService.createUserMoveLog(userMoveLog);

        logger.info("logUserPage end");

    }

    /*
    *
    *
    * */
}

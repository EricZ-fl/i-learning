package prevent.submit.chart;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class Token{

    /*@RequestMapping("/add")
    public void add(HttpServletRequest request,HttpServletResponse response,Test test){

        //验证重复提交的令牌
        if("false".equals(Token.validateToken(request))){
            return;
        }
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }*/


    /**
     * 设置令牌
     */
    public static void setToken(HttpServletRequest request) {
        request.getSession().setAttribute("SesToken", UUID.randomUUID().toString());
    }

    public static String getToken(HttpServletRequest request) {

        String sessionToken = (String) request.getSession().getAttribute("SesToken");

        if (null == sessionToken || "".equals(sessionToken)) {
            sessionToken = UUID.randomUUID().toString();
            request.getSession().setAttribute("SesToken", sessionToken);
        }

        return sessionToken;
    }

    /**
     * 验证是否重复提交
     * @param request 表单请求
     * @return
     */
    public static String validateToken(HttpServletRequest request) {

        String sessionToken = (String) request.getSession().getAttribute("SesToken");
        String requestToken = request.getParameter("SesToken");

        if (null == sessionToken || "null".equals(sessionToken)) {
            sessionToken = "";
        }
        if (null == requestToken || "null".equals(requestToken)) {
            requestToken = "";
        }

        if (sessionToken.equals(requestToken)) {
            // 返回前一定要重置session中的SesToken
            request.getSession().setAttribute("SesToken", UUID.randomUUID().toString());
            // 非重复提交
            return "true";
        } else {
            // 返回前一定要重置session中的SesToken
            request.getSession().setAttribute("SesToken", UUID.randomUUID().toString());
            // 重复提交
            return "false";
        }
    }

}
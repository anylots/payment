package com.frame.coupon.common;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * response util
 *
 * @author anylots
 * @version $Id: ResponseUtil.java, v 0.1 2020年10月17日 13:40 anylots Exp $
 */
public class ResponseUtil {

    /**
     * apply response data
     *
     * @param response
     * @param result
     */
    public static void applyResponseData(HttpServletResponse response, String result) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(result);
        } catch (IOException e) {
            System.out.println("system error");
            e.printStackTrace();
        }
    }
}
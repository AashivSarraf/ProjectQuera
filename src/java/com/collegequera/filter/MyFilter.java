
package com.collegequera.filter;

import com.collegequera.dto.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName= "webf" ,urlPatterns = {"/*"})
public class MyFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        String url=req.getRequestURI().toString();//????//url lakar dega
        System.out.println("\nURL >>>"+url);
        Object sessionobj=req.getSession().getAttribute("user");
        if(url.contains(".")){//????//means .java .jsp ya aur kuch
            chain.doFilter(request, response);//????//matlab filter ko aage badaega
        }
        else{
            if(url.contains("/student") || url.contains("/faculty")){
                if(sessionobj==null){
                    String u=req.getContextPath()+"/login.jsp";
                    resp.sendRedirect(u);
                }
                else{
                    User user=(User)sessionobj;
                    if(url.contains(user.getType())){//???
                        chain.doFilter(request, response);
                    }
                    else{
                        if(user.getType().equals("faculty")){
                            resp.sendRedirect(req.getContextPath()+"/faculty/home.jsp");
                        }
                        else{
                            resp.sendRedirect(req.getContextPath()+"/student/home.jsp");
                        }
                    }
                }
            }
            else if(url.contains("/logout")){
                chain.doFilter(request, response);
            }
            else if(sessionobj != null){
                User user=(User) sessionobj;
                if(user.getType().equals("faculty")){
                    resp.sendRedirect(req.getContextPath()+"/faculty/home.jsp");
                }
                else{
                    resp.sendRedirect(req.getContextPath()+"/student/home.jsp");
                }
            }
            else{//?????
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
    
}

package com.water76016.ourtask.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 创建客户端访问时携带的token校验过滤器
 * */
public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        //获得http的头
        //todo:由于这里没传值，这里是空指针异常
        String jwtToken = req.getHeader("authorization");
        String token = new String(jwtToken.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("-----------     " + token + "          -----------");
        //走到这里说明jwt已经通过，下面代码是去springsecurity验证路径是否有权限访问
        Claims claims = Jwts.parser().setSigningKey("fern@123")
                .parseClaimsJws(token.replace("Bearer", "")).getBody();
        System.out.println("claims:    " + claims.toString());
        //获取当前用户名
        String username = claims.getSubject();
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String)claims.get("authorities"));
        UsernamePasswordAuthenticationToken tokenBean = new UsernamePasswordAuthenticationToken(username, null, authorities);
        //校验
        SecurityContextHolder.getContext().setAuthentication(tokenBean);
        //过滤器继续往下走
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

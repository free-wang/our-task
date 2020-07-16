package com.water76016.ourtask.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.water76016.ourtask.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager){
        //登录处理的url
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        super.setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //json登录
        User user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), User.class);
        //authenticate()是自动校验帐号密码是否正确的方法
        return super.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getTelephone(),
                user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Authentication存放着登录成功的信息
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer sb = new StringBuffer();
        for(GrantedAuthority authority : authorities){
            sb.append(authority.getAuthority()).append(",");
            String jwt = Jwts.builder()
                    //配置用户的角色
                    .claim("authoritiees", sb)
                    //设置主题，这里是用户名
                    .setSubject(authResult.getName())
                    //设置过期时间
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    //选择加密算法
                    .signWith(SignatureAlgorithm.ES512, "fern@123")
                    .compact();
            Map<String, String> map = new HashMap<>();
            map.put("token", jwt);
            map.put("msg", "success");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        }
    }
}

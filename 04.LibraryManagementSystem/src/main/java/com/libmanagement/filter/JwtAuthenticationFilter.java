package com.libmanagement.filter;
import java.io.IOException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.libmanagement.repository.service.Jwtservice;
import com.libmanagement.repository.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final Jwtservice jwtservice;
	
	private final UserDetailsServiceImp userDetailsService;




	public JwtAuthenticationFilter(Jwtservice jwtservice, UserDetailsServiceImp userDetailsService) {
		super();
		this.jwtservice = jwtservice;
		this.userDetailsService = userDetailsService;
	}




	@Override
	protected void doFilterInternal(
			@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException{
		
		String authHeader = request.getHeader("Authorization");
		System.out.println("in filter class");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authHeader.substring(7);
				//replace(String.valueOf(/),"");
				//
				//split("/")[1].trim();

	String username = jwtservice.extractUsername(token);
	if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		if(jwtservice.isValid(token, userDetails)) {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
	}
	filterChain.doFilter(request, response);		
	}
}

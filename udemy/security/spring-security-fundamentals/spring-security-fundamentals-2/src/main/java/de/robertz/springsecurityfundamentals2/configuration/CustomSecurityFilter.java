package de.robertz.springsecurityfundamentals2.configuration;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/* Instead of Filter, we could use:
 	GenericFilterBean (auto DI of fields e.g. "userName")
	OncePerRequestFilter (Filter logic can be quite complex, so this special type of filter is guaranteed to be executed exactly once per each request)
 */
public class CustomSecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		System.out.println("Filter handling Before");

		// Pass along req, resp
		filterChain.doFilter(servletRequest, servletResponse);

		System.out.println("Filter handling After");
	}
}

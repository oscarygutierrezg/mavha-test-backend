package com.mavha.test.backend.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleCORSFilterTest {

	@Test
	public void testDoFilter() throws IOException, ServletException {
		SimpleCORSFilter  filter = new SimpleCORSFilter();
		HttpServletRequest mockReq = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse mockResp = Mockito.mock(HttpServletResponse.class);
		FilterChain mockFilterChain = Mockito.mock(FilterChain.class);
		FilterConfig mockFilterConfig = Mockito.mock(FilterConfig.class);
		// mock the getRequestURI() response
		Mockito.when(mockReq.getRequestURI()).thenReturn("/");

		BufferedReader br = new BufferedReader(new StringReader("test"));
		// mock the getReader() call
		Mockito.when(mockReq.getReader()).thenReturn(br);

		filter.init(mockFilterConfig);
		filter.doFilter(mockReq, mockResp, mockFilterChain);
		filter.destroy();
		Mockito.verify(mockFilterConfig);
	}
}

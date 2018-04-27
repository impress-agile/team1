package rodeo.agile.impress.pos;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

public class ZaikoStockServletTest {

    private ZaikoStockServlet servlet;
    private ServletContext context;
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    
    @Before
    public void setup() {
    	this.context = mock(ServletContext.class);
    	when(context.getRealPath("WEB-INF/pos.db")).thenReturn("src/test/pos.db");
    	this.servlet = new ZaikoStockServlet() {
			private static final long serialVersionUID = 1L;
			public ServletContext getServletContext() {
        		return context;
        	}
        };
    	
    }
	
    @Test
    public void testGetAccessShouldBeForwardedToInputPage() throws ServletException, IOException {
        this.servlet.doGet(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/zaiko/input.jsp"));
    }

    @Test
    public void testPostAccessShouldBeForwardedToSuccessPageIfInputValuesAreValid() throws ServletException, IOException {
    	this.request.addParameter("name", "ValidName");
    	this.request.addParameter("amount", "5");

        this.servlet.doPost(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/zaiko/success.jsp"));
    } 
    
    @Test
    public void testPostAccessShouldBeForwaredToInputPageIfInputValuesAreNotValid() throws ServletException, IOException {
    	this.request.addParameter("name", "ValidName");
    	this.request.addParameter("amount", "InvalidNumber");

        this.servlet.doPost(this.request, this.response);    

        assertThat(this.response.getForwardedUrl(), is("jsp/zaiko/input.jsp"));    	
    }
}

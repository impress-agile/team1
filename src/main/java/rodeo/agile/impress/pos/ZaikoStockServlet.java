package rodeo.agile.impress.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZaikoStockServlet extends HttpServlet {

    private static final long serialVersionUID = 6961400581681209885L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/zaiko/input.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String amount = request.getParameter("amount");

        int amountNum = 0;
        try {
        	amountNum = Integer.parseInt(amount);

        	String dbPath = getServletContext().getRealPath("WEB-INF/pos.db");
        	ZaikoDao dao = new ZaikoDao(dbPath);

        	ZaikoStockLogic logic = new ZaikoStockLogic(dao);
        	logic.add(name, amountNum);
        } catch (Exception e) {
        	e.printStackTrace();

        	request.setAttribute("name", name);
            request.setAttribute("amount", amountNum);
            request.getRequestDispatcher("jsp/zaiko/input.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("jsp/zaiko/success.jsp").forward(request, response);
    }

}

package mk.ukim.finki.lab.web.servlets;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="confirmation-servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp, getServletContext());

        context.setVariable("balloon", req.getSession().getAttribute("balloon"));
        context.setVariable("color", req.getSession().getAttribute("color"));
        context.setVariable("size", req.getSession().getAttribute("size"));

        context.setVariable("clientName", req.getSession().getAttribute("clientName"));
        context.setVariable("clientAddress", req.getSession().getAttribute("clientAddress"));

        req.getSession().setAttribute("User", req.getSession().getAttribute("clientName"));

        context.setVariable("clientBrowser", req.getHeader("User-Agent"));
        context.setVariable("ipAddress", req.getRemoteAddr());

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("clientName",req.getParameter("clientName"));
        req.getSession().setAttribute("clientAddress", req.getParameter("clientAddress"));
        resp.sendRedirect("/ConfirmationInfo");
    }
}

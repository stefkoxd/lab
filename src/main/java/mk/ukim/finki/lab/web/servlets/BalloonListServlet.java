package mk.ukim.finki.lab.web.servlets;

import mk.ukim.finki.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-list-servlet", urlPatterns = "/servlet/balloons")
public class BalloonListServlet extends HttpServlet {

    private final BalloonService service;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService service, SpringTemplateEngine springTemplateEngine) {
        this.service = service;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp, getServletContext());
        context.setVariable("balloons", service.listAll());
        springTemplateEngine.process("listBalloons.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
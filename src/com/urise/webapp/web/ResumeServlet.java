package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "<title>Резюме</title>\n" +
                "</head>\n" +
                "<body>\n");
        writer.write("<section>\n");
        writer.write("<table>\n");
        writer.write("<tr><th>Full name</th><th>UUID</th></tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write("<tr>\n");
            writer.write("<td><a href=\"" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n");
            writer.write("<td>" + resume.getUuid() + "</td>\n");
            writer.write("</tr>\n");
        }
        writer.write("</table>\n");
        writer.write("</section>\n" +
                "</body>\n" +
                "</html>\n");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

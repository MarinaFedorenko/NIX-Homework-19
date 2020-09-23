package nix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class Servlet extends HttpServlet{

    private static List<String> ipAndUser = new ArrayList<>();;
    private static final Logger log = LoggerFactory.getLogger(Servlet.class);

    @Override
    public void init() {
        log.info("Servlet is initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUser = req.getRemoteAddr()+" :: "+req.getHeader("User-Agent");
        boolean alreadyExist = false;
        if (ipAndUser.size()!=0){
            for(int i=0; i<ipAndUser.size(); i++){
                if(ipAndUser.get(i).equals(currentUser)){
                    alreadyExist=true;
                    break;
                }
            }
            if(!alreadyExist)
                ipAndUser.add(currentUser);
        }
        else ipAndUser.add(currentUser);

        PrintWriter response = resp.getWriter();

//        resp.setContentType("text/html");
        response.println("<html>");
        response.println("<h1 align=\"center\">Sample Servlet GET method processing</h1>");
        for(int i=0; i<ipAndUser.size(); i++){
            if(ipAndUser.get(i).equals(currentUser))
                response.println("<p align=\"center\"><b>"+ipAndUser.get(i)+"</b></p>");
            else
                response.println("<p align=\"center\">"+ipAndUser.get(i)+"</p>");
        }
        response.println("</html>");

    }

    @Override
    public void destroy() {
        log.info("Sample Servlet destroyed");
    }

}

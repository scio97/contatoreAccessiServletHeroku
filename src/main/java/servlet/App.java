package app;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        name = "AppServlet",
        urlPatterns = {"/prova"}
    )

public class App extends HttpServlet{
    private static final long serialVersionUID = -3967314453512919811L;
    public static void main(String[] args) throws Exception {
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        HttpSession session = req.getSession();
        res.setContentType("text/html");      
        PrintWriter out = res.getWriter();
        if(session.getAttribute("cont")==null){
            session.setAttribute("cont", 1);
            out.println("<h1>Accesso numero "+(int)session.getAttribute("cont")+"</h1>");
        }else{
            int cont=(int)session.getAttribute("cont");
            cont++;
            session.setAttribute("cont", cont);
            out.println("<h1>Accesso numero "+cont+"</h1>");
        }
    }
}
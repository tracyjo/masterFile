
import json.JSONInputStream;
import json.JSONOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by josephtracy on 6/14/16.
 */
@WebServlet(name = "Servlet1", urlPatterns = "/json")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONInputStream inFromClient = new JSONInputStream(request.getInputStream());
        JSONOutputStream outToClient = new JSONOutputStream(response.getOutputStream());
        try {System.out.println("Waiting for a message from the client.");
            HashMap aRequest = (HashMap) inFromClient.readObject();
            System.out.println("Just got:" + aRequest + " from client");aRequest.put("command", "Done");
            outToClient.writeObject(aRequest);
        }
        catch (Exception e) {e.printStackTrace();}
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);}
}
package ec.project.web;

import java.io.*;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.project.db.AppUsers;
import ec.project.ejb.AppUserStatefulLocal;

@WebServlet("/ManageServlet")
public class ManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    AppUserStatefulLocal appusers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        PrintWriter pw = response.getWriter();

        AppUsers loginUser = appusers.deleteUser(username);

        pw.close();
    }
}

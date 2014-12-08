package it.unisa.dottorato.phdCurriculum;

import it.unisa.dottorato.exception.ConnectionException;
import it.unisa.dottorato.exception.EntityNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdatePhdCurriculum", urlPatterns = {"/dottorato/UpdatePhdCurriculum"})
public class UpdatePhdCurriculumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idPhdCurriculum = request.getParameter("idPhdCurriculum");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String professor = request.getParameter("professor");

        PhdCurriculum aPhdCurriculum = new PhdCurriculum();
        aPhdCurriculum.setName(name);
        aPhdCurriculum.setDescription(description);
        aPhdCurriculum.setFK_Professor(professor);

        try {
            PhdCurriculumManager.getInstance().update(aPhdCurriculum);
        } catch (ClassNotFoundException | SQLException | EntityNotFoundException | ConnectionException ex) {
            Logger.getLogger(UpdatePhdCurriculumServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

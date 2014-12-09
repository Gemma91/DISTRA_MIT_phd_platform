package it.unisa.dottorato.phdCurriculum;

import it.unisa.dottorato.exception.ConnectionException;
import it.unisa.dottorato.exception.EntityNotFoundException;
import it.unisa.dottorato.phdCycle.GetPhdCycleServlet;
import it.unisa.dottorato.phdCycle.PhdCycle;
import it.unisa.dottorato.phdCycle.PhdCycleManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "GetPhdCurriculum", urlPatterns = {"/dottorato/GetPhdCurriculum"})
public class GetPhdCurriculumServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String phdCurriculumName = request.getParameter("phdCurriculumName");
            JSONObject result = new JSONObject();
            try {
                PhdCurriculum curriculum = PhdCurriculumManager.getInstance().getPhdCurriculumById(phdCurriculumName);
                result.put("phdCurriculumDescription", curriculum.getDescription());
                result.put("phdCurriculumName", curriculum.getName());
                result.put("FK_Professor", curriculum.getFK_Professor());
                out.write(result.toString());
            } catch (ClassNotFoundException | SQLException | EntityNotFoundException | ConnectionException | JSONException ex) {
                Logger.getLogger(GetPhdCycleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
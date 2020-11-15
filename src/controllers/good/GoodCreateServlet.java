package controllers.good;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;

@WebServlet("/good/create")
public class GoodCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GoodCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Good g = new Good();
        EntityManager em = DBUtil.createEntityManager();


        Employee e = (Employee)request.getSession().getAttribute("login_employee");

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("good_report_id")));

        long checkFlag = (long) em.createNamedQuery("checkRegisteredGood")
                .setParameter("good_employee_id", e)
                .setParameter("good_report_id", r)
                .getSingleResult();
        if(checkFlag > 0) {
            g  = em.createNamedQuery("getGood", Good.class)
              .setParameter("good_employee_id", e)
              .setParameter("good_report_id", r)
            .getSingleResult();
            em.getTransaction().begin();
            em.remove(g);
            em.getTransaction().commit();
        } else {
            g.setGood_employee_id(e);
            g.setGood_report_id(r);
            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
        }


        em.close();

        response.sendRedirect(request.getContextPath() + "/reports/show?id=" + r.getId());

    }

}

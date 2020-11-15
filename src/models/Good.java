package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "getGoodCount",
            query = "SELECT COUNT(g) FROM Good AS g WHERE g.good_report_id = :good_report_id"
            ),
    @NamedQuery(
            name = "checkRegisteredGood",
            query = "SELECT COUNT(g) FROM Good AS g WHERE (g.good_employee_id = :good_employee_id) AND (g.good_report_id = :good_report_id)"
            ),
    @NamedQuery(
            name = "getGood",
            query = "SELECT g FROM Good AS g WHERE (g.good_employee_id = :good_employee_id) AND (g.good_report_id = :good_report_id)"
            )
})
@Table(name = "good_count")

public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "good_employee_id")
    private Employee good_employee_id;

    @ManyToOne
    @JoinColumn(name = "good_report_id")
    private Report good_report_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getGood_employee_id() {
        return good_employee_id;
    }

    public void setGood_employee_id(Employee good_employee_id) {
        this.good_employee_id = good_employee_id;
    }

    public Report getGood_report_id() {
        return good_report_id;
    }

    public void setGood_report_id(Report good_report_id) {
        this.good_report_id = good_report_id;
    }

}

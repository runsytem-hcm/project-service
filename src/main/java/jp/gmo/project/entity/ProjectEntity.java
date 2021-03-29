package jp.gmo.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "project")
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_code")
    private Integer projectCode;

    @Column(name = "project_name_jp")
    private String projectNameJP;

    @Column(name = "project_name_vn")
    private String projectNameVN;

    @Column(name = "billable_effort")
    private String billableEffort;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "sale")
    private String sale;

    @Column(name = "rank")
    private int rank;

    @Column(name = "scope")
    private String scope;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "email_cc")
    private String emailCC;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "delete_flag")
    private Integer deleteFlag;
}

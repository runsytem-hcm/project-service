package jp.gmo.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
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
    private Integer rank;

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

    @OneToMany(mappedBy = "projectEntity", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProjectDetailEntity> projectDetailList;

    public void setProjectDetailList(List<ProjectDetailEntity> projectDetailList) {
        this.projectDetailList = projectDetailList;
        for (ProjectDetailEntity projectDetailEntity: projectDetailList) {
            projectDetailEntity.setProjectEntity(this);
        }
    }

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "projectCode=" + projectCode +
                ", projectNameJP='" + projectNameJP + '\'' +
                ", projectNameVN='" + projectNameVN + '\'' +
                ", billableEffort='" + billableEffort + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customerName='" + customerName + '\'' +
                ", sale='" + sale + '\'' +
                ", rank=" + rank +
                ", scope='" + scope + '\'' +
                ", objectives='" + objectives + '\'' +
                ", emailCC='" + emailCC + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", projectDetailList=" + projectDetailList +
                '}';
    }
}

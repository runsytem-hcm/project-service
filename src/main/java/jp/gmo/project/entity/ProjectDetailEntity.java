package jp.gmo.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "project_detail")
public class ProjectDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "project_code")
//    private Integer projectCode;

    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "position_code")
    private Integer positionCode;

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

    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "project_code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProjectEntity projectEntity;


    @Override
    public String toString() {
        return "ProjectDetailEntity{" +
                "id=" + id +
                ", employeeCode='" + employeeCode + '\'' +
                ", positionCode=" + positionCode +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}

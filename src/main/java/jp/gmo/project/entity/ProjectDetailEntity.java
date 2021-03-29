package jp.gmo.project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ProjectDetailEntity implements Serializable {

    @Id
    @Column(name = "project_code")
    private Integer projectCode;

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
}

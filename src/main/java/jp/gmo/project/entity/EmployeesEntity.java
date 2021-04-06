package jp.gmo.project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "employees")
public class EmployeesEntity {

    @Id
    @Column(name = "employee_code")
    private String employeeCode;

    @Column(name = "email")
    private String email;

    @Column(name = "employee_name")
    private String employeeName;

    @NotNull
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @NotNull
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "update_by")
    private String updateBy;

    @NotNull
    @Column(name = "delete_flag")
    private int deleteFlag;
}

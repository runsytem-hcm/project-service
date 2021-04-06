package jp.gmo.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private String employeeCode;
    private String employeeName;
    private String email;
}

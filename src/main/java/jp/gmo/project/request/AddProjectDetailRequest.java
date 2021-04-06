package jp.gmo.project.request;

import lombok.Data;

@Data
public class AddProjectDetailRequest {
    private String employeeCode;
    private String positionCode;
}
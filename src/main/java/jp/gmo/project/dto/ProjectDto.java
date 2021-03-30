package jp.gmo.project.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {

    private Integer projectCode;
    private String projectNameJP;
    private String projectNameVN;
    private String billableEffort;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerName;
    private String sale;
    private Integer rank;
    private String scope;
    private String objectives;
    private String emailCC;
}

package jp.gmo.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjectDto {

    private Integer projectCode;
    private String projectNameJP;
    private String projectNameVN;
    private String billableEffort;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String customerName;
    private String sale;
    private Integer rank;
    private String scope;
    private String objectives;
    private String emailCC;
    private List<ProjectDetailDto> memberList;
}

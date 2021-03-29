package jp.gmo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto implements Serializable {
    private String projectCode;
    private String projectName;
    private String memberCode;
    private String memberName;
    private String position;
    private String startDate;
    private String endDate;
    private String createTime;
}

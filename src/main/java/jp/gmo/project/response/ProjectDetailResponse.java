package jp.gmo.project.response;

import jp.gmo.project.dto.ProjectDetailDto;
import jp.gmo.project.dto.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailResponse {
    private ProjectDto project;
    private List<ProjectDetailDto> member;
}

package jp.gmo.project.response;

import jp.gmo.project.dto.ProjectDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDetailResponse {
    private ProjectDto project;
}

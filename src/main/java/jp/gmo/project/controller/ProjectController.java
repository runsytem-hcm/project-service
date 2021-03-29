package jp.gmo.project.controller;

import jp.gmo.project.dto.ProjectDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void executeAddProject(@Valid @RequestBody AddProjectRequest request) {
        projectService.executeAddProject("", request);
    }

    @PostMapping(path = "/list-rank")
    public ResponseEntity<List<RankDto>> executeGetProjectRank() {
        return new ResponseEntity<>(projectService.executeGetListRank(), HttpStatus.OK);
    }

    @PostMapping("/list-project")
    public ResponseEntity<PageAndDataResponse<List<ProjectDto>>> executeGetListEmployees(@Valid @RequestBody SearchProjectRequest request) {
        return new ResponseEntity<>(projectService.executeGetLisProject(request), HttpStatus.OK);
    }
}
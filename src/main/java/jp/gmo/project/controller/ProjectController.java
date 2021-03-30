package jp.gmo.project.controller;

import jp.gmo.project.dto.ProjectSearchDto;
import jp.gmo.project.dto.RankDto;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.DetailProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;
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
    public void executeAddProject(@RequestHeader("email") String email, @Valid @RequestBody AddProjectRequest request) {
        projectService.executeAddProject(email, request);
    }

    @PostMapping(path = "/list-rank")
    public ResponseEntity<List<RankDto>> executeGetProjectRank() {
        return new ResponseEntity<>(projectService.executeGetListRank(), HttpStatus.OK);
    }

    @PostMapping("/list-project")
    public ResponseEntity<PageAndDataResponse<List<ProjectSearchDto>>> executeGetListEmployees(@Valid @RequestBody SearchProjectRequest request) {
        return new ResponseEntity<>(projectService.executeGetListProject(request), HttpStatus.OK);
    }

    @PostMapping(path = "/detail-project")
    public ResponseEntity<ProjectDetailResponse> executeGetDetailProject(@Valid @RequestBody DetailProjectRequest request) {
        return new ResponseEntity<>(projectService.executeDetailProject(request), HttpStatus.OK);
    }
}
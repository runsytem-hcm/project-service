package jp.gmo.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.dto.*;
import jp.gmo.project.entity.ProjectDetailEntity;
import jp.gmo.project.entity.ProjectEntity;
import jp.gmo.project.exception.*;
import jp.gmo.project.mapper.ProjectDetailMapper;
import jp.gmo.project.mapper.ProjectMapper;
import jp.gmo.project.repository.ProjectDetailRepository;
import jp.gmo.project.repository.ProjectRepository;
import jp.gmo.project.repository.RankRepository;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.request.SearchProjectRequest;
import jp.gmo.project.request.UpdateProjectRequest;
import jp.gmo.project.response.PageAndDataResponse;
import jp.gmo.project.response.ProjectDetailResponse;
import jp.gmo.project.service.CallAPIsService;
import jp.gmo.project.service.ProjectService;
import jp.gmo.project.utils.DateUtils;
import jp.gmo.project.utils.Utils;
import jp.gmo.project.utils.enums.PositionEnum;
import jp.gmo.project.utils.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectDetailMapper projectDetailMapper;
    private final ProjectRepository projectRepository;
    private final ProjectDetailRepository detailRepository;
    private final RankRepository rankRepository;
    private final CallAPIsService callAPIsService;

    @Override
    public void executeAddProject(String email, AddProjectRequest request) {

        if (projectRepository.findByProjectNameJPAndProjectNameVNAndDeleteFlag(request.getProjectNameJP(), request.getProjectNameVN(), 0).isPresent()) {
            throw new ProjectExistException();
        }

        ProjectEntity projectEntity = projectMapper.dtoToEntity(request);
        projectEntity.setCreateTime(LocalDateTime.now());
        projectEntity.setCreateBy(Utils.getUpdateBy(email));
        projectEntity.setDeleteFlag(0);

        List<ProjectDetailEntity> memberList = new ArrayList<>();
        LocalDateTime time = LocalDateTime.now();

        request.getMemberList().forEach(addProjectDetailRequest -> {
            ProjectDetailEntity projectDetail = projectDetailMapper.dtoToEntity(addProjectDetailRequest);
            projectDetail.setCreateTime(time);
            projectDetail.setCreateBy(Utils.getUpdateBy(email));
            projectDetail.setDeleteFlag(0);
            memberList.add(projectDetail);
        });

        projectEntity.setProjectDetailList(memberList);

        projectRepository.save(projectEntity);
        log.info("Created Information for Project: {}", projectEntity);
        // Send Mail
    }

    @Override
    public List<RankDto> executeGetListRank() {
        return rankRepository.findByDeleteFlag(0).stream().map(rank -> new RankDto(rank.getRank(), rank.getDescription())).collect(Collectors.toList());
    }

    @Override
    public PageAndDataResponse<List<ProjectSearchDto>> executeGetListProject(String projectName, String startDate, String endDate, int page, int size) {

        int offset = (page - 1) * size;

        List<ProjectSearchDto> projectDtoList = projectRepository.getListProject(projectName, startDate, endDate, offset, size);
        BigInteger totalRecord = projectRepository.countProject(projectName, startDate, endDate);

        return PageAndDataResponse.create(projectDtoList, totalRecord.intValue(), page, size);
    }

    @Override
    public ProjectDetailResponse executeDetailProject(String projectCode) {

        if (StringUtils.isEmpty(projectCode)) {
            throw new ProjectNotExistException();
        }

        ProjectDto project = projectRepository
                .findByProjectCodeAndDeleteFlag(Integer.parseInt(projectCode), 0)
                .map(projectMapper::entityToDto).orElseThrow(ProjectNotExistException::new);

        return new ProjectDetailResponse(project);

    }

    @Override
    public void executeUpdateProject(String email, UpdateProjectRequest request) throws ResourceAccessException, JsonProcessingException {

//        AccountDto accountDto = callAPIsService.getAccountInfo(email);
//        boolean isUpdate = false;
//
//        if (accountDto == null) {
//            throw new ProjectNotExistException();
//        }
//
//        if (!accountDto.getRoleId().equals(RoleEnum.USER.getValue())) {
//            isUpdate = true;
//        } else {
//            int countProject = projectRepository.checkRoleOfProject(Integer.parseInt(request.getProjectCode()),
//                    accountDto.getEmployeeCode(),
//                    Arrays.asList(PositionEnum.PROJECT_MANAGER.getValue().toString(), PositionEnum.TEAM_LEADER.getValue().toString()));
//            if (countProject > 0) {
//                isUpdate = true;
//            }
//        }
//
//        if (isUpdate) {
//            projectRepository
//                    .findByProjectCodeAndDeleteFlag(Integer.parseInt(request.getProjectCode()), 0)
//                    .ifPresent(project -> {
//
//                        project.setProjectNameJP(request.getProjectNameJP());
//                        project.setProjectNameVN(request.getProjectNameVN());
//                        project.setBillableEffort(request.getBillableEffort());
//                        project.setStartDate(DateUtils.stringToDate(request.getStartDate(), RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD));
//                        project.setEndDate(DateUtils.stringToDate(request.getEndDate(), RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD));
//                        project.setCustomerName(request.getCustomerName());
//                        project.setSale(request.getSale());
//                        project.setScope(request.getScope());
//                        project.setRank(Integer.parseInt(request.getRank()));
//                        project.setScope(request.getScope());
//                        project.setObjectives(request.getObjectives());
//                        project.setEmailCC(String.join(",", request.getEmailCC()));
//                        project.setUpdateBy(Utils.getUpdateBy(accountDto.getEmail()));
//                        project.setUpdateTime(LocalDateTime.now());
//
//                        log.debug("Update Information for Project: {}", project);
//
//                        // Get list member update from request
//                        List<ProjectDetailDto> memberNewList = request.getMember();
//
//                        // Check role PM duplicate
//                        List<ProjectDetailDto> countRolePM = memberNewList.stream()
//                                .filter(projectDto -> projectDto.getPositionCode().equals(PositionEnum.PROJECT_MANAGER.getValue()))
//                                .collect(Collectors.toList());
//
//                        if(countRolePM.size() > 1){
//                            throw new DuplicateException("Role của PM không được trùng.");
//                        }
//
//                        // Check duplicate
//                        if (Utils.findDuplicate(memberNewList).size() > 0) {
//                            throw new DuplicateException("Nhân viên và vai trò trong dự án không được trùng nhau.");
//                        }
//
//                        // Get List member of project
//                        List<ProjectDetailEntity> memberOldList = detailRepository.findByProjectCodeAndDeleteFlag(project.getProjectCode(), 0);
//                        // Delete member old
//                        detailRepository.deleteAll(memberOldList);
//
//                        // Add member new
//                        memberNewList.forEach(memberNewDto -> {
//
//                            ProjectDetailEntity member = new ProjectDetailEntity();
//                           // member.setProjectCode(project.getProjectCode());
//                            member.setEmployeeCode(memberNewDto.getEmployeeCode());
//                            member.setPositionCode(memberNewDto.getPositionCode());
//                            member.setCreateTime(LocalDateTime.now());
//                            member.setCreateBy(Utils.getUpdateBy(accountDto.getEmail()));
//                            member.setDeleteFlag(0);
//
//                            detailRepository.save(member);
//                            log.debug("Update Information for Project Detail: {}", member);
//                        });
//
//                    });
//        } else {
//            throw new ForbiddenException();
//        }
    }
}
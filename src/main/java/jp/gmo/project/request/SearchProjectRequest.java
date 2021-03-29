package jp.gmo.project.request;

import jp.gmo.project.constant.MessageConstants;
import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.validator.annotation.date.ValidDate;
import jp.gmo.project.validator.annotation.project.CreateProject;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SearchProjectRequest {

    private String projectName;
    private String startDate;
    private String endDate;
    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private String currentPage;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private String totalRecordOfPage;
}
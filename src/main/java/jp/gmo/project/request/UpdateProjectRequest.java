package jp.gmo.project.request;

import jp.gmo.project.constant.MessageConstants;
import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.dto.ProjectDetailDto;
import jp.gmo.project.validator.annotation.date.ValidDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UpdateProjectRequest {

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private String projectCode;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 150)
    private String projectNameJP;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 150)
    private String projectNameVN;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 250)
    private String billableEffort;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    @Pattern(regexp = RegexConstants.DATE_REGEX, message = "{" + MessageConstants.CONST_MSG_VALIDATE_DATE_FORMAT + "}")
    @ValidDate(pattern = RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD, message = "{" + MessageConstants.CONST_MSG_VALIDATE_INVALID_DATE + "}")
    private String startDate;

    @Pattern(regexp = RegexConstants.DATE_REGEX, message = "{" + MessageConstants.CONST_MSG_VALIDATE_DATE_FORMAT + "}")
    @ValidDate(pattern = RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD, message = "{" + MessageConstants.CONST_MSG_VALIDATE_INVALID_DATE + "}")
    private String endDate;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 150)
    private String customerName;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 50)
    private String sale;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private String projectManagement;

    private String brSE;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private String teamLead;

    private String rank;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 300)
    private String scope;

    @Length(message = "{" + MessageConstants.CONST_VALIDATE_MAX_LENGTH + "}", max = 300)
    private String objectives;

    private String[] emailCC;

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    private List<ProjectDetailDto> member;
}
package jp.gmo.project.request;

import jp.gmo.project.constant.MessageConstants;
import jp.gmo.project.constant.RegexConstants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DetailProjectRequest {

    @NotNull(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
    @Pattern(message = "{" + MessageConstants.CONST_MSG_VALIDATE_NUMBER + "}", regexp = RegexConstants.NUMBER_REGEX)
    private String projectCode;
}

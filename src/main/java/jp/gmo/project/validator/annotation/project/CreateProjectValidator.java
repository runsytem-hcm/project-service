package jp.gmo.project.validator.annotation.project;

import jp.gmo.project.constant.MessageConstants;
import jp.gmo.project.constant.RegexConstants;
import jp.gmo.project.entity.Rank;
import jp.gmo.project.repository.RankRepository;
import jp.gmo.project.request.AddProjectRequest;
import jp.gmo.project.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidator;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
public class CreateProjectValidator implements HibernateConstraintValidator<CreateProject, AddProjectRequest> {

    private final RankRepository rankRepository;


    @Override
    public boolean isValid(AddProjectRequest request, ConstraintValidatorContext context) {

        if (request == null) {
            return false;
        }

        String projectNameJP = request.getProjectNameJP();
        String projectNameVN = request.getProjectNameVN();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        String projectRank = request.getRank();

        // Initial flag valid = true
        boolean isValid = true;

        // Set default constraint violation
        context.disableDefaultConstraintViolation();
        // unwrap context to hibernateContext
        HibernateConstraintValidatorContext hibernateContext = context
                .unwrap(HibernateConstraintValidatorContext.class);

        if (StringUtils.isEmpty(projectNameJP) && StringUtils.isEmpty(projectNameVN)) {
            hibernateContext
                    .buildConstraintViolationWithTemplate("{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
                    .addPropertyNode("projectNameJP").addConstraintViolation();
            hibernateContext
                    .buildConstraintViolationWithTemplate("{" + MessageConstants.CONST_MSG_VALIDATE_NOT_NULL + "}")
                    .addPropertyNode("projectNameVN").addConstraintViolation();
            isValid = false;
        }

        // Check validate field StartDate and EndDate
        if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)) {

            if (startDate.matches(RegexConstants.DATE_REGEX) && endDate.matches(RegexConstants.DATE_REGEX)) {
                // Parse hold StartDate and startDate to type Date
                LocalDate dateFrom = DateUtils.convertStringToLocalDate(startDate, RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD);
                LocalDate dateTo = DateUtils.convertStringToLocalDate(endDate, RegexConstants.CONST_STR_PATTERN_YYYY_MM_DD);

                // Check dateFrom > dateTo
                if (dateFrom != null && dateTo != null && dateFrom.compareTo(dateTo) > 0) {
                    hibernateContext
                            .buildConstraintViolationWithTemplate("{" + MessageConstants.CONST_VALIDATE_FROM_LARGE_TO + "}")
                            .addPropertyNode("startDate").addConstraintViolation();
                    isValid = false;
                }
            }

        }

        if (Integer.parseInt(projectRank) != 0) {
            Optional<Rank> optionalRank = rankRepository.findByIdAndDeleteFlag(Integer.parseInt(projectRank), 0);

            if (!optionalRank.isPresent()) {
                hibernateContext
                        .buildConstraintViolationWithTemplate("{" + MessageConstants.CONST_VALIDATE_RANK_NOT_EXIST + "}")
                        .addPropertyNode("projectRank").addConstraintViolation();
                isValid = false;
            }
        }

        return isValid;
    }
}

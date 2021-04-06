package jp.gmo.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.gmo.project.dto.AccountDto;
import org.springframework.web.client.ResourceAccessException;

public interface CallAPIsService {

    AccountDto getAccountInfo(String email) throws ResourceAccessException, JsonProcessingException;
}

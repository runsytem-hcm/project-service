package jp.gmo.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import jp.gmo.project.config.UrlApiProperties;
import jp.gmo.project.dto.AccountDto;
import jp.gmo.project.dto.EmployeeDto;
import jp.gmo.project.service.CallAPIsService;
import jp.gmo.project.utils.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CallAPIsServiceImpl implements CallAPIsService {

    private final RestTemplate template;
    private final UrlApiProperties urlApiProperties;

//    @Override
//    public List<EmployeeDto> getAllEmployees() throws ResourceAccessException, JsonProcessingException {
//
//        List<EmployeeDto> employeeDto = new ArrayList<>();
//
//        String url = urlApiProperties.getGetAllEmployees();
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//
//        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);
//
//        if (response.getBody() != null) {
//            // Parsing entity of response
//            employeeDto = Utils.convertJsonStringToObject(response.getBody(), new TypeReference<List<EmployeeDto>>() {});
//        }
//
//        return employeeDto;
//    }

    @Override
    public AccountDto getAccountInfo(String email) throws ResourceAccessException, JsonProcessingException {
        AccountDto accountDto = null;

        String url = urlApiProperties.getGetAccountInfo();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("email", email);

        ResponseEntity<String> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, String.class);

        if (response.getBody() != null) {
            // Parsing entity of response
            accountDto = Utils.convertJsonStringToObject(response.getBody(), AccountDto.class);
        }

        return accountDto;
    }
}

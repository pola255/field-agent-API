package learn.field_agent.domain;


import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class SecurityClearanceServiceTest {
    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository securityClearanceRepository;

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = new SecurityClearance(0, "TEST");
        SecurityClearance mockOut = new SecurityClearance(5, "TEST");

        when(securityClearanceRepository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {

        SecurityClearance securityClearance = new SecurityClearance(35, "TEST");

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance.setSecurityClearanceId(0);
        securityClearance.setName(null);
        actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance.setName("   ");
        actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddWhenNameExists() {
        SecurityClearance securityClearance = new SecurityClearance(0, "Secret");

        when(securityClearanceRepository.findByName("Secret")).thenReturn(new SecurityClearance(5, "Secret"));

        Result<SecurityClearance> actual = service.add(securityClearance);

        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = new SecurityClearance(5, "TEST");

        when(securityClearanceRepository.update(securityClearance)).thenReturn(true);
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        SecurityClearance securityClearance = new SecurityClearance(35, "TEST");

        when(securityClearanceRepository.update(securityClearance)).thenReturn(false);
        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        SecurityClearance securityClearance = new SecurityClearance(35, null);

        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance.setName(" ");
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        securityClearance.setSecurityClearanceId(0);
        securityClearance.setName("Name Test");
        actual = service.update(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());


    }
    @Test
    void shouldNotUpdateWhenNameExists() {

        SecurityClearance securityClearance = new SecurityClearance(0, "Secret");

        when(securityClearanceRepository.findByName("Secret")).thenReturn(new SecurityClearance(5, "Secret"));

        Result<SecurityClearance> actual = service.update(securityClearance);

        assertEquals(ResultType.INVALID, actual.getType());


    }
}

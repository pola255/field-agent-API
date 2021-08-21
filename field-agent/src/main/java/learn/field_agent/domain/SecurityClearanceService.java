package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {
    private final SecurityClearanceRepository securityClearanceRepository;


    public SecurityClearanceService(SecurityClearanceRepository securityClearanceRepository) {
        this.securityClearanceRepository = securityClearanceRepository;

    }

    public List<SecurityClearance> findAll() {
        return securityClearanceRepository.findAll();
    }

    public SecurityClearance findById(int securityClearanceId) {
        return securityClearanceRepository.findById(securityClearanceId);
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("SecurityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = securityClearanceRepository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("securityClearanceId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!securityClearanceRepository.update(securityClearance)) {
            String msg = String.format("securityClearanceId: %s, not found", securityClearance.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    public boolean deleteById(int securityClearanceId) {
        return securityClearanceRepository.deleteById(securityClearanceId);
    }


    public SecurityClearance findByName(String name) {
        return securityClearanceRepository.findByName(name);
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();
        if (securityClearance == null) {
            result.addMessage("securityClearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }
        SecurityClearance scWithSameName = securityClearanceRepository.findByName(securityClearance.getName());
        if (scWithSameName != null) {
            result.addMessage("Name already exits", ResultType.INVALID);
        }
        return result;
    }


}

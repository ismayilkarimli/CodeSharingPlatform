package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.exception.CodeNotFoundException;
import platform.model.Code;
import platform.repository.CodeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public UUID addCode(Code code) {
        if (code.getViewsLeft() > 0) code.setViewRestricted(true);
        if (code.getTimeLeft() > 0) code.setTimeRestricted(true);
        Code savedCode = codeRepository.save(code);
        return savedCode.getId();
    }

    public List<Code> getLatest() {
        return codeRepository.findLatest();
    }

    @Transactional
    public Code getCode(String id) {
        Optional<Code> optionalCode = codeRepository.findById(id.replaceAll("-", ""));
        Code code = optionalCode.orElseThrow(() -> new CodeNotFoundException("Code with this UUID does not exist"));
        if (code.isRestricted()) {
            throw new CodeNotFoundException("code is restricted");
        }
        if (code.getViewsLeft() > 0 || code.getTimeLeft() > 0) {
            updateRestrictionStates(code);
            codeRepository.updateCodeViewsAndTime(code.getId(), code.getViewsLeft(), code.getTimeLeft());
        }

        return code;
    }

    private void updateRestrictionStates(Code code) {
        if (code.isTimeRestricted()) {
            Long elapsedTime = (System.currentTimeMillis() - code.getCreationTimestamp()) / 1000;
            code.setTimeLeft(code.getTimeLeft() - elapsedTime);
            if (code.getTimeLeft() < 0) {
                code.setRestricted(true);
                throw new CodeNotFoundException("code is restricted");
            }
        }
        if (code.isViewRestricted()) {
            code.setViewsLeft(code.getViewsLeft() - 1);
            if (code.getViewsLeft() == 0) {
                code.setRestricted(true);
            }
        }
    }
}

package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.model.Code;
import platform.service.CodeService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class ViewController {

    private final CodeService codeService;

    @Autowired
    public ViewController(CodeService service) {
        this.codeService = service;
    }

    @GetMapping(value = "/code/{id}")
    public String getCode(@PathVariable String id, Model model) {
        Code code = codeService.getCode(id);
        model.addAttribute("code", code);
        return "code";
    }

    @GetMapping("/code/new")
    public String createCodeView() {
        return "create";
    }

    @GetMapping("/code/latest")
    public String getLatestCodes(Model model) {
        List<Code> codes = codeService.getLatest();
        model.addAttribute("codes", codes);
        return "latest";
    }

}

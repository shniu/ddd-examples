package info.chaintech.july.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/bd")
public class BdController {

    @GetMapping("index")
    public String index() {
        return "index";
    }
}

package censusapp.contoller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ApplicationController {

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

}

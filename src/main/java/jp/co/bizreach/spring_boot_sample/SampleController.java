package jp.co.bizreach.spring_boot_sample;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bizreach.Inc
 */
@Controller
@RequestMapping("")
public class SampleController {
    @RequestMapping("")
    @ResponseBody
    public String index() {
        return "hello,world";
    }

    @RequestMapping("sample")
    public String sample(Model model,
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "sample";
    }
}

package zuun.tech.budget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zuun.tech.budget.domain.Unity;
import zuun.tech.budget.service.UnityService;

@Controller
@RequestMapping("/unities")
public class UnityController {

    @Autowired
    private UnityService unityService;

    @GetMapping
    public String listUnities(Model model) {
        model.addAttribute("unities", unityService.findAll());
        return "unity/list";
    }

    @GetMapping("/new")
    public String newUnityForm(Model model) {
        model.addAttribute("unity", new Unity());
        return "unity/form";
    }

    @PostMapping
    public String saveUnity(@ModelAttribute Unity unity) {
        unityService.save(unity);
        return "redirect:/unities";
    }

    @GetMapping("/edit/{id}")
    public String editUnityForm(@PathVariable Integer id, Model model) {
        model.addAttribute("unity", unityService.findById(id));
        return "unity/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteUnity(@PathVariable Integer id) {
        unityService.deleteById(id);
        return "redirect:/unities";
    }

    @GetMapping("/view/{id}")
    public String viewUnity(@PathVariable Integer id, Model model) {
        model.addAttribute("unity", unityService.findById(id));
        return "unity/view";
    }

}


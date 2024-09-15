package zuun.tech.budget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zuun.tech.budget.domain.Provider;
import zuun.tech.budget.service.ProviderService;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public String listProviders(Model model) {
        model.addAttribute("providers", providerService.findAll());
        return "provider/list";
    }

    @GetMapping("/new")
    public String newProviderForm(Model model) {
        model.addAttribute("provider", new Provider());
        return "provider/form";
    }

    @PostMapping
    public String saveProvider(@ModelAttribute Provider provider) {
        providerService.save(provider);
        return "redirect:/providers";
    }

    @GetMapping("/edit/{id}")
    public String editProviderForm(@PathVariable Long id, Model model) {
        model.addAttribute("provider", providerService.findById(id));
        return "provider/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteProvider(@PathVariable Long id) {
        providerService.deleteById(id);
        return "redirect:/providers";
    }

    @GetMapping("/view/{id}")
    public String viewProvider(@PathVariable Long id, Model model) {
        model.addAttribute("provider", providerService.findById(id));
        return "provider/view";
    }
}

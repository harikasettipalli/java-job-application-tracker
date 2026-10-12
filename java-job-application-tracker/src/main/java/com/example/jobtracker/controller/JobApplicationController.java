package com.example.jobtracker.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import com.example.jobtracker.model.JobApplication;
import com.example.jobtracker.service.JobApplicationService;

@Controller
@RequestMapping("/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public String listApplications(Model model) {
        model.addAttribute("applications", service.findAll());
        return "applications/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        JobApplication application = new JobApplication();
        application.setApplicationDate(LocalDate.now());
        application.setStatus("Applied");
        model.addAttribute("application", application);
        model.addAttribute("pageTitle", "Add application");
        return "applications/form";
    }

    @PostMapping
    public String createApplication(
            @Valid @ModelAttribute("application") JobApplication application,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Add application");
            return "applications/form";
        }
        service.save(application);
        redirectAttributes.addFlashAttribute("successMessage", "Application added successfully.");
        return "redirect:/applications";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("application", service.findById(id));
        model.addAttribute("pageTitle", "Edit application");
        return "applications/form";
    }

    @PostMapping("/{id}")
    public String updateApplication(
            @PathVariable Long id,
            @Valid @ModelAttribute("application") JobApplication application,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            application.setId(id);
            model.addAttribute("pageTitle", "Edit application");
            return "applications/form";
        }
        application.setId(id);
        service.save(application);
        redirectAttributes.addFlashAttribute("successMessage", "Application updated successfully.");
        return "redirect:/applications";
    }

    @PostMapping("/{id}/delete")
    public String deleteApplication(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Application deleted.");
        return "redirect:/applications";
    }
}

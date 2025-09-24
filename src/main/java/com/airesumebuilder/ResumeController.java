package com.airesumebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("resume", new Resume());
        return "index";
    }

    @GetMapping("/create")
    public String createForm(@ModelAttribute("resume") Resume resume, Model model) {
        if (!model.containsAttribute("resume")) {
            model.addAttribute("resume", new Resume());
        }
        return "resume-form";
    }

    @GetMapping("/templates")
    public String selectTemplate() {
        return "template-selection";
    }

    @PostMapping("/generate")
    public String generateResume(@Valid @ModelAttribute("resume") Resume resume,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resume", bindingResult);
            redirectAttributes.addFlashAttribute("resume", resume);
            return "redirect:/create";
        }

        try {
            Resume generatedResume = resumeService.generateResume(resume);
            model.addAttribute("resume", generatedResume);
            return "resume-display";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to generate resume: " + e.getMessage());
            model.addAttribute("resume", resume);
            return "resume-form";
        }
    }

    @PostMapping("/download")
    public String downloadResume(@ModelAttribute("resume") Resume resume,
                                Model model) {
        try {
            // For now, just redirect to the PDF download page
            // In a real implementation, you would generate the actual PDF here
            model.addAttribute("resume", resume);
            return "pdf-download";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to prepare download: " + e.getMessage());
            model.addAttribute("resume", resume);
            return "resume-display";
        }
    }
}

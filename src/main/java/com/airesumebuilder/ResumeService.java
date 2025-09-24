package com.airesumebuilder;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    public Resume generateResume(Resume resumeData) {
        try {
            // Generate professional summary using AI
            String summary = generateSummary(resumeData);
            resumeData.setSummary(summary);

            // Generate skills description if not provided
            if (resumeData.getSkills() != null && !resumeData.getSkills().isEmpty()) {
                String skillsDescription = generateSkillsDescription(resumeData.getSkills());
                resumeData.setSkills(skillsDescription);
            }

            // Enhance experience descriptions
            if (resumeData.getExperience() != null) {
                List<Resume.Experience> enhancedExperiences = resumeData.getExperience().stream()
                    .map(this::enhanceExperienceDescription)
                    .collect(Collectors.toList());
                resumeData.setExperience(enhancedExperiences);
            }

            return resumeData;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate resume: " + e.getMessage(), e);
        }
    }

    private String generateSummary(Resume resumeData) {
        if (resumeData.getSummary() != null && !resumeData.getSummary().isEmpty()) {
            return resumeData.getSummary();
        }

        // Mock AI-generated summary based on target job role
        String targetRole = resumeData.getTargetJobRole().toLowerCase();

        if (targetRole.contains("software") || targetRole.contains("developer") || targetRole.contains("engineer")) {
            return "Results-driven software professional with expertise in full-stack development and modern technologies. " +
                   "Proven track record of delivering high-quality software solutions and collaborating with cross-functional teams. " +
                   "Passionate about writing clean, maintainable code and implementing best practices in software development.";
        } else if (targetRole.contains("manager") || targetRole.contains("lead")) {
            return "Experienced leader with strong project management and team building skills. " +
                   "Demonstrated ability to drive team performance and deliver projects on time and within budget. " +
                   "Skilled in strategic planning, resource allocation, and stakeholder management.";
        } else if (targetRole.contains("marketing") || targetRole.contains("sales")) {
            return "Dynamic marketing professional with a creative mindset and data-driven approach. " +
                   "Experienced in developing and executing marketing campaigns that drive brand awareness and revenue growth. " +
                   "Strong analytical skills with a focus on ROI and market trends.";
        } else {
            return "Dedicated professional with comprehensive experience in " + resumeData.getTargetJobRole() + ". " +
                   "Committed to delivering high-quality results and contributing to organizational success. " +
                   "Strong problem-solving abilities with a focus on continuous improvement and professional development.";
        }
    }

    private String generateSkillsDescription(String skills) {
        if (skills == null || skills.isEmpty()) {
            return "Technical and professional skills will be enhanced by AI processing.";
        }

        // Mock AI-enhanced skills description
        String[] skillArray = skills.split(",");
        StringBuilder enhanced = new StringBuilder();

        enhanced.append("Technical Skills: ");
        for (int i = 0; i < skillArray.length; i++) {
            if (i > 0) enhanced.append(", ");
            enhanced.append(skillArray[i].trim());
        }

        enhanced.append("\n\nCore Competencies: Problem-solving, Team collaboration, Project management, " +
                       "Continuous learning, and Adaptability to new technologies and methodologies.");

        return enhanced.toString();
    }

    private Resume.Experience enhanceExperienceDescription(Resume.Experience experience) {
        if (experience.getDescription() == null || experience.getDescription().isEmpty()) {
            // Mock AI-generated experience description
            String jobTitle = experience.getJobTitle().toLowerCase();

            if (jobTitle.contains("software") || jobTitle.contains("developer") || jobTitle.contains("engineer")) {
                experience.setDescription("• Developed and maintained software applications using modern technologies and best practices\n" +
                                        "• Collaborated with cross-functional teams to deliver high-quality software solutions\n" +
                                        "• Participated in code reviews and implemented feedback to improve code quality\n" +
                                        "• Contributed to the full software development lifecycle from requirements to deployment");
            } else if (jobTitle.contains("manager") || jobTitle.contains("lead")) {
                experience.setDescription("• Led and managed team of professionals to achieve project objectives and deliverables\n" +
                                        "• Developed project plans, timelines, and resource allocation strategies\n" +
                                        "• Coordinated with stakeholders to ensure alignment with business goals\n" +
                                        "• Monitored project progress and implemented corrective actions as needed");
            } else if (jobTitle.contains("analyst")) {
                experience.setDescription("• Analyzed business requirements and translated them into technical specifications\n" +
                                        "• Conducted data analysis and generated insights to support decision-making\n" +
                                        "• Created reports and presentations to communicate findings to stakeholders\n" +
                                        "• Collaborated with teams to implement process improvements");
            } else {
                experience.setDescription("• Successfully performed key responsibilities and contributed to team objectives\n" +
                                        "• Demonstrated strong work ethic and commitment to quality results\n" +
                                        "• Collaborated effectively with colleagues and stakeholders\n" +
                                        "• Continuously improved skills and knowledge in relevant areas");
            }
        }
        return experience;
    }

    public byte[] generatePdf(Resume resume) throws IOException {
        // For now, return a simple text-based PDF
        // In a real implementation, you would use iText or similar library
        String content = generateResumeText(resume);
        return content.getBytes();
    }

    public byte[] generatePdfWithTemplate(Resume resume) throws IOException {
        // Generate PDF with template-specific styling
        // In a real implementation, this would use a PDF library like iText or Apache PDFBox
        // to create a properly formatted PDF with the selected template styles
        String content = generateResumeTextWithTemplate(resume);
        return content.getBytes();
    }

    private String generateResumeText(Resume resume) {
        StringBuilder sb = new StringBuilder();
        sb.append("RESUME\n\n");
        sb.append("Name: ").append(resume.getName()).append("\n");
        sb.append("Email: ").append(resume.getEmail()).append("\n");
        sb.append("Phone: ").append(resume.getPhone()).append("\n");

        if (resume.getAddress() != null) {
            sb.append("Address: ").append(resume.getAddress()).append("\n");
        }

        sb.append("\nSUMMARY\n");
        sb.append(resume.getSummary()).append("\n\n");

        sb.append("SKILLS\n");
        sb.append(resume.getSkills()).append("\n\n");

        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            sb.append("EXPERIENCE\n");
            for (Resume.Experience exp : resume.getExperience()) {
                sb.append(exp.getJobTitle()).append(" at ").append(exp.getCompany()).append("\n");
                sb.append(exp.getStartDate()).append(" - ");
                sb.append(exp.getEndDate() != null ? exp.getEndDate() : "Present").append("\n");
                sb.append(exp.getDescription()).append("\n\n");
            }
        }

        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            sb.append("EDUCATION\n");
            for (Resume.Education edu : resume.getEducation()) {
                sb.append(edu.getDegree()).append(" in ").append(edu.getFieldOfStudy());
                sb.append(" from ").append(edu.getInstitution()).append("\n");
                sb.append("Graduation: ").append(edu.getEndYear()).append("\n\n");
            }
        }

        return sb.toString();
    }

    private String generateResumeTextWithTemplate(Resume resume) {
        StringBuilder sb = new StringBuilder();
        sb.append("RESUME - ").append(resume.getTemplate().toUpperCase()).append(" TEMPLATE\n\n");
        sb.append("Name: ").append(resume.getName()).append("\n");
        sb.append("Email: ").append(resume.getEmail()).append("\n");
        sb.append("Phone: ").append(resume.getPhone()).append("\n");

        if (resume.getAddress() != null) {
            sb.append("Address: ").append(resume.getAddress()).append("\n");
        }

        sb.append("\nSUMMARY\n");
        sb.append(resume.getSummary()).append("\n\n");

        sb.append("SKILLS\n");
        sb.append(resume.getSkills()).append("\n\n");

        if (resume.getExperience() != null && !resume.getExperience().isEmpty()) {
            sb.append("EXPERIENCE\n");
            for (Resume.Experience exp : resume.getExperience()) {
                sb.append(exp.getJobTitle()).append(" at ").append(exp.getCompany()).append("\n");
                sb.append(exp.getStartDate()).append(" - ");
                sb.append(exp.getEndDate() != null ? exp.getEndDate() : "Present").append("\n");
                sb.append(exp.getDescription()).append("\n\n");
            }
        }

        if (resume.getEducation() != null && !resume.getEducation().isEmpty()) {
            sb.append("EDUCATION\n");
            for (Resume.Education edu : resume.getEducation()) {
                sb.append(edu.getDegree()).append(" in ").append(edu.getFieldOfStudy());
                sb.append(" from ").append(edu.getInstitution()).append("\n");
                sb.append("Graduation: ").append(edu.getEndYear()).append("\n\n");
            }
        }

        return sb.toString();
    }
}

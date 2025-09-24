package com.airesumebuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.util.List;

public class Resume {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    @Size(max = 1000, message = "Summary must not exceed 1000 characters")
    private String summary;

    @Size(max = 2000, message = "Skills must not exceed 2000 characters")
    private String skills;

    private List<Education> education;

    private List<Experience> experience;

    private List<String> achievements;

    @NotBlank(message = "Target job role is required")
    private String targetJobRole;

    private String template = "professional"; // Default template

    private String githubLink;

    private String linkedinLink;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }

    public String getTargetJobRole() {
        return targetJobRole;
    }

    public void setTargetJobRole(String targetJobRole) {
        this.targetJobRole = targetJobRole;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    // Inner classes for Education and Experience
    public static class Education {
        @NotBlank(message = "Institution is required")
        private String institution;

        @NotBlank(message = "Degree is required")
        private String degree;

        private String fieldOfStudy;

        @NotBlank(message = "Start year is required")
        private String startYear;

        private String endYear;

        private String gpa;

        // Getters and Setters
        public String getInstitution() { return institution; }
        public void setInstitution(String institution) { this.institution = institution; }
        public String getDegree() { return degree; }
        public void setDegree(String degree) { this.degree = degree; }
        public String getFieldOfStudy() { return fieldOfStudy; }
        public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }
        public String getStartYear() { return startYear; }
        public void setStartYear(String startYear) { this.startYear = startYear; }
        public String getEndYear() { return endYear; }
        public void setEndYear(String endYear) { this.endYear = endYear; }
        public String getGpa() { return gpa; }
        public void setGpa(String gpa) { this.gpa = gpa; }
    }

    public static class Experience {
        @NotBlank(message = "Company is required")
        private String company;

        @NotBlank(message = "Job title is required")
        private String jobTitle;

        @NotBlank(message = "Start date is required")
        private String startDate;

        private String endDate;

        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        private String description;

        // Getters and Setters
        public String getCompany() { return company; }
        public void setCompany(String company) { this.company = company; }
        public String getJobTitle() { return jobTitle; }
        public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }
        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}

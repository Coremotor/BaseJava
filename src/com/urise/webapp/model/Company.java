package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Company {
    private final List<Period> periods;
    private final String name;
    private String website;

    public Company(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(periods, company.periods) && Objects.equals(name, company.name) && Objects.equals(website, company.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, name, website);
    }

    @Override
    public String toString() {
        return "\nCompany: \n" + "{ periods: " + periods + " }\n" + "{ name: " + name + " }\n" + "{ website: " + website + " }";
    }

    public static class Period {
        final private LocalDate startDate;
        final private LocalDate endDate;
        final private String title;
        final private String description;

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate) && Objects.equals(title, period.title) && Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "\nPeriod: \n" + "{ startDate: " + startDate + " }\n" + "{ endDate: " + endDate + " }\n" + "{ title: " + title + " }\n" + "{ description: " + description + " }";
        }
    }
}
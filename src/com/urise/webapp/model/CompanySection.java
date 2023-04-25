package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends AbstractSection {
    private final List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }


    @Override
    public String toString() {
        return "\nCompanySection: \n" + "{ companies: " + companies + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection section = (CompanySection) o;
        return Objects.equals(companies, section.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }
}

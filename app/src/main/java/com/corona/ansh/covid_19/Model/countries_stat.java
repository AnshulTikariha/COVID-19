
package com.corona.ansh.covid_19.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class countries_stat {

    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("cases")
    @Expose
    private String cases;
    @SerializedName("deaths")
    @Expose
    private String deaths;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("total_recovered")
    @Expose
    private String totalRecovered;
    @SerializedName("new_deaths")
    @Expose
    private String newDeaths;
    @SerializedName("new_cases")
    @Expose
    private String newCases;
    @SerializedName("serious_critical")
    @Expose
    private String seriousCritical;
    @SerializedName("active_cases")
    @Expose
    private String activeCases;
    @SerializedName("total_cases_per_1m_population")
    @Expose
    private String totalCasesPer1mPopulation;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getSeriousCritical() {
        return seriousCritical;
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getTotalCasesPer1mPopulation() {
        return totalCasesPer1mPopulation;
    }

    public void setTotalCasesPer1mPopulation(String totalCasesPer1mPopulation) {
        this.totalCasesPer1mPopulation = totalCasesPer1mPopulation;
    }

}

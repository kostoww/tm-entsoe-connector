package com.trendminer.entsoe.database;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistorianDTO {
    @JsonProperty("DbId")
    private int id;

    @JsonProperty("Name")
    private final String name;

    @JsonProperty("Prefix")
    private final String prefix;

    @JsonProperty("Provider")
    private final String provider;

    @JsonProperty("DataSource")
    private final String dataSource;

    @JsonProperty("UserId")
    private final String userId;

    @JsonProperty("Password")
    private final String password;

    @JsonProperty("Version")
    private final String version;

    @JsonProperty("TagFilter")
    private final String tagFilter;

    public HistorianDTO() {
        //TODO: Remove this at some point
        id = -1;
        name = null;
        prefix = null;
        provider = null;
        dataSource = null;
        userId = null;
        password = null;
        version = null;
        tagFilter = null;
    }

    @JsonCreator
    public HistorianDTO(
            @JsonProperty("Name") String name,
            @JsonProperty("Prefix") String prefix,
            @JsonProperty("Provider") String provider,
            @JsonProperty("DataSource") String dataSource,
            @JsonProperty("UserId") String userId,
            @JsonProperty("Password") String password,
            @JsonProperty("Version") String version,
            @JsonProperty("TagFilter") String tagFilter
            ) {
        this.name = name;
        this.prefix = prefix;
        this.provider = provider;
        this.dataSource = dataSource;
        this.userId = userId;
        this.password = password;
        this.version = version;
        this.tagFilter = tagFilter;
    }

    @JsonCreator
    public HistorianDTO(
            @JsonProperty("DbId") int id,
            @JsonProperty("Name") String name,
            @JsonProperty("Prefix") String prefix,
            @JsonProperty("Provider") String provider,
            @JsonProperty("DataSource") String dataSource,
            @JsonProperty("UserId") String userId,
            @JsonProperty("Password") String password,
            @JsonProperty("Version") String version,
            @JsonProperty("TagFilter") String tagFilter) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.provider = provider;
        this.dataSource = dataSource;
        this.userId = userId;
        this.password = password;
        this.version = version;
        this.tagFilter = tagFilter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getProvider() {
        return provider;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getTagFilter() {
        return tagFilter;
    }

    public String getVersion() {
        return version;
    }
}

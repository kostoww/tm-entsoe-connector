package com.trendminer.entsoe.tags;

import com.google.common.base.Strings;
import com.trendminer.entsoe.connector.EntsoeConnector;
import com.trendminer.entsoe.tags.model.ConnectorPoint;
import com.trendminer.entsoe.tags.model.InterpolationType;
import com.trendminer.entsoe.tags.model.TagDetailsDTO;
import com.trendminer.entsoe.tags.model.TagType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
public class TagsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagsController.class);

    private final EntsoeConnector plantIntegrationsConnector;

    @Autowired
    public TagsController(EntsoeConnector plantIntegrationsConnector) {
        this.plantIntegrationsConnector = plantIntegrationsConnector;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong arguments")
    @ExceptionHandler(IllegalArgumentException.class)
    public void conflict() {
        // Nothing to do
    }

    @GetMapping(value = "/v2/tags")
    public List<TagDetailsDTO> listTags(@RequestParam("historianName") String historianName) {
        return plantIntegrationsConnector.getAllTags(historianName);
    }

    @GetMapping("/v2/tags/rawvalues")
    public List<ConnectorPoint> getRawValues(
            @RequestParam("tagName") String tagName,
            @RequestParam("startDate") String start,
            @RequestParam("endDate") String end) {
        if (Strings.isNullOrEmpty(tagName)) {
            throw new IllegalArgumentException("Tag Name is required");
        }
        return Collections.emptyList();
    }

    @GetMapping("/v2/tags/digitalstates")
    public List getDigitalStates(@RequestParam("tagName") String tagName) {
        if (Strings.isNullOrEmpty(tagName)) {
            throw new IllegalArgumentException("Tag Name is required");
        }
        return Collections.emptyList();
    }

    @GetMapping("/v2/tags/plotvalues")
    public List<ConnectorPoint> getPlotValues(
            @RequestParam(value = "historianName", defaultValue = "") String historianName,
            @RequestParam("tagName") String tagName,
            @RequestParam(value = "tagType", defaultValue = "ANALOG") TagType tagType,
            @RequestParam(value = "interpolationType", defaultValue = "DEFAULT") InterpolationType interpolationType,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam(value = "numberOfIntervals", defaultValue = "1") int numberOfIntervals) {
        return fetchPoints(historianName, tagName, tagType, interpolationType, startDate, endDate, numberOfIntervals);
    }

    @GetMapping("/v2/tags/indexvalues")
    public List<ConnectorPoint> getIndexValues(
            @RequestParam(value = "historianName", defaultValue = "") String historianName,
            @RequestParam("tagName") String tagName,
            @RequestParam(value = "tagType", defaultValue = "ANALOG") TagType tagType,
            @RequestParam(value = "interpolationType", defaultValue = "DEFAULT") InterpolationType interpolationType,
            @RequestParam("startDate") String start,
            @RequestParam("endDate") String end,
            @RequestParam(value = "numberOfIntervals", defaultValue = "1") int numberOfIntervals) {
        return fetchPoints(historianName, tagName, tagType, interpolationType, start, end, numberOfIntervals);
    }


    private List<ConnectorPoint> fetchPoints(String historianName, String tagName, TagType tagType, InterpolationType interpolationType,
                                             String startDate, String endDate, int numberOfIntervals) {
        return plantIntegrationsConnector.getPlotValues(historianName, tagName, tagType, interpolationType, startDate, endDate, numberOfIntervals);
    }
}

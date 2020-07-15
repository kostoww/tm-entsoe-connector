package com.trendminer.entsoe.connector;

import com.trendminer.entsoe.tags.model.ConnectorPoint;
import com.trendminer.entsoe.tags.model.InterpolationType;
import com.trendminer.entsoe.tags.model.TagDetailsDTO;
import com.trendminer.entsoe.tags.model.TagType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntsoeConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntsoeConnector.class);
    private static final String NOT_SUPPORTED_YET = "Operation is currently not implemented";

    @Autowired
    public EntsoeConnector() {
        LOGGER.info("Initializing the ENTSOE Connector.");
    }

    public List<TagDetailsDTO> getAllTags(String historianName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    public List<ConnectorPoint> getPlotValues(String historianName,
                                              String tagName,
                                              TagType tagType,
                                              InterpolationType interpolationType,
                                              String startDate,
                                              String endDate,
                                              int numberOfIntervals) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
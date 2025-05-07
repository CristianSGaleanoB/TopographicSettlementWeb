package com.settlements.settlementssurveyors.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "MeasurementPoint")
public class MeasurementPoint {
    @Id
    private String id;
    private String projectId;
    private String measurementId;
    @GeoSpatialIndexed
    private double[] location;
    private String description;
    private double initialHeight;
}

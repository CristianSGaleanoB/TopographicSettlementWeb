package com.settlements.settlementssurveyors.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Measurement")
public class Measurement {
    @Id
    private String id;
    private String MeasurementPointId; // ID measurementPoint
    private String ProjectId; // ID Project
    private double Height; // In mts
    private double Weight; // Also in mts
    private EnvironmentalConditions EnvironmentalConditions;
    private String instrument;

    @Data
    public static class EnvironmentalConditions{
        private double temperature; // C°
        private double humidity; // %
        private String notes;

    }

}

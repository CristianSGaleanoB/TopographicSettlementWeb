package com.settlements.settlementssurveyors.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "Projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    @GeoSpatialIndexed
    private double[] location;
    @Field("start_date")
    private Date startDate;
    @Field("end_date")
    private Date endDate;
    private String status;
}

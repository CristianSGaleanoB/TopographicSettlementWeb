db = db.getSiblingDB('topographyDB');

// Project
const project = db.projects.insertOne({
    name: "Central Building Construction",
    description: "Settlement monitoring",
    location: [-74.0059, 40.7128],
    startDate: new ISODate("2025-01-01"),
    endDate: null,
    status: "active"
});

// Measurement Point
const measurementPoint = db.measurementPoints.insertOne({
    projectId: project.insertedId,
    identifier: "Point_A1",
    coordinates: [-74.0060, 40.7129],
    description: "Northwest corner",
    initialHeight: 100.5
});

// Measurement
db.measurements.insertOne({
    pointId: measurementPoint.insertedId,
    projectId: project.insertedId,
    date: new ISODate("2025-05-06"),
    height: 100.48,
    displacement: 0.02,
    environmentalConditions: { temperature: 22.5, humidity: 60, notes: "Clear sky" },
    instrument: "Leica NA730 Optical Level"
});

// User
db.users.insertOne({
    name: "Admin",
    email: "admin@topo.com",
    password: "password", // In production, use a hash
    role: "admin"
});
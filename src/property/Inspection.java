package property;

import employees.SalesConsultant;
import util.DateTime;

public class Inspection {

    private String inspectType;
    private DateTime inspectionDate;
    private String propertyId;
    private String employeeId;

    public Inspection(String inspectType, DateTime inspectionDate, String propertyId,
    String employeeId) {
        this.inspectType = inspectType;
        this.inspectionDate = inspectionDate;
        this.propertyId = propertyId;
        this.employeeId = employeeId;
    }
    public String getInspectType() {
        return inspectType;
    }
    public DateTime getInspectionDate() {
        return inspectionDate;
    }
    public String getPropertyId() {
        return propertyId;
    }
    public String getEmployeeId() {
        return employeeId;
    }

}


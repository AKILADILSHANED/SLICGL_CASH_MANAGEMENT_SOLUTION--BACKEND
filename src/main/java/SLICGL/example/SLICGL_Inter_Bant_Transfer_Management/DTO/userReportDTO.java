package SLICGL.example.SLICGL_Inter_Bant_Transfer_Management.DTO;

public class userReportDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String department;
    private String section;
    private String position;
    private String epf;
    private String email;
    private String status;
    private String registeredBy;

    public userReportDTO() {
    }

    public userReportDTO(String userId, String firstName, String lastName, String department, String section, String position, String epf, String email, String status, String registeredBy) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.section = section;
        this.position = position;
        this.epf = epf;
        this.email = email;
        this.status = status;
        this.registeredBy = registeredBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEpf() {
        return epf;
    }

    public void setEpf(String epf) {
        this.epf = epf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
}

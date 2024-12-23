package DataBase.Projects;

import DataBase.EntityAbstract;

public class Ticket extends EntityAbstract {

    private String internalId;
    private String description;

    public Ticket(String internalId, String description) {
        this.internalId = internalId;
        this.description = description;
    }

    public String getInternalId() {
        return this.internalId;
    }

    public String getLabel() {
        return this.getInternalId();
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DataBase.Projects.Ticket{" +
                "id='" + internalId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

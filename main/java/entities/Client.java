package entities;

public class Client extends Entity {
    private Integer clientId;
    private String clientName;
    private String password;

    public Client(Integer clientId, String clientName, String password) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.password = password;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

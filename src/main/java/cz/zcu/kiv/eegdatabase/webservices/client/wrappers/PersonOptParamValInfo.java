package cz.zcu.kiv.eegdatabase.webservices.client.wrappers;

import cz.zcu.kiv.eegdatabase.data.pojo.PersonOptParamValId;

/**
 * @author František Liška
 */
public class PersonOptParamValInfo {
    private PersonOptParamValId id;
    private String paramValue;

    public PersonOptParamValId getId() {
        return id;
    }

    public void setId(PersonOptParamValId id) {
        this.id = id;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}

package com.potaychuk.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
@XmlRootElement(name = "log")
public class EventLog {

    private List<String> eventsMessages = new ArrayList<>();

    public List<String> getEventsMessages() {
        return eventsMessages;
    }

    @XmlElement(name = "event")
    public void setEventsMessages(List<String> eventsMessages) {
        this.eventsMessages = eventsMessages;
    }
}

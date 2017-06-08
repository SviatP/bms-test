package com.potaychuk.XMLWriter;

import com.potaychuk.domain.EventLog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
public class XMLWriter {
    public void writeToXml(EventLog data, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EventLog.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(data, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

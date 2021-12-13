package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlConverter {
    public static void main(String[] args) throws Exception {
        Stranger stranger = new Stranger(true, 19, "Igor", new Passport("550-771"),
                new String[] {"Student", "Foreigner"});

        JAXBContext context = JAXBContext.newInstance(Stranger.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(stranger, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Stranger result = (Stranger) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}

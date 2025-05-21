package gov.odf.geoevent.processor.checksum;

import java.util.ArrayList;
import java.util.List;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.geoevent.DefaultFieldDefinition;
import com.esri.ges.core.geoevent.FieldDefinition;
import com.esri.ges.core.geoevent.FieldType;
import com.esri.ges.core.geoevent.GeoEvent;
import com.esri.ges.core.geoevent.GeoEventDefinition;
import com.esri.ges.framework.i18n.BundleLogger;
import com.esri.ges.framework.i18n.BundleLoggerFactory;
import com.esri.ges.manager.geoeventdefinition.GeoEventDefinitionManager;
import com.esri.ges.messaging.Messaging;
import com.esri.ges.processor.GeoEventProcessorBase;
import com.esri.ges.processor.GeoEventProcessorDefinition;

public class ChecksumProcessor  extends GeoEventProcessorBase implements ChecksumProperties {
    
    GeoEventDefinitionManager _manager;
    Messaging _messaging;

    private static final BundleLogger LOGGER = BundleLoggerFactory.getLogger(ChecksumProcessor.class);
    private String sentenceFieldName;
    private String checksumStartingChar;
    private String checksumEndingChar;
    private String geoEventDefName;
    private String checksumField; 

    public ChecksumProcessor(GeoEventProcessorDefinition definition,GeoEventDefinitionManager manager, Messaging messaging) throws ComponentException{
        super(definition);
        _manager = manager;
        _messaging = messaging;
    }

    @Override
    public void afterPropertiesSet()
    {
        LOGGER.trace("Enter afterPropertiesSet() of delay processor.");
        try{

            sentenceFieldName = properties.get(SENTENCE_FIELD_NAME).getValueAsString();
            checksumStartingChar = properties.get(CHECKSUM_STARTING_CHAR).getValueAsString();
            checksumEndingChar = properties.get(CHECKSUM_ENDING_CHAR).getValueAsString();
            checksumField = properties.get(CHECKSUM_FIELD).getValueAsString();

            if (LOGGER.isTraceEnabled())
            {
                LOGGER.trace("Sentence Field Name: {0}", sentenceFieldName);
                LOGGER.trace("Checksum Starting Char: {0}", checksumStartingChar);
                LOGGER.trace("Checksum Ending Char: {0}", checksumEndingChar);
                LOGGER.trace("GeoEvent Definition Name: {0}", geoEventDefName);
                LOGGER.trace("Checksum Field: {0}", checksumField);
            }
           
        }
        catch (Exception e)
        {
            LOGGER.error("Error getting properties in afterPropertiesSet", e);
        }
        
    }
    

    public GeoEvent process(GeoEvent event) throws Exception {
        LOGGER.trace("Processsing event {0}", event);
        

        String checksum = calculateChecksum((String)event.getField(sentenceFieldName), checksumStartingChar, checksumEndingChar);
        LOGGER.trace("Checksum: {0}", checksum);
               

        GeoEventDefinition originalDef = event.getGeoEventDefinition();
        
  
        if (originalDef.getFieldDefinition(checksumField) == null) {
        
        List<FieldDefinition> newChecksumPropertyCollection = new ArrayList<FieldDefinition>();
		newChecksumPropertyCollection.add(new DefaultFieldDefinition(checksumField, FieldType.Double));
        }
        

        event.setField(checksumField, checksum);

        return event;

    }

    private String calculateChecksum(String sentence, String checksumStartingChar, String checksumEndingChar) {
        int start = sentence.indexOf(checksumStartingChar) +1;
        int end = sentence.indexOf(checksumEndingChar);
        LOGGER.trace("Start: {0}, End: {1}", start, end);
        if (start == -1 || end == -1) {
            LOGGER.error("Checksum starting or ending character not found in the sentence.");
            return null;
        }
        
        int checksum = 0;
            for (int i = start; i < end; i++) {
            checksum ^= sentence.charAt(i);
        }
        
        return "*" + String.format("%02X", checksum);
    }
}

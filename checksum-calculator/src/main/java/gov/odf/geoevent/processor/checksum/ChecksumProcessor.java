package gov.odf.geoevent.processor.checksum;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.geoevent.GeoEvent;
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
    private String geoEventDefinitionName;
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
            geoEventDefinitionName = properties.get(GEOEVENT_DEFINITION_NAME).getValueAsString();
            checksumStartingChar = properties.get(CHECKSUM_STARTING_CHAR).getValueAsString();
            checksumEndingChar = properties.get(CHECKSUM_ENDING_CHAR).getValueAsString();
            checksumField = properties.get(CHECKSUM_FIELD).getValueAsString();

            if (LOGGER.isTraceEnabled())
            {
                LOGGER.trace("Sentence Field Name: {0}", sentenceFieldName);
                LOGGER.trace("Checksum Starting Char: {0}", checksumStartingChar);
                LOGGER.trace("Checksum Ending Char: {0}", checksumEndingChar);
                LOGGER.trace("GeoEvent Definition Name: {0}", geoEventDefinitionName);
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
        return event;
    }
}

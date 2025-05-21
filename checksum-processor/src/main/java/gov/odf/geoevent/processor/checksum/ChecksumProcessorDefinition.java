package gov.odf.geoevent.processor.checksum;

import com.esri.ges.core.property.PropertyDefinition;
import com.esri.ges.core.property.PropertyException;
import com.esri.ges.core.property.PropertyType;
import com.esri.ges.framework.i18n.BundleLogger;
import com.esri.ges.framework.i18n.BundleLoggerFactory;
import com.esri.ges.processor.GeoEventProcessorDefinitionBase;

public class ChecksumProcessorDefinition extends GeoEventProcessorDefinitionBase implements ChecksumProperties
{
    private static final BundleLogger LOGGER                                = BundleLoggerFactory.getLogger(ChecksumProcessorDefinition.class);
    private static final String       PROCESSOR_LABEL                       = STRINGS_PATH + "PROCESSOR_LABEL}";
    private static final String       PROCESSOR_DESC                        = STRINGS_PATH + "PROCESSOR_DESC}";
    private static final String       SENTENCE_NAME_LABEL                   = STRINGS_PATH + "SENTENCE_NAME_LABEL}";
    private static final String       SENTENCE_NAME_DESC                    = STRINGS_PATH + "SENTENCE_NAME_DESC}";
    private static final String       CHECKSUM_STARTING_CHAR_LABEL          = STRINGS_PATH + "CHECKSUM_STARTING_CHAR_LABEL}";
    private static final String       CHECKSUM_STARTING_CHAR_DESC           = STRINGS_PATH + "CHECKSUM_STARTING_CHAR_DESC}";
    private static final String       CHECKSUM_ENDING_CHAR_LABEL            = STRINGS_PATH + "CHECKSUM_ENDING_CHAR_LABEL}";
    private static final String       CHECKSUM_ENDING_CHAR_DESC             = STRINGS_PATH + "CHECKSUM_ENDING_CHAR_DESC}";
    private static final String       CHECKSUM_FIELD_LABEL                  = STRINGS_PATH + "CHECKSUM_FIELD_LABEL}";
    private static final String       CHECKSUM_FIELD_DESC                   = STRINGS_PATH + "CHECKSUM_FIELD_DESC}";


    public ChecksumProcessorDefinition() {
		LOGGER.info("Creating Definition for Checksum Calculator");
			
        try {
			    propertyDefinitions.put(SENTENCE_FIELD_NAME, new PropertyDefinition(SENTENCE_FIELD_NAME, PropertyType.String, "",SENTENCE_NAME_LABEL, SENTENCE_NAME_DESC, true, false ));
                propertyDefinitions.put(CHECKSUM_STARTING_CHAR, new PropertyDefinition(CHECKSUM_STARTING_CHAR, PropertyType.String, "$", CHECKSUM_STARTING_CHAR_LABEL, CHECKSUM_STARTING_CHAR_DESC, false, false));
                propertyDefinitions.put(CHECKSUM_ENDING_CHAR, new PropertyDefinition(CHECKSUM_ENDING_CHAR, PropertyType.String, "*", CHECKSUM_ENDING_CHAR_LABEL, CHECKSUM_ENDING_CHAR_DESC, false, false));
				propertyDefinitions.put(CHECKSUM_FIELD, new PropertyDefinition(CHECKSUM_FIELD, PropertyType.String, "*", CHECKSUM_FIELD_LABEL, CHECKSUM_FIELD_DESC, true, false));
        }
        catch (PropertyException e) {
            LOGGER.warn("Failed to construct definition.", e);
        }
	}
	
	@Override
	public String getName() {
		return "checksum-processor";
	}

	@Override
	public String getDomain() {
		return "gov.odf.geoevent.processor";
	}

	@Override
	public String getVersion() {
		return "11.3.0";
	}

	@Override
	public String getLabel() {
		return PROCESSOR_LABEL;
	}

	@Override
	public String getDescription() {
		return PROCESSOR_DESC;
	}

	@Override
	public String getContactInfo() {
		return "brian.c.locke@odf.oregon.gov";
	}
}
package gov.odf.geoevent.processor.checksum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.esri.ges.core.component.ComponentException;
import com.esri.ges.core.property.PropertyException;
import com.esri.ges.manager.geoeventdefinition.GeoEventDefinitionManager;
import com.esri.ges.messaging.Messaging;
import com.esri.ges.processor.GeoEventProcessor;
import com.esri.ges.processor.GeoEventProcessorServiceBase;

public class ChecksumProcessorService extends GeoEventProcessorServiceBase
{
    GeoEventDefinitionManager manager;
	Messaging messaging;
	private static final Log LOG = LogFactory.getLog(ChecksumProcessorService.class);
	public ChecksumProcessorService() throws PropertyException {
		definition = new ChecksumProcessorDefinition();
	}

    @Override
	public GeoEventProcessor create() throws ComponentException {
		
		return new ChecksumProcessor(definition, manager, messaging);
	}
	
	public void setManager(GeoEventDefinitionManager m)
	{
		manager = m;
	}
	
	public void setMessaging(Messaging m)
	{
		messaging = m;
	}
}
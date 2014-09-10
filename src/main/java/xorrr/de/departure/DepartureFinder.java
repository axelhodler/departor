package xorrr.de.departure;

import java.util.List;

import org.w3c.dom.Document;

public interface DepartureFinder {
	public List<DepartureInfo> getDepatureInfos(Document doc);
}

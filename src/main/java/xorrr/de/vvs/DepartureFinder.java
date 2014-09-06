package xorrr.de.vvs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xorrr.de.util.XPathExpressions;
import xorrr.de.util.XmlAttributes;

public class DepartureFinder {

	private Document doc;
	private XPath xPath;


	public DepartureFinder(Document doc) {
		this.doc = doc;
		this.xPath = XPathFactory.newInstance().newXPath();
	}

	public List<DepartureInfo> getDepatureInfos() {
		List<DepartureInfo> infos = new ArrayList<>();
		NodeList nodes = getDepatureNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			DepartureInfo info = new DepartureInfo();

			Node n = nodes.item(i);

			info.setLine(getLine(n));
			info.setRoute(getRoute(n));
			info.setTime(getTime(n));

			infos.add(info);
		}

		return infos;
	}

	private String getLine(Node node) {
		Node lineNode = null;
		try {
			XPathExpression expr = xPath.compile(XPathExpressions.SERVING_LINE);
			lineNode = (Node) expr.evaluate(node, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return getLineAttributeContent(lineNode);
	}

	private String getRoute(Node node) {
		Node routeNode = null;
		try {
			XPathExpression expr = xPath.compile(XPathExpressions.ROUTE);
			routeNode = (Node) expr.evaluate(node, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return routeNode.getTextContent();
	}

	private String getTime(Node node) {
		Node timeNode = null;
		try {
			XPathExpression expr = xPath.compile(XPathExpressions.TIME);
			timeNode = (Node) expr.evaluate(node, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return getHourAttributeContent(timeNode) + ":"
				+ getMinuteAttributeContent(timeNode);
	}

	private String getMinuteAttributeContent(Node timeNode) {
		return timeNode.getAttributes().getNamedItem(XmlAttributes.DEPARTURE_MINUTE).getTextContent();
	}

	private String getHourAttributeContent(Node timeNode) {
		return timeNode.getAttributes().getNamedItem(XmlAttributes.DEPARTURE_HOUR).getTextContent();
	}

	private String getLineAttributeContent(Node routeNode) {
		return routeNode.getAttributes().getNamedItem(XmlAttributes.LINE_NUMBER).getTextContent();
	}

	private NodeList getDepatureNodes() {
		NodeList nodes = null;		
		try {
			XPathExpression expr = xPath.compile(XPathExpressions.DEPARTURES);
			nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return nodes;
	}

	
}

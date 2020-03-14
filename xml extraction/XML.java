import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class XML {

	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException{  

		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(new File("character.xml"));
		 
		//Normalize the XML Structure
		document.getDocumentElement().normalize();
		 
		//Get the root node
		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());
		 
		//Get all characters information
		NodeList nList = document.getElementsByTagName("character");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				//Print character's detail
				Element eElement = (Element) node;
				System.out.println("character id : "    + eElement.getAttribute("id"));
				System.out.println("First Name : "  + eElement.getElementsByTagName("firstName").item(0).getTextContent());
				System.out.println("Last Name : "   + eElement.getElementsByTagName("lastName").item(0).getTextContent());
				System.out.println("Location : "    + eElement.getElementsByTagName("location").item(0).getTextContent());
			}

			System.out.println("------------------------------"); //separate each charactor detail
		}

	}

}

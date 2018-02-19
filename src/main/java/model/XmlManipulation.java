package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * 
 * @author Paulo Pereira
 *
 */
public class XmlManipulation {

	public void write( ArrayList<Student> studentsList){
		//guarda o caminho de onde o arquivo XML vai ser salva
		String filePath = Constants.FILE_PATH+Constants.FOLDER_NAME+Constants.FILENAME;
		Document dom;
		Element element = null;
		//cria uma instancia do builderFactory sera usada para a construção do documento
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			//cria um documentBuilder para criar o documento dom
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			dom = dBuilder.newDocument();
			//cria um elemento que sera o nó root do arquivo XML, ele recebe o nome de Students
			Element rootElement = dom.createElement("Students");
			
			for (Student student : studentsList){
				element = dom.createElement("Student");
				element = addNewStudent(student, element, dom);
				//adciona o elemento ao elemento principal
				rootElement.appendChild(element);
			}
			
			//adiciona o elemento principal ao documento
			dom.appendChild(rootElement);
			//adiciona os paramentros e cria o documento na pasta 
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath)));
		} catch (ParserConfigurationException pcE) {
			System.out.println(pcE.getMessage());
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (TransformerFactoryConfigurationError e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<Student> read (){
		
		ArrayList<Student> studentsList = new ArrayList<Student>();
		File file = new File(Constants.FILE_PATH+Constants.FOLDER_NAME+Constants.FILENAME);
		if(file.exists()){
			//se o arquivo existir
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				//converte o arquivo xml para Document
				Document dom = dBuilder.parse(file);
				dom.getDocumentElement().normalize();
				
				//cria uma lista de nós com a tag Student
				NodeList nList = dom.getElementsByTagName("Student");
				Node nNode =null;
				Student student = null;

				for(int i = 0; i< nList.getLength(); i++){
					nNode = nList.item(i);
					if(nNode.getNodeType()==Node.ELEMENT_NODE){
						//converte o Nó para element
						Element element = (Element)nNode;
						student = new Student();
						//acessa o texto contido dentro do elemento baseado no nome de sua tag
						student.setName(element.getElementsByTagName("Name").item(0).getTextContent());
						student.setAge(Integer.parseInt(element.getElementsByTagName("Age").item(0).getTextContent()));
						student.setSex(Boolean.parseBoolean(element.getElementsByTagName("Sex").item(0).getTextContent()));
					}
					studentsList.add(student);
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return studentsList;
		}
		else{
			return null;
		}
	}

	private Element addNewStudent(Student student, Element currentElement, Document dom){
		//Serializa o estudante em um elemento
		Element element = null;
		if(student != null){			
			element = dom.createElement("Name");
			//adiciona ao elemento o nome contido do aluno
			element.appendChild(dom.createTextNode(student.getName()));
			currentElement.appendChild(element);
			element = dom.createElement("Age");
			element.appendChild(dom.createTextNode(Integer.toString(student.getAge())));
			currentElement.appendChild(element);
			element = dom.createElement("Sex");
			element.appendChild(dom.createTextNode(Boolean.toString(student.isSex())));
			//adiciona ao elemento corrente o novo elemento
			currentElement.appendChild(element);
		}
		return currentElement;
	}
}

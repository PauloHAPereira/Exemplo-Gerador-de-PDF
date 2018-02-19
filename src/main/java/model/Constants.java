package model;
/**
 * 
 * @author Paulo Pereira
 *
 */
public class Constants {
	public static String FILE_PATH = System.getProperty("user.home");
	public static String FILENAME = "/Student.xml";
	public static String PDF_FILE_NAME = "/Students.pdf";
	public static String FOLDER_NAME = "/Gerador de pdf";
	public static String PDF_TITLE = "\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Relação de estudantes \n \n \n";
	public static String PDF_PARAGRAPH = "\n\t Este é um programa gerador de PDF. \n \t nele esta sendo usado MAVEN para importar as"
			+" dependencias da biblioteca IText. essa biblioteca tem a finalidade de facilitar na criação de arquivos pdf."
			+"\n\t Tabém estou usando arquivos XML para persistir os dados dos estudantes.\n \n \n ";
	
}

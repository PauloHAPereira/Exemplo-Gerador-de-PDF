package model;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * 
 * @author Paulo Pereira
 *
 */
public class PdfGenerator {
	
	public void generatePDF(){
		int acaoJOptionpane;//recebe a opção selecionada pelo usuario
		
		//Mostra uma confirmação para o usuario
		acaoJOptionpane = JOptionPane.showConfirmDialog(null, "Deseja abrir o documento? " + 
				"\n Caminho:" + Constants.FILE_PATH + Constants.FOLDER_NAME , 
				"Documento criado com sucesso!", JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
		
		if(acaoJOptionpane == JOptionPane.YES_OPTION){
			//se selecionar sim, então crie o PDF e abre ele
			createPDF();
			//Abre o arquivo PDF
			try {
				Desktop.getDesktop().open(new File(Constants.FILE_PATH 
						+ Constants.FOLDER_NAME + Constants.PDF_FILE_NAME));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(acaoJOptionpane == JOptionPane.NO_OPTION){
			//se não, então apenas crie o pdf 
			createPDF();
		}
	}
	
	private void createPDF(){
		
		Document document = new Document();
		try {
			//cria um documento pdf no caminho especificado com o nome especificado
			PdfWriter.getInstance(document, new FileOutputStream(Constants.FILE_PATH 
					+ Constants.FOLDER_NAME + Constants.PDF_FILE_NAME));
			document.open();
			
			//Configuração da fonte do cabeçalho
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			font.setStyle(Font.BOLD);
			//Adciona o cabeçalho ao documento PDF
			document.add(new Paragraph(Constants.PDF_TITLE, font));
			
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
			document.add(new Paragraph(Constants.PDF_PARAGRAPH,font2));
			
			//Cria uma tabela com tres campos
			PdfPTable table = new PdfPTable(3);
			this.addHeader(table);
			this.addRows(table);
			//Adciona a tabela criada no documento PDF
			document.add(table);
			//Fecha o documento
			document.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addRows(PdfPTable table){
		
		ArrayList<Student>studentsList = new ArrayList<Student>();
		XmlManipulation xmlManipulation = new XmlManipulation();
		
		String name = "";
		String sex = "";
		String age = "";
		
		//obtem os alunos salvos no arquivo XML
		studentsList = xmlManipulation.read();
		
		//varre o array vindo do arquivo xml
		for (int i = 0 ; i < studentsList.size() ; i++) {
			
			name = studentsList.get(i).getName();
			age = Integer.toString(studentsList.get(i).getAge());
			if(studentsList.get(i).isSex()){
				sex= "Man";
			}else{
				sex= "Woman";
			}
			//adciona os dados do aluno a tabela
			table.addCell(name);
			table.addCell(age);
			table.addCell(sex);
		}
	}
	
	private void addHeader (PdfPTable table){
		//método para adicionar o cabeçalho a tabela
		String name = "Name";
		String sex = "Sex";
		String age = "Age";
		//fonte para o cabeçalho
		Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
		font.setStyle(Font.BOLD);
		//adciona o cabeçalho na tabela
		table.addCell(name);
		table.addCell(age);
		table.addCell(sex);
	}
	
}

package control;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.PdfGenerator;
import model.Student;
import model.XmlManipulation;
/**
 * 
 * @author Paulo Pereira
 *
 */

public class TelaPrincipalControl {

	private XmlManipulation xmlManipulation = new XmlManipulation();
	private ArrayList<Student> studentsList = new ArrayList<Student>();

	public String registerButton (String name, int age, boolean sex){
		Student student = new Student(name, age, sex);
		ArrayList<Student> studentL = new ArrayList<Student>();
		String status="";
		if(xmlManipulation.read()!=null){
			//Se a lista não estiver nulo então
			studentL = xmlManipulation.read();
		}
		studentL.add(student);
		//reescreve o XML
		xmlManipulation.write(studentL);
		status = name + " was successfully registered";
		return status;
	}

	public String deletButton (String name){
		//mensagem defalt
		String status ="We can't find the student " + name;
		boolean strudentExist = false;
		studentsList.clear();
		studentsList = xmlManipulation.read();
		for(Student student : studentsList){
			if(name.equals(student.getName())){
				//se o aluno foi encontrado então delete-o
				studentsList.remove(student);
				//seta um novo status
				status = name + " was deleted successfully";
				strudentExist = true;
				break;
			}
		}
		if(strudentExist){
			//se o estudante existe reescreva o XML
			xmlManipulation.write(studentsList);
		}

		return status;
	}

	public JComboBox<String> updateComboBox (JComboBox<String> comboBox){
		//limpa o combobox corrente
		comboBox.removeAllItems();
		if(studentsList!=null){
			//para evitar nullpointer
			studentsList.clear();
		}
		//obtem uma lista de alunos vindo do arquivo XML
		studentsList = xmlManipulation.read();
		//popula o combobox com os valores vindo do arquivo XML
		if(studentsList!=null){
			for(Student student : studentsList){
				comboBox.addItem(student.getName());
			}
		}
		return comboBox;
	}

	public JTable updateJTable(){

		JTable table = new JTable();
		//cria uma linha com 3 colunas
		Object row []= new Object[3];
		//cria uma tabela
		DefaultTableModel dfTM = new DefaultTableModel(	
			//cabeçalho da tabela
			new Object[][] {
			},
			new String[] {
					"Name", "Age", "Sex"
			}
			);
		if(studentsList!=null){
			studentsList.clear();
			//popula a lista com valores vindo do XML
			studentsList = xmlManipulation.read();

			//varre os estudantes da lista
			for (int i = 0 ; i < studentsList.size() ; i++) {
				//adciona a coluna [i] um valor
				row[0] = studentsList.get(i).getName();
				row[1] = studentsList.get(i).getAge();
				if(studentsList.get(i).isSex()){
					row[2] = "Man";
				}else{
					row[2] = "Woman";
				}
				//adciona a linha a tabela
				dfTM.addRow(row);
			}
			
		}
		//seta tabela no JTable
		table.setModel(dfTM);
		return table;
	}

	public void generatePdfButton(){
		//tratamento do botão gerar pdf
		PdfGenerator pdfGenerator = new PdfGenerator();
		//gera o documento PDF
		pdfGenerator.generatePDF();
	}
}

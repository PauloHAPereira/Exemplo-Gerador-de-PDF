package model;

import java.io.File;
/**
 * 
 * @author Paulo Pereira
 *	Essa classe verifica se a pasta onde os arquivos serão salvos existem,
 *Caso não existam, ela cria a nova pasta.
 */

public class FolderManagement {
	
	public void verifyFolder(){
		//caminho onde a pasta esta
		String path = Constants.FILE_PATH + Constants.FOLDER_NAME;
		File folder = new File(path);
		if(!folder.exists()){
			//se o a pasta não, existe então crie uma
			folder.mkdir();
		}
	}
}

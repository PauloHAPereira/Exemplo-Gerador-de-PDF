package model;

import java.io.File;
/**
 * 
 * @author Paulo Pereira
 *	Essa classe verifica se a pasta onde os arquivos ser�o salvos existem,
 *Caso n�o existam, ela cria a nova pasta.
 */

public class FolderManagement {
	
	public void verifyFolder(){
		//caminho onde a pasta esta
		String path = Constants.FILE_PATH + Constants.FOLDER_NAME;
		File folder = new File(path);
		if(!folder.exists()){
			//se o a pasta n�o, existe ent�o crie uma
			folder.mkdir();
		}
	}
}

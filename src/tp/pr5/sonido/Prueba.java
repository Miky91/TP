package tp.pr5.sonido;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;import javax.sound.sampled.Clip;


public class Prueba {

	private Clip archivo_de_sonido;
	
	public Prueba(){
		try{
			archivo_de_sonido = AudioSystem.getClip();
			archivo_de_sonido.open(AudioSystem.getAudioInputStream(new File("The Super Mario Song.wav")));
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			//Retorna el mensaje de error
			}
	}

	public Clip getArchivo_de_sonido() {
		return archivo_de_sonido;
	}
}

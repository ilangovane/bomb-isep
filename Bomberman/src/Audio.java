 
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

private String path;
private Clip audioClip;
private AudioInputStream audioStream;

public Audio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	this.path = path;
	this.audioClip = audioClip;
	this.audioStream = audioStream;
	File audioFile = new File(path);
	audioStream = AudioSystem.getAudioInputStream(audioFile);
	AudioFormat format = audioStream.getFormat();
	DataLine.Info info = new DataLine.Info(Clip.class, format);
	audioClip = (Clip) AudioSystem.getLine(info);

}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public Clip getAudioClip() {
	return audioClip;
}

public void setAudioClip(Clip audioClip) {
	this.audioClip = audioClip;
}

public AudioInputStream getAudioStream() {
	return audioStream;
}

public void setAudioStream(AudioInputStream audioStream) {
	this.audioStream = audioStream;
}

public void start() throws LineUnavailableException, IOException{
	audioClip.open(audioStream);
	audioClip.start();
}

public void repeat(){
	audioClip.loop(Clip.LOOP_CONTINUOUSLY);
}

public void stop() throws LineUnavailableException, IOException{
	audioClip.close();
	audioStream.close();
}

}

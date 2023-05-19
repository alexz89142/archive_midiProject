import javax.sound.midi.*;
import java.util.ArrayList;
import java.io.File;
import java.io.File.*;
import java.io.IOException;
import java.util.Scanner;

public class MidiStart {

	public static void main(String[] args) throws InvalidMidiDataException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Note> notes=new ArrayList<Note>();
		readTrack(notes);
		
		Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			changeTrack(track,notes); //Calls makeEvent, only modifies track
			sequencer.setSequence(seq);
			sequencer.start();
		} catch (MidiUnavailableException e) {
			//do nothing
		}



	}
	public static MidiEvent makeEvent(int comd, Note note) {
		int which=note.getStart();
		if(comd==128)
			which=note.getEnd();
		
		
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, note.getInstrument(),note.getNote(),note.getVelocity());
			event = new MidiEvent(a, which);

		}catch(Exception e) { }
		return event;
	}
	
	public static void changeTrack(Track track, ArrayList<Note> notes) throws IOException{
		for(int x=0;x<notes.size();x++){
			track.add(makeEvent(144,notes.get(x)));
			track.add(makeEvent(128,notes.get(x)));
		}
	}
	public static void readTrack(ArrayList<Note> notes) throws IOException{
		int currentBeat=1;
		Scanner reader=new Scanner(new File("happyBirthdayStart.txt"));
		
		while(reader.hasNextLine()){
			String input=reader.nextLine();
			Scanner lineReader=new Scanner(input);
			lineReader.useDelimiter(",");
			
			//Creates note
			int instrument=lineReader.nextInt();
			int note=lineReader.nextInt();
			int velocity=lineReader.nextInt(); //Is velocity actually volume?
			int duration=lineReader.nextInt(); 
			Note cNote=new Note(instrument,note,velocity,duration,currentBeat);
			
			 if(instrument!=-1) //Does the OOP version create both on and off events in makeEvent?
				notes.add(cNote);
			
			currentBeat+=duration+1;  //increments beat by duration for a rest (instrument=-1)

		} 
	}
}




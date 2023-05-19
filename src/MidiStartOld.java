import javax.sound.midi.*;

import java.io.File;
import java.io.File.*;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class MidiStartOld{

	public static void main(String[] args) throws InvalidMidiDataException, IOException {
		// TODO Auto-generated method stub
		Sequencer sequencer;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			getTrack(track); //calls makeEvent
			
			sequencer.setSequence(seq);
			sequencer.start();
		} catch (MidiUnavailableException e) {
			//do nothing
		}



	}
	public static MidiEvent makeEvent(int comd, int chan, int note, int vel, int dur) {
		//System.out.println(comd+","+chan+","+note+","+vel+","+dur);
		
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, note, vel);
			event = new MidiEvent(a, dur);

		}catch(Exception e) { }
		return event;
	}
	
	
	public static void getTrack(Track track) throws IOException{
		int currentBeat=1;
		Scanner reader=new Scanner(new File("happyBirthdayStart.txt"));
		while(reader.hasNextLine()){
			String input=reader.nextLine();
			Scanner lineReader=new Scanner(input);
			lineReader.useDelimiter(",");
			int instrument=lineReader.nextInt();
			int note=lineReader.nextInt();
			int velocity=lineReader.nextInt();
			int duration=lineReader.nextInt();
			System.out.println(duration);
			
			if(instrument!=-1){ //If instrument==-1 do not add to track
				//create a note on event for the given instrument, note, and velocity at the current beat
				track.add(makeEvent(144, instrument, note, velocity, currentBeat)); 
				//create a note off event
				track.add(makeEvent(128,instrument,note,velocity, currentBeat+duration));
				//increment the current beat by the duration and add one for the next beat
				currentBeat+=duration+1;
			}
			else
				currentBeat+=duration+1;  //increments beat by duration for a rest (instrument=-1)

		} 
	}
}

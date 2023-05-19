
public class Note {
	private int instrument;
	private int note;
	private int velocity;
	private int duration;
	
	private int start;
	private int end;
	
	public Note(int instrument,int note,int velocity,int duration, int currentBeat){
		if(!setInstrument(instrument))
			this.instrument=0;
		if(!setNote(note))
			this.note=0;
		if(!setVelocity(velocity))
			this.velocity=0;
		if(!setDuration(duration))
		this.duration=0;
		
		if(!setStart(currentBeat))
			this.start=0;
		if(!setEnd(currentBeat+duration))
			this.end=1;
	}
	
	public boolean setInstrument(int instrument){
		if(instrument>=0 && instrument<=127){
			this.instrument=instrument;
			return true;
		}
		else
			return false;
	}
	public boolean setNote(int note){
		if(note>=0 && note<=127){
			this.note=note;
			return true;
		}
		else
			return false;
	}
	public boolean setVelocity(int velocity){
		if(velocity>=0 && velocity<=127){
			this.velocity=velocity;
			return true;
		}
		else
			return false;
	}
	public boolean setDuration(int duration){
		if(duration>=0){
			this.duration=duration;
			return true;
		}
		else
			return false;
	}
	public boolean setStart(int start){
		this.start=start;
		return true;
	}
	public boolean setEnd(int end){
		this.end=end;
		return true;
	}
	
	public int getInstrument(){
		return this.instrument;
	}
	public int getNote(){
		return this.note;
	}
	public int getVelocity(){
		return this.velocity;
	}
	public int getDuration(){
		return this.duration;
	}
	public int getStart(){
		return this.start;
	}
	public int getEnd(){
		return this.end;
	}
	
	public String toString(){
		return this.instrument+","+this.note+","+this.velocity+","+this.duration+"\n";
	}
}

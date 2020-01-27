package logic.entity;

public class Volume {
	private int volume;  // in cl
	
	public Volume() {
		this.volume = 0;
	}
	
	public Volume(int volume) {
		this.volume = volume;
	}

	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		if(volume < 100) {
			return String.valueOf(volume) + " cl";
		} else {
			return String.valueOf(((float) volume)/100f) + " l";
		}
	}
	
	public static String toText(int value) {
		Volume volume = new Volume(value);
		return volume.toString();
	}
}

package logic.entity;

public class Volume {
	private int volumeValue;  // in cl
	
	public Volume() {
		this.volumeValue = 0;
	}
	
	public Volume(int volume) {
		this.volumeValue = volume;
	}

	
	public int getVolume() {
		return volumeValue;
	}

	public void setVolume(int volume) {
		this.volumeValue = volume;
	}
	
	@Override
	public String toString() {
		if(Math.abs(volumeValue) < 100) {
			return String.valueOf(volumeValue) + " cl";
		} else {
			return String.valueOf(((float) volumeValue)/100f) + " l";
		}
	}
	
	public static String toText(int value) {
		Volume volume = new Volume(value);
		return volume.toString();
	}
}

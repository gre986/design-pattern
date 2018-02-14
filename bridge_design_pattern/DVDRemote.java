package bridge_design_pattern;

public class DVDRemote extends RemoteButton{

	public DVDRemote(EntertainmentDevice newDevice) {
		super(newDevice);
		
	}

	@Override
	public void buttonNinePressed() {
	
		System.out.println("DVD was open");
	}

}


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Record implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3533523515464626129L;
	private TimerSugus time;
	private int record;
	private String path = "record.txt";

	public Record() {
		time = new TimerSugus();
		record = 0;
		nhapFile();
	}

	public void nhapFile() {
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
				luuFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
				Record bingo = (Record) ois.readObject();
				record = bingo.getRecord();
				time = bingo.getTime();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public TimerSugus getTime() {
		return time;
	}

	public void setTime(TimerSugus time) {
		this.time = time;
	}

	public void luuFile() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record, TimerSugus time) {
		this.record = record;
		this.time = time;
		luuFile();
	}

}

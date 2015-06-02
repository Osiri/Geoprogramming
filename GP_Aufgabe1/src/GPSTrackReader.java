import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class GPSTrackReader {

	private String filename;
	
	public GPSTrackReader(String filename) {
		this.filename = filename;
	}
	
	public GPSPoint[] readGPSTrack() {
		
		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			StreamTokenizer st = new StreamTokenizer(br);

			List<GPSPoint> track = new ArrayList<GPSPoint>();
			
			st.ordinaryChar('/');
			
			// skip header
			readHeader(st);

			// read points until end of file
			while (st.nextToken() != StreamTokenizer.TT_EOF) {
				st.pushBack();
				
				GPSPoint p = readGPSPoint(st);
				track.add(p);
			}
			
			fr.close();
			br.close();
			
			GPSPoint[] pts = new GPSPoint[track.size()];
			pts = track.toArray(pts);
			return pts;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File " + filename + " not found.");
		}
		catch (IOException e)
		{
			System.out.println("An IO error occurred while reading " + filename + ".");
		}
		catch (ParseException e)
		{
			System.out.println("An unexpected parse error occurred while parsing " + filename + ".");
		}
		
		return null;
	}
	
	private void readHeader(StreamTokenizer st) throws IOException, ParseException {

		// Datum,WGS84,WGS84,0,0,0,0,0
		consumeToken(st, "Datum");
		consumeToken(st, ',');
		consumeToken(st, "WGS84");
		consumeToken(st, ',');
		consumeToken(st, "WGS84");
		
		for (int i = 0; i < 5; i++) {
			consumeToken(st, ',');
			consumeToken(st, 0.0);
		}
	}
	
	// reads a line from the GPS track and returns a point object (without date and time)
	private GPSPoint readGPSPoint(StreamTokenizer st) throws IOException, ParseException {

		// skip over "TP,D,"
		consumeToken(st, "TP");
		consumeToken(st, ',');
		consumeToken(st, "UTM");
		consumeToken(st, ',');
		consumeToken(st);
		consumeToken(st, "U");
		consumeToken(st, ',');
		
		// read coordinates
		double x = readNumber(st);
		consumeToken(st, ',');
		double y = readNumber(st);
		
		// read date and time
		GregorianCalendar gc = readDateAndTime(st);
		
		// skip rest of line
		consumeToken(st, ',');
		consumeToken(st);

		return new GPSPoint(x, y, gc);
	}
	
	// convenience method to read a number
	private double readNumber(StreamTokenizer st) throws IOException, ParseException {
		
		if (st.nextToken() != StreamTokenizer.TT_NUMBER)
			throw new ParseException("", 0);
		else
			return st.nval;
	}
	
	// convenience method to read the date and time
	private GregorianCalendar readDateAndTime(StreamTokenizer st) throws IOException, ParseException {
		
		consumeToken(st, ',');
		double month = readNumber(st); 
		consumeToken(st, '/');
		double date = readNumber(st);
		consumeToken(st, '/');
		double year = readNumber(st);
		consumeToken(st, ',');

		double hrs = readNumber(st);
		consumeToken(st, ':');
		double min = readNumber(st);
		consumeToken(st, ':');
		double sec = readNumber(st);
		
		return new GregorianCalendar((int) year, (int) month, (int) date, (int) hrs, (int) min, (int) sec);
	}
	
	// checks that the next token is a string equal to s and consumes it
	private void consumeToken(StreamTokenizer st, String s)  throws IOException, ParseException {
		if (st.nextToken() != StreamTokenizer.TT_WORD || !st.sval.equalsIgnoreCase(s))
			throw new ParseException("", 0);
	}
	
	// checks that the next token is a character equal to c and consumes it
	private void consumeToken(StreamTokenizer st, int c) throws IOException, ParseException {
		if (st.nextToken() != c)
			throw new ParseException("", 0);
	}
	
	// checks that the next token is a number equal to d and consumes it 
	private void consumeToken(StreamTokenizer st, double d)  throws IOException, ParseException {
		if (st.nextToken() != StreamTokenizer.TT_NUMBER || st.nval != d)
			throw new ParseException("", 0);
	}

	// consumes the next token
	private void consumeToken(StreamTokenizer st)  throws IOException, ParseException {
		st.nextToken();
	}
	
	// prints the next token and pushes it back afterwards (for debug purposes)
//	private void printNextToken(StreamTokenizer st) {
//		
//		try
//		{
//			st.nextToken();
//			
//			if (st.ttype == StreamTokenizer.TT_WORD)
//				System.out.println("Word Token: " + st.sval);
//			else if (st.ttype == StreamTokenizer.TT_NUMBER)
//				System.out.println("Number Token: " + st.nval);
//			else
//				System.out.println("Character Token: " + st.ttype);
//		}
//		catch (IOException ioe)
//		{
//			System.out.println("Exception");
//		}
//		
//	}
//	
}



import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 2167633403373016075L;

	private MainWindowPanel panel;
	private JMenuBar menuBar;

	private List<GPSTrack> gpsTracks = new ArrayList<GPSTrack>(); 					// Modell
	
	public MainWindow()
	{
		super ("Geoinformatik-Programmierung"); 									// Constructor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		
		panel = new MainWindowPanel(gpsTracks);										// Array vom typ gpstracks wird generiert
		add(panel);
		
		menuBar = new JMenuBar();					
		setJMenuBar(menuBar);

		ConstructActions();
	}

	private void ConstructActions()
	{
		Action openAction = new AbstractAction()
		{
			private static final long serialVersionUID = 1233967092555550642L;

			{
				putValue(Action.NAME, "Open");
			}

			public void actionPerformed(ActionEvent e)								// ruft Action open file auf
			{
				openFile();
			}
		};
		
		JMenu menu = new JMenu("File");
		menu.add(openAction);
		menuBar.add(menu);
	}

	private void openFile()															// open file
	{
		JFileChooser fc = new JFileChooser();										// j file chooser
		switch (fc.showOpenDialog(null))
		{
		case JFileChooser.APPROVE_OPTION:
			GPSTrackReader gps = new GPSTrackReader(fc.getSelectedFile().getAbsolutePath()); // file wird gelesen, am ende hat man objekt vom typ gpstrackreader
			GPSPoint[] pts = gps.readGPSTrack();
			gpsTracks.add(new GPSTrack(pts));
			panel.repaint();
			break;
		default:
			System.out.println("No file choosen");
		}
	}
	
}

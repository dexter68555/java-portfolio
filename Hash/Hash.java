import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.apache.commons.codec.digest.DigestUtils;

public class Hash{

	public static String md5 = "";
	public static String sha1 = "";
	public static String sha256 = "";
	
	public static void main(String[] args){
		
		int WINDOW_WIDTH = 500;
		int WINDOW_HEIGHT = 250;
		
		//Creates the JFrame and configures it.
		JFrame window = new JFrame("Hash Generator");
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new FlowLayout(FlowLayout.LEFT));
		window.setResizable(false);
		
		//Creates the JPanels
		JPanel panel = new JPanel();
		window.add(panel);
	
		JButton chooseFile = new JButton("Select a file");
		panel.add(chooseFile);
		
		//Creates the file chooser
		String userDir = System.getProperty("user.home");
		JFileChooser input = new JFileChooser(userDir +"/");
		Action details = input.getActionMap().get("viewTypeDetails");
		details.actionPerformed(null);
		
		// Creates the output display
		JTextPane finalText = new JTextPane();
		finalText.setBorder(BorderFactory.createEmptyBorder());
		finalText.setOpaque(false);
		finalText.setEditable(false);
		finalText.setPreferredSize( new Dimension( 300, 100 ) );
		finalText.setText("MD5: \nSHA1: \nSHA-256: ");
		panel.add(finalText);
		
		ImageIcon icon = new ImageIcon("icon.png");
        Image image = icon.getImage();
        window.setIconImage(image);
		window.setVisible(true);
		
		//Adds a listener to opens the file chooser.
		chooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent onClick) {
				
				//Opens the file chooser.
				int result = input.showOpenDialog(chooseFile);
				
				if (result == JFileChooser.FILES_ONLY) {
					
					try 
					{
						//Grab the selected file
						File finalFile = new File(input.getSelectedFile().getAbsolutePath());
						InputStream is = new FileInputStream(finalFile);
						InputStream is2 = new FileInputStream(finalFile);
						InputStream is3 = new FileInputStream(finalFile);

						//Generate checksum with the selected file
						md5 = DigestUtils.md5Hex(is);
						sha1 = DigestUtils.shaHex(is2);
						sha256 = DigestUtils.sha256Hex(is3);
						
						//Print all the hash
						finalText.setText("MD5: " + md5 + "\nSHA1: " + sha1 + "\nSHA-256: " + sha256);

					}
					catch(IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		});
	}
        
}

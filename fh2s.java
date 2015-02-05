/*
 *	Author: I-Sheng Lin	
 *	Date: 01/15/2015
 *
 * 	The purpose of this source code is used for converting the hackpad  to .srt.
 * 
 *  1. Remove html tags
 *  2. Remove comments
 *  3. Remove subtitles
 *  4. Well organized subtitle file
 *  5. The subtitle generator for hackpad
 */
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class fh2s extends JFrame {
	
	JTextField jtfText1;
	JButton jButton = new JButton();
	JLabel label1 = new JLabel("Url");
	String disp = "";
	buttonHandler handler = null;
	static String content = null;
	static URLConnection connection = null;
	
	/*class constructor to initialize some swing objects*/
	public fh2s(){
		super("Subtitle Generator");
		Container container = getContentPane();
		jtfText1 = new JTextField(30);
		jtfText1.setBounds(130, 5, 334, 19);
		handler = new buttonHandler();
		getContentPane().setLayout(null);
		label1.setBounds(100, 7, 20, 15);
		container.add(label1);
		container.add(jtfText1);
		jButton.setBounds(223, 64, 107, 25);
		container.add(jButton);
		jButton.setText("Generator");
		jButton.addActionListener(handler);
		setSize(500, 130);
		setVisible(true);
	}

	
	private class buttonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(jtfText1.getText().equals("")||!jtfText1.getText().startsWith("https://") || !jtfText1.getText().contains("hackpad")){
				JOptionPane.showMessageDialog(null, "Incorrect format");
				jtfText1.setText("");
				return;
			}
			
			try {
				  connection =  new URL(jtfText1.getText()).openConnection();
				  Scanner scanner = new Scanner(connection.getInputStream());
				  scanner.useDelimiter("\\Z");
				  content = scanner.next();
				  
				}catch ( Exception ex ) {
				    ex.printStackTrace();
				}
					
				String[] output = content.split("</p>");
				StringBuffer result = new StringBuffer();
				
				for(String content2:output){
					result.append(content2);
					result.append("\n");
				}
				
				
				String test = result.toString();
					   test = test.replace("&nbsp;",""); // Remove html entities
					   test = test.replace("&gt;","");
					   test = test.replace("&rsquo;","'");
					   
				String toSub = htmlRemover.delHTMLTag(test); //Remove html tags
				try {
					JOptionPane.showMessageDialog(null, " The file name must be the same as the video file name and "+"\n Both files must be in the same directory"+
				                                  "\n No File Extension needed");
					
					JFileChooser fs = new JFileChooser();
					int a = fs.showSaveDialog(getParent());
					if( a == JFileChooser.APPROVE_OPTION){
						srtConverter.log(toSub,fs.getSelectedFile().getCanonicalPath());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
	}
	
	

	public static void main(String args[]) throws IOException {
		
		fh2s swing = new fh2s();
		swing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	
}


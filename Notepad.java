import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class Notepad implements ActionListener{
	
	public static void main(String[] args) throws Exception {
	       try { 
	             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	        }catch (Exception e) {  
	             e.printStackTrace();  
	        }  
		new Notepad();
	
	}  
	
	JFileChooser file=new JFileChooser();
	StringBuilder sb=new StringBuilder();
	
	Font f=new Font("Arial",Font.PLAIN,30);
	
	JTextArea txtarea=new JTextArea();
	JScrollPane scroll=new JScrollPane(txtarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JSlider sl=new JSlider(0,100);
	
	JFrame jf=new JFrame();
	JMenuBar jmb=new JMenuBar();
	JMenu filemenu=new JMenu("File");
	JMenu Editmenu=new JMenu("Edit");
	JMenu Formatmenu=new JMenu("Format");
	JMenu Viewmenu=new JMenu("View");
	JMenu Helpmenu=new JMenu("Help");
	

	JMenuItem FNew=new JMenuItem("New");
	JMenuItem FOpen=new JMenuItem("Open");
	JMenuItem FSave=new JMenuItem("Save");
	JMenuItem FSaveAs=new JMenuItem("Save As");
	JMenuItem FPageSetup=new JMenuItem("Page Setup");
	JMenuItem FPrint=new JMenuItem("Print ctrl+P");
	JMenuItem FExit=new JMenuItem("Exit");
	
	JMenuItem eUndo=new JMenuItem("Undo");
	JMenuItem eCut=new JMenuItem("Cut");
	JMenuItem eCopy=new JMenuItem("Copy");
	JMenuItem ePaste=new JMenuItem("Paste");
	JMenuItem eDel=new JMenuItem("Delete");
	JMenuItem eFind=new JMenuItem("Find");
	JMenuItem eFindNext=new JMenuItem("Find Next");
	JMenuItem eReplace=new JMenuItem("Replace");
	JMenuItem eGoto=new JMenuItem("Go To");
	JMenuItem eSelectAll=new JMenuItem("Select All");
	JMenuItem eDate=new JMenuItem("Date/Time");
	
	JMenuItem forWordRap=new JMenuItem("Wrap Word");
	JMenuItem forFont=new JMenuItem("Font");
	
	JMenuItem vStatusBar=new JMenuItem("Status Bar");
	
	JMenuItem hViewHelp=new JMenuItem("View Help");
	JMenuItem hAbout=new JMenuItem("About NotePad");

	public Notepad()
	{
		
		// File Menu Items Adding
		FNew.addActionListener(this);
		filemenu.setMnemonic('M');
		FNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		filemenu.add(FNew);
		
		
		FOpen.addActionListener(this);
		FOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));

		filemenu.add(FOpen);
		
		FSave.addActionListener(this);		
		filemenu.add(FSave);
		
		FSaveAs.addActionListener(this);
		FSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		filemenu.add(FSaveAs);
		filemenu.add(FPageSetup);
		filemenu.add(FPrint);
		
		filemenu.add(FExit);
		
		//Edit Menu Items Adding
		Editmenu.add(eUndo);
		Editmenu.add(eCut);
		Editmenu.add(eCopy);
		Editmenu.add(ePaste);
		Editmenu.add(eDel);
		Editmenu.add(eFind);
		Editmenu.add(eFindNext);
		Editmenu.add(eReplace);
		Editmenu.add(eGoto);
		Editmenu.add(eSelectAll);
		Editmenu.add(eDate);
		
		// Format Menu Items Adding
		Formatmenu.add(forWordRap);
		Formatmenu.add(forFont);
		
		//View Menu Items Adding
		Viewmenu.add(vStatusBar);
		
		//Help Menu Items Adding
		Helpmenu.add(hViewHelp);
		Helpmenu.add(hAbout);
		
		txtarea.setFont(f);
		txtarea.setLineWrap(true);
		txtarea.setWrapStyleWord(true);
		txtarea.setMargin(null);
	
		
		scroll.setSize(795,495);

		jmb.add(filemenu);
		jmb.add(Editmenu);
		jmb.add(Formatmenu);
		jmb.add(Viewmenu);
		jmb.add(Helpmenu);
		
		jf.add(sl);
		jf.add(scroll);
		jf.setJMenuBar(jmb);
		jf.setTitle("NotePad");
		jf.setSize(800,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("the event is "+e);
		if(e.getSource().equals(FNew) )
		{
			txtarea.append("hello");
			//JOptionPane.showMessageDialog(null, "My New is Working");
		
			try {
				Notepad.main(new String[0]);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		else if(e.getSource().equals(FOpen)) {
		try {
			pickme();
		}
		catch(Exception e2) {
			System.out.println("file not found"+e2.getMessage());
		}
		txtarea.setText(sb.toString());
		}
		else if(e.getSource().equals(FSaveAs)) {
				JFileChooser fc=new JFileChooser();
				fc.setDialogTitle("Save file");
				int res=fc.showSaveDialog(null);
				if(res==JFileChooser.APPROVE_OPTION) {
					File f= fc.getSelectedFile();
					FileOutputStream out;
					try {
						out = new FileOutputStream(f);
						out.write(txtarea.getText().getBytes());
						out.flush();
						out.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				
				}
	
	}
}

	public void pickme() throws FileNotFoundException {
			if(file.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File f= file.getSelectedFile();
				Scanner obj=new Scanner(f);
				while(obj.hasNext()) {
					sb.append(obj.nextLine());
					sb.append("\n");
				}
				obj.close();
				
			}
			else {
				sb.append("no file was selected");
			}
	}
}


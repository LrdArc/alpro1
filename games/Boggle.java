import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JApplet.*;

public class Boggle extends JApplet implements Runnable, ActionListener{
	JPanel panel_1; 
	JButton button[], newgame, exit;
	JTextField textfield;
	private final static int SIZE=25;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private JCheckBox checkbox; 
	private Thread threads[];
	private boolean suspended; 

	public static void main(String[] args) {
		new Boggle();
	}

	public Boggle(){
		Container c = getContentPane();
		c.setLayout(null);
		panel_1 = new JPanel(); 
		panel_1.setLayout(new GridLayout(5,5,2,2));	

		threads = new Thread[SIZE];

		newgame = new JButton("New Game");
		newgame.addActionListener(this); 

		exit = new JButton("Exit");
		// exit.addActionListener(this);	

		textfield = new JTextField(10);
		button = new JButton[ SIZE ];

		for( int i=0; i<SIZE; i++){
			button[i] = new JButton("-");
			button[i].setFont(new Font("Arial", Font.BOLD, 20));
			button[i].setBackground(Color.orange);
			button[i].setOpaque(true);
			panel_1.add(button[i]);
		} 

		checkbox = new JCheckBox("suspended");
		checkbox.addActionListener(this);
		c.add(checkbox);

		c.add(panel_1);
		c.add(textfield);
		c.add(newgame); 
		c.add(exit);

		panel_1.setBounds(30,50,300,300);
		textfield.setBounds(30,365,90,30);	
		newgame.setBounds(125,365,100,30);
		exit.setBounds(230,365,100,30);
		checkbox.setBounds(30,400,90,30);

	}

	public void run(){
		Thread currentThread = Thread.currentThread();
		int index = getIndex(currentThread);
		char displayChar;

		while (threads[index] == currentThread ){
			try{
				Thread.sleep( (int) (Math.random() * 1000));
				synchronized(this){
					while( suspended && threads[ index ] == currentThread) wait();
				}
			} catch ( InterruptedException e){
				System.err.println("sleep interrupted");
			}

			displayChar = alphabet.charAt((int) (Math.random() * 26));
			button[index].setText(""+displayChar);
		}

		System.err.println("terminating");
	}

	private int getIndex(Thread current){
		for( int i=0; i<threads.length; i++) if( current == threads[i]) return i;
		return -1;
	}

	public synchronized void stop(){
		for( int i=0; i< threads.length; i++) threads[i] = null;
		notifyAll();
	}

	public synchronized void actionPerformed(ActionEvent e){
		if(e.getSource()==newgame){
			for ( int i=0; i<threads.length;i++){
				threads[i]= new Thread(this);
				threads[i].start();
			}
		}

		if(e.getSource()==checkbox){
			suspended=!suspended;
			if(!suspended) notify();
			return;
		}
	} 
}

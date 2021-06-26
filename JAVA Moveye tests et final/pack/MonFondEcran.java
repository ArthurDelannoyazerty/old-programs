package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollBar;

public class MonFondEcran extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonFondEcran frame = new MonFondEcran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MonFondEcran() {
		//création d'un groupe de bouton
		ButtonGroup groupeDeBoutons = new ButtonGroup();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JRadioButton rdbtnBleu = new JRadioButton("bleu");
		final JRadioButton rdbtnRouge = new JRadioButton("rouge");
		final JRadioButton rdbtnVert = new JRadioButton("vert");
		rdbtnBleu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.BLUE);
				rdbtnBleu.setBackground(Color.BLUE);
				rdbtnVert.setBackground(Color.BLUE);
				rdbtnRouge.setBackground(Color.BLUE);
			}
		});
		rdbtnBleu.setBounds(80, 64, 109, 23);
		contentPane.add(rdbtnBleu);
		groupeDeBoutons.add(rdbtnBleu);
		
		
		
		
		rdbtnRouge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.RED);
				rdbtnBleu.setBackground(Color.RED);
				rdbtnVert.setBackground(Color.RED);
				rdbtnRouge.setBackground(Color.RED);
			}
		});
		rdbtnRouge.setBounds(80, 110, 109, 23);
		contentPane.add(rdbtnRouge);
		groupeDeBoutons.add(rdbtnRouge);
		
		
		
		
		rdbtnVert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.setBackground(Color.GREEN);
				rdbtnBleu.setBackground(Color.GREEN);
				rdbtnVert.setBackground(Color.GREEN);
				rdbtnRouge.setBackground(Color.GREEN);
			}
		});
		rdbtnVert.setBounds(80, 160, 109, 23);
		contentPane.add(rdbtnVert);
		groupeDeBoutons.add(rdbtnVert);
	}
}

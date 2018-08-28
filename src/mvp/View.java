package mvp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import properties.Messages;

public class View {

	private JFrame frame;
	private JTextField tfUntranslatedWord;
	private JRadioButton rdbtnGerArticleDer;
	private JRadioButton rdbtnGerArticleDie;
	private JRadioButton rdbtnGerArticleDas;
	private JRadioButton rdbtnTranslation1;
	private JRadioButton rdbtnTranslation2;
	private JRadioButton rdbtnTranslation3;
	private JRadioButton rdbtnTranslation4;
	private JRadioButton rdbtnTranslation5;
	private ButtonGroup btngrpGerArticles;
	private ButtonGroup btngrpTranslations;
	private JComboBox<String> cmTranslationDirection;
	private JButton btnLoadNewDictionaryElement;
	private JButton btnSelectDictionary;
	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel articlesPanel;
	private JLabel lblGerWord;

	private Presenter presenter;

	public View() {
		initialize();
		groupArticleRadioButtons();
		groupTranslationRadioButtons();
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void initialize() {
		
		// TOP PANEL
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		lblGerWord = new JLabel(Messages.getMessage(this.getClass(), "lblGerWord"));
		lblGerWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		topPanel.add(lblGerWord);
		
		tfUntranslatedWord = new JTextField();
		tfUntranslatedWord.setEnabled(false);
		tfUntranslatedWord.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfUntranslatedWord.setBackground(new Color(240, 240, 240));
		tfUntranslatedWord.setColumns(20);
		topPanel.add(tfUntranslatedWord);
		
		btnLoadNewDictionaryElement = new JButton(Messages.getMessage(this.getClass(), "btnLoadNewDictionaryElement"));
		btnLoadNewDictionaryElement.setEnabled(false);
		btnLoadNewDictionaryElement.setMnemonic(KeyEvent.VK_ENTER);
		btnLoadNewDictionaryElement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLoadNewDictionaryElementActionPerformed();
			}
		});
		topPanel.add(btnLoadNewDictionaryElement);
		
		// LEFT PANEL
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		TitledBorder leftPanelBorder = new TitledBorder(
				BorderFactory.createEmptyBorder(),
				Messages.getMessage(this.getClass(), "lblArticle"),
				TitledBorder.CENTER,
				TitledBorder.BELOW_TOP);
		leftPanelBorder.setTitleFont(new Font("Tahoma", Font.PLAIN, 16));
		leftPanel.setBorder(leftPanelBorder);
		
		leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		articlesPanel = new JPanel();
		articlesPanel.setLayout(new BoxLayout(articlesPanel, BoxLayout.PAGE_AXIS));
		articlesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(articlesPanel);
		
		rdbtnGerArticleDer = new JRadioButton(Model.DER);
		rdbtnGerArticleDer.setEnabled(false);
		rdbtnGerArticleDer.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDer.setActionCommand(Model.DER);
		rdbtnGerArticleDer.addActionListener(new MyRDBTGerArticleActionListener());
		rdbtnGerArticleDer.setAlignmentX(Component.LEFT_ALIGNMENT);
		articlesPanel.add(rdbtnGerArticleDer);
		
		articlesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rdbtnGerArticleDie = new JRadioButton(Model.DIE);
		rdbtnGerArticleDie.setEnabled(false);
		rdbtnGerArticleDie.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDie.setActionCommand(Model.DIE);
		rdbtnGerArticleDie.addActionListener(new MyRDBTGerArticleActionListener());
		rdbtnGerArticleDie.setAlignmentX(Component.LEFT_ALIGNMENT);
		articlesPanel.add(rdbtnGerArticleDie);
		
		articlesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rdbtnGerArticleDas = new JRadioButton(Model.DAS);
		rdbtnGerArticleDas.setEnabled(false);
		rdbtnGerArticleDas.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDas.setActionCommand(Model.DAS);
		rdbtnGerArticleDas.addActionListener(new MyRDBTGerArticleActionListener());
		rdbtnGerArticleDas.setAlignmentX(Component.LEFT_ALIGNMENT);
		articlesPanel.add(rdbtnGerArticleDas);
		
		leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		leftPanel.add(Box.createVerticalGlue());

		btnSelectDictionary = new JButton(Messages.getMessage(this.getClass(), "btnSelectDictionary"));
		btnSelectDictionary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectDictionaryActionPerformed();
			}
		});
		btnSelectDictionary.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(btnSelectDictionary);
		
		leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		cmTranslationDirection = new JComboBox<String>();
		cmTranslationDirection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmTranslationDirection.addItem(Model.FROM_GERMAN);
		cmTranslationDirection.addItem(Model.TO_GERMAN);
		cmTranslationDirection.setSelectedItem(Model.FROM_GERMAN);
		cmTranslationDirection.setAlignmentX(Component.CENTER_ALIGNMENT);
		cmTranslationDirection.setMaximumSize(new Dimension(Integer.MAX_VALUE, cmTranslationDirection.getMinimumSize().height));
		leftPanel.add(cmTranslationDirection);
		
		// RIGHT PANEL
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		TitledBorder rightPanelBorder = new TitledBorder(
				BorderFactory.createEmptyBorder(),
				Messages.getMessage(this.getClass(), "lblTranslation"),
				TitledBorder.CENTER,
				TitledBorder.BELOW_TOP);
		rightPanelBorder.setTitleFont(new Font("Tahoma", Font.PLAIN, 16));
		rightPanel.setBorder(rightPanelBorder);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		rdbtnTranslation1 = new JRadioButton(" ");
		rdbtnTranslation1.setEnabled(false);
		rdbtnTranslation1.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation1.addActionListener(new MyRDBTTranslationActionListener());
		rightPanel.add(rdbtnTranslation1);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rdbtnTranslation2 = new JRadioButton(" ");
		rdbtnTranslation2.setEnabled(false);
		rdbtnTranslation2.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation2.addActionListener(new MyRDBTTranslationActionListener());
		rightPanel.add(rdbtnTranslation2);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rdbtnTranslation3 = new JRadioButton(" ");
		rdbtnTranslation3.setEnabled(false);
		rdbtnTranslation3.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation3.addActionListener(new MyRDBTTranslationActionListener());
		rightPanel.add(rdbtnTranslation3);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rdbtnTranslation4 = new JRadioButton(" ");
		rdbtnTranslation4.setEnabled(false);
		rdbtnTranslation4.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation4.addActionListener(new MyRDBTTranslationActionListener());
		rightPanel.add(rdbtnTranslation4);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		rdbtnTranslation5 = new JRadioButton(" ");
		rdbtnTranslation5.setEnabled(false);
		rdbtnTranslation5.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation5.addActionListener(new MyRDBTTranslationActionListener());
		rightPanel.add(rdbtnTranslation5);
		
		rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		// FRAME
		
		frame = new JFrame();
		frame.setTitle("GERDict");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(30, 10));
		frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(leftPanel, BorderLayout.LINE_START);
		frame.getContentPane().add(rightPanel, BorderLayout.CENTER);
		frame.pack();
	}

	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}

	private void groupArticleRadioButtons() {
		btngrpGerArticles = new ButtonGroup();
		btngrpGerArticles.add(rdbtnGerArticleDer);
		btngrpGerArticles.add(rdbtnGerArticleDie);
		btngrpGerArticles.add(rdbtnGerArticleDas);
	}

	private void groupTranslationRadioButtons() {
		btngrpTranslations = new ButtonGroup();
		btngrpTranslations.add(rdbtnTranslation1);
		btngrpTranslations.add(rdbtnTranslation2);
		btngrpTranslations.add(rdbtnTranslation3);
		btngrpTranslations.add(rdbtnTranslation4);
		btngrpTranslations.add(rdbtnTranslation5);
	}

	public void loadDictionaryElement() {
		tfUntranslatedWord.setEnabled(true);
		tfUntranslatedWord.setText(getPresenter().getModel().getUntranslatedWord());
		for (AbstractButton abstractButton : Collections.list(btngrpGerArticles.getElements())) {
			JRadioButton rdbtnArticle = (JRadioButton) abstractButton;
			rdbtnArticle.setBackground(new Color(240, 240, 240));
			if (getPresenter().getModel().getCorrectGerArticle() == null) {
				rdbtnArticle.setEnabled(false);
			} else {
				rdbtnArticle.setEnabled(true);
			}
		}
		btngrpGerArticles.clearSelection();
		int i = 0;
		List<String> possibleTranslations = new ArrayList<String>(getPresenter().getModel().getPossibleTranslations());
		Collections.shuffle(possibleTranslations, new Random(System.nanoTime()));
		for (AbstractButton abstractButton: Collections.list(btngrpTranslations.getElements())) {
			JRadioButton rdbtnTranslation = (JRadioButton) abstractButton;
			rdbtnTranslation.setEnabled(true);
			rdbtnTranslation.setBackground(new Color(240, 240, 240));
			rdbtnTranslation.setText(possibleTranslations.get(i));
			rdbtnTranslation.setActionCommand(possibleTranslations.get(i));
			i++;
		}
		btngrpTranslations.clearSelection();
		btnLoadNewDictionaryElement.setEnabled(true);
	}
	
	private void highLightArticles() {
		for (AbstractButton abstractButton : Collections.list(btngrpGerArticles.getElements())) {
			JRadioButton rdbtnAnswer = (JRadioButton) abstractButton;
			if (rdbtnAnswer.getActionCommand().equals(getPresenter().getModel().getCorrectGerArticle())) {
				rdbtnAnswer.setBackground(Color.GREEN);
			} else {
				if (rdbtnAnswer.isSelected()) {
					rdbtnAnswer.setBackground(Color.RED);
				} else {
					rdbtnAnswer.setBackground(new Color(240, 240, 240));
				}
			}
		}
	}

	private void highlightTranslations() {
		for (AbstractButton abstractButton : Collections.list(btngrpTranslations.getElements())) {
			JRadioButton rdbtnTranslation = (JRadioButton) abstractButton;
			if (rdbtnTranslation.getActionCommand().equals(getPresenter().getModel().getCorrectTranslation())) {
				rdbtnTranslation.setBackground(Color.GREEN);
			} else {
				if (rdbtnTranslation.isSelected()) {
					rdbtnTranslation.setBackground(Color.RED);
				} else {
					rdbtnTranslation.setBackground(new Color(240, 240, 240));
				}
			}
		}
	}

	private void btnLoadNewDictionaryElementActionPerformed() {
		getPresenter().getModel().setTranslationDirection((String) cmTranslationDirection.getSelectedItem());
		getPresenter().loadNewDictionaryElement();
	}

	private void btnSelectDictionaryActionPerformed() {
		getPresenter().getModel().setTranslationDirection((String) cmTranslationDirection.getSelectedItem());
		getPresenter().loadDictionary();
	}

	private class MyRDBTGerArticleActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getPresenter().getModel().setSelectedGerArticle(btngrpGerArticles.getSelection().getActionCommand());
			highLightArticles();
		}
	}

	private class MyRDBTTranslationActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getPresenter().getModel().setSelectedTranslation(btngrpTranslations.getSelection().getActionCommand());
			highlightTranslations();
		}
	}
}
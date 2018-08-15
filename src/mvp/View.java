package mvp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

	private Presenter presenter;

	private static final Color REGULAR_GRAY = new Color(240, 240, 240);

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
		frame = new JFrame();
		frame.setBounds(100, 100, 565, 350);
		frame.setTitle("GERDict");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblGerWord = new JLabel(Messages.getMessage(this.getClass(), "lblGerWord"));
		lblGerWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGerWord.setBounds(26, 23, 73, 25);
		frame.getContentPane().add(lblGerWord);

		tfUntranslatedWord = new JTextField();
		tfUntranslatedWord.setEnabled(false);
		tfUntranslatedWord.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfUntranslatedWord.setBounds(98, 23, 322, 25);
		tfUntranslatedWord.setBackground(REGULAR_GRAY);
		frame.getContentPane().add(tfUntranslatedWord);
		tfUntranslatedWord.setColumns(20);

		JLabel lblArticle = new JLabel(Messages.getMessage(this.getClass(), "lblArticle"));
		lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblArticle.setBounds(63, 69, 56, 16);
		frame.getContentPane().add(lblArticle);

		JLabel lblTranslation = new JLabel(Messages.getMessage(this.getClass(), "lblTranslation"));
		lblTranslation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTranslation.setBounds(320, 69, 83, 16);
		frame.getContentPane().add(lblTranslation);

		JPanel pnlArticles = new JPanel();
		pnlArticles.setBounds(36, 98, 83, 104);
		frame.getContentPane().add(pnlArticles);
		pnlArticles.setLayout(null);

		rdbtnGerArticleDer = new JRadioButton(Model.DER);
		rdbtnGerArticleDer.setEnabled(false);
		rdbtnGerArticleDer.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDer.setBounds(15, 5, 60, 25);
		rdbtnGerArticleDer.setActionCommand(Model.DER);
		rdbtnGerArticleDer.addActionListener(new MyRDBTGerArticleActionListener());
		pnlArticles.add(rdbtnGerArticleDer);

		rdbtnGerArticleDie = new JRadioButton(Model.DIE);
		rdbtnGerArticleDie.setEnabled(false);
		rdbtnGerArticleDie.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDie.setBounds(15, 35, 60, 25);
		rdbtnGerArticleDie.setActionCommand(Model.DIE);
		rdbtnGerArticleDie.addActionListener(new MyRDBTGerArticleActionListener());
		pnlArticles.add(rdbtnGerArticleDie);

		rdbtnGerArticleDas = new JRadioButton(Model.DAS);
		rdbtnGerArticleDas.setEnabled(false);
		rdbtnGerArticleDas.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnGerArticleDas.setBounds(15, 65, 60, 25);
		rdbtnGerArticleDas.setActionCommand(Model.DAS);
		rdbtnGerArticleDas.addActionListener(new MyRDBTGerArticleActionListener());
		pnlArticles.add(rdbtnGerArticleDas);

		btnLoadNewDictionaryElement = new JButton(Messages.getMessage(this.getClass(), "btnLoadNewDictionaryElement"));
		btnLoadNewDictionaryElement.setEnabled(false);
		btnLoadNewDictionaryElement.setBounds(432, 24, 97, 25);
		btnLoadNewDictionaryElement.setMnemonic(KeyEvent.VK_ENTER);
		btnLoadNewDictionaryElement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLoadNewDictionaryElementActionPerformed();
			}
		});
		frame.getContentPane().add(btnLoadNewDictionaryElement);

		JPanel pnlTranslationSelection = new JPanel();
		pnlTranslationSelection.setBounds(182, 82, 367, 213);
		frame.getContentPane().add(pnlTranslationSelection);
		pnlTranslationSelection.setLayout(null);

		rdbtnTranslation1 = new JRadioButton("");
		rdbtnTranslation1.setEnabled(false);
		rdbtnTranslation1.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation1.setBounds(8, 19, 351, 35);
		rdbtnTranslation1.addActionListener(new MyRDBTTranslationActionListener());
		pnlTranslationSelection.add(rdbtnTranslation1);

		rdbtnTranslation2 = new JRadioButton("");
		rdbtnTranslation2.setEnabled(false);
		rdbtnTranslation2.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation2.setBounds(8, 57, 351, 35);
		rdbtnTranslation2.addActionListener(new MyRDBTTranslationActionListener());
		pnlTranslationSelection.add(rdbtnTranslation2);

		rdbtnTranslation3 = new JRadioButton("");
		rdbtnTranslation3.setEnabled(false);
		rdbtnTranslation3.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation3.setBounds(8, 95, 351, 35);
		rdbtnTranslation3.addActionListener(new MyRDBTTranslationActionListener());
		pnlTranslationSelection.add(rdbtnTranslation3);

		rdbtnTranslation4 = new JRadioButton("");
		rdbtnTranslation4.setEnabled(false);
		rdbtnTranslation4.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation4.setBounds(8, 133, 351, 35);
		rdbtnTranslation4.addActionListener(new MyRDBTTranslationActionListener());
		pnlTranslationSelection.add(rdbtnTranslation4);

		rdbtnTranslation5 = new JRadioButton("");
		rdbtnTranslation5.setEnabled(false);
		rdbtnTranslation5.setFont(new Font("Tahoma", Font.BOLD, 18));
		rdbtnTranslation5.setBounds(8, 171, 351, 35);
		rdbtnTranslation5.addActionListener(new MyRDBTTranslationActionListener());
		pnlTranslationSelection.add(rdbtnTranslation5);

		btnSelectDictionary = new JButton(Messages.getMessage(this.getClass(), "btnSelectDictionary"));
		btnSelectDictionary.setBounds(12, 211, 139, 49);
		btnSelectDictionary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectDictionaryActionPerformed();
			}
		});
		frame.getContentPane().add(btnSelectDictionary);

		cmTranslationDirection = new JComboBox<String>();
		cmTranslationDirection.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmTranslationDirection.setBounds(12, 273, 139, 32);
		cmTranslationDirection.addItem(Model.FROM_GERMAN);
		cmTranslationDirection.addItem(Model.TO_GERMAN);
		cmTranslationDirection.setSelectedItem(Model.FROM_GERMAN);
		frame.getContentPane().add(cmTranslationDirection);
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
			rdbtnArticle.setBackground(REGULAR_GRAY);
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
			rdbtnTranslation.setBackground(REGULAR_GRAY);
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
					rdbtnAnswer.setBackground(REGULAR_GRAY);
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
					rdbtnTranslation.setBackground(REGULAR_GRAY);
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
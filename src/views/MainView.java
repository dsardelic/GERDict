package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import dict.Dictionary;
import presenters.MainPresenter;
import presenters.Presenter;
import properties.Messages;

public class MainView extends JFrame implements View {

    private static final long serialVersionUID = -4144132040991912402L;

    private final Color rdbtnColor = new Color(230, 230, 230);

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem miOpenDictionary;
    private JMenuItem miExit;
    private JMenu menuDirection;
    private ButtonGroup btngrpTranslationDirections;
    private JRadioButtonMenuItem rbmiDirectionFromGerman;
    private JRadioButtonMenuItem rbmiDirectionToGerman;
    private JMenu menuStats;
    private JMenuItem miShowStats;
    private JPanel topPanel;
    private JLabel lblWordToTranslate;
    private JTextField tfWordToTranslate;
    private JButton btnLoadNewQuestion;
    private JPanel bottomPanel;
    private JLabel lblArticle;
    private ButtonGroup btngrpArticles;
    private JRadioButton rdbtnArticleDer;
    private JRadioButton rdbtnArticleDie;
    private JRadioButton rdbtnArticleDas;
    private JLabel lblTranslation;
    private ButtonGroup btngrpTranslations;
    private JRadioButton rdbtnTranslation1;
    private JRadioButton rdbtnTranslation2;
    private JRadioButton rdbtnTranslation3;
    private JRadioButton rdbtnTranslation5;
    private JRadioButton rdbtnTranslation4;
    private ActionListener updateStatsListener;

    private MainPresenter presenter;

    public MainView() {
        initializeFrame();
        createRadioButtonGroups();
        setInputEnabled(false);
    }

    private void initializeFrame() {
        menuBar = new JMenuBar();
        menuFile = new JMenu(Messages.getMessage(this.getClass(), "menuFile"));
        miOpenDictionary = new JMenuItem(Messages.getMessage(this.getClass(), "miOpenDictionary"));
        miExit = new JMenuItem(Messages.getMessage(this.getClass(), "miExit"));
        menuDirection = new JMenu(Messages.getMessage(this.getClass(), "menuDirection"));
        btngrpTranslationDirections = new ButtonGroup();
        rbmiDirectionFromGerman = new JRadioButtonMenuItem(
                Messages.getMessage(this.getClass(), "rbmiDirectionFromGerman"));
        rbmiDirectionToGerman = new JRadioButtonMenuItem(Messages.getMessage(this.getClass(), "rbmiDirectionToGerman"));
        menuStats = new JMenu(Messages.getMessage(this.getClass(), "menuStats"));
        miShowStats = new JMenuItem(Messages.getMessage(this.getClass(), "miShowStats"));
        topPanel = new JPanel();
        lblWordToTranslate = new JLabel(Messages.getMessage(this.getClass(), "lblWordToTranslate"));
        tfWordToTranslate = new JTextField();
        btnLoadNewQuestion = new JButton(Messages.getMessage(this.getClass(), "btnLoadNewQuestion"));
        bottomPanel = new JPanel();
        lblArticle = new JLabel(Messages.getMessage(this.getClass(), "lblArticle"));
        btngrpArticles = new ButtonGroup();
        rdbtnArticleDer = new JRadioButton(Dictionary.DER);
        rdbtnArticleDie = new JRadioButton(Dictionary.DIE);
        rdbtnArticleDas = new JRadioButton(Dictionary.DAS);
        lblTranslation = new JLabel(Messages.getMessage(this.getClass(), "lblTranslation"));
        btngrpTranslations = new ButtonGroup();
        rdbtnTranslation1 = new JRadioButton(" ");
        rdbtnTranslation2 = new JRadioButton(" ");
        rdbtnTranslation3 = new JRadioButton(" ");
        rdbtnTranslation5 = new JRadioButton(" ");
        rdbtnTranslation4 = new JRadioButton(" ");
        updateStatsListener = new UpdateStatsListener();

        ActionListener highlightArticlesListener = new HighlightArticlesListener();
        ActionListener highlightTranslationsListener = new HighlightTranslationsListener();
        MouseListener myMouseListener = new MyMouseListener();

        // MENU 'FILE'

        menuBar.add(menuFile);

        miOpenDictionary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        miOpenDictionary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOpenDictionaryActionPerformed();
            }
        });
        menuFile.add(miOpenDictionary);

        menuFile.addSeparator();

        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuFile.add(miExit);

        // MENU 'DIRECTION'

        menuBar.add(menuDirection);

        rbmiDirectionFromGerman.setActionCommand("true");
        rbmiDirectionFromGerman.setSelected(true);
        menuDirection.add(rbmiDirectionFromGerman);

        rbmiDirectionToGerman.setActionCommand("false");
        menuDirection.add(rbmiDirectionToGerman);

        // MENU 'STATISTICS'

        menuBar.add(menuStats);

        miShowStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miShowStatsActionPerformed();
            }
        });
        menuStats.add(miShowStats);

        // TOP PANEL

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(lblWordToTranslate);
        topPanel.add(tfWordToTranslate);
        topPanel.add(btnLoadNewQuestion);

        lblWordToTranslate.setFont(new Font("Tahoma", Font.PLAIN, 16));

        tfWordToTranslate.setFont(new Font("Tahoma", Font.BOLD, 20));
        tfWordToTranslate.setBackground(rdbtnColor);
        tfWordToTranslate.setColumns(22);

        btnLoadNewQuestion.setMnemonic(KeyEvent.VK_ENTER);
        btnLoadNewQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoadNewQuestionActionPerformed();
            }
        });

        // BOTTOM PANEL

        GridBagLayout gbl_bottomPanel = new GridBagLayout();
        gbl_bottomPanel.columnWidths = new int[] { 0, 460, 0 };
        gbl_bottomPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl_bottomPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_bottomPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        bottomPanel.setLayout(gbl_bottomPanel);

        GridBagConstraints gbc_lblArticle = new GridBagConstraints();
        gbc_lblArticle.insets = new Insets(5, 15, 5, 15);
        gbc_lblArticle.gridx = 0;
        gbc_lblArticle.gridy = 0;
        lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblArticle.setVerticalAlignment(SwingConstants.TOP);
        lblArticle.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lblArticle, gbc_lblArticle);

        GridBagConstraints gbc_rdbtnArticleDer = new GridBagConstraints();
        gbc_rdbtnArticleDer.anchor = GridBagConstraints.WEST;
        gbc_rdbtnArticleDer.insets = new Insets(0, 15, 5, 25);
        gbc_rdbtnArticleDer.gridx = 0;
        gbc_rdbtnArticleDer.gridy = 1;
        rdbtnArticleDer.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnArticleDer.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnArticleDer.setActionCommand(Dictionary.DER);
        rdbtnArticleDer.addActionListener(highlightArticlesListener);
        rdbtnArticleDer.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnArticleDer, gbc_rdbtnArticleDer);

        GridBagConstraints gbc_rdbtnArticleDie = new GridBagConstraints();
        gbc_rdbtnArticleDie.anchor = GridBagConstraints.WEST;
        gbc_rdbtnArticleDie.insets = new Insets(0, 15, 5, 25);
        gbc_rdbtnArticleDie.gridx = 0;
        gbc_rdbtnArticleDie.gridy = 2;
        rdbtnArticleDie.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnArticleDie.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnArticleDie.setActionCommand(Dictionary.DIE);
        rdbtnArticleDie.addActionListener(highlightArticlesListener);
        rdbtnArticleDie.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnArticleDie, gbc_rdbtnArticleDie);

        GridBagConstraints gbc_rdbtnArticleDas = new GridBagConstraints();
        gbc_rdbtnArticleDas.anchor = GridBagConstraints.WEST;
        gbc_rdbtnArticleDas.insets = new Insets(0, 15, 5, 25);
        gbc_rdbtnArticleDas.gridx = 0;
        gbc_rdbtnArticleDas.gridy = 3;
        rdbtnArticleDas.setHorizontalAlignment(SwingConstants.LEFT);
        rdbtnArticleDas.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnArticleDas.setActionCommand(Dictionary.DAS);
        rdbtnArticleDas.addActionListener(highlightArticlesListener);
        rdbtnArticleDas.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnArticleDas, gbc_rdbtnArticleDas);

        GridBagConstraints gbc_lblTranslation = new GridBagConstraints();
        gbc_lblTranslation.insets = new Insets(5, 0, 5, 0);
        gbc_lblTranslation.gridx = 1;
        gbc_lblTranslation.gridy = 0;
        lblTranslation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTranslation.setVerticalAlignment(SwingConstants.TOP);
        lblTranslation.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lblTranslation, gbc_lblTranslation);

        GridBagConstraints gbc_rdbtnTranslation1 = new GridBagConstraints();
        gbc_rdbtnTranslation1.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnTranslation1.anchor = GridBagConstraints.WEST;
        gbc_rdbtnTranslation1.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnTranslation1.gridx = 1;
        gbc_rdbtnTranslation1.gridy = 1;
        rdbtnTranslation1.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnTranslation1.addActionListener(highlightTranslationsListener);
        rdbtnTranslation1.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnTranslation1, gbc_rdbtnTranslation1);

        GridBagConstraints gbc_rdbtnTranslation2 = new GridBagConstraints();
        gbc_rdbtnTranslation2.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnTranslation2.anchor = GridBagConstraints.WEST;
        gbc_rdbtnTranslation2.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnTranslation2.gridx = 1;
        gbc_rdbtnTranslation2.gridy = 2;
        rdbtnTranslation2.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnTranslation2.addActionListener(highlightTranslationsListener);
        rdbtnTranslation2.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnTranslation2, gbc_rdbtnTranslation2);

        GridBagConstraints gbc_rdbtnTranslation3 = new GridBagConstraints();
        gbc_rdbtnTranslation3.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnTranslation3.anchor = GridBagConstraints.WEST;
        gbc_rdbtnTranslation3.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnTranslation3.gridx = 1;
        gbc_rdbtnTranslation3.gridy = 3;
        rdbtnTranslation3.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnTranslation3.addActionListener(highlightTranslationsListener);
        rdbtnTranslation3.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnTranslation3, gbc_rdbtnTranslation3);

        GridBagConstraints gbc_rdbtnTranslation4 = new GridBagConstraints();
        gbc_rdbtnTranslation4.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnTranslation4.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnTranslation4.anchor = GridBagConstraints.WEST;
        gbc_rdbtnTranslation4.gridx = 1;
        gbc_rdbtnTranslation4.gridy = 4;
        rdbtnTranslation4.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnTranslation4.addActionListener(highlightTranslationsListener);
        rdbtnTranslation4.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnTranslation4, gbc_rdbtnTranslation4);

        GridBagConstraints gbc_rdbtnTranslation5 = new GridBagConstraints();
        gbc_rdbtnTranslation5.fill = GridBagConstraints.HORIZONTAL;
        gbc_rdbtnTranslation5.anchor = GridBagConstraints.WEST;
        gbc_rdbtnTranslation5.insets = new Insets(0, 0, 10, 0);
        gbc_rdbtnTranslation5.gridx = 1;
        gbc_rdbtnTranslation5.gridy = 5;
        rdbtnTranslation5.setFont(new Font("Tahoma", Font.BOLD, 18));
        rdbtnTranslation5.addActionListener(highlightTranslationsListener);
        rdbtnTranslation5.addMouseListener(myMouseListener);
        bottomPanel.add(rdbtnTranslation5, gbc_rdbtnTranslation5);

        // FRAME

        setTitle("GERDict");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        getContentPane().setLayout(new BorderLayout(0, 0));
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);
        pack();
    }

    private void createRadioButtonGroups() {
        btngrpTranslationDirections = new ButtonGroup();
        btngrpTranslationDirections.add(rbmiDirectionFromGerman);
        btngrpTranslationDirections.add(rbmiDirectionToGerman);

        btngrpArticles = new ButtonGroup();
        btngrpArticles.add(rdbtnArticleDer);
        btngrpArticles.add(rdbtnArticleDie);
        btngrpArticles.add(rdbtnArticleDas);

        btngrpTranslations = new ButtonGroup();
        btngrpTranslations.add(rdbtnTranslation1);
        btngrpTranslations.add(rdbtnTranslation2);
        btngrpTranslations.add(rdbtnTranslation3);
        btngrpTranslations.add(rdbtnTranslation4);
        btngrpTranslations.add(rdbtnTranslation5);
    }

    public void setInputEnabled(boolean enabled) {
        tfWordToTranslate.setEnabled(enabled);
        btnLoadNewQuestion.setEnabled(enabled);
        rdbtnArticleDer.setEnabled(enabled);
        rdbtnArticleDie.setEnabled(enabled);
        rdbtnArticleDas.setEnabled(enabled);
        rdbtnTranslation1.setEnabled(enabled);
        rdbtnTranslation2.setEnabled(enabled);
        rdbtnTranslation3.setEnabled(enabled);
        rdbtnTranslation4.setEnabled(enabled);
        rdbtnTranslation5.setEnabled(enabled);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = (MainPresenter) presenter;
    }

    @Override
    public void displaySelf() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setVisible(true);
    }

    public boolean getIsTranslateFromGerman() {
        return Boolean.parseBoolean(btngrpTranslationDirections.getSelection().getActionCommand());
    }

    public String getSelectedArticle() {
        if (btngrpArticles.getSelection() != null) {
            return btngrpArticles.getSelection().getActionCommand();
        }
        return null;
    }

    public String getSelectedTranslation() {
        if (btngrpTranslations.getSelection() != null) {
            return btngrpTranslations.getSelection().getActionCommand();
        }
        return null;
    }

    private void miOpenDictionaryActionPerformed() {
        presenter.openDictionary();
    }

    private void btnLoadNewQuestionActionPerformed() {
        presenter.loadNewQuestion();
    }

    private void miShowStatsActionPerformed() {
        presenter.displayStats();
    }

    public void displayNewQuestion(String wordToTranslate, String correctGerArticle,
            List<String> possibleTranslations) {
        tfWordToTranslate.setText(wordToTranslate);

        btngrpArticles.clearSelection();
        for (AbstractButton abstractButton : Collections.list(btngrpArticles.getElements())) {
            JRadioButton rdbtnArticle = (JRadioButton) abstractButton;
            rdbtnArticle.setBackground(rdbtnColor);
            rdbtnArticle.setEnabled(correctGerArticle != null);
            rdbtnArticle.addActionListener(updateStatsListener);
        }

        btngrpTranslations.clearSelection();
        int i = 0;
        for (AbstractButton abstractButton : Collections.list(btngrpTranslations.getElements())) {
            JRadioButton rdbtnTranslation = (JRadioButton) abstractButton;
            rdbtnTranslation.setBackground(rdbtnColor);
            rdbtnTranslation.setText(possibleTranslations.get(i));
            rdbtnTranslation.setActionCommand(possibleTranslations.get(i));
            rdbtnTranslation.addActionListener(updateStatsListener);
            ++i;
        }
    }

    private class HighlightArticlesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            presenter.checkArticle();
        }
    }

    public void displayCorrectArticle(String correctArticle) {
        for (AbstractButton abstractButton : Collections.list(btngrpArticles.getElements())) {
            JRadioButton rdbtnAnswer = (JRadioButton) abstractButton;
            if (rdbtnAnswer.getActionCommand().equals(correctArticle)) {
                rdbtnAnswer.setBackground(Color.GREEN);
            } else {
                if (rdbtnAnswer.isSelected()) {
                    rdbtnAnswer.setBackground(Color.RED);
                } else {
                    rdbtnAnswer.setBackground(rdbtnColor);
                }
            }
        }
    }

    private class HighlightTranslationsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            presenter.checkTranslation();
        }
    }

    public void displayCorrectTranslation(String correctTranslation) {
        for (AbstractButton abstractButton : Collections.list(btngrpTranslations.getElements())) {
            JRadioButton rdbtnTranslation = (JRadioButton) abstractButton;
            if (rdbtnTranslation.getActionCommand().equals(correctTranslation)) {
                rdbtnTranslation.setBackground(Color.GREEN);
            } else {
                if (rdbtnTranslation.isSelected()) {
                    rdbtnTranslation.setBackground(Color.RED);
                } else {
                    rdbtnTranslation.setBackground(rdbtnColor);
                }
            }
        }
    }

    private class UpdateStatsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            presenter.updateStats();
        }
    }

    public void stopFiringStatsUpdates() {
        for (AbstractButton abstractButton : Collections.list(btngrpArticles.getElements())) {
            JRadioButton rdbtnArticle = (JRadioButton) abstractButton;
            rdbtnArticle.removeActionListener(updateStatsListener);
        }
        for (AbstractButton abstractButton : Collections.list(btngrpTranslations.getElements())) {
            JRadioButton rdbtnTranslation = (JRadioButton) abstractButton;
            rdbtnTranslation.removeActionListener(updateStatsListener);
        }
    }

    class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent evt) {
            // catch right mouse click
            if ((evt.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) != 0) {
                presenter.loadNewQuestion();
                btnLoadNewQuestion.requestFocusInWindow();
            }
        }
    }
}

package _03_Intro_To_Regular_Expressions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Use regex to check if a password meets the minimum criteria
 */

public class _02_PasswordChecker implements KeyListener {
    /*
     * 1. Check a minimum of 8 characters
     */
    static final String regexCriteria1 = ".{6,}";
    
    /*
     * 2. Check there is at least 1 number, i.e. a digit 0-9
     */
    static final String regexCriteria2 = "[0-9]";
    
    /*
     * 3. Check there is at least 1 capital letter
     */
    static final String regexCriteria3 = "[A-Z]";
    
    /*
     * 4. Check there is at least 1 special character, where special
     * characters are one of the following, @#$%^&-+=()
     */
    static final String regexCriteria4 = "[@#$%^&-+=()]";

    static final String INCORRECT = " X - ";
    static final String CORRECT = " + - ";
    static final String CRITERIA1 = "A minimum of 8 characters";
    static final String CRITERIA2 = "Must contain at least 1 number";
    static final String CRITERIA3 = "Must contain at least 1 capital letter";
    static final String CRITERIA4 = "Must contain at least 1 special character";
    static Font textFont = new Font( "Arial", Font.PLAIN, 18);
    JLabel criteriaMinimumLength;
    JLabel criteriaNumber;
    JLabel criteriaCapitalLetter;
    JLabel criteriaSpecialCharacter;
    JTextField passwordField;
    String password = "";
    String asterixPassword = "";

    public _02_PasswordChecker() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel instructions = new JLabel("Please create a password that meets the following criteria:");
        passwordField = new JTextField(20);
        criteriaMinimumLength = new JLabel(INCORRECT + CRITERIA1);
        criteriaNumber = new JLabel(INCORRECT + CRITERIA2);
        criteriaCapitalLetter = new JLabel(INCORRECT + CRITERIA3);
        criteriaSpecialCharacter = new JLabel(INCORRECT + CRITERIA4);

        criteriaMinimumLength.setForeground(Color.RED);
        criteriaNumber.setForeground(Color.RED);
        criteriaCapitalLetter.setForeground(Color.RED);
        criteriaSpecialCharacter.setForeground(Color.RED);
        
        instructions.setFont(textFont);
        passwordField.setFont(textFont);

        panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
        panel.setBorder(BorderFactory.createCompoundBorder( passwordField.getBorder(), 
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        passwordField.setBorder(BorderFactory.createCompoundBorder( passwordField.getBorder(), 
                BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        passwordField.addKeyListener(this);
        
        // Keep the order of add() 
        panel.add(instructions);
        panel.add(criteriaMinimumLength);
        panel.add(criteriaNumber);
        panel.add(criteriaCapitalLetter);
        panel.add(criteriaSpecialCharacter);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Password Checker");
        frame.pack();
    }
    
    public boolean checkPasswordCriteria() {
        boolean goodPassword = true;
        
        // Minimum length. Use matches() to check entire password string
        if( !regexCriteria1.isEmpty() &&
            Pattern.compile(regexCriteria1).matcher(this.password).matches() )
        {
            criteriaMinimumLength.setText(CORRECT + CRITERIA1);
            criteriaMinimumLength.setForeground(Color.GREEN);
        } else {
            criteriaMinimumLength.setText(INCORRECT + CRITERIA1);
            criteriaMinimumLength.setForeground(Color.RED);
            goodPassword = false;
        }
        
        // At least 1 digit. Use find() for substring within password string
        if( !regexCriteria2.isEmpty() &&
            Pattern.compile(regexCriteria2).matcher(this.password).find() )
        {
            criteriaNumber.setText(CORRECT + CRITERIA2);
            criteriaNumber.setForeground(Color.GREEN);
        } else {
            criteriaNumber.setText(INCORRECT + CRITERIA2);
            criteriaNumber.setForeground(Color.RED);
            goodPassword = false;
        }
        
        // At least 1 capital letter. Use find() for substring within password string
        if( !regexCriteria3.isEmpty() &&
            Pattern.compile(regexCriteria3).matcher(this.password).find() )
        {
            criteriaCapitalLetter.setText(CORRECT + CRITERIA3);
            criteriaCapitalLetter.setForeground(Color.GREEN);
        } else {
            criteriaCapitalLetter.setText(INCORRECT + CRITERIA3);
            criteriaCapitalLetter.setForeground(Color.RED);
            goodPassword = false;
        }
        
        // At least 1 special character. Use find() for substring within password string
        if( !regexCriteria4.isEmpty() &&
            Pattern.compile( regexCriteria4).matcher(this.password).find() )
        {
            criteriaSpecialCharacter.setText(CORRECT + CRITERIA4);
            criteriaSpecialCharacter.setForeground(Color.GREEN);
        } else {
            criteriaSpecialCharacter.setText(INCORRECT + CRITERIA4);
            criteriaSpecialCharacter.setForeground(Color.RED);
            goodPassword = false;
        }
        
        return goodPassword;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        int keyCode = (int)arg0.getKeyChar();
        
        if (keyCode == KeyEvent.VK_ENTER) {
            if( checkPasswordCriteria() ) {
                JOptionPane.showMessageDialog(null, "Successful password!");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Password doesn't meet password criteria.");
            }
        } else if( keyCode == KeyEvent.VK_BACK_SPACE ) {
            if( this.password.length() > 0 ) {
                this.password = this.password.substring(0, this.password.length() - 1);
            }
            
            if( this.asterixPassword.length() > 0 ) {
                this.asterixPassword = this.asterixPassword.substring(0, this.asterixPassword.length() - 1);
            }
            
            checkPasswordCriteria();
        } else if( keyCode == 127 ){
            // Delete key not allowed, only use backspace key for coding simplicity
            passwordField.setText( this.asterixPassword );
        } else {
            passwordField.setText( this.asterixPassword );
            this.password += (char)keyCode;
            this.asterixPassword += "*";
            checkPasswordCriteria();
        }
    }
}

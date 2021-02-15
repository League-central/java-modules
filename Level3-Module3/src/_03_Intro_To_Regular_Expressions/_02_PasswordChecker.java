package _03_Intro_To_Regular_Expressions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/*
 * Use regex to check if a password meets the minimum criteria
 */

public class _02_PasswordChecker implements KeyListener {
    /*
     * 1. Check a minimum of 8 characters
     */
    static final String regexCriteria1 = "";

    /*
     * 2. Check there is at least 1 number, i.e. a digit 0-9
     */
    static final String regexCriteria2 = "";

    /*
     * 3. Check there is at least 1 capital letter
     */
    static final String regexCriteria3 = "";

    /*
     * 4. Check there is at least 1 special character, where special
     * characters are one of the following, @#$%^&-+=()
     */
    static final String regexCriteria4 = "";
    
    /*
     * *BONUS* Add more password criteria. Some suggestions are:
     *  - check for no whitespace characters
     *  - check for lower case letters
     *  - check for maximum of 20 characters
     *  
     *  Below is a more rigorous regex password checker. Can you figure out
     *  what it's checking?
     *  \A(?=\w{6,10}\z)(?=[^a-z]*[a-z])(?=(?:[^A-Z]*[A-Z]){3})\D*\d.*\z
     *  
     *  
     *  http://www.rexegg.com/regex-lookarounds.html#password
     */
    
    static final String INCORRECT = " X ";
    static final String CORRECT = " + ";
    static final boolean USE_FIND = true;
    static final boolean USE_MATCH = false;
    static final String CRITERIA1 = "A minimum of 8 characters";
    static final String CRITERIA2 = "Must contain at least 1 number";
    static final String CRITERIA3 = "Must contain at least 1 capital letter";
    static final String CRITERIA4 = "Must contain at least 1 special character";
    static Font textFont = new Font( "Arial", Font.PLAIN, 18);
    JPasswordField passwordField;
    ArrayList<Criteria> criterias;

    public _02_PasswordChecker() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel instructions = new JLabel("Please create a password that meets the following criteria:");
        passwordField = new JPasswordField(20);
        instructions.setFont(textFont);
        passwordField.setFont(textFont);
        
        /*
         * Add new criteria below!
         */
        criterias = new ArrayList<Criteria>();
        criterias.add(new Criteria(CRITERIA1, regexCriteria1, USE_MATCH));
        criterias.add(new Criteria(CRITERIA2, regexCriteria2, USE_FIND));
        criterias.add(new Criteria(CRITERIA3, regexCriteria3, USE_FIND));
        criterias.add(new Criteria(CRITERIA4, regexCriteria4, USE_FIND));
        
        panel.setLayout( new BoxLayout(panel, BoxLayout.Y_AXIS) );
        panel.setBorder(BorderFactory.createCompoundBorder( passwordField.getBorder(), 
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        passwordField.setBorder(BorderFactory.createCompoundBorder( passwordField.getBorder(), 
                BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        passwordField.addKeyListener(this);
        
        // Keep the order of add() 
        panel.add(instructions);
        for( Criteria criteria : criterias ) {
            panel.add(criteria.label);
        }
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Password Checker");
        frame.pack();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String password = new String( passwordField.getPassword() ) + e.getKeyChar();
        
        for( Criteria criteria : criterias ) {
            criteria.match(password);
        }
    }
    
    class Criteria {
        String criteriaDescription;
        String regex;
        boolean useFindToValidate;
        JLabel label;
        
        Criteria(String description, String regex, boolean useFind) {
            this.criteriaDescription = description;
            this.regex = regex;
            this.useFindToValidate = useFind;
            
            this.label = new JLabel(INCORRECT + this.criteriaDescription);
            this.label.setForeground(Color.RED);
        }
        
        // Match inserts implicit starting and ending anchors to the regex
        public boolean match(String password) {
            return validate(password, this.useFindToValidate);
        }

        // Call match() instead
        private boolean validate(String password, boolean useFind) {
            boolean validPassword = true;
            boolean regexSuccess;
            
            // Match inserts implicit starting and ending anchors to the regex
            // Use find if that's not desired
            if(useFind) {
                regexSuccess = Pattern.compile(this.regex).matcher(password).find();
            } else {
                regexSuccess = Pattern.compile(this.regex).matcher(password).matches();
            }
            
            if( !this.regex.isEmpty() && regexSuccess ) {
                this.label.setText(CORRECT + this.criteriaDescription);
                this.label.setForeground(Color.GREEN);
            } else {
                this.label.setText(INCORRECT + this.criteriaDescription);
                this.label.setForeground(Color.RED);
                validPassword = false;
            }
            
            return validPassword;
        }
    }
}

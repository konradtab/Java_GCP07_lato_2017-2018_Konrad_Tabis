import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Dell on 05.03.2017.
 */
public class Statki extends JFrame implements ActionListener
{
    private JButton graj,wyjscie;

    public Statki()
    {
        setSize(800,600);
        setTitle("STATKI");
        setLayout(null);

        graj=new JButton("Graj");
        graj.setBounds(300,100,200,50);
        add(graj);
        graj.addActionListener(this);

        wyjscie=new JButton("Wyjście");
        wyjscie.setBounds(300,450,200,50);
        add(wyjscie);
        wyjscie.addActionListener(this);
    }
    public static void main(String[] args)
    {
        Statki okno = new Statki();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object zrodlo=e.getSource();
        if(zrodlo==graj)
        {
            //Listener dla buttona graj
            Menu okno=new Menu();
            okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            okno.setVisible(true);
        }
        else if(zrodlo==wyjscie)
        {
            dispose();
        }
    }
}

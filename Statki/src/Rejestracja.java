import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by Dell on 05.03.2017.
 */
public class Rejestracja extends JFrame implements ActionListener
{
    JTextField log,haslo;
    JLabel Llog, Lhaslo;
    JButton potwier,wyjscie;

    public Rejestracja()
    {
        setSize(800,600);
        setTitle("STATKI Rejestracja");
        setLayout(null);

        Llog=new JLabel("Login");
        Llog.setBounds(50,50,100,20);
        add(Llog);

        Lhaslo=new JLabel("Hasło:");
        Lhaslo.setBounds(50,75,100,20);
        add(Lhaslo);

        log=new JTextField("");
        log.setBounds(150,50,200,20);
        add(log);

        //ustawić ukrywanie wpisywanego tekstu dla hasła
        haslo=new JTextField("");
        haslo.setBounds(150,75,200,20);
        add(haslo);

        potwier=new JButton("Utwórz konto");
        potwier.setBounds(50,100,150,20);
        add(potwier);
        potwier.addActionListener(this);

        wyjscie=new JButton("Wyjście");
        wyjscie.setBounds(50,125,150,20);
        add(wyjscie);
        wyjscie.addActionListener(this);
    }
    public static void main(String[] args)
    {
        Rejestracja okno = new Rejestracja();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        Object zrodlo=e.getSource();
        if(zrodlo==potwier)
        {
            //Sprawdzenie czy login wolny
        }
        else if(zrodlo==wyjscie)
        {
            dispose();
        }
    }
}

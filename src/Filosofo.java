public class Filosofo extends  Thread{
    final static int TEMPOMAXIMO = 100;
    Mesa mesa;
    int filosofo;

    public Filosofo(String nome, Mesa mesadejantar, int fil)
    {
        super(nome);
        mesa = mesadejantar;
        filosofo = fil;
    }

    public void run ()
    {
        int tempo = 0;
        while (true)
        {
            tempo = (int) (Math.random() * TEMPOMAXIMO);
            pensar(tempo);
            mesa.pegarGarfos(filosofo);
            tempo = (int) (Math.random() * TEMPOMAXIMO);
            comer(tempo);
            mesa.returnGarfos(filosofo);
        }
    }

    public void pensar (int tempo)
    {
        try
        {
            sleep(tempo);
        }
        catch (InterruptedException e)
        {
            System.out.println("O Filófoso pensou em demasia");
        }
    }

    public void comer (int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("O Filósofo comeu em demasia");

        }
    }
    }
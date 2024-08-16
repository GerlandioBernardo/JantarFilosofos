public class Mesa {
    private final static int PENSANDO = 1;
    private final static  int COMENDO = 2;
    private final static int FOME =3;
    private final static  int NUMERODEFILOSOFOS = 5;
    private final static  int PRIMEIROFILOSOFO = 0;
    private  final  static  int ULTIMOFILOSOFO =NUMERODEFILOSOFOS - 1;
    boolean[] garfosDaMesa = new boolean[NUMERODEFILOSOFOS];
    int[] filosofos = new  int[NUMERODEFILOSOFOS];
    int[] tentativas = new int[NUMERODEFILOSOFOS];
    public  Mesa(){
        for( int i =0; i < 5; i++){
            garfosDaMesa[i] = true;
            filosofos[i] = PENSANDO;
            tentativas[i] = 0;
        }
    }
    public synchronized void pegarGarfos(int filosofo){
        filosofos[filosofo] = FOME;
        while (filosofos[aEsqueda(filosofo)] == COMENDO || filosofos[aDireita(filosofo)] == COMENDO){
            try
            {
                tentativas[filosofo]++;
                wait();
            }
            catch (InterruptedException e){ }

        }
        System.out.println("O Filósofo morreu devido a starvation");
        tentativas[filosofo] = 0;
        garfosDaMesa[garfoEsquerdo(filosofo)] = false;
        garfosDaMesa[garfoDireito(filosofo)] = false;
        filosofos[filosofo] = COMENDO;
        imprimirEstadosDosFilosofos();
        imprimirGarfosDaMesa();
        imprimirTentativasDosFilosofos();
    }
    public synchronized void returnGarfos(int filosofo){
        garfosDaMesa[garfoEsquerdo(filosofo)] = true;
        garfosDaMesa[garfoDireito(filosofo)] = true;
        if (filosofos[aEsqueda(filosofo)] == FOME || filosofos[aDireita(filosofo)] == FOME) {
            notifyAll();
        }
        filosofos[filosofo] = PENSANDO;
        imprimirEstadosDosFilosofos();
        imprimirGarfosDaMesa();
        imprimirTentativasDosFilosofos();
    }
    public int aDireita(int filosofo){
        int direito;
        if (filosofo == ULTIMOFILOSOFO){
            direito = PRIMEIROFILOSOFO;
        }
        else {
            direito = filosofo + 1;
        }
        return direito;

    }
    public  int aEsqueda(int filosofo){
        int esquerdo;
        if (filosofo == PRIMEIROFILOSOFO){
            esquerdo = ULTIMOFILOSOFO;
        }
        else {
            esquerdo = filosofo -1;
        }
        return esquerdo;

    }
    public int garfoEsquerdo (int filosofo)
    {
        int garfoEsquerdo = filosofo;
        return garfoEsquerdo;
    }

    public int garfoDireito (int filosofo)
    {
        int garfoDireito;
        if (filosofo == ULTIMOFILOSOFO)
        {
            garfoDireito = 0;
        }
        else
        {
            garfoDireito = filosofo + 1;
        }
        return garfoDireito;
    }
    public void imprimirEstadosDosFilosofos() {
        String texto = "*";
        System.out.print("Filósofos = [ ");
        for (int i = 0; i < NUMERODEFILOSOFOS; i++)
        {
            switch (filosofos[i])
            {
                case PENSANDO :
                    texto = "PENSANDO";
                    break;
                case FOME :
                    texto = "FOME";
                    break;
                case COMENDO :
                    texto = "COMENDO";
                    break;
            }
            System.out.print(texto + " ");
        }
        System.out.println("]");
    }
    public void imprimirGarfosDaMesa() {
        String garfo = "*";
        System.out.print("Garfos = [ ");
        for (int i = 0; i < NUMERODEFILOSOFOS; i++)
        {
            if (garfosDaMesa[i]) {
                garfo = "LIVRE";
            }
            else {
                garfo = "OCUPADO";
            }
            System.out.print(garfo + " ");
        }
        System.out.println("]");
    }
    public void imprimirTentativasDosFilosofos()
    {
        System.out.print("Tentou comer = [ ");
        for (int i = 0; i < NUMERODEFILOSOFOS; i++)
        {
            System.out.print(filosofos[i] + " ");
        }
        System.out.println("]");
    }

}

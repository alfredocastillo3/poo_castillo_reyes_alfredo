


public class CuadroMagico {

    private int n;
    private int[][] cuadro;

    public CuadroMagico() {
    }

    public CuadroMagico(int n) {
        this.n = n;
        this.cuadro = new int[n][n];
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++)
                this.cuadro[i][j]=0;
    }

    public boolean esCuadroMagico() {
        return sumanFila() && sumanColumna() && sumanDiagonal();
    }

    public int sumaTotal() {
        int sum = n*(n*n + 1)/2;
        return sum;
    }

    public boolean sumanFila() {
        boolean cumpleSuma = true;
        int sumaFila = 0;
        int constanteMagica = sumaTotal();
        int f = 0;
        while (f < n && cumpleSuma) {
            for (int c = 0; c < n; c++)
                sumaFila += cuadro[f][c];

            if (sumaFila != constanteMagica) {
                cumpleSuma = false;
            }
            f++;
            sumaFila = 0;
        }
        return cumpleSuma;
    }

    public boolean sumanColumna() {
        boolean cumpleSuma = true;
        int sumaColumna = 0;
        int constanteMagica = sumaTotal();
        int c = 0;
        while (c < n && cumpleSuma) {
            for (int f = 0; f < n; f++)
                sumaColumna += cuadro[f][c];

            if (sumaColumna != constanteMagica) {
                cumpleSuma = false;
            }
            c++;
            sumaColumna = 0;
        }
        return cumpleSuma;
    }

    public boolean sumanDiagonal() {
        boolean cumpleSuma = true;
        int constanteMagica = sumaTotal();
        int sumaDiag1 = 0;
        int f = 0, c = 0;
        while (f < n && c < n) {
            sumaDiag1 += cuadro[f][c];
            f++;
            c++;
        }
        if(sumaDiag1 != constanteMagica)
            cumpleSuma = false;
        else{
            int sumaDiag2 = 0;
            f = 0;
            c = n - 1;
            while (f < n && c >= 0) {
                sumaDiag2 += cuadro[f][c];
                f++;
                c--;
            }
            if (sumaDiag2 != constanteMagica)
                cumpleSuma = false;
        }
        return cumpleSuma;
    }

    // referencia URL: http://es.wikihow.com/resolver-un-cuadro-m%C3%A1gico

    public void pintarCuadroMagico(){
        if(n%2==1)
            setCuadro(cuadroMagicoImpar(n));
        else if(n%4==0)
            cuadroMagicoPar4n();
        else
            cuadroMagicoPar2n();
    }

    public int[][] cuadroMagicoImpar(int n) {
        int coUlt = 0, fiUlt = 0;

        int[][] cuadroN = new int[n][n];

        /* Ubica el número 1 en la casilla central en la fila superior*/
        int coSig = (n - 1) / 2;
        int fiSig = 0;
        int num = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //guardamos el numero en el cuadro
                cuadroN[fiSig][coSig] = num;

                //guardamos la ultima posicion donde guardamos algo
                fiUlt = fiSig;
                coUlt = coSig;

                /* Primer paso Mover una casilla hacia arriba y luego una casilla hacia la derecha*/
                fiSig = fiUlt - 1;
                coSig = coUlt + 1;

                /* Si un movimiento te lleva a una "casilla" por encima de la fila
                superior del cuadrado mágico, permanece en esa columna, pero ubica
                el número en la fila inferior de dicha columna*/
                if (fiSig < 0) {
                    fiSig=n-1;
                }

                /*Si el movimiento te lleva a una "casilla" fuera del límite derecho 
                del cuadrado mágico, permanece en la fila de dicha casilla, pero ubica 
                el número en la columna más alejada hacia la izquierda de esa fila.*/
                if(coSig>=n){
                    fiSig=fiUlt - 1;
                    coSig=0;
                }

                /*Si el movimiento te lleva a una casilla que ya está ocupada, regresa 
                a la última casilla que llenaste y ubica el número debajo.*/
                if(fiSig==-1 || cuadroN[fiSig][coSig]!=0){
                    fiSig=fiUlt+1;
                    coSig=coUlt;                    
                }
                num++;
            }
        }
        return cuadroN;
    }

     public void cuadroMagicoPar4n() {
         int k= n/4;
         int num=1;
         int lim=n-1-k;

         for (int f = 0; f < n; f++) {
             for (int c = 0; c < n; c++) {
                 if((c<k && f<k) || (c<k && f>lim) || (c>lim && f<k) || (c>lim && f>lim))
                     cuadro[f][c]=num;
                 else if((c>=k && f>=k) && (c>=k && f<=lim) && (c<=lim && f>=k) && (c<=lim && f<=lim))
                     cuadro[f][c]=num;
                 num++;
             }             
         }
         num=1;
         for (int f = n-1; f >= 0; f--) {
             for (int c = n-1; c >= 0; c--) {
                 if(cuadro[f][c]==0)
                    cuadro[f][c]=num;
                 num++;               
             }
         }
     }

     public void cuadroMagicoPar2n() {
         int k=n/2;        

         // Divide el cuadrado mágico en cuatro cuadrantes del mismo tamaño. 
         int[][] A= cuadroMagicoImpar(k); //cuadrante superior izq
         int[][] B= new int[k][k]; //cuadrante inferior derecho
         int[][] C= new int[k][k]; //cuadrante superior derecho
         int[][] D= new int[k][k]; //cuadrante inferior izquiero

         int num=k*k;
         for (int f = 0; f < k; f++) {
             for (int c = 0; c < k; c++) {
                 B[f][c]=A[f][c]+num;
                 C[f][c]=A[f][c]+num*2;
                 D[f][c]=A[f][c]+num*3;
             }             
         }

         //Resalta las áreas A y D e Intercambia las áreas resaltadas A y D
         int med=k/2;
         for (int f = 0; f < k; f++) {
             for (int c = 0; c <=med; c++) {
                 if((f<med && c<med) || (f>med && c<med) || (f==med && c>0)){
                     int aux= A[f][c];
                     A[f][c]=D[f][c];
                     D[f][c]=aux;
                 }
             }
         }

         //mover ultimas columnas URL: http://www.1728.org/magicsq3.htm
         for (int c =(k-1); c >(med+1); c--) {
             for (int f = 0; f < k; f++) {             
                 int aux= C[f][c];
                 C[f][c]=B[f][c];
                 B[f][c]=aux;                 
             }
         }

         //armar el cuadro
         for (int f = 0; f < n; f++) {
             for (int c = 0; c < n; c++) {
                 if(f<k && c<k)
                     cuadro[f][c]=A[f][c];
                 else if(f>=k && c>=k)
                     cuadro[f][c]=B[f-k][c-k];
                 else if(f<k && c>=k)
                     cuadro[f][c]=C[f][c-k];
                 else if(f>=k && c<k)
                     cuadro[f][c]=D[f-k][c];
             }             
         }
     }

    public String imprimirCuadro(){
        String imprime="";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                imprime+=cuadro[i][j] + "\t"; 
            }
            imprime+="\n";            
        }
        return imprime;
    }

    public int[][] getCuadro() {
        return cuadro;
    }

    public void setCuadro(int[][] cuadro) {
        this.cuadro = cuadro;
    }
}
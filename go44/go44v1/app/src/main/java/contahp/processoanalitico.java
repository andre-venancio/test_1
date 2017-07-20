package contahp;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Environment;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by vennax on 19/07/2017.
 */

public class processoanalitico extends ActionBarActivity {
    {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GO44/MAIN";  //diretório de arquivos MAIN *(talvez seja reaproveitado)
        String path2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GO44/RESPOSTAS";// *(talvez seja reaproveitado)

        //path = Pasta contendo todos os arquivos usados no programa            *Formação das pastas*
        File dir = new File(path);
        dir.mkdirs();
        //----------------------------------

        //path2 = Pasta contendo as respostas a serem usadas
        File respostas_dir = new File(path2);
        respostas_dir.mkdirs();
        //----------------------------------

        // Conta os arquivos na pasta MAIN                *Esse procedimento é usada para contar os arquivos e armazená-los em ordem
        File main_folder = new File(path + "/");            // A menos que eu use essa contagem, esse procedimento deve ser removido
        int cont_file = -1;                                   // Essa remoção deve ser feita devagar pois a partir da...
        File[] listOfMain = main_folder.listFiles();        // contagem de arquivos (cont_file) na pasta /MAIN o programa...
        for (int i = 0; i < listOfMain.length; i++) {               //  referencia a criação dos arquivos na pasta /RESPOSTAS mais a frente
            if (listOfMain[i] != null) {
                cont_file++;
            }
        }
        int cont_file_REAL = cont_file + 1;
        System.out.println("1- Numero de arquivos contados é: " + cont_file_REAL + "..");
        //----------------------------------


        //File - Cria Arquivo de acordo com o botão apertado pelo usuário no frontend
        int c = 0; //numero de critérios
        File inFile = new File(path + "/" + listOfMain[(cont_file)].getName());//*Alterar para armazenamento em variável
        System.out.println("2.1- Criando a instância de arquivo inFile");
        System.out.println("2.2- O valor de listOfMain[(cont_file)].getName() é: " + listOfMain[(cont_file)].getName() + " ..");
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(inFile);
            br = new BufferedReader(fr);
            c = Integer.valueOf(br.readLine()); //Diz o número de critérios pois seu valor é resgatado do arquivo criado em path*
        } catch (IOException e) {
            e.printStackTrace();
        }//Check For Error
        System.out.println("2.3- O número de critérios no input C é: " + c + " ..");
        //----------------------------------

        //AHP-------------------------------AHP
        /* 	c = numero de critérios escolhidos
	        valor = variável que diz quantos termos a diagonal superior a matriz vai ter, dependendo do numero de critérios
	        v_superior = valores da diagonal superior convertidos em um VETOR
	        v_inferior = valores da diagonal inferior convertidos em um VETOR
	        mat_s = matriz contendo os valores da diagonal superior
	        mat_i = matriz contendo os valores da diagonal inferior
	        MAT = Matriz principal contendo a junção da superior com a inferior */

        //USUARIO ESCOLHE O VALOR DO NUMERO DE CRITERIOS
        float[][] mat_s = new float[c][c];
        float[][] mat_i = new float[c][c];
        //CALCULA QUANTOS TERMOS (quantas comparacoes) TERAO A DIAGONAL SUPERIOR (que depende do numero de criterios)
        int valor = 0, valorAnterior = 0;
        for (int x = 1; x <= c; x++) {
            valorAnterior = valor;
            valor = valorAnterior + (x - 1);
        }
        System.out.println("3.1- valor: " + valor + " ..");
        System.out.println("3.2- valorAnterior: " + valorAnterior + " ..");
        //----------------------------------

        //PRIMEIRO: PREENCHE *VETOR* QUE ARMAZENA OS VALORES(escolhidos pelo usuario), DA DIAGONAL SUPERIOR
        //float[] v_superior = new float[valor];
        int[] v_superior_int = new int[valor];
        try {
            fr = new FileReader(inFile);
            br = new BufferedReader(fr);
            int x = 0;
            while (br.readLine() != null) {  //preenche vetor dos valores da Diagonal Superior
                while (x < valor) {
                    v_superior_int[x] = Integer.valueOf(br.readLine());
                    x++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Converte escala de valores de (0 até 16) para (1/9 até 9) da matrix superior. Com seus valores FLOAT
        float[] v_superior = new float[valor];
        for (int i = 0; i < v_superior.length; i++) {
            int valor_real = 0;
            float valor_corrigido = 0;
            valor_real = v_superior_int[i];
            switch (valor_real) { // tratamento de valores de 0 até 16
                case 0:
                    valor_corrigido = 0.111111f;
                    break; //equivale a 1/9
                case 1:
                    valor_corrigido = 0.125f;
                    break;    //equivale a 1/8
                case 2:
                    valor_corrigido = 0.142857f;
                    break; //equivale a 1/7
                case 3:
                    valor_corrigido = 0.166667f;
                    break; //equivale a 1/6
                case 4:
                    valor_corrigido = 0.2f;
                    break;      //equivale a 1/5
                case 5:
                    valor_corrigido = 0.25f;
                    break;     //equivale a 1/4
                case 6:
                    valor_corrigido = 0.333333f;
                    break; //equivale a 1/3
                case 7:
                    valor_corrigido = 0.5f;
                    break;      //equivale a 1/2
                case 8:
                    valor_corrigido = 1f;
                    break;        //equivale a 1
                case 9:
                    valor_corrigido = 2f;
                    break;        //equivale a 2
                case 10:
                    valor_corrigido = 3f;
                    break;       //equivale a 3
                case 11:
                    valor_corrigido = 4f;
                    break;       //equivale a 4
                case 12:
                    valor_corrigido = 5f;
                    break;       //equivale a 5
                case 13:
                    valor_corrigido = 6f;
                    break;       //equivale a 6
                case 14:
                    valor_corrigido = 7f;
                    break;       //equivale a 7
                case 15:
                    valor_corrigido = 8f;
                    break;       //equivale a 8
                case 16:
                    valor_corrigido = 9f;
                    break;       //equivale a 9
            }
            v_superior[i] = valor_corrigido;//Integer.valueOf(br.readLine()) x++;
        }
        //SEGUNDO: PREENCHE A MATRIZ SUPERIOR COM OS VALORES DO VETOR_DE_ARMAZENAMENTO_DA_MATRIZ_SUPERIOR e os valores da DIAGONAL_PRINCIPAL=1
        float[][] MAT = new float[c][c];//matriz principal (que rebera os vetores para preencher a diagonal superior e inferior)
        int cont = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {    //option=1; se estiver na Diagonal Principal
                    mat_s[i][j] = 1;
                    MAT[i][j] = mat_s[i][j];
                } else if (i < j) {    //option=2; se estiver na Diagonal Superior
                    mat_s[i][j] = v_superior[cont];
                    cont++;
                    MAT[i][j] = mat_s[i][j];
                }
            }//termina j
        }//termina i
        //----------------------------------------------


        //PRIMEIRO: PREENCHE VETOR QUE ARMAZENA OS VALORES(JA escolhidos pelo usuario), NA DIAGONAL INFERIOR
        float[] v_inferior = new float[valor];
        for (int x = 0; x < valor; x++) {//Preenche vetor dos valores Diagonal Inferior (que são o inverso dos valores da diagonal superior)
            v_inferior[x] = 1.0f / v_superior[x];
        }
        //SEGUNDO: PREENCHE A MATRIZ INFERIOR COM OS VALORES DO VETOR_DE_ARMAZENAMENTO_DA_MATRIZ_INFERIOR
        int cont2 = 0;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < c; i++) {
                if (i > j) {    //option=3; se estiver na Diagonal Inferior
                    mat_i[i][j] = v_inferior[cont2];
                    cont2++;
                    MAT[i][j] = mat_i[i][j];
                }
            }//termina j
        }//termina i
        //----------------------------------------------

        //CRIA A MATRIZ FINAL PREENCHENDO ELA COM OS VETORES DA DIAGONAL SUPERIOR E INFERIOR
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j) {    //se estiver na Diagonal Principal, a matriz recebe os valores (1)
                    MAT[i][j] = mat_s[i][j];//option=1;
                } else if (i < j) {    //se estiver na parte Superior, a matriz recebe o vetor de preenchimento para diagonal superior
                    MAT[i][j] = mat_s[i][j];//option=2;
                } else if (i > j) {    //se estiver na parte Inferior, a matriz recebe o vetor de preenchimento para diagonal inferior
                    MAT[i][j] = mat_i[i][j];//option=3;
                }
                System.out.println("4- Matriz célula[" + i + "][" + j + "]: " + MAT[i][j]);
            }
        }
        //----------------------------------------------

        // AUTO-VETORES
        float[] auto_vetor = new float[c];
        float[] perc_vetor = new float[c];
        float multiplicado = 0, aux = 0, total_auto_vetor = 0, total_perc_vetor = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                aux = MAT[i][j];
                if (j > 0) {
                    multiplicado = (float) multiplicado * aux;
                } else multiplicado = aux;
            }
            auto_vetor[i] = (float) Math.pow(multiplicado, (1.0f / c));
            total_auto_vetor = total_auto_vetor + auto_vetor[i];
        }
        //----------------------------------------------

        // VETORES NORMALIZADOS			*RESPOSTA 1*
        //***String porcentagem = new String((cont_file+1) + "porcentagem.txt"); // ANTIGO
        String porcentagem = new String("porcentagem.txt");
        File outFile1 = new File(path2 + "/" + porcentagem); //File file = new File (path + "/savedFile.txt");
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(outFile1);
            bw = new BufferedWriter(fw);
            for (int i = 0; i < c; i++) {
                perc_vetor[i] = (float) (auto_vetor[i] / total_auto_vetor) * 100f;
                //Resultado das comparações----------
                bw.write(perc_vetor[i] + "\n");
                //-----------------------------------
                total_perc_vetor = total_perc_vetor + perc_vetor[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } //Check For Error
        finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } //Check For Error
        }
        //----------------------------------------------

        // AUTO-VALORES
        float[] somado = new float[c];
        float[] auto_valor = new float[c];
        float total_auto_valor = 0;
        for (int j = 0; j < c; j++) {
            somado[j] = 0;
            for (int i = 0; i < c; i++) {
                somado[j] = somado[j] + MAT[i][j];
            }
            auto_valor[j] = somado[j];
            total_auto_valor = total_auto_valor + auto_valor[j];
        }
        //----------------------------------------------

        // Multiplicação de matrizes Auto Vetores x Valores
        float lambda = 0;
        for (int x2 = 0; x2 < c; x2++) {
            lambda = lambda + (perc_vetor[x2] * auto_valor[x2]);
        }
        lambda = lambda / 100;
        //----------------------------------------------

        // Indice de consistencia
        float IC, RC;
        float IR = 0;
        IC = (lambda - c) / (c - 1);
        //----------------------------------------------

        // Razão de consistencia
        switch (c) {
            case 1:
                IR = 0f;
                break;
            case 2:
                IR = 0f;
                break;
            case 3:
                IR = 0.58f;
                break;
            case 4:
                IR = 0.9f;
                break;
            case 5:
                IR = 1.12f;
                break;
            case 6:
                IR = 1.24f;
                break;
            case 7:
                IR = 1.32f;
                break;
            case 8:
                IR = 1.41f;
                break;
            case 9:
                IR = 1.45f;
                break;
            case 10:
                IR = 1.49f;
                break;
        }
        //----------------------------------------------

        // Julgamento da Razão de consistencia (ou Inconsistência)	*RESPOSTA 2*
        //***String consistencia = new String((cont_file+1) + "consistencia.txt"); // ANTIGO
        String consistencia = new String("consistencia.txt");
        File outFile2 = new File(path2 + "/" + consistencia); //File file = new File (path + "/savedFile.txt");
        BufferedWriter bw2 = null;
        FileWriter fw2 = null;
        try {
            fw2 = new FileWriter(outFile2); //2* Arquivo que armazena a resposta de consistencia*
            bw2 = new BufferedWriter(fw2);
            RC = (IC / IR);
            if (RC < 0.1)
                bw2.write("Os julgamentos são consistentes! Valor de Consistencia: " + RC);
            else bw2.write("Os julgamentos são inconsistentes! Valor de Inconsistencia: " + RC);
        } catch (IOException e) {
            e.printStackTrace();
        } //Check For Error
        finally {
            try {
                if (bw2 != null) bw2.close();
                if (fw2 != null) fw2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } //Check For Error
        }

    }
}